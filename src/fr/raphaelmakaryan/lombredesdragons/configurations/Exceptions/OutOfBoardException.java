package fr.raphaelmakaryan.lombredesdragons.configurations.Exceptions;

/**
 * Exception thrown when a move is attempted outside the boundaries of the game board.
 */
public class OutOfBoardException extends Exception {
    public OutOfBoardException(String message) {
        super(message);
    }
}
