package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

import java.sql.Connection;

public class Box {
    /**
     * Verification function if it is a box
     *
     * @param menu
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     * @param connection
     * @param database
     * @param level
     */
    public void haveBox(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        menu.boxCell(boardClass, user, game, boardInt, caseNumber, connection, database, level);
    }
}
