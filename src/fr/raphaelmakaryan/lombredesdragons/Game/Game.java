package fr.raphaelmakaryan.lombredesdragons.Game;

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
        System.out.println("Le jeu commence !");
    }

    public void main() {
        Menu menuGame = new Menu();
        User user = new User();
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame);
    }
}
