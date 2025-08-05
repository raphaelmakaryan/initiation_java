package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;

import java.util.Random;

public class Level extends Admin {

    public void addExp(User user, Menu menu) {
        Random rand = new Random();
        Character character = user.getCharacterPlayer();

        int value = rand.nextInt(1, 6); // Gain aléatoire entre 1 et 4
        int currentExp = character.getExp();
        int newExp = currentExp + value;

        character.setExp(newExp);
        System.out.println("Vous avez gagné " + value + " XP. Total : " + newExp);

        haveNewLevel(character, menu);
    }


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
            System.out.println("🎉 Niveau UP ! Vous êtes maintenant niveau " + newLevel);
        }
    }
}
