package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;

import java.util.Random;

public class Dice extends Admin {
    public int diceRoll() {
        Random rand = new Random();
        if (debugDice) {
            return valueDebugDice;
        }
        return rand.nextInt(1, 6);
    }
}
