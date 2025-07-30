package fr.raphaelmakaryan.lombredesdragons.Game;

import java.util.Random;

public class Dice {
    public int diceRoll() {
        boolean debug = true;
        Random rand = new Random();
        if (debug) {
            return 1;
        }
        return rand.nextInt(1, 6);
    }
}
