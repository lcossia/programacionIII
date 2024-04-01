package com.cossia.cardgame.utils;

import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scanner;

    private ScannerSingleton() {} // Constructor privado para evitar instanciación externa

    public static synchronized Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}