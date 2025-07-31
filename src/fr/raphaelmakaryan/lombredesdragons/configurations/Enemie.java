package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.configurations.characters.Warrior;
import fr.raphaelmakaryan.lombredesdragons.configurations.characters.Wizard;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Dragon;

import java.util.Objects;

public class Enemie {
    protected String name;
    protected int ID;
    protected int lifePoints;
    protected int attackLevel;

    public Enemie(int ID, String name, int lifePoints, int attackLevel) {
        this.ID = ID;
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackLevel = attackLevel;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Enemie{" +
                "name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", attackLevel=" + attackLevel +
                '}';
    }
}
