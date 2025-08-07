package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class DefensiveEquipment {
    protected String name;
    protected int levelDefense;
    protected int idObject;
    protected int valuePrice;

    // Constructor
    public DefensiveEquipment(String name, int levelDefense, int idObject, int valuePrice) {
        this.name = name;
        this.levelDefense = levelDefense;
        this.idObject = idObject;
        this.valuePrice = valuePrice;
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

    public int getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(int valuePrice) {
        this.valuePrice = valuePrice;
    }

    @Override
    public String toString() {
        return "DefensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelDefense=" + levelDefense +
                ", idObject=" + idObject +
                ", valuePrice=" + valuePrice +
                '}';
    }
}