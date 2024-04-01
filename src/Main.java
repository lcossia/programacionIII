/*
MATERIA  : PROGRAMACION III
PROFESOR : MARCO USTARROZ
ALUMNO   : LUCIANO COSSIA
 */

import com.cossia.cardgame.battle.Battle;
import com.cossia.cardgame.deck.Deck;
import com.cossia.cardgame.menu.Menu;
import com.cossia.cardgame.player.Player;
import com.cossia.cardgame.player.PlayerSetup;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        PlayerSetup playerSetup = new PlayerSetup(player1, player2, deck1, deck2);
        Battle battle = new Battle(player1, player2);

        Menu menu = new Menu(playerSetup, battle);

        menu.principalMenu();

    }
}