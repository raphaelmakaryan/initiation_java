package fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

public class OutOfBoardException extends Exception {
    public OutOfBoardException(String message) {
        super(message);
    }
}
