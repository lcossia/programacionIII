package com.cossia.cardgame.battle;

import com.cossia.cardgame.cards.Card;
import com.cossia.cardgame.player.Player;

import static com.cossia.cardgame.utils.Logger.log;

public class Battle {
    private final Player player1;
    private final Player player2;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startFastGame() {

        player1.aleatoryCardSelection();
        player2.aleatoryCardSelection();

        boolean firstRound = true;

        while (true) {
            player1.showHand();
            player2.showHand();

            // Seleccionar una carta de cada jugador
            Card cardPlayer1 = player1.selectCard();
            Card cardPlayer2 = player2.selectCard();

            if (cardPlayer1 == null || cardPlayer2 == null) {
                break;
            }

            System.out.println(player1.getName() + " seleccionó: " + cardPlayer1.getName() + " " + cardPlayer1.getAlias());
            System.out.println(player2.getName() + " seleccionó: " + cardPlayer2.getName() + " " + cardPlayer2.getAlias());
            log(player1.getName() + " seleccionó: " + cardPlayer1.getName() + " " + cardPlayer1.getAlias());
            log(player2.getName() + " seleccionó: " + cardPlayer2.getName() + " " + cardPlayer2.getAlias());

            for (int round = 1; round <= 7; round++) {
                System.out.println();
                System.out.println("--- Ronda " + round + " ---");
                log("--- Ronda " + round + " ---");

                boolean firstAttack;

                if (firstRound) {
                    firstAttack = Math.random() < 0.5;
                    firstRound = false; // Después de la primera ronda, cambiar la bandera a false
                } else {
                    // En las siguientes rondas, determinar quién comienza basándose en la carta que recibió más daño
                    firstAttack = (cardPlayer1.getHealth() < cardPlayer2.getHealth());
                }

                if (firstAttack) {
                    performAttack(cardPlayer1, cardPlayer2);
                    if (cardPlayer2.isDefeated()) {
                        handleDefeatedCard(player1, player2, cardPlayer1, cardPlayer2);
                        break;
                    }

                    performAttack(cardPlayer2, cardPlayer1);
                    if (cardPlayer1.isDefeated()) {
                        handleDefeatedCard(player2, player1, cardPlayer2, cardPlayer1);
                        break;
                    }
                } else {
                    performAttack(cardPlayer2, cardPlayer1);
                    if (cardPlayer1.isDefeated()) {
                        handleDefeatedCard(player2, player1, cardPlayer2, cardPlayer1);
                        break;
                    }

                    performAttack(cardPlayer1, cardPlayer2);
                    if (cardPlayer2.isDefeated()) {
                        handleDefeatedCard(player1, player2, cardPlayer1, cardPlayer2);
                        break;
                    }
                }

                if (round == 7) {
                    player1.setPlayerHand(cardPlayer1);
                    player2.setPlayerHand(cardPlayer2);
                }
            }

            // Repartir nuevas cartas si uno de los jugadores se queda sin cartas en la mano y se asigna score +1
            if (player1.noMoreCards()) {
                System.out.println(player1.getName() + " se ha quedado sin ejército.");
                log(player1.getName() + " se ha quedado sin ejército.");
                player2.incrementScore();
                System.out.println(player2.getName() + " es el ganador de la batalla, gana 1 punto!");
                log(player2.getName() + " es el ganador de la batalla, gana 1 punto!");
                player1.aleatoryCardSelection();
            } else if (player2.noMoreCards()) {
                System.out.println(player2.getName() + " se ha quedado sin ejército.");
                log(player2.getName() + " se ha quedado sin ejército.");
                player1.incrementScore();
                System.out.println(player1.getName() + " es el ganador de la batalla, gana 1 punto!");
                log(player1.getName() + " es el ganador de la batalla, gana 1 punto!");
                player2.aleatoryCardSelection();
            }
        }

        handleFastWinner();
    }

    public void customGame() {
        player1.aleatoryCardSelection();
        player2.aleatoryCardSelection();

        boolean firstRound = true;

        while (true) {
            player1.showHand();
            player2.showHand();

            // Seleccionar una carta de cada jugador
            Card cardPlayer1 = player1.selectCard();
            Card cardPlayer2 = player2.selectCard();

            if (cardPlayer1 == null || cardPlayer2 == null) {
                break;
            }

            System.out.println(player1.getName() + " seleccionó: " + cardPlayer1.getName() + " " + cardPlayer1.getAlias());
            System.out.println(player2.getName() + " seleccionó: " + cardPlayer2.getName() + " " + cardPlayer2.getAlias());

            log(player1.getName() + " seleccionó: " + cardPlayer1.getName() + " " + cardPlayer1.getAlias());
            log(player2.getName() + " seleccionó: " + cardPlayer2.getName() + " " + cardPlayer2.getAlias());

            for (int round = 1; round <= 7; round++) {
                System.out.println();
                System.out.println("\n----- Ronda " + round + " -----");
                log("\n----- Ronda " + round + " -----");

                boolean firstAttack;

                if (firstRound) {
                    firstAttack = Math.random() < 0.5;
                    firstRound = false; // Después de la primera ronda, cambiar la bandera a false
                } else {
                    // En las siguientes rondas, determinar quién comienza basándose en la carta que tiene menos salud
                    // para balancear el combate.
                    firstAttack = (cardPlayer1.getHealth() < cardPlayer2.getHealth());
                }

                if (firstAttack) {
                    performAttack(cardPlayer1, cardPlayer2);
                    if (cardPlayer2.isDefeated()) {
                        handleDefeatedCard(player1, player2, cardPlayer1, cardPlayer2);
                        break;
                    }

                    performAttack(cardPlayer2, cardPlayer1);
                    if (cardPlayer1.isDefeated()) {
                        handleDefeatedCard(player2, player1, cardPlayer2, cardPlayer1);
                        break;
                    }
                } else {
                    performAttack(cardPlayer2, cardPlayer1);
                    if (cardPlayer1.isDefeated()) {
                        handleDefeatedCard(player2, player1, cardPlayer2, cardPlayer1);
                        break;
                    }

                    performAttack(cardPlayer1, cardPlayer2);
                    if (cardPlayer2.isDefeated()) {
                        handleDefeatedCard(player1, player2, cardPlayer1, cardPlayer2);
                        break;
                    }
                }

                if (round == 7) {
                    player1.setPlayerHand(cardPlayer1);
                    player2.setPlayerHand(cardPlayer2);
                }
            }
        }

        handleCustomWinner();
    }



    private void performAttack(Card attacker, Card defender) {
        System.out.println("Es el turno de " + attacker.getName() + " " + attacker.getAlias());
        log("Es el turno de " + attacker.getName() + " " + attacker.getAlias());
        int damage = attacker.attack(defender);
        System.out.println(attacker.getName() + " " + attacker.getAlias() + " ataca a "
                + defender.getName() + " " + defender.getAlias() + " y causa "
                + damage + " puntos de daño.");
        log(attacker.getName() + " " + attacker.getAlias() + " ataca a "
                + defender.getName() + " " + defender.getAlias() + " y causa "
                + damage + " puntos de daño.");
        System.out.println("La vida de " + defender.getName() + " " + defender.getAlias()
                + " es de " + defender.getHealth() + " puntos.");
        log("La vida de " + defender.getName() + " " + defender.getAlias()
                + " es de " + defender.getHealth() + " puntos.");
    }

    private void handleDefeatedCard(Player winner, Player loser, Card winningCard, Card losingCard) {
        System.out.println(losingCard.getName() + " " + losingCard.getAlias() + " ha sido derrotado!");
        log(losingCard.getName() + " " + losingCard.getAlias() + " ha sido derrotado!");
        loser.setDeadCards(losingCard);
        winningCard.randomPowerUp(winner);
        winner.setPlayerHand(winningCard);
    }

    private void handleFastWinner() {
        System.out.println("Fin del Juego");
        log("Fin del Juego");
        System.out.println("Puntaje de " + player1.getName() + ": " + player1.getScore());
        log("Puntaje de " + player1.getName() + ": " + player1.getScore());
        System.out.println("Puntaje de " + player2.getName() + ": " + player2.getScore());
        log("Puntaje de " + player2.getName() + ": " + player2.getScore());

        if ((player1.getScore() > player2.getScore())) {
            System.out.println(player1.getName() + " ha conquistado el trono de hierro!");
            log(player1.getName() + " ha conquistado el trono de hierro!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println(player2.getName() + " ha conquistado el trono de hierro!");
            log(player2.getName() + " ha conquistado el trono de hierro!");
        } else {
            System.out.println("Ha sido un empate!");
            log("Ha sido un empate!");
        }
    }

    private void handleCustomWinner() {
        System.out.println("Fin del Juego");
        log("Fin del Juego");
        if (player1.noMoreCards()) {
            System.out.println(player2.getName() + " ha conquistado el trono de hierro!");
            log(player2.getName() + " ha conquistado el trono de hierro!");
        } else {
            System.out.println(player1.getName() + " ha conquistado el trono de hierro!");
            log(player1.getName() + " ha conquistado el trono de hierro!");
        }
    }
}