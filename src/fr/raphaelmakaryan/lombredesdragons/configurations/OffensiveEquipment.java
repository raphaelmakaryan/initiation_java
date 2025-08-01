package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class OffensiveEquipment {

    protected String name;
    protected int levelAttack;
    protected int idObject;

    // Constructor
    public OffensiveEquipment(String name, int levelAttack, int idObject) {
        this.name = name;
        this.levelAttack = levelAttack;
        this.idObject = idObject;
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

    @Override
    public String toString() {
        return "OffensiveEquipment{" +
                "name='" + name + '\'' +
                ", levelAttack=" + levelAttack +
                ", idObject=" + idObject +
                '}';
    }
}