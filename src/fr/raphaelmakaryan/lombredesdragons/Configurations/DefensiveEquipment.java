package fr.raphaelmakaryan.lombredesdragons.Configurations;

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

    public int getLevelAttack() {
        return levelDefense;
    }

    public void setLevelAttack(int levelDefense) {
        this.levelDefense = levelDefense;
    }

    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelDefense=" + levelDefense +
                '}';
    }
}
