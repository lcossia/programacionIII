package com.cossia.cardgame.cards;

import com.cossia.cardgame.utils.DateGenerator;

import java.time.LocalDate;
import java.time.Period;

import static com.cossia.cardgame.utils.Logger.log;

public class Elf extends Card {
    private static final String[] arrayNames = {
            "Elandril", "Lirael", "Thandor", "Galadria", "Elowen", "Agis"
    };
    private static final String[] arrayAliases = {
            "El Bosque", "El Hermoso", "El Sigiloso", "El Brillante", "El Veloz", "El arquero"
    };

    public Elf() {
        this.lifeExpectancy = 300;
    }

    public static String[] getArrayNames() {
        return arrayNames;
    }

    public static String[] getArrayAliases() {
        return arrayAliases;
    }

    @Override
    public int getMinStrength() {
        return 5;
    }

    @Override
    public int getMaxStrength() {
        return 10;
    }

    @Override
    public int getMinSpeed() {
        return 10;
    }

    @Override
    public int getMaxSpeed() {
        return 15;
    }

    @Override
    public int getMinDexterity() {
        return 15;
    }

    @Override
    public int getMaxDexterity() {
        return 20;
    }

    @Override
    public int getMinArmor() {
        return 5;
    }

    @Override
    public int getMaxArmor() {
        return 10;
    }

    public void initializeSpecificAttributes() {
        setRace(Race.ELF);
        this.birthDate = DateGenerator.generateRandomBirthDate(random, lifeExpectancy);
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.strength = random.getRandom(getMinStrength(), getMaxStrength());
        this.speed = random.getRandom(getMinSpeed(), getMaxSpeed());
        this.dexterity = random.getRandom(getMinDexterity(), getMaxDexterity());
        this.armor = random.getRandom(getMinArmor(), getMaxArmor());
    }

    @Override
    protected void battleMessages(int damage) {
        if (damage == 0) {
            System.out.println(this.name + ": 'Los vientos han desviado mi disparo...'");
            log(this.name + ": 'Los vientos han desviado mi disparo...'");
        } else if (damage >= -20) {
            System.out.println(this.name + ": 'Si que sabes defenderte!!!'");
            log(this.name + ": 'Si que sabes defenderte!!!'");
        } else if (damage >= -30) {
            System.out.println(this.name + ": 'Tu vida se agotará más rápido que la mía!!!'");
            log(this.name + ": 'Tu vida se agotará más rápido que la mía!!!'");
        } else if (damage >= -50) {
            System.out.println(this.name + ": 'Su concentración se eleva y acerta un disparo Crítico!!!'");
            log(this.name + ": 'Su concentración se eleva y acerta un disparo Crítico!!!'");
        }
        else {
            System.out.println("Golpe letal!!!");
            log("Golpe letal!!!");
        }
    }

    @Override
    public int attack(Card opponent) {
        final double elfFactor = 1.08f;
        return (int)(super.attack(opponent) * elfFactor);
    }
}