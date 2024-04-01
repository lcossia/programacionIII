package com.cossia.cardgame.menu;

import com.cossia.cardgame.battle.Battle;
import com.cossia.cardgame.player.PlayerSetup;
import com.cossia.cardgame.utils.ScannerSingleton;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.cossia.cardgame.utils.FileReader.showText;
import static com.cossia.cardgame.utils.Logger.clearLog;
import static com.cossia.cardgame.utils.Logger.readLog;

public class Menu {
    private final Scanner scanner = ScannerSingleton.getInstance();
    private boolean introShowedFlag = false;
    private final PlayerSetup playerSetup;
    private final Battle battle;

    public Menu(PlayerSetup playerSetup, Battle battle) {
        this.playerSetup = playerSetup;
        this.battle = battle;
    }

    public void principalMenu() {

        if (!introShowedFlag) {
            showText("src/com/cossia/cardgame/text/intro.txt");
            introShowedFlag = true;
            System.out.println("\n(Pulse la tecla 'Enter' para continuar)");
            scanner.nextLine();
        }

        System.out.println("***** MENU PRINCIPAL *****");
        System.out.println("1. Nuevo Juego");
        System.out.println("2. Historial de Batalla");
        System.out.println("3. Borrar Historial de Batalla");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    newGameMenu();
                    break;
                case 2:
                    System.out.println();
                    List<String> logMessages = readLog();
                    for (String message : logMessages) {
                        System.out.println(message);
                    }
                    System.out.println("\n(Presione la tecla 'Enter' para continuar)");
                    scanner.nextLine();
                    principalMenu();
                case 3:
                    clearLog();
                    System.out.println("\nRegistro de Batalla borrado...");
                    System.out.println("\n(Presione la tecla 'Enter' para continuar)");
                    scanner.nextLine();
                    principalMenu();
                case 4:
                    System.out.println("\nSaliendo del juego...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    principalMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número válido.");
            scanner.nextLine();
            principalMenu();
        }
    }

    public void newGameMenu() {
        System.out.println("***** NUEVO JUEGO *****");
        System.out.println("1. Partida Rápida");
        System.out.println("2. Partida Personalizada");
        System.out.println("3. Volver al Menu Principal");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    fastGameMenu();
                    break;
                case 2:
                    System.out.println();
                    customGameMenu();
                    break;
                case 3:
                    System.out.println();
                    principalMenu();
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
                    System.out.println();
                    newGameMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número válido.");
            scanner.nextLine();
            newGameMenu();
        }
    }

    public void gameOverMenu() {
        System.out.println("\n----- FIN DEL COMBATE -----");
        System.out.println("1. Menu Principal");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    principalMenu();
                    break;
                case 2:
                    System.out.println("Saliendo del juego...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    gameOverMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número válido.");
            scanner.nextLine();
            gameOverMenu();
        }
    }

    public void fastGameMenu() {
        System.out.println("Iniciando partida rápida...");
        System.out.println();
        showText("src/com/cossia/cardgame/text/fastGame");
        System.out.println("\n(Presione la tecla 'Enter' para continuar)");
        scanner.nextLine();
        playerSetup.createPlayers();
        playerSetup.setRandomDecks();
        battle.startFastGame();
        gameOverMenu();
    }

    public void customGameMenu() {
        System.out.println("Iniciando partida personalizada...");
        System.out.println();
        showText("src/com/cossia/cardgame/text/customGame");
        System.out.println("\n(Presione la tecla 'Enter' para continuar)");
        scanner.nextLine();
        playerSetup.createPlayers();
        playerSetup.setCustomDecks();
        battle.customGame();
        gameOverMenu();
    }
}
