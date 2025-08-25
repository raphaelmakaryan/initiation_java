package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.configurations.exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;
import fr.raphaelmakaryan.lombredesdragons.verifications.Cell;

import java.sql.Connection;
import java.util.Random;

public class Board extends Admin {
    private int currentCasePlayers = 0;
    private int[] board;
    public boolean isSurvival = false;
    public int caseBoardSurvival = 1000;
    Tools tools = new Tools();

    /**
     * Creation of the game board
     */
    public Board(String levelDifficulty) {
        int valueBoard;
        Random rand = new Random();
        if (debugBoard) {
            valueBoard = valueDebugBoard;
        } else {
            valueBoard = caseEnd;
        }
        if (levelDifficulty != "survival") {
            board = new int[valueBoard];
            setRandomCellBoard(ennemisCell, rand, enemyValue, "enemy", levelDifficulty);
            setRandomCellBoard(boxCell, rand, boxValue, "box", levelDifficulty);
            setRandomCellBoard(merchantsCell, rand, merchantsValue, "merchants", levelDifficulty);
            setRandomCellBoard(hostelCell, rand, hostelValue, "hostel", levelDifficulty);
            setRandomCellBoard(blacksmithCell, rand, blacksmithValue, "blacksmith", levelDifficulty);
            board[caseStart] = valuePlayer;
            board[board.length - 1] = valueCaseEnd;
            if (debugBoardCellInt) {
                Tools.displayAArrayint(board);
            }
        } else {
            isSurvival = true;
            board = new int[caseBoardSurvival];
            setRandomCellBoardSurvival(rand);
            board[caseStart] = valuePlayer;
            board[board.length - 1] = valueCaseEnd;
        }
    }

    /**
     * Returns the index of the player’s box exactly
     *
     * @return intCurrentCasePlayers
     */
    public int getCurrentCasePlayers() {
        return currentCasePlayers;
    }

    /**
     * Update the index of the current player’s box
     */
    public void setNewCurrentCasePlayers(int newCurrentCasePlayers) {
        this.currentCasePlayers = newCurrentCasePlayers;
    }

    /**
     * Return the entire game board
     *
     * @return int[]Board
     */
    public int[] getBoard() {
        return board;
    }

    /**
     * Make the player move in the game board
     *
     * @param steps
     * @param boardClass
     * @param menu
     */
    public void movePlayer(int steps, Board boardClass, Menu menu, User user, Game game, Database database, Level level) {
        Cell cellInstance = new Cell();
        int oldPosition = currentCasePlayers;
        tools.setTimeout(1);
        System.out.println("Vous avez lancé le dé et obtenu : " + Colors.DICE_MAGENTA + steps + Colors.RESET + " !");
        int[] boardInt = boardClass.getBoard();
        int newPosition = currentCasePlayers + steps;
        boardInt[currentCasePlayers] = 0;
        setNewCurrentCasePlayers(newPosition);
        setNewBoard(boardInt);
        if (boardClass.isSurvival) {
            database.modSurvival(user, newPosition);
        }
        if (debugBoardDisplay) {
            boardClass.displayBoard();
        }
        if (debugBoardIndexCell) {
            int indexDebug = boardClass.getCurrentCasePlayers();
            int valueDebug = boardInt[indexDebug];
            System.out.println("Le joueur est actuellement à l'index de la case " + (indexDebug) + " | La case a comme valeur : " + valueDebug + " | Il étais a la case : " + oldPosition + " pour un dé de " + steps);
        }
        try {
            cellInstance.verifyCase(newPosition, boardInt, boardClass, menu, user, game, database, level);
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
    public void outOfBoard(int positionNow, Board boardClass, int[] boardInt, String type) {
        int calculReturnGame;
        int difference;
        int who;
        if (type.equals("normal")) {
            who = caseEnd;
        } else {
            who = boardClass.caseBoardSurvival;
        }
        if (positionNow >= who) {
            difference = (positionNow - who);
            calculReturnGame = who - difference;
            boardInt[calculReturnGame] = valuePlayer;
            boardClass.setNewCurrentCasePlayers(calculReturnGame);
            boardClass.setNewBoard(boardInt);
            System.out.println("Vous avez essayé de sortir du plateau de jeu ! Vous avez été renvoyé à la case " + calculReturnGame + ".");
        }
    }

    /**
     * Display in the console at the player’s request when choosing an action the game board
     */
    public void displayBoard() {
        int[] board = getBoard();
        String[] boardStr = new String[board.length];
        for (int i = 0; i < board.length; i++) {
            boardStr[i] = String.valueOf(board[i]);
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                boardStr[i] = Colors.NOTHING_BLUE + "NT" + Colors.RESET;
            } else if (board[i] == valuePlayer) {
                boardStr[i] = Colors.PLAYER_BRIYEL + "YOU" + Colors.RESET;
            } else if (board[i] == 4) {
                boardStr[i] = Colors.MERCHANTS_BRICY + "MCH" + Colors.RESET;
            } else if (board[i] == 5) {
                boardStr[i] = Colors.HOSTEL_BRIBLU + "HOS" + Colors.RESET;
            } else if (board[i] == 6) {
                boardStr[i] = Colors.BLACKSMITH_BRIYEL + "BLS" + Colors.RESET;
            } else if (board[i] == 20 || board[i] == 21 || board[i] == 22 || board[i] == 23 || board[i] == 24) {
                boardStr[i] = Colors.ENEMY_RED + "ENEMY" + Colors.RESET;
            } else if (board[i] >= 300) {
                boardStr[i] = Colors.BOX_GREEN + "BOX" + Colors.RESET;
            } else if (board[i] == valueCaseEnd) {
                boardStr[i] = Colors.END_PURPLE + "END" + Colors.RESET;
            }
        }
        System.out.println("Game board display :");
        System.out.println(Colors.PLAYER_BRIYEL + "'YOU' : Your position on the board" + Colors.RESET);
        System.out.println(Colors.ENEMY_RED + "'ENEMY' : Enemy position on the board" + Colors.RESET);
        System.out.println(Colors.BOX_GREEN + "'BOX' : Box position on the board" + Colors.RESET);
        System.out.println(Colors.HOSTEL_BRIBLU + "'HOS' : Hostel position on the board" + Colors.RESET);
        System.out.println(Colors.MERCHANTS_BRICY + "'MCH' : Merchants position on the board" + Colors.RESET);
        System.out.println(Colors.BLACKSMITH_BRIYEL + "'BLS' : Blacksmith position on the board" + Colors.RESET);
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
     * @param displayMessage
     * @param database
     * @param user
     */
    public void setNewCellPlayer(int[] boardInt, int newPosition, boolean displayMessage, Database database, User user) {
        if (newPosition <= 0) {
            newPosition = 0;
        }
        setNewCurrentCasePlayers(newPosition);
        boardInt[newPosition] = valuePlayer;
        setNewBoard(boardInt);
        database.updateBoard(boardInt, user);
        if (displayMessage) {
            System.out.println("Vous êtes maintenant à la case " + Colors.CELL_BRIGHTMAGENTA + newPosition + Colors.RESET + ".");
        }
    }

    /**
     * Adds enemies and random objects to the game board in fixed positions
     *
     * @param cell
     * @param random
     * @param valueBox
     * @param type
     * @param difficulty
     */
    public void setRandomCellBoard(int[] cell, Random random, int[] valueBox, String type, String difficulty) {
        int valueCell = chooseLevelDifficulty(cell, type, difficulty);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < valueCell; j++) {
                if (i == cell[j]) {
                    int index = random.nextInt((valueBox.length - valueBox.length), valueBox.length);
                    int value = valueBox[index];
                    board[i] = value;
                    break;
                }
            }
        }
    }

    /**
     * Create the random boxes for the survival mod
     * @param random
     */
    public void setRandomCellBoardSurvival(Random random) {
        for (int i = 0; i < board.length; i++) {
            int index = randomChooseCellInfinity(random.nextInt(1, 7), random);
            board[i] = index;
        }
    }

    /**
     * Recover according to the random value the corresponding IDs for the survival mod
     * @param value
     * @param random
     * @return
     */
    public int randomChooseCellInfinity(int value, Random random) {
        int valueCell = 0;
        if (!debugBoardSurvival) {
            switch (value) {
                case 2 -> {
                    int valueEnemy = random.nextInt(0, enemyValue.length);
                    valueCell = enemyValue[valueEnemy];
                }
                case 3 -> {
                    int valueBox = random.nextInt(0, boxValue.length);
                    valueCell = boxValue[valueBox];
                }
                case 4 -> {
                    valueCell = 4;
                }
                case 5 -> {
                    valueCell = 5;
                }
                case 6 -> {
                    valueCell = 6;
                }
            }
        }
        return valueCell;
    }

    /**
     * The player chooses their level of difficulty
     *
     * @param cell
     * @param type
     * @param difficulty
     * @return
     */
    public int chooseLevelDifficulty(int[] cell, String type, String difficulty) {
        int value = cell.length;
        if (difficulty.equals("easy")) {
            if (type.equals("enemy")) {
                return value / 2;
            } else if (type.equals("box")) {
                return value;
            }
        } else if (difficulty.equals("medium")) {
            if (type.equals("enemy")) {
                return value;
            } else if (type.equals("box")) {
                return value;
            }
        } else if (difficulty.equals("hard")) {
            if (type.equals("enemy")) {
                return value;
            } else if (type.equals("box")) {
                return value / 2;
            }
        }
        return value;
    }
}