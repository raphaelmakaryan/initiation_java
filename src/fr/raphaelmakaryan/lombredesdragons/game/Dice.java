package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;

import java.util.Random;

public class Dice extends Admin {
    public int dice6() {
        Random rand = new Random();
        if (debugDice) {
            return valueDebugDice;
        }
        return rand.nextInt(1, 6);
    }

    public int[][] dice20(int attaquant) {
        Random rand = new Random();
        int value = rand.nextInt(1, 20);
        int[][] attackAndDice;
        if (value == 20) {
            attackAndDice = new int[][]{{attaquant + 2, value}};
            return attackAndDice;
        } else if (value == 1) {
            attackAndDice = new int[][]{{0, value}};
            return attackAndDice;
        } else {
            attackAndDice = new int[][]{{attaquant, value}};
            return attackAndDice;
        }
    }
}