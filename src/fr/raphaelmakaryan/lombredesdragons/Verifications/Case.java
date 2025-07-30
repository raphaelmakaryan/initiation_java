package fr.raphaelmakaryan.lombredesdragons.Verifications;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;
import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;

public class Case {
    public static void verifyCase(int caseNumber, int[] boardInt, Board boardClass, Menu menu) {
        int endValue = 4;
        int verificationCase = boardInt[caseNumber];
        if (verificationCase == endValue) {
            EndGame.endGame("fin");
        } else {
            boardClass.setNewCurrentCasePlayers(caseNumber);
            boardInt[caseNumber] = 1;
            boardClass.setNewBoard(boardInt);
            menu.choiceGameProgress(boardClass);
        }
    }
}
