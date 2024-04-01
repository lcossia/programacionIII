package com.cossia.cardgame.utils;

import java.util.Random;

public class RandomGenerator {

    private static RandomGenerator instance;
    private final Random random;

    private RandomGenerator() {
        random = new Random();
    }

    public static RandomGenerator getInstance() {
        if (instance == null) {
            synchronized (RandomGenerator.class) {
                if (instance == null) {
                    instance = new RandomGenerator();
                }
            }
        }
        return instance;
    }

    public int getRandom(int min, int max) {
        return instance.random.nextInt(max - min + 1) + min;
    }

    public double generateRandomPercentage() {
        int randomNumber = instance.random.nextInt(100) + 1;
        return (double) randomNumber / 100.0;
    }
}
