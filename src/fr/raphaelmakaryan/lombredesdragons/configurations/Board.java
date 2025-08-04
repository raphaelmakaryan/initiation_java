package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;
import fr.raphaelmakaryan.lombredesdragons.verifications.Cell;
import java.util.Random;

public class Board extends Admin {
    private int currentCasePlayers = 0;
    private int[] board;


    public Board() {
        int valueBoard;
        Random rand = new Random();
        if (debugBoard) {
            valueBoard = valueDebugBoard;
        } else {
            valueBoard = caseEnd;
        }
        board = new int[valueBoard];
        setRandomCellBoard(ennemisCell, rand, enemyValue);
        setRandomCellBoard(boxCell, rand, boxValue);
        board[caseStart] = 1;
        board[board.length - 1] = 4;
        if (debugBoardCellInt) {
            Tools.displayAArrayint(board);
        }
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
    public void movePlayer(int steps, Board boardClass, Menu menu, User user, Game game) {
        Cell cellInstance = new Cell();
        System.out.println("Vous avez lancé le dé et obtenu : " + Colors.DICE_MAGENTA + steps + Colors.RESET + " !");
        int[] boardInt = boardClass.getBoard();
        int newPosition = currentCasePlayers + steps;
        boardInt[currentCasePlayers] = 0;
        setNewBoard(boardInt);
        if (debugBoardDisplay) {
            boardClass.displayBoard();
        }
        if (debugBoardIndexCell) {
            int indexDebug = boardClass.getCurrentCasePlayers();
            int valueDebug = boardInt[indexDebug];
            System.out.println("Le joueur est actuellement à la case " + indexDebug + " | La case a comme valeur : " + valueDebug);
        }
        try {
            cellInstance.verifyCase(newPosition, boardInt, boardClass, menu, user, game);
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
            System.out.println("Vous avez essayé de sortir du plateau de jeu ! Vous avez été renvoyé à la case " + calculReturnGame + ".");
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
            } else if (board[i] == 20 || board[i] == 21 || board[i] == 22) {
                boardStr[i] = Colors.ENEMY_RED + "ENEMY" + Colors.RESET;
            } else if (board[i] >= 300) {
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

    public void setRandomCellBoard(int[] cell, Random random, int[] valueBox) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < cell.length; j++) {
                if (i == cell[j]) {
                    int index = random.nextInt((valueBox.length - valueBox.length), valueBox.length);
                    int value = valueBox[index];
                    board[i] = value;
                    break;
                }
            }
        }
    }
}