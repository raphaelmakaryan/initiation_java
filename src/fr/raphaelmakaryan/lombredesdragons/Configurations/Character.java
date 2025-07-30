package fr.raphaelmakaryan.lombredesdragons.Configurations;

public abstract class Character {
    protected String type;
    protected String name;
    protected int healthLevel;
    protected int attackLevel;
    protected String offensiveEquipment;

    // constructor
    public Character(String name, int healthLevel, int attackLevel, String offensiveEquipment) {
        this.name = name;
        this.healthLevel = healthLevel;
        this.attackLevel = attackLevel;
        this.offensiveEquipment = offensiveEquipment;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public int getAttackLevel() {
        return attackLevel;
    }

    public void setAttackLevel(int attackLevel) {
        this.attackLevel = attackLevel;
    }

    public String getOffensiveEquipment() {
        return offensiveEquipment;
    }

    public void setOffensiveEquipment(String offensiveEquipment) {
        this.offensiveEquipment = offensiveEquipment;
    }

    // Methode pour afficher les informations du personnage
    @Override
    public String toString() {
        return "Character{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", healthLevel=" + healthLevel +
                ", attackLevel=" + attackLevel +
                ", offensiveEquipment='" + offensiveEquipment + '\'' +
                '}';
    }
}
