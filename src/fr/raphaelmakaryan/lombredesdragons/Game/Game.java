package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;

import java.util.Arrays;
import java.util.Scanner;

import static fr.raphaelmakaryan.lombredesdragons.Tools.Tools.isACharacter;
import static fr.raphaelmakaryan.lombredesdragons.Tools.Tools.itIsString;

public class Game {

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
        Board board = new Board();
        System.out.println("Le jeu commence !");
        System.out.println("Le plateau de jeu a " + board.getCases() + " cases.");
        System.out.println(Arrays.toString(board.getBoard()));
    }

    public void main() {
        Menu menuGame = new Menu();
        User user = new User();
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame);
    }
}
