package com.cossia.cardgame.deck;

import com.cossia.cardgame.player.Player;
import com.cossia.cardgame.cards.*;
import com.cossia.cardgame.utils.RandomGenerator;
import com.cossia.cardgame.utils.ScannerSingleton;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Deck {
    private final RandomGenerator randomGenerator = RandomGenerator.getInstance();
    private final Scanner scanner = ScannerSingleton.getInstance();
    private List<Card> cards;
    private final Set<String> uniqueNames = new HashSet<>();
    private final Set<String> uniqueAliases = new HashSet<>();

    private static final int MAX_TOTAL_POINTS = 45;
    private static final int MAX_POINTS_PER_ATTRIBUTE = 10;

    public Deck() {}

    public void generateRandomDeck(Player player) {
        this.cards = new ArrayList<>();
        int totalCards = 0;

        int maxNumberOfCardsInDeck = 15;
        while (totalCards < maxNumberOfCardsInDeck) {
            Race randomRace = getRandomRace();
            int cardsForRace = cardsForRace(randomRace);

            //si las cartas de la raza llegan a 3 corta la iteración.
            int maxCardsPerRace = 3;
            if (cardsForRace == maxCardsPerRace) {
                continue;
            }

            Card card = createRandomCard(randomRace);
            if (card != null) {
                cards.add(card);
                totalCards++;
            }
        }
        System.out.println("Se ha generado el mazo de " + player.getName());
    }

    private Race getRandomRace() {
        Race[] races = Race.values();
        return races[randomGenerator.getRandom(0, races.length - 1)];
    }

    private int cardsForRace(Race race) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRace() == race) {
                count++;
            }
        }
        return count;
    }

    private Card createRandomCard(Race race) {
        switch (race) {
            case DWARF:
                Dwarf dwarf = new Dwarf();
                dwarf.setRace(Race.DWARF);
                String dwarfName = generateUniqueName(Race.DWARF);
                String dwarfAlias = generateUniqueAlias(Race.DWARF);
                dwarf.setName(dwarfName);
                dwarf.setAlias(dwarfAlias);
                dwarf.initializeSpecificAttributes();
                return dwarf;
            case ELF:
                Elf elf = new Elf();
                elf.setRace(Race.ELF);
                String elfName = generateUniqueName(Race.ELF);
                String elfAlias = generateUniqueAlias(Race.ELF);
                elf.setName(elfName);
                elf.setAlias(elfAlias);
                elf.initializeSpecificAttributes();
                return elf;
            case HUMAN:
                Human human = new Human();
                human.setRace(Race.HUMAN);
                String humanName = generateUniqueName(Race.HUMAN);
                String humanAlias = generateUniqueAlias(Race.HUMAN);
                human.setName(humanName);
                human.setAlias(humanAlias);
                human.initializeSpecificAttributes();
                return human;
            case OGRE:
                Ogre ogre = new Ogre();
                ogre.setRace(Race.OGRE);
                String ogreName = generateUniqueName(Race.OGRE);
                String ogreAlias = generateUniqueAlias(Race.OGRE);
                ogre.setName(ogreName);
                ogre.setAlias(ogreAlias);
                ogre.initializeSpecificAttributes();
                return ogre;
            case WITCHER:
                Witcher witcher = new Witcher();
                witcher.setRace(Race.WITCHER);
                String witcherName = generateUniqueName(Race.WITCHER);
                String witcherAlias = generateUniqueAlias(Race.WITCHER);
                witcher.setName(witcherName);
                witcher.setAlias(witcherAlias);
                witcher.initializeSpecificAttributes();
                return witcher;
            default:
                return null;
        }
    }

    private String generateUniqueName(Race race) {
        String[] arrayNames;
        switch (race) {
            case DWARF:
                arrayNames = Dwarf.getArrayNames();
                break;
            case ELF:
                arrayNames = Elf.getArrayNames();
                break;
            case HUMAN:
                arrayNames = Human.getArrayNames();
                break;
            case OGRE:
                arrayNames = Ogre.getArrayNames();
                break;
            case WITCHER:
                arrayNames = Witcher.getArrayNames();
                break;
            default:
                return null;
        }

        String randomName;
        do {
            randomName = arrayNames[randomGenerator.getRandom(0, arrayNames.length - 1)];
        } while (uniqueNames.contains(randomName));

        uniqueNames.add(randomName);
        return randomName;
    }

    private String generateUniqueAlias(Race race) {
        String[] arrayAliases;
        switch (race) {
            case DWARF:
                arrayAliases = Dwarf.getArrayAliases();
                break;
            case ELF:
                arrayAliases = Elf.getArrayAliases();
                break;
            case HUMAN:
                arrayAliases = Human.getArrayAliases();
                break;
            case OGRE:
                arrayAliases = Ogre.getArrayAliases();
                break;
            case WITCHER:
                arrayAliases = Witcher.getArrayAliases();
                break;
            default:
                return null;
        }

        String randomAlias;
        do {
            randomAlias = arrayAliases[randomGenerator.getRandom(0, arrayAliases.length - 1)];
        } while (uniqueAliases.contains(randomAlias));

        uniqueAliases.add(randomAlias);
        return randomAlias;
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card removeCard() {
        if (!cards.isEmpty()) {
            return cards.removeFirst();
        }
        return null;
    }

    public boolean deckIsEmpty() {
        return cards.isEmpty();
    }

    public void displayCards(Player player) {
        int counter = 0;

        System.out.println("---");
        System.out.println("Cartas en el mazo de " + player.getName());

        for (Card card : cards) {
            System.out.println(card.toString());
            counter++;
        }

        System.out.println("---");
        System.out.println("La cantidad de cartas en el mazo es: " + counter);
    }

    public void generateCustomDeck(Player player) {
        this.cards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("\nCreando carta " + (i + 1) + " de " + player.getName());
            Card card = createCustomCard(player);
            cards.add(card);
        }
        System.out.println("Mazo personalizado creado con éxito!");
    }

    private Card createCustomCard(Player player) {
        Card card;

        System.out.println(player.getName() + " ¿Deseas personalizar esta carta? (si/no)");
        String answer = scanner.nextLine();

        while (!answer.equalsIgnoreCase("si") && !answer.equalsIgnoreCase("no")) {
            System.out.println("Por favor, responde 'si' o 'no'.");
            answer = scanner.nextLine();
        }

        if (answer.equalsIgnoreCase("si")) {
            card = selectCardType();
            boolean validName = false;
            while (!validName) {
                try {
                    System.out.println("Cual es el nombre del guerrero?");
                    String cardName = validateInput(scanner.nextLine().trim(), 15); // Limitar a 15 caracteres
                    card.setName(cardName);

                    System.out.println("Cual es su nombre de guerra?");
                    String cardAlias = validateInput(scanner.nextLine().trim(), 15); // Limitar a 15 caracteres
                    card.setAlias(cardAlias);
                    validName = true; // Si llega aquí sin lanzar excepción, el nombre y el alias son válidos
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            LocalDate dateOfBirth = getValidatedDateOfBirth(card);
            int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
            card.setBirthDate(dateOfBirth);
            card.setAge(age);
            System.out.println("La edad de " + card.getName() + " " + card.getAlias() + " es: " + card.getAge());

            assignAttributePoints(card);

        } else {
            card = generateRandomCard();
            System.out.println("Se ha generado una carta aleatoria, el Guerrero es: " + card.getName() + " " + card.getAlias());
        }

        return card;
    }

    private String validateInput(String input, int maxLength) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (input.length() > maxLength) {
            throw new IllegalArgumentException("El nombre no puede exceder " + maxLength + " caracteres.");
        }
        return input;
    }

    private LocalDate getValidatedDateOfBirth(Card card) {
        LocalDate minBirthDate = LocalDate.now().minusYears(card.getLifeExpectancy());
        System.out.println("\nCual es la fecha de nacimiento? Ingresar con el siguiente formato AAAA-MM-DD");
        System.out.println("Se ha acordado que los oponentes no deben tener menos de 15 años!");
        System.out.println("Debes tener en cuenta que la fecha no puede ser menor a: " + minBirthDate);

        LocalDate dateOfBirth;
        while (true) {
            try {
                String dateOfBirthString = scanner.nextLine();
                dateOfBirth = LocalDate.parse(dateOfBirthString);
                if (dateOfBirth.isBefore(minBirthDate)) {
                    throw new IllegalArgumentException("La fecha de nacimiento no puede ser anterior a " + minBirthDate);
                }
                int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
                if (age <= 15) {
                    throw new IllegalArgumentException("La edad no puede ser menor a 15 años.");
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Ingrese la fecha en formato AAAA-MM-DD:");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return dateOfBirth;
    }

    private void assignAttributePoints(Card card) {
        int remainingPoints = MAX_TOTAL_POINTS;

        while (remainingPoints > 0) {
            System.out.println("\nPuntos restantes para asignar: " + remainingPoints);
            remainingPoints = setAttributePoints(card, "ataque", remainingPoints);

            if (remainingPoints <= 0) {
                break;
            }

            System.out.println("\nPuntos restantes para asignar: " + remainingPoints);
            remainingPoints = setAttributePoints(card, "velocidad", remainingPoints);

            if (remainingPoints <= 0) {
                break;
            }

            System.out.println("\nPuntos restantes para asignar: " + remainingPoints);
            remainingPoints = setAttributePoints(card, "destreza", remainingPoints);

            if (remainingPoints <= 0) {
                break;
            }

            System.out.println("\nPuntos restantes para asignar: " + remainingPoints);
            remainingPoints = setAttributePoints(card, "armadura", remainingPoints);
        }
    }

    private Card selectCardType() {
        try {
            System.out.println("Selecciona el tipo de carta:");
            System.out.println("1. Enano\n2. Elfo\n3. Humano\n4. Ogro\n5. Hechicero");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Elegiste Enano (Dwarf)");
                    return new Dwarf();
                case 2:
                    System.out.println("Elegiste Elfo (Elf)");
                    return new Elf();
                case 3:
                    System.out.println("Elegiste Humano (Human)");
                    return new Human();
                case 4:
                    System.out.println("Elegiste Ogro (Ogre)");
                    return new Ogre();
                case 5:
                    System.out.println("Elegiste Hechicero (Witcher)");
                    return new Witcher();
                default:
                    System.out.println("Opción inválida. Se asignará el tipo por defecto: Humano.");
                    return new Human();
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.nextLine();
            return selectCardType();
        }
    }

    private int setAttributePoints(Card card, String attributeName, int remainingPoints) {

        int maxPoints;

        switch (attributeName) {
            case "ataque":
                maxPoints = card.getMaxStrength() - card.getStrength();
                break;
            case "velocidad":
                maxPoints = card.getMaxSpeed() - card.getSpeed();
                break;
            case "destreza":
                maxPoints = card.getMaxDexterity() - card.getDexterity();
                break;
            case "armadura":
                maxPoints = card.getMaxArmor() - card.getArmor();
                break;
            default:
                System.out.println("Asignación inválida, se asignan los puntos por defecto");
                maxPoints = MAX_POINTS_PER_ATTRIBUTE;
                break;
        }

        if (maxPoints <= 0) {
            System.out.println("No quedan puntos restantes para asignar a " + attributeName + ".");
            return remainingPoints;
        }

        System.out.println("Ingresa la cantidad de puntos para " + attributeName + " (máximo " + maxPoints +
                ", puntos restantes: " + remainingPoints + "):");

        int points;
        while (true) {
            try {
                points = scanner.nextInt();
                scanner.nextLine();
                if (points < 0 || points > maxPoints || points > remainingPoints) {
                    throw new IllegalArgumentException("Puntos inválidos para " + attributeName +
                            ". Deben estar entre 0 y " + maxPoints +
                            " y no pueden exceder los puntos restantes (" + remainingPoints + "). Intenta nuevamente.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingresa un número válido. Intenta nuevamente.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        switch (attributeName) {
            case "ataque":
                card.setStrength(card.getStrength() + points);
                break;
            case "velocidad":
                card.setSpeed(card.getSpeed() + points);
                break;
            case "destreza":
                card.setDexterity(card.getDexterity() + points);
                break;
            case "armadura":
                card.setArmor(card.getArmor() + points);
                break;
            default:
                break;
        }

        return remainingPoints - points;
    }

    public Card generateRandomCard() {
        Race randomRace = getRandomRace();
        return createRandomCard(randomRace);
    }
}