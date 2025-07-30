package fr.raphaelmakaryan.lombredesdragons.Configurations;

public abstract class OffensiveEquipment {

    protected String name;
    protected int levelAttack;

    // Constructor
    public OffensiveEquipment(String name, int levelAttack) {
        this.name = name;
        this.levelAttack = levelAttack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OffensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelAttack=" + levelAttack +
                '}';
    }
}