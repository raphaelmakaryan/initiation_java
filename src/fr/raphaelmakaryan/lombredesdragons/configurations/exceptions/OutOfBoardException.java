package fr.raphaelmakaryan.lombredesdragons.configurations.exceptions;

/**
 * Exception thrown when a move is attempted outside the boundaries of the game board.
 */
public class OutOfBoardException extends Exception {
    public OutOfBoardException(String message) {
        super(message);
    }
}
