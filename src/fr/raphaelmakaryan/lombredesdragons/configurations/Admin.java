package fr.raphaelmakaryan.lombredesdragons.configurations;

public abstract class Admin {
    public boolean debugViewHeroMenu = false;

    public boolean debugBoard = false;
    public int valueDebugBoard = 10;
    public boolean debugBoardDisplay = false;
    public boolean debugBoardIndexCell = false;

    public boolean debugDice = false;
    public int valueDebugDice = 1;

    public boolean usingDatabase = false;

    public boolean debugEnemiesCell = false;

    public int[] ennemisCell = {45, 52, 56, 62, 10, 20, 25, 32, 35, 36, 37, 40, 44, 47, 3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
    public int[] boxCell = {2, 11, 5, 22, 38, 19, 26, 42, 53, 1, 4, 8, 17, 23, 48, 49, 7, 13, 31, 33, 39, 43, 28, 41};
    public int[] enemyValue = {20, 21, 22};
    public int[] boxValue = {300, 301, 310, 311, 320, 321};

    public int caseEnd = 64;
    public int caseStart = 0;
}