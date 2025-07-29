package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;

public class Game {
    Menu menuGame = new Menu();
    User user = new User();
    Board board = new Board();

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
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame);
    }

    public void gameProgress() {

    }
}
