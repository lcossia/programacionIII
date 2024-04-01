package com.cossia.cardgame.player;

import com.cossia.cardgame.cards.Card;
import com.cossia.cardgame.deck.Deck;

import java.util.ArrayList;
import java.util.List;

import static com.cossia.cardgame.utils.Logger.log;

public class Player {
    private String name;
    private Deck deck;
    private final List<Card> playerHand = new ArrayList<>();
    private final List<Card> deadCards = new ArrayList<>();
    private int score = 0;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(Card card) {
        this.playerHand.add(card);
    }

    public List<Card> getDeadCards() {
        return deadCards;
    }

    public void setDeadCards(Card deadCard) {
        this.deadCards.add(deadCard);
    }

    public int getScore() {
        return score;
    }


    public void aleatoryCardSelection() {
        if (deck.deckIsEmpty()) {
            System.out.println("El mazo de cartas de " + this.getName() + " está vacio.");
            log("El mazo de cartas de " + this.getName() + " está vacio.");

        } else {

            while (playerHand.size() < 3 && !deck.deckIsEmpty()) {
                Card card = deck.removeCard();
                if (card != null) {
                    playerHand.add(card);
                    System.out.println("Se ha agregado 1 carta a la mano de " + this.getName());
                    log("Se ha agregado 1 carta a la mano de " + this.getName());
                }
            }
        }
    }

    public void showHand() {
        System.out.println("\nLas cartas de " + this.getName() + " son las siguientes:\n");
        log("\nLas cartas de " + this.getName() + " son las siguientes:\n");
        for (Card card : playerHand) {
            System.out.println(card.toString());
            System.out.println("----------\n");
            log(card.toString());
            log("----------\n");
        }
        if (playerHand.isEmpty()) {
            System.out.println("Sin cartas en la mano de " + this.getName());
            log("Sin cartas en la mano de " + this.getName());
        }
    }

    public Card selectCard() {
        if (!playerHand.isEmpty()) {
            // Indice aleatorio para seleccionar una carta de la mano
            int randomIndex = (int) (Math.random() * playerHand.size());

            // Retorna la carta seleccionada y la saca de la mano del jugador
            return playerHand.remove(randomIndex);
        } else {
            // Si la mano está vacía, retorna null (no hay cartas para jugar), rompe bucle en battle.
            return null;
        }
    }

    public boolean noMoreCards() {
        // Verifica si la mano del jugador está vacía.
        return playerHand.isEmpty();
    }

    public void incrementScore() {
        this.score += 1;
    }
}
