package com.cossia.cardgame.player;

import com.cossia.cardgame.deck.Deck;
import com.cossia.cardgame.utils.ScannerSingleton;

import java.util.Scanner;

public class PlayerSetup {
    private final Scanner scanner = ScannerSingleton.getInstance();
    private final Player player1;
    private final Player player2;
    private final Deck deck1;
    private final Deck deck2;

    public PlayerSetup(Player player1, Player player2, Deck deck1, Deck deck2) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck1 = deck1;
        this.deck2 = deck2;
    }

    public void createPlayers() {
        final int MAX_LENGTH = 15;
        final int NUM_PLAYERS = 2;

        try {
            System.out.println("Comencemos con la creación de los Jugadores.");

            for (int i = 1; i <= NUM_PLAYERS; i++) {
                boolean validName = false;
                String playerName = null;
                while (!validName) {
                    try {
                        System.out.println("Jugador " + i);
                        System.out.println("Cuál es tu nombre? (máximo " + MAX_LENGTH + " caracteres)");
                        playerName = validateName(scanner.nextLine().trim(), MAX_LENGTH);
                        validName = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Vuelve a intentar...");
                    }
                }
                if (i == 1) {
                    player1.setName(playerName);
                } else {
                    player2.setName(playerName);
                }
                System.out.println("Jugador " + i + " creado correctamente...");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private String validateName(String name, int maxLength) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (name.length() > maxLength) {
            throw new IllegalArgumentException("El nombre no puede exceder " + maxLength + " caracteres.");
        }
        return name;
    }

    public void setRandomDecks() {
        deck1.generateRandomDeck(player1);
        deck1.shuffleDeck();
        deck1.displayCards(player1);
        player1.setDeck(deck1);

        deck2.generateRandomDeck(player2);
        deck2.shuffleDeck();
        deck2.displayCards(player2);
        player2.setDeck(deck2);
    }

    public void setCustomDecks() {
        deck1.generateCustomDeck(player1);
        player1.setDeck(deck1);

        deck2.generateCustomDeck(player2);
        player2.setDeck(deck2);
    }
}