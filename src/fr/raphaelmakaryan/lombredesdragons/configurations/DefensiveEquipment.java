package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class DefensiveEquipment {
    protected String name;
    protected int levelDefense;

    // Constructor
    public DefensiveEquipment(String name, int levelDefense) {
        this.name = name;
        this.levelDefense = levelDefense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelDefense=" + levelDefense +
                '}';
    }
}