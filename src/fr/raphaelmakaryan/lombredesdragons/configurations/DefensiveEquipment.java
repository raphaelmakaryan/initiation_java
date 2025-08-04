package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class DefensiveEquipment {
    protected String name;
    protected int levelDefense;
    protected int idObject;

    // Constructor
    public DefensiveEquipment(String name, int levelDefense, int idObject) {
        this.name = name;
        this.levelDefense = levelDefense;
        this.idObject = idObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelDefense() {
        return levelDefense;
    }

    public void setLevelDefense(int levelDefense) {
        this.levelDefense = levelDefense;
    }

    public int getIdObject() {
        return idObject;
    }

    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelDefense=" + levelDefense +
                ", idObject=" + idObject +
                '}';
    }
}