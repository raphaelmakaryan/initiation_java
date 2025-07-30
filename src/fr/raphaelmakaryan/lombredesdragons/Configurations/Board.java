package fr.raphaelmakaryan.lombredesdragons.Configurations;

import fr.raphaelmakaryan.lombredesdragons.Game.Menu;
import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;
import fr.raphaelmakaryan.lombredesdragons.Verifications.Case;

public class Board {
    private int currentCasePlayers = 0;
    private int caseEnd = 64;
    private int caseStart = 0;
    private int[] board;

    public Board() {
        board = new int[caseEnd];
        board[caseStart] = 1;
        board[caseEnd - 1] = 4;
    }

    public int getCurrentCasePlayers() {
        return currentCasePlayers;
    }

    public void setNewCurrentCasePlayers(int newCurrentCasePlayers) {
        this.currentCasePlayers = newCurrentCasePlayers;
    }

    public int[] getBoard() {
        return board;
    }

    public void movePlayer(int steps, Board board, Menu menu) {
        int[] boardCases = board.getBoard();
        int newPosition = (currentCasePlayers + 1) + steps;
        boardCases[currentCasePlayers] = 0;
        setNewBoard(boardCases);
        Case.verifyCase(newPosition, boardCases, board, menu);
    }

    public void displayBoard() {
        int[] board = getBoard();
        String[] boardStr = new String[board.length];
        for (int i = 0; i < board.length; i++) {
            boardStr[i] = String.valueOf(board[i]);
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                boardStr[i] = "VIDE";
            }
            else if (board[i] == 1) {
                boardStr[i] = "VOUS";
            } else if (board[i] == 4) {
                boardStr[i] = "FIN";
            }
        }
        System.out.println("Affichage du plateau de jeu :");
        System.out.println(String.join(", ", boardStr));
    }

    public void setNewBoard(int[] newBoard) {
        this.board = newBoard;
    }
}