package fr.raphaelmakaryan.lombredesdragons.Configurations.Equipment;

import fr.raphaelmakaryan.lombredesdragons.Configurations.DefensiveEquipment;

public class Potion extends DefensiveEquipment {
    private String type;

    public Potion(String name, int levelDefense) {
        super(name, 2);
    }
}
