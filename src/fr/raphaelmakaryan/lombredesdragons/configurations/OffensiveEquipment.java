package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class OffensiveEquipment {

    protected String name;
    protected int levelAttack;
    protected int idObject;
    protected int valuePrice;

    // Constructor
    public OffensiveEquipment(String name, int levelAttack, int idObject, int valuePrice) {
        this.name = name;
        this.levelAttack = levelAttack;
        this.idObject = idObject;
        this.valuePrice = valuePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelAttack() {
        return levelAttack;
    }

    public void setLevelAttack(int levelAttack) {
        this.levelAttack = levelAttack;
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
        return "OffensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelAttack=" + levelAttack +
                ", idObject=" + idObject +
                '}';
    }
}