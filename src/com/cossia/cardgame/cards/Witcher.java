package com.cossia.cardgame.cards;

import com.cossia.cardgame.utils.DateGenerator;

import java.time.LocalDate;
import java.time.Period;

import static com.cossia.cardgame.utils.Logger.log;

public class Witcher extends Card {
    private static final String[] arrayNames = {
            "Malachius Thorne", "Eoin Razor", "Zephyr Darkspell", "Beck Riddle", "Ignatius Stormrider", "Eiran Discord"
    };
    private static final String[] arrayAliases = {
            "El Invocador de Tormentas", "La Sombra Encantada", "El Maestro de las Runas", "La Bruja de las Sombras",
            "El Hechicero del Viento", "Domador de Dragones"
    };

    public Witcher() {
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
        return 15;
    }

    @Override
    public int getMaxSpeed() {
        return 20;
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
        return 5;
    }

    @Override
    public int getMaxArmor() {
        return 10;
    }

    @Override
    protected void battleMessages(int damage) {
        if (damage >= 0) {
            System.out.println(this.name + ": 'He fallado, cambiaré de técnica de combate!!!'");
            log(this.name + ": 'He fallado, cambiaré de técnica de combate!!!'");
        } else if (damage >= -20) {
            System.out.println(this.name + ": 'Su ki es muy alto!!!'");
            log(this.name + ": 'Su ki es muy alto!!!'");
        } else if (damage >= -30) {
            System.out.println(this.name + ": 'Nunca me daré por vencido!!!'");
            log(this.name + ": 'Nunca me daré por vencido!!!'");
        } else if (damage >= -50) {
            System.out.println(this.name + ": 'Se ha cargado Onda de Energía Crítica!!!'");
            log(this.name + ": 'Se ha cargado Onda de Energía Crítica!!!'");
        }
        else {
            System.out.println("Golpe letal!!!");
            log("Golpe letal!!!");
        }
    }

    @Override
    public void initializeSpecificAttributes() {
        setRace(Race.WITCHER);
        this.birthDate = DateGenerator.generateRandomBirthDate(random, lifeExpectancy);
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.strength = random.getRandom(getMinStrength(), getMaxStrength());
        this.speed = random.getRandom(getMinSpeed(), getMaxSpeed());
        this.dexterity = random.getRandom(getMinDexterity(), getMaxDexterity());
        this.armor = random.getRandom(getMinArmor(), getMaxArmor());
    }

    @Override
    public int attack(Card opponent) {
        final double witcherFactor = 1.1f;
        return (int)(super.attack(opponent) * witcherFactor);
    }
}