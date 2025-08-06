package fr.raphaelmakaryan.lombredesdragons.configurations.equipments;

import fr.raphaelmakaryan.lombredesdragons.configurations.DefensiveEquipment;

/**
 * Represents a shield in the game.
 * Shields are a type of defensive equipments that can be used to enhance a character's defense.
 */
public class Shiled extends DefensiveEquipment {
    public Shiled(String name, int levelDefense, int idObject, int valuePrice) {
        super(name, levelDefense, idObject, valuePrice);
    }
}