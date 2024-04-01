package com.cossia.cardgame.cards;

import com.cossia.cardgame.utils.DateGenerator;

import java.time.LocalDate;
import java.time.Period;

import static com.cossia.cardgame.utils.Logger.log;

public class Human extends Card {
    private static final String[] arrayNames = {
            "Cedric", "Longshard", "Guimar", "Drugo", "Gaillart", "Stark"
    };
    private static final String[] arrayAliases = {
            "El Bárbaro", "El León", "El Valiente", "El Ladrón", "El Noble", "El Loco"
    };

    public Human() {
        this.lifeExpectancy = 100;
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
        return 10;
    }

    @Override
    public int getMaxDexterity() {
        return 15;
    }

    @Override
    public int getMinArmor() {
        return 10;
    }

    @Override
    public int getMaxArmor() {
        return 15;
    }

    public void initializeSpecificAttributes() {
        setRace(Race.HUMAN);
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
            System.out.println(this.name + ": 'Estoy entrando en calor...'");
            log(this.name + ": 'Estoy entrando en calor...'");
        } else if (damage >= -20) {
            System.out.println(this.name + ": 'Eres muy hábil esquivando!!!'");
            log(this.name + ": 'Eres muy hábil esquivando!!!'");
        } else if (damage >= -30) {
            System.out.println(this.name + ": 'Terminemos con esto de una vez!!!'");
            log(this.name + ": 'Terminemos con esto de una vez!!!'");
        } else if (damage >= -50) {
            System.out.println(this.name + ": 'El brillo de su espada incandila, Golpe Crítico!!!'");
            log(this.name + ": 'El brillo de su espada incandila, Golpe Crítico!!!'");
        }
        else {
            System.out.println("Golpe letal!!!");
            log("Golpe letal!!!");
        }
    }

    @Override
    public int attack(Card opponent) {
        final double humanFactor = 1.05f;
        return (int)(super.attack(opponent) * humanFactor);
    }
}
