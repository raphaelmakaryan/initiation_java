package fr.raphaelmakaryan.lombredesdragons.Verifications;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

public class Case {
    public static void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu, int dee) throws OutOfBoardException {
        if (caseNumber >= 63) {
            boardClass.outOfBoard(caseNumber, boardClass, boardInt);
            menu.choiceGameProgress(boardClass);
            throw new OutOfBoardException("Position hors du plateau !");
        }
        int endValue = 4;
        int verificationCase = boardInt[caseNumber];
        if (verificationCase == endValue) {
            EndGame.endGame("fin", menu);
        } else {
            boardClass.setNewCurrentCasePlayers(caseNumber);
            boardInt[caseNumber] = 1;
            boardClass.setNewBoard(boardInt);
            menu.choiceGameProgress(boardClass);
        }
    }
}
