package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.verifications.Cell;

import java.util.Arrays;
import java.util.Random;

public class Board extends Admin {
    private int currentCasePlayers = 0;
    private int caseEnd = 64;
    private int caseStart = 0;
    private int[] board;


    public Board() {
        Random rand = new Random();
        int valueBoard;
        if (debugBoard) {
            valueBoard = valueDebugBoard;
        } else {
            valueBoard = caseEnd;
        }
        board = new int[valueBoard];
        int minIndex = caseStart + 1;
        int maxIndex = board.length - 2;
        //int nbValues = rand.nextInt(1, (maxIndex - minIndex + 1));
        setRandomBoard();
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
            } else if (board[i] == 20 || board[i] == 21 || board[i] == 22) {
                boardStr[i] = Colors.ENEMY_RED + "ENEMY" + Colors.RESET;
            } else if (board[i] == 30 || board[i] == 31 || board[i] == 32 || board[i] == 33) {
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

    public void setRandomAdminBoard(int nbValues, Random rand, int minIndex, int maxIndex) {
        for (int i = 0; i < nbValues; i++) {
            int index = rand.nextInt(minIndex, maxIndex + 1);
            int value = rand.nextInt(2, 4);
            if (value == 2) {
                int valueEnemy = rand.nextInt(0, 2);
                board[index] = Integer.sum(20, valueEnemy);
            } else if (value == 3) {
                int valueBox = rand.nextInt(0, 3);
                board[index] = Integer.sum(30, valueBox);
            }
        }
    }

    /*
    public void setRandomBoard(int minIndex, int maxIndex, Random rand) {
        int[] ennemis = {45, 52, 56, 62, 10, 20, 25, 32, 35, 36, 37, 40, 44, 47, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
        int[] bonus = {2, 11, 5, 22, 38, 19, 26, 42, 53, 1, 4, 8, 17, 23, 48, 49, 7, 13, 31, 33, 39, 43, 28, 41};

        for (int i = 0; i < ennemis.length; i++) {
            int index = rand.nextInt(minIndex, maxIndex + 1);
            int valueEnemy = rand.nextInt(0, 2);
            board[index] = Integer.sum(20, valueEnemy);
        }

        for (int i = 0; i < bonus.length; i++) {
            int index = rand.nextInt(minIndex, maxIndex + 1);
            if (index != ennemis[i]) {
                int valueBox = rand.nextInt(0, 3);
                board[index] = Integer.sum(30, valueBox);
            }
        }

    }
 */

    public void setRandomBoard() {
        setEnemyRandomOnBoard();
        setBoxRandomOnBoard();
    }


    public void setEnemyRandomOnBoard() {
        int[][] ennemisCell = {{45, 22}, {52, 22}, {56, 22}, {62, 22}, {10, 21}, {20, 21}, {25, 21}, {32, 21}, {35, 21}, {36, 21}, {37, 21}, {40, 21}, {44, 21}, {47, 21}, {3, 20}, {6, 20}, {9, 20}, {12, 20}, {15, 20}, {18, 20}, {21, 20}, {24, 20}, {27, 20}, {30, 20}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < ennemisCell.length; j++) {
                if (i == ennemisCell[j][0]) {
                    board[i] = ennemisCell[j][1];
                    break;
                }
            }
        }
    }

    public void setBoxRandomOnBoard() {
        int[][] boxCell = {{2, 320}, {11, 320}, {5, 320}, {22, 320}, {38, 320}, {19, 321}, {26, 321}, {42, 321}, {53, 321}, {1, 310}, {4, 310}, {8, 310}, {17, 310}, {23, 310}, {48, 311}, {49, 311}, {7, 300}, {13, 300}, {31, 300}, {33, 300}, {39, 300}, {43, 300}, {28, 301}, {41, 301}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < boxCell.length; j++) {
                if (i == boxCell[j][0]) {
                    board[i] = boxCell[j][1];
                    break;
                }
            }
        }
    }
}