package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;

import java.util.Random;

public class Dice extends Admin {
    /**
     * Dice 6 faces
     *
     * @return
     */
    public int dice6() {
        Random rand = new Random();
        if (debugDice) {
            return valueDebugDice;
        }
        return rand.nextInt(1, 7);
    }

    /**
     * Dice 20 faces
     *
     * @param attacking
     * @return
     */
    public int[][] dice20(int attacking) {
        Random rand = new Random();
        int value = rand.nextInt(1, 20);
        int[][] attackAndDice;
        if (debugDiceCriticalLoose) {
            return new int[][]{{0, 1}};
        }
        if (debugDiceCriticalSuccess) {
            return new int[][]{{attacking + 2, 20}};
        }
        if (value == 20) {
            attackAndDice = new int[][]{{attacking + 2, value}};
            return attackAndDice;
        } else if (value == 1) {
            attackAndDice = new int[][]{{0, value}};
            return attackAndDice;
        } else {
            attackAndDice = new int[][]{{attacking, value}};
            return attackAndDice;
        }
    }
}