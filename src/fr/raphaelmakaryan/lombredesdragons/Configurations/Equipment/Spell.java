package fr.raphaelmakaryan.lombredesdragons.Configurations.Equipment;

import fr.raphaelmakaryan.lombredesdragons.Configurations.OffensiveEquipment;

public class Spell extends OffensiveEquipment {
    private String type;

    public Spell(String name, int levelAttack) {
        super(name, 5);
    }
}
