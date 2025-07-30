package fr.raphaelmakaryan.lombredesdragons.Configurations.Characters;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Character;

/**
 * Represents a Warrior character in the game.
 * A Warrior has a strength of 5, a defense of 5, and no special ability.
 */
public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 5, 5, null, null);
    }
}
