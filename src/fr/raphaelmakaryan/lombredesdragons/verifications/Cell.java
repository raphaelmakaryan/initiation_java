package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

public class Cell {
    /**
     * Checks if the square number is valid and updates the game board state.
     *
     * @param caseNumber
     * @param boardInt
     * @param boardClass
     * @param menu
     * @throws OutOfBoardException
     */
    public void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game) throws OutOfBoardException {
        int verificationCase = boardInt[caseNumber];
        outOfBoard(caseNumber, boardClass, boardInt, menu, user, game);
        endGame(menu, verificationCase);
        enemyCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game);
        boxCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game);
        nothingCell(verificationCase, caseNumber, boardInt, boardClass, menu, user, game);
    }

    public void outOfBoard(int caseNumber, Board boardClass, int[] boardInt, Menu menu, User user, Game game) throws OutOfBoardException {
        if (caseNumber >= 63) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt);
            menu.choiceGameProgress(boardClass, user, game);
            throw new OutOfBoardException("Position hors du plateau !");
        }
    }

    public void endGame(Menu menu, int verificationCase) {
        if (verificationCase == 4) {
            EndGame.endGame("fin", menu);
        }
    }

    public void enemyCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game) {
        if (verificationCase == 20 || verificationCase == 21 || verificationCase == 22) {
            // ENEMY
            Enemies enemies = new Enemies();
            enemies.haveEnemies(menu, boardClass, user, game, boardInt, caseNumber);
        }
    }

    public void boxCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game) {
        if (verificationCase >= 300) {
            // BOX
            Box box = new Box();
            box.haveBox(menu, boardClass, user, game, boardInt, caseNumber);
        }
    }

    public void nothingCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu, User user, Game game) {
        if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            menu.choiceGameProgress(boardClass, user, game);
        }
    }
}