package fr.raphaelmakaryan.lombredesdragons.configurations;

public class Enemie {
    protected String name;
    protected int ID;
    protected int lifePoints;
    protected int attackLevel;
    protected String whoCanAttack;

    public Enemie(int ID, String name, int lifePoints, int attackLevel, String whoCanAttack) {
        this.ID = ID;
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackLevel = attackLevel;
        this.whoCanAttack = whoCanAttack;
    }

    public String getWhoCanAttack() {
        return whoCanAttack;
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

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Enemie{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", lifePoints=" + lifePoints +
                ", attackLevel=" + attackLevel +
                ", whoCanAttack='" + whoCanAttack + '\'' +
                '}';
    }
}
