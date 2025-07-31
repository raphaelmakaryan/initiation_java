package fr.raphaelmakaryan.lombredesdragons.configurations.equipments;

import fr.raphaelmakaryan.lombredesdragons.configurations.OffensiveEquipment;

/**
 * Represents a Weapon in the game.
 * Weapon are a type of offensive equipment that can be used to enhance a character's attack.
 */
public class Weapon extends OffensiveEquipment {
    private String type;

    public Weapon(String name, int levelAttack) {
        super(name, 5);
    }
}
