package fr.raphaelmakaryan.lombredesdragons.Configurations.Equipment;

import fr.raphaelmakaryan.lombredesdragons.Configurations.DefensiveEquipment;

/**
 * Represents a shield in the game.
 * Shields are a type of defensive equipment that can be used to enhance a character's defense.
 */
public class Shiled extends DefensiveEquipment {
    private String type;

    public Shiled(String name, int levelDefense) {
        super(name, 5);
    }
}
