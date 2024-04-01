package com.cossia.cardgame.cards;

import com.cossia.cardgame.player.Player;
import com.cossia.cardgame.utils.RandomGenerator;

import java.time.LocalDate;

import static com.cossia.cardgame.utils.Logger.log;

public abstract class Card {
    protected RandomGenerator random = RandomGenerator.getInstance();
    protected Race race;
    protected String name;
    protected String alias;
    protected LocalDate birthDate = null;
    protected int age;
    protected int level = 1;
    protected int health = 100;
    protected int speed;
    protected int dexterity;
    protected int strength;
    protected int armor;

    protected int lifeExpectancy;

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    @Override
    public String toString() {
        return "Carta {" +
                "Raza=" + race +
                ", Nombre='" + name + '\'' +
                ", Alias='" + alias + '\'' +
                ", Fecha Nacimiento=" + birthDate +
                ", Edad=" + age +
                ", Nivel=" + level +
                ", Vida=" + health +
                ", Fuerza=" + strength +
                ", Velocidad=" + speed +
                ", Destreza=" + dexterity +
                ", Armadura=" + armor +
                '}';
    }

    protected abstract void initializeSpecificAttributes();

    public abstract int getMinStrength();
    public abstract int getMaxStrength();

    public abstract int getMinSpeed();
    public abstract int getMaxSpeed();

    public abstract int getMinDexterity();
    public abstract int getMaxDexterity();

    public abstract int getMinArmor();
    public abstract int getMaxArmor();

    protected abstract void battleMessages(int damage);


    public int attack(Card opponent) {
        int shootingPower = this.dexterity * this.strength * this.level;
        double shootingEfficiency = random.generateRandomPercentage();
        double attackValue = shootingPower * shootingEfficiency;
        int defensePower = armor * opponent.speed;
        double damage = (((attackValue * shootingEfficiency) - defensePower) / 500) * 100;

        if (damage >= 0) {
            damage = 0;
        }

        battleMessages((int)damage);
        opponent.receiveDamage((int)damage);

        return (int)damage;
    }

    public void receiveDamage(int damage) {
        int receivedDamage = this.getHealth() + damage;
        this.setHealth(receivedDamage);

        if (this.getHealth() <= 0) {
            this.setHealth(0);
        }
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }

    public void levelIncreased() {
        this.level++;
    }

    public void healthRestored() {
        this.setHealth(100);
    }

    public void randomPowerUp(Player player) {
        int action = random.getRandom(1, 3);

        switch (action) {
            case 1:
                // Incrementar level +1
                this.levelIncreased();
                System.out.println(this.getName() + " ha subido de nivel a " + this.getLevel() + "!");
                log(this.getName() + " ha subido de nivel a " + this.getLevel() + "!");
                break;
            case 2:
                // Restaurar vida a 100
                this.healthRestored();
                System.out.println(this.getName() + " ha recuperado su vida a 100 puntos.");
                log(this.getName() + " ha recuperado su vida a 100 puntos.");
                break;
            case 3:
                // Recuperar una carta del cementerio, si la mano del jugador tiene menos de 2 cartas
                if (player.getPlayerHand().size() < 2 && !player.getDeadCards().isEmpty()) {
                    int randomIndex = random.getRandom(0, player.getDeadCards().size() - 1);
                    Card recoveredCard = player.getDeadCards().remove(randomIndex);
                    recoveredCard.setHealth(100);
                    System.out.println("Se ha recuperado del cementerio la carta " + recoveredCard.getName() + " " +
                            recoveredCard.getAlias() + ".");
                    log("Se ha recuperado del cementerio la carta " + recoveredCard.getName() + " " +
                            recoveredCard.getAlias() + ".");
                    player.getPlayerHand().add(recoveredCard);
                } else {
                    System.out.println("No se puede recuperar una carta en este momento." +
                            " Se restaurará la vida de " + this.getName() + " " + this.getAlias() + ".");
                    log("No se puede recuperar una carta en este momento." +
                            " Se restaurará la vida de " + this.getName() + " " + this.getAlias() + ".");
                    this.healthRestored();
                }
                break;
            default:
                System.out.println("Opción no válida.");
                log("Opción no válida.");
        }
    }
}