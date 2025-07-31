package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;

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
    public void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu) throws OutOfBoardException {
        int verificationCase = boardInt[caseNumber];
        outOfBoard(caseNumber, boardClass, boardInt, menu);
        endGame(menu, verificationCase);
        enemyCell(verificationCase, caseNumber, boardInt, boardClass, menu);
        boxCell(verificationCase, caseNumber, boardInt, boardClass, menu);
        nothingCell(verificationCase, caseNumber, boardInt, boardClass, menu);
    }

    public void outOfBoard(int caseNumber, Board boardClass, int[] boardInt, Menu menu) throws OutOfBoardException {
        if (caseNumber >= 63) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt);
            menu.choiceGameProgress(boardClass);
            throw new OutOfBoardException("Position hors du plateau !");
        }
    }

    public void endGame(Menu menu, int verificationCase) {
        if (verificationCase == 4) {
            EndGame.endGame("fin", menu);
        }
    }

    public void enemyCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu) {
        if (verificationCase == 20 || verificationCase == 21 || verificationCase == 22) {
            // ENEMY
            Enemies enemies = new Enemies();
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            enemies.haveEnemies(menu, boardClass);
        }
    }

    public void boxCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu) {
        if (verificationCase == 30 || verificationCase == 31 || verificationCase == 32 || verificationCase == 33) {
            // BOX
            Box box = new Box();
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            box.haveBox(menu, boardClass);
        }
    }

    public void nothingCell(int verificationCase, int caseNumber, int[] boardInt, Board boardClass, Menu menu) {
        if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            menu.choiceGameProgress(boardClass);
        }
    }

}
