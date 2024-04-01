package com.cossia.cardgame.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    public static void showText(String fileName) {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
