package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class Admin {
    public boolean debugViewHeroMenu = false;

    public boolean debugBoard = false;
    public int valueDebugBoard = 10;
    public boolean debugBoardDisplay = false;
    public boolean debugBoardIndexCell = false;
    public boolean debugBoardCellInt = false;

    public boolean debugDice = false;
    public int valueDebugDice = 1;
    public boolean debugDiceCriticalLoose = false;
    public boolean debugDiceCriticalSuccess = false;

    public boolean usingDatabase = false;

    public boolean debugEnemiesCell = false;

    public int[] ennemisCell = {45, 52, 56, 62, 10, 20, 25, 32, 35, 36, 37, 40, 44, 47, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
    public int[] boxCell = {2, 11, 5, 22, 38, 19, 26, 42, 53, 1, 4, 8, 17, 23, 48, 49, 7, 13, 31, 33, 39, 43, 28, 41};
    public int[] enemyValue = {20, 21, 22, 23, 24};
    public int[] boxValue = {300, 301, 310, 311, 312, 320, 321, 322, 330};
    public int[][] levelDifficulty = {{0, 0}, {5, 1}, {10, 2}, {15, 3}, {20, 4}, {25, 5}, {30, 6}, {35, 7}, {40, 8}, {45, 9}, {50, 10}, {55, 11}, {60, 12}, {65, 13}, {70, 14}, {75, 15}, {80, 16}, {85, 17}, {90, 18}, {95, 19}, {100, 20}, {105, 21}, {110, 22}, {115, 23}, {120, 24}};

    public int caseEnd = 64;
    public int caseStart = 0;

    public int debugForceBox = 0;
}