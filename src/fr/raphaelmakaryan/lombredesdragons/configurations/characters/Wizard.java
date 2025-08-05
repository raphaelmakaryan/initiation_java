package fr.raphaelmakaryan.lombredesdragons.configurations.characters;

import fr.raphaelmakaryan.lombredesdragons.configurations.Character;

/**
 * Represents a Wizard character in the game.
 * Wizards have a low strength but high intelligence and dexterity.
 */
public class Wizard extends Character {
    public Wizard(String name) {
        super(name, 3, 8, 3, null, null);
    }
}