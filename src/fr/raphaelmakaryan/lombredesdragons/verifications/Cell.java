package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

public class Cell extends Admin {
    /**
     * Checks if the square number is valid and updates the game board state.
     *
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @param database
     * @param level
     * @throws OutOfBoardException
     */
    public void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) throws OutOfBoardException {
        outOfBoard(caseNumber, boardClass, boardInt, menu, user, game, database, level);
        int verificationCase = boardInt[caseNumber];
        endGame(menu, verificationCase, game, database, boardClass);
        enemyCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
        boxCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
        merchantsCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
        hostelCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
        blacksmithCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
        nothingCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game, database, level);
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
     * @param database
     * @param level
     * @throws OutOfBoardException
     */
    public void outOfBoard(int caseNumber, Board boardClass, int[] boardInt, Menu menu, User user, Game game, Database database, Level level) throws OutOfBoardException {
        if (caseNumber >= caseEnd && valueDebugBoard == 0 && !boardClass.isSurvival || caseNumber >= valueDebugBoard && valueDebugBoard != 0 && !boardClass.isSurvival) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt, "normal");
            menu.choiceGameProgress(boardClass, user, game, database, level);
            throw new OutOfBoardException("Position hors du plateau !");
        }
        /*
        else if (caseNumber >= boardClass.caseBoardSurvival && boardClass.isSurvival) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt, "survival");
            menu.choiceGameProgress(boardClass, user, game, database, level);
            throw new OutOfBoardException("Position hors du plateau !");
        }

         */
    }

    /**
     * End of the game if he arrives at the end
     *
     * @param menu
     * @param verificationCase
     * @param game
     * @param database
     */
    public void endGame(Menu menu, int verificationCase, Game game, Database database, Board board) {
        if (verificationCase == valueCaseEnd && !board.isSurvival) {
            EndGame.endGame("fin", menu, game, database);
        } else if (verificationCase >= board.caseBoardSurvival && board.isSurvival) {
            EndGame.endGame("finSurvival", menu, game, database);
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
     * @param database
     * @param level
     */
    public void enemyCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase == 20 || verificationCase == 21 || verificationCase == 22 || verificationCase == 23 || verificationCase == 24) {
            // ENEMY
            Enemies enemies = new Enemies();
            enemies.haveEnemies(menu, boardClass, user, game, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void boxCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase >= 300) {
            // BOX
            Box box = new Box();
            box.haveBox(menu, boardClass, user, game, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void nothingCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCellPlayer(boardInt, caseNumber, true, database, user);
            menu.choiceGameProgress(boardClass, user, game, database, level);
        }
    }

    /**
     * Checking if the player is on an merchant cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @param database
     * @param level
     */
    public void merchantsCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase == 4) {
            // Merchants
            Merchants merchants = new Merchants();
            merchants.haveMerchants(menu, boardClass, user, game, boardInt, caseNumber, database, level);
        }
    }

    /**
     * Checking if the player is on an hostel cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @param database
     * @param level
     */
    public void hostelCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase == 5) {
            // Hostel
            Hostel hostel = new Hostel();
            hostel.haveHostel(menu, boardClass, user, game, boardInt, caseNumber, database, level);
        }
    }

    /**
     * Checking if the player is on an blacksmith cell
     *
     * @param verificationCase
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @param database
     * @param level
     */
    public void blacksmithCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        if (verificationCase == 6) {
            // Blacksmith
            Blacksmith blacksmith = new Blacksmith();
            blacksmith.haveBlacksmith(menu, boardClass, user, game, boardInt, caseNumber, database, level);
        }
    }
}