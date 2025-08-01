package fr.raphaelmakaryan.lombredesdragons.configurations;

import java.util.ArrayList;

public abstract class Character {
    protected String type;
    protected String name;
    protected int lifePoints;
    protected int lifeDefault;
    protected int attackLevel;
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;

    // constructor
    public Character(String name, int lifePoints, int attackLevel, OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        this.name = name;
        this.type = this.getClass().getSimpleName();
        this.lifePoints = lifePoints;
        this.lifeDefault = lifePoints;
        this.attackLevel = attackLevel;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
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

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public OffensiveEquipment getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(OffensiveEquipment offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    public DefensiveEquipment getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(DefensiveEquipment defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifeDefault() {
        return lifeDefault;
    }

    // Methode pour afficher les informations du personnage
    @Override
    public String toString() {
        return "Character{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", attackLevel=" + attackLevel +
                ", offensiveEquipment='" + offensiveEquipment + '\'' +
                ", defensiveEquipment='" + defensiveEquipment + '\'' +
                '}';
    }
}
