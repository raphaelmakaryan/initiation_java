package fr.raphaelmakaryan.lombredesdragons.configurations.Equipment;

import fr.raphaelmakaryan.lombredesdragons.configurations.DefensiveEquipment;

/**
 * Represents a potion in the game.
 * Potions are a type of defensive equipment that can be used to enhance a character's defense.
 */
public class Potion extends DefensiveEquipment {
    private String type;

    public Potion(String name, int levelDefense) {
        super(name, 2);
    }
}
