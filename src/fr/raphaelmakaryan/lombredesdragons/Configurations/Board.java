package fr.raphaelmakaryan.lombredesdragons.Configurations;

public class Board {
    private int cases = 64;
    private int currentCasePlayers = 0;
    private int caseEnd = 64;
    private int caseStart = 0;
    private int[] board = new int[cases];

    public Board() {
        for (int i = 0; i < cases; i++) {
            board[i] = 0; // Initialize all cases to 0
        }
    }

    public int getCases() {
        return cases;
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
}
