package fr.raphaelmakaryan.lombredesdragons.Game;

public class Dice {
    public int diceRoll() {
        boolean debug = false;
        int max = 6;
        int min = 1;
        if (debug) {
            return 10;
        }
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
