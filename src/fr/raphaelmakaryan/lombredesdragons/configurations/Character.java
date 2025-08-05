package fr.raphaelmakaryan.lombredesdragons.configurations;

import java.util.ArrayList;

public abstract class Character {
    protected String type;
    protected String name;
    protected int level;
    protected int exp;
    protected int lifePoints;
    protected int lifeDefault;
    protected int maxHealth;
    protected int attackLevel;
    protected OffensiveEquipment offensiveEquipment;
    protected DefensiveEquipment defensiveEquipment;

    /**
     * Character Constructordifference
     *
     * @param name
     * @param lifePoints
     * @param attackLevel
     * @param offensiveEquipment
     * @param defensiveEquipment
     */
    public Character(String name, int lifePoints, int attackLevel, int maxHealth ,OffensiveEquipment offensiveEquipment, DefensiveEquipment defensiveEquipment) {
        this.name = name;
        this.type = this.getClass().getSimpleName();
        this.lifePoints = lifePoints;
        this.lifeDefault = lifePoints;
        this.attackLevel = attackLevel;
        this.maxHealth = maxHealth;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
        this.exp = 0;
        this.level = 0;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
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
