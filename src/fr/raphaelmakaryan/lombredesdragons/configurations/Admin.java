package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class Admin {
    public int debugLifeHealth = 0;
    public int debugMoney = 0;
    public int debugLifeTimeWeapon = 0;

    public boolean debugViewHeroMenu = false;

    public boolean debugBoard = false;
    public int valueDebugBoard = 0;
    public boolean debugBoardDisplay = false;
    public boolean debugBoardIndexCell = false;
    public boolean debugBoardCellInt = false;
    public boolean debugBoardSurvival = false;

    public boolean debugDice = false;
    public int valueDebugDice = 100;
    public boolean debugDiceCriticalLoose = false;
    public boolean debugDiceCriticalSuccess = false;

    public boolean usingDatabase = true;

    public boolean debugEnemiesCell = false;

    public int[] ennemisCell = {3, 6, 9, 10, 12, 15, 18, 20, 21, 24, 25, 27, 30, 32, 35, 36, 37, 40, 44, 45, 47, 52, 56, 62};
    public int[] enemyValue = {20, 21, 22, 23, 24};
    public int[] boxCell = {1, 2, 4, 5, 7, 8, 11, 13, 17, 19, 22, 23, 26, 28, 31, 33, 38, 39, 41, 42, 43, 48, 49, 53};
    public int[] boxValue = {300, 301, 310, 311, 312, 320, 321, 322, 330};
    public int[] merchantsCell = {14, 61};
    public int[] merchantsValue = {4, 4};
    public int[] hostelCell = {16, 54, 60};
    public int[] hostelValue = {5, 5, 5};
    public int[] blacksmithCell = {29, 59};
    public int[] blacksmithValue = {6, 6, 6};
    public int[][] levelDifficulty = {{0, 0}, {5, 1}, {10, 2}, {15, 3}, {20, 4}, {25, 5}, {30, 6}, {35, 7}, {40, 8}, {45, 9}, {50, 10}, {55, 11}, {60, 12}, {65, 13}, {70, 14}, {75, 15}, {80, 16}, {85, 17}, {90, 18}, {95, 19}, {100, 20}, {105, 21}, {110, 22}, {115, 23}, {120, 24}};
    // Cases vide : {34, 46, 50, 51, 55, 57, 58};

    public int caseEnd = 64;
    public int valueCaseEnd = 7;
    public int valuePlayer = 1;
    public int caseStart = 0;

    public int debugForceBox = 0;

    public int priceHostel = 10;
}