package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.util.Random;

public class Level extends Admin {
    Tools tools = new Tools();


    /**
     * Adds exp to each victory against an enemy
     *
     * @param user
     * @param menu
     */
    public void addExp(User user, Menu menu) {
        Random rand = new Random();
        Character character = user.getCharacterPlayer();

        int value = rand.nextInt(1, 6);
        int currentExp = character.getExp();
        int newExp = currentExp + value;

        tools.clearLine();
        character.setExp(newExp);
        System.out.println("Vous avez gagné " + value + " XP ! Total d'exp actuel : " + newExp);

        haveNewLevel(character, menu);
    }


    /**
     * Check if the player has unlocked a new one otherwise or not
     * @param character
     * @param menu
     */
    public void haveNewLevel(Character character, Menu menu) {
        int exp = character.getExp();
        int currentLevel = character.getLevel();
        int newLevel = currentLevel;
        int maxH = character.getMaxHealth();
        int attack = character.getAttackLevel();

        for (int i = 0; i < levelDifficulty.length; i++) {
            if (exp >= levelDifficulty[i][0]) {
                newLevel = levelDifficulty[i][1];
            }
        }

        if (newLevel > currentLevel) {
            character.setLevel(newLevel);
            character.setExp(attack + 1);
            character.setMaxHealth(maxH + 1);
            tools.clearLine();
            System.out.println("🎉 Niveau UP ! Vous êtes maintenant niveau " + newLevel);
            tools.clearLine();
        }
    }
}
