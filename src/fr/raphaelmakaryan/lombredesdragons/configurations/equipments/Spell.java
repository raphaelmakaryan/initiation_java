package fr.raphaelmakaryan.lombredesdragons.configurations.equipments;

import fr.raphaelmakaryan.lombredesdragons.configurations.OffensiveEquipment;

/**
 * Represents a spell in the game.
 * Spells are a type of offensive equipment that can be used to enhance a character's attack.
 */
public class Spell extends OffensiveEquipment {
    public Spell(String name, int levelAttack, int idObject) {
        super(name, levelAttack, idObject);
    }
}