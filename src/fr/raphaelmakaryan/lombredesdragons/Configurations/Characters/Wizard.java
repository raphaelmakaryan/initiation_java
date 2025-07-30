package fr.raphaelmakaryan.lombredesdragons.Configurations.Characters;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Character;

/**
 * Represents a Wizard character in the game.
 * Wizards have a low strength but high intelligence and dexterity.
 */
public class Wizard extends Character {
    public Wizard(String name) {
        super(name, 3, 8, null);
    }
}