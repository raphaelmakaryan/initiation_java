package fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

/**
 * Exception thrown when a move is attempted outside the boundaries of the game board.
 */
public class OutOfBoardException extends Exception {
    public OutOfBoardException(String message) {
        super(message);
    }
}
