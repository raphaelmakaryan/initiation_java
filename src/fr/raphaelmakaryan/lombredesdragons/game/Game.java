package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

public class Game {
    Menu menuGame = new Menu();
    User user = new User();

    // Méthode principale pour la creation du joueur
    public void main() {
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame, user);
    }

    /**
     * Constructor of the Game class
     * Initializes the menu and the user
     */
    // Lancement du jeu
    public void start(Menu menu, User user) {
        menu.startMenu(user);
    }

    /**
     * Method to start the game
     *
     * @param menu The menu to create the player
     * @param user The user
     */
    // Création du joueur
    public void creationPlayer(Menu menu, User user) {
        menu.creationPlayerMenu(user);
    }

    /**
     * Method to launch the game after creating the player
     *
     * @param menu The menu to launch the game
     */
    // Après la création du joueur, on lance le systeme du jeu
    public void afterCreationPlayer(Menu menu, User user) {
        Tools tools = new Tools();
        boolean startGame = menu.afterCreationPlayerMenu(user, menu);
        tools.verificationChoiceWhile(startGame, true, menu, "afterCreationPlayerMenu");
        Board board = new Board();
        startGame(board);
    }

    /**
     * Method to start the game
     *
     * @param board The game board
     */
    // Démarrage du jeu
    public void startGame(Board board) {
        playTurn(board);
    }

    // Méthode pour gérer la progression du jeu
    public void playTurn(Board board) {
        Dice dice = new Dice();
        int diceValue = dice.diceRoll();
        board.movePlayer(diceValue, board, menuGame);
    }
}