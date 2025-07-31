package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Exceptions.OutOfBoardException;
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
    public static void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu) throws OutOfBoardException {
        if (caseNumber >= 63) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt);
            menu.choiceGameProgress(boardClass);
            throw new OutOfBoardException("Position hors du plateau !");
        }
        int verificationCase = boardInt[caseNumber];
        if (verificationCase == 4) {
            // END
            EndGame.endGame("fin", menu);
        } else if (verificationCase == 2) {
            // ENEMY
            Enemies enemies = new Enemies();
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            enemies.haveEnemies(menu, boardClass);
        } else if (verificationCase == 3) {
            // BOX
            Box box = new Box();
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            box.haveBox(menu, boardClass);
        } else if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            menu.choiceGameProgress(boardClass);
        }
    }
}
