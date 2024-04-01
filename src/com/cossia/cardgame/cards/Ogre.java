package com.cossia.cardgame.cards;

import com.cossia.cardgame.utils.DateGenerator;

import java.time.LocalDate;
import java.time.Period;

import static com.cossia.cardgame.utils.Logger.log;

public class Ogre extends Card {
    private static final String[] arrayNames = {
            "Grommash", "Grul'kash", "Thokk", "Groknar", "Blorg", "Ghorlorz"
    };
    private static final String[] arrayAliases = {
            "El Colosal", "El Destructor", "El Brutal", "El Tremendo", "El Hambriento", "El Predador"
    };

    public Ogre() {
        this.lifeExpectancy = 150;
    }

    public static String[] getArrayNames() {
        return arrayNames;
    }

    public static String[] getArrayAliases() {
        return arrayAliases;
    }

    @Override
    public int getMinStrength() {
        return 15;
    }

    @Override
    public int getMaxStrength() {
        return 20;
    }

    @Override
    public int getMinSpeed() {
        return 5;
    }

    @Override
    public int getMaxSpeed() {
        return 10;
    }

    @Override
    public int getMinDexterity() {
        return 5;
    }

    @Override
    public int getMaxDexterity() {
        return 10;
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
        setRace(Race.OGRE);
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
            System.out.println(this.name + ": 'UHH!!!'");
            log(this.name + ": 'UHH!!!'");
        } else if (damage >= -20) {
            System.out.println(this.name + ": 'AGGAGGHH!!!'");
            log(this.name + ": 'AGGAGGHH!!!'");
        } else if (damage >= -30) {
            System.out.println(this.name + ": 'Pronto te saborearé!!!'");
            log(this.name + ": 'Pronto te saborearé!!!'");
        } else if (damage >= -50) {
            System.out.println(this.name + ": 'Se aproxima tu muerte y mi comida... Golpe Crítico!!!'");
            log(this.name + ": 'Se aproxima tu muerte y mi comida... Golpe Crítico!!!'");
        }
        else {
            System.out.println("Golpe letal!!!");
            log("Golpe letal!!!");
        }
    }

    @Override
    public int attack(Card opponent) {
        final double ogreFactor = 1.1f;
        return (int)(super.attack(opponent) * ogreFactor);
    }
}