package fr.raphaelmakaryan.lombredesdragons.Configurations;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;
import fr.raphaelmakaryan.lombredesdragons.Verifications.Cell;

import java.util.Random;

public class Board {
    private int currentCasePlayers = 0;
    private int caseEnd = 64;
    private int caseStart = 0;
    private int[] board;

    public Board() {
        boolean debug = false;
        Random rand = new Random();
        if (debug) {
            board = new int[10];
        } else {
            board = new int[caseEnd];
        }
        int minIndex = caseStart + 1;
        int maxIndex = board.length - 2;
        int nbValues = rand.nextInt(1, (maxIndex - minIndex + 1));
        for (int i = 0; i < nbValues; i++) {
            int index = rand.nextInt(minIndex, maxIndex + 1);
            int value = rand.nextInt(2, 4);
            board[index] = value;
        }
        board[caseStart] = 1;
        board[board.length - 1] = 4;
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
            Cell.verifyCase(newPosition, boardInt, boardClass, menu);
        } catch (OutOfBoardException ignored) {
        }
    }

    /**
     * Checks if the player is trying to move out of the board and handles the situation.
     *
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
                boardStr[i] = Colors.NOTHING_BLUE + "NT" + Colors.RESET;
            } else if (board[i] == 1) {
                boardStr[i] = Colors.PLAYER_BRIYEL + "YOU" + Colors.RESET;
            } else if (board[i] == 2) {
                boardStr[i] = Colors.ENEMY_RED + "ENEMY" + Colors.RESET;
            } else if (board[i] == 3) {
                boardStr[i] = Colors.BOX_GREEN + "BOX" + Colors.RESET;
            } else if (board[i] == 4) {
                boardStr[i] = Colors.END_PURPLE + "END" + Colors.RESET;
            }
        }
        System.out.println("Game board display :");
        System.out.println(Colors.PLAYER_BRIYEL + "'YOU' : Your position on the board" + Colors.RESET);
        System.out.println(Colors.ENEMY_RED + "'ENEMY' : Enemy position on the board" + Colors.RESET);
        System.out.println(Colors.BOX_GREEN + "'BOX' : Box position on the board" + Colors.RESET);
        System.out.println(Colors.END_PURPLE + "'END' : End of the game position" + Colors.RESET);
        System.out.println(Colors.NOTHING_BLUE + "'NT' : Nothing on the board" + Colors.RESET);
        System.out.println("\n");
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

    /**
     * Sets the new cell for the player on the board.
     *
     * @param boardInt
     * @param newPosition
     */
    public void setNewCellPlayer(int[] boardInt, int newPosition) {
        setNewCurrentCasePlayers(newPosition);
        boardInt[newPosition] = 1;
        setNewBoard(boardInt);
        System.out.println("Vous êtes maintenant à la case " + Colors.CELL_BRIGHTMAGENTA + newPosition + Colors.RESET + ".");
    }
}