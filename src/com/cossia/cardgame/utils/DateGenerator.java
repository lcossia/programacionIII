package com.cossia.cardgame.utils;

import java.time.LocalDate;

public class DateGenerator {

    public static LocalDate generateRandomBirthDate(RandomGenerator randomGenerator, int lifeExpectancy) {
        int minimumWarriorAge = 15;
        int maxDaysAgo = lifeExpectancy * 365;
        int randomDays = randomGenerator.getRandom(0, maxDaysAgo);
        LocalDate currentDate = LocalDate.now();
        LocalDate randomBirthDate = currentDate.minusDays(randomDays);

        LocalDate minBirthDate = LocalDate.of(LocalDate.now().getYear() - lifeExpectancy + minimumWarriorAge, 1, 1); // Año mínimo
        if (randomBirthDate.isBefore(minBirthDate)) {
            randomBirthDate = minBirthDate;
        }

        if (randomBirthDate.isAfter(currentDate)) {
            randomBirthDate = currentDate;
        }

        return randomBirthDate;
    }
}
