package fr.raphaelmakaryan.lombredesdragons.Configurations.Equipment;

import fr.raphaelmakaryan.lombredesdragons.Configurations.OffensiveEquipment;

public class Weapon extends OffensiveEquipment {
    private String type;

    public Weapon(String name, int levelAttack) {
        super(name, 5);
    }
}
