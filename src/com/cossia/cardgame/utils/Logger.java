package com.cossia.cardgame.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {
    private static final String LOG_FILE = "src/com/cossia/cardgame/text/log.txt";

    public Logger() {
    }

    public static void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.println("[" + timestamp + "] " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLog() {
        List<String> logMessages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logMessages.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  logMessages;
    }

    public static void clearLog() {
        try (PrintWriter writer = new PrintWriter(LOG_FILE)) {
            writer.print(""); // Vaciar el archivo de registro
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
