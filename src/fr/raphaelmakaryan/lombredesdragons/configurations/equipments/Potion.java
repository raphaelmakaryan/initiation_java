package fr.raphaelmakaryan.lombredesdragons.configurations.equipments;

import fr.raphaelmakaryan.lombredesdragons.configurations.DefensiveEquipment;

/**
 * Represents a potion in the game.
 * Potions are a type of defensive equipments that can be used to enhance a character's defense.
 */
public class Potion extends DefensiveEquipment {
    public Potion(String name, int levelDefense, int idObject, int valuePrice) {
        super(name, levelDefense, idObject, valuePrice);
    }
}
