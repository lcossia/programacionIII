package com.cossia.cardgame.cards;

import com.cossia.cardgame.utils.DateGenerator;

import java.time.LocalDate;
import java.time.Period;

import static com.cossia.cardgame.utils.Logger.log;

public class Dwarf extends Card {
    private static final String[] arrayNames = {
            "Thorin", "Gimli", "Dain", "Durin", "Balin", "Thimuk"
    };
    private static final String[] arrayAliases = {
            "El Barbudo", "El Forjador", "El Intrépido", "El Minero", "El Birrero", "El Gruñon"
    };

    public Dwarf() {
        this.lifeExpectancy = 200;
    }

    public static String[] getArrayNames() {
        return arrayNames;
    }

    public static String[] getArrayAliases() {
        return arrayAliases;
    }

    @Override
    public int getMinStrength() {
        return 10;
    }

    @Override
    public int getMaxStrength() {
        return 15;
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
        return 15;
    }

    @Override
    public int getMaxArmor() {
        return 20;
    }

    public void initializeSpecificAttributes() {
        setRace(Race.DWARF);
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
            System.out.println(this.name + ": 'Mi hacha debe estar desafilada...'");
            log(this.name + ": 'Mi hacha debe estar desafilada...'");
        } else if (damage >= -20) {
            System.out.println(this.name + ": 'Quien ha sido el enano que forjo esa armadura?!!!'");
            log(this.name + ": 'Quien ha sido el enano que forjo esa armadura?!!!'");
        } else if (damage >= -30) {
            System.out.println(this.name + ": 'Jaaa!! Ese golpe lo has sentido!!!'");
            log(this.name + ": 'Jaaa!! Ese golpe lo has sentido!!!'");
        } else if (damage >= -50) {
            System.out.println(this.name + ": 'Su hacha se enciende y acesta un Golpe Crítico!!!'");
            log(this.name + ": 'Su hacha se enciende y acesta un Golpe Crítico!!!'");
        }
        else {
            System.out.println("Golpe letal!!!");
            log("Golpe letal!!!");
        }
    }

    @Override
    public int attack(Card opponent) {
        final double dwarfFactor = 1.08f;
        return (int)(super.attack(opponent) * dwarfFactor);
    }
}