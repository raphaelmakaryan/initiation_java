package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class OffensiveEquipment extends Admin {

    protected String name;
    protected int levelAttack;
    protected int idObject;
    protected int valuePrice;
    protected int lifetime;
    protected int lifetimeDefault;
    protected int priceRepair;

    // Constructor
    public OffensiveEquipment(String name, int levelAttack, int idObject, int valuePrice, int lifetime, int priceRepair) {
        this.name = name;
        this.levelAttack = levelAttack;
        this.idObject = idObject;
        this.valuePrice = valuePrice;
        if (debugLifeTimeWeapon != 0) {
            this.lifetime = 1;
            this.lifetimeDefault = 10;
        } else {
            this.lifetime = lifetime;
            this.lifetimeDefault = lifetime;
        }
        this.priceRepair = priceRepair;
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

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getLifetimeDefault() {
        return lifetimeDefault;
    }

    public int getPriceRepair() {
        return priceRepair;
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