package fr.raphaelmakaryan.lombredesdragons.Configurations;

public abstract class Character {
    protected String type;
    protected String name;
    protected int lifePoints;
    protected int attackLevel;
    protected String offensiveEquipment;
    protected String defensiveEquipment;

    // constructor
    public Character(String name, int lifePoints, int attackLevel, String offensiveEquipment, String defensiveEquipment) {
        this.name = name;
        this.type = this.getClass().getSimpleName();
        this.lifePoints = lifePoints;
        this.attackLevel = attackLevel;
        this.offensiveEquipment = offensiveEquipment;
        this.defensiveEquipment = defensiveEquipment;
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

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
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

    public String getDefensiveEquipment() {
        return defensiveEquipment;
    }

    public void setDefensiveEquipment(String defensiveEquipment) {
        this.defensiveEquipment = defensiveEquipment;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methode pour afficher les informations du personnage
    @Override
    public String toString() {
        return "Character{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", attackLevel=" + attackLevel +
                ", offensiveEquipment='" + offensiveEquipment + '\'' +
                ", defensiveEquipment='" + defensiveEquipment + '\'' +
                '}';
    }
}
