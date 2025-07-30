package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Game.Dice;
import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;

public class Game {
    Menu menuGame = new Menu();
    User user = new User();

    public void start(Menu menu, User user) {
        menu.startMenu(user);
    }

    public void creationPlayer(Menu menu, User user) {
        menu.creationPlayerMenu(user);
    }

    public void afterCreationPlayer(Menu menu) {
        boolean startGame = menu.afterCreationPlayerMenu();
        if (startGame) {
            startGame();
        }
    }

    public void startGame() {
        System.out.println("Le jeu commence !");
        System.out.println("Vous êtes sur la case de départ");
        gameProgress();
    }

    public void main() {
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame);
    }

    public static void gameProgress() {
        Menu menuGame = new Menu();
        Board board = new Board();
        Dice dice = new Dice();
        int diceValue = dice.diceRoll();
        System.out.println("Vous avez lancé le dé et obtenu : " + diceValue);
        board.movePlayer(diceValue, board, menuGame);
    }
}
