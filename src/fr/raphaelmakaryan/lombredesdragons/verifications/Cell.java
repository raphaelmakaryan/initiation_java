package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

import java.sql.Connection;

public class Cell {
    /**
     * Checks if the square number is valid and updates the game board state.
     *
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @throws OutOfBoardException
     */
    public void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Connection connection, Database database, Level level) throws OutOfBoardException {
        outOfBoard(caseNumber, boardClass, boardInt, menu, user, game, connection, database, level);
        int verificationCase = boardInt[caseNumber];
        endGame(menu, verificationCase);
        enemyCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, connection, database, level);
        boxCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, connection, database, level);
        nothingCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, connection, database, level);
    }

    /**
     * Verification if the player leaves the board and modification of their position
     *
     * @param caseNumber
     * @param boardClass
     * @param boardInt
     * @param menu
     * @param user
     * @param game
     * @throws OutOfBoardException
     */
    public void outOfBoard(int caseNumber, Board boardClass, int[] boardInt, Menu menu, User user, Game game, Connection connection, Database database, Level level) throws OutOfBoardException {
        if (caseNumber >= 64) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt);
            menu.choiceGameProgress(boardClass, user, game, connection, database, level);
            throw new OutOfBoardException("Position hors du plateau !");
        }
    }

    /**
     * End of the game if he arrives at the end
     *
     * @param menu
     * @param verificationCase
     */
    public void endGame(Menu menu, int verificationCase) {
        if (verificationCase == 4) {
            EndGame.endGame("fin", menu);
        }
    }

    /**
     * Checking if the player is on an enemy cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     */
    public void enemyCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Connection connection, Database database, Level level) {
        if (verificationCase == 20 || verificationCase == 21 || verificationCase == 22 || verificationCase == 23 || verificationCase == 24) {
            // ENEMY
            Enemies enemies = new Enemies();
            enemies.haveEnemies(menu, boardClass, user, game, boardInt, caseNumber, connection, database, level);
        }
    }

    /**
     * Checking if the player is on an box cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     */
    public void boxCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Connection connection, Database database, Level level) {
        if (verificationCase >= 300) {
            // BOX
            Box box = new Box();
            box.haveBox(menu, boardClass, user, game, boardInt, caseNumber, connection, database, level);
        }
    }

    /**
     * Checking if the player is on an nothing cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     */
    public void nothingCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Connection connection, Database database, Level level) {
        if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCellPlayer(boardInt, caseNumber, true, connection, database, user, level);
            menu.choiceGameProgress(boardClass, user, game, connection, database, level);
        }
    }
}