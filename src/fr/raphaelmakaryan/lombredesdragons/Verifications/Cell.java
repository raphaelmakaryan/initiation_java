package fr.raphaelmakaryan.lombredesdragons.Verifications;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.Verifications.Enemies;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

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
            boardClass.setNewCurrentCasePlayers(caseNumber);
            boardInt[caseNumber] = 1;
            boardClass.setNewBoard(boardInt);
            enemies.haveEnemies(menu, boardClass);
        } else if (verificationCase == 3) {
            // BOX
            Box box = new Box();
            boardClass.setNewCurrentCasePlayers(caseNumber);
            boardInt[caseNumber] = 1;
            boardClass.setNewBoard(boardInt);
            box.haveBox(menu, boardClass);
        } else if (verificationCase == 0) {
            // NOTHING
            System.out.println(Colors.NOTHING_BLUE + "Il n'y a rien ici !\n" + Colors.RESET);
            boardClass.setNewCurrentCasePlayers(caseNumber);
            boardInt[caseNumber] = 1;
            boardClass.setNewBoard(boardInt);
            menu.choiceGameProgress(boardClass);
        }
    }
}
