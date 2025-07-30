package fr.raphaelmakaryan.lombredesdragons.Configurations;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.Game.Game;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;
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

    /**
     * @param steps
     * @param boardClass
     * @param menu
     */
    public void movePlayer(int steps, Board boardClass, Menu menu) {
        int[] boardInt = boardClass.getBoard();
        int newPosition = currentCasePlayers + steps;
        boardInt[currentCasePlayers] = 0;
        setNewBoard(boardInt);
        try {
            Case.verifyCase(newPosition, boardInt, boardClass, menu, steps);
        } catch (OutOfBoardException ignored) {
        }
    }


    /**
     * Checks if the player is trying to move out of the board and handles the situation.
     * @param positionNow
     * @param boardClass
     * @param boardInt
     */
    public void outOfBoard(int positionNow, Board boardClass, int[] boardInt) {
        int calculReturnGame;
        int difference;
        if (positionNow >= 63) {
            difference = (positionNow - 63);
            calculReturnGame = 63 - difference;
            boardInt[currentCasePlayers] = 0;
            boardClass.setNewCurrentCasePlayers(calculReturnGame);
            boardInt[calculReturnGame] = 1;
            boardClass.setNewBoard(boardInt);
            System.out.println("Vous avez essayez de sortir du plateau de jeu ! Vous avez été renvoyé à la case " + calculReturnGame + ".");
        }
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
            } else if (board[i] == 1) {
                boardStr[i] = "X";
            } else if (board[i] == 4) {
                boardStr[i] = "FIN";
            }
        }
        System.out.println("Affichage du plateau de jeu :");
        System.out.println(String.join(", ", boardStr));
    }


    /**
     * Sets a new board configuration.
     *
     * @param newBoard the new board configuration as an array of integers
     */
    public void setNewBoard(int[] newBoard) {
        this.board = newBoard;
    }
}