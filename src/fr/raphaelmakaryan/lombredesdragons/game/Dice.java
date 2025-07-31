package fr.raphaelmakaryan.lombredesdragons.game;

import java.util.Random;

public class Dice {
    public int diceRoll() {
        boolean debug = false;
        Random rand = new Random();
        if (debug) {
            return 1;
        }
        return rand.nextInt(1, 6);
    }
}
