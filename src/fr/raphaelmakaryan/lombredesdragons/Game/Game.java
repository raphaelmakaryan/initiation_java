package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Exceptions.OutOfBoardException;
import fr.raphaelmakaryan.lombredesdragons.Game.Dice;
import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;

public class Game {
    Menu menuGame = new Menu();
    User user = new User();

    // Méthode principale pour la creation du joueur
    public void main() {
        start(menuGame, user);
        creationPlayer(menuGame, user);
        afterCreationPlayer(menuGame);
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
    public void afterCreationPlayer(Menu menu) {
        Tools tools = new Tools();
        boolean startGame = menu.afterCreationPlayerMenu();
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
        gameProgress(board);
    }

    // Méthode pour gérer la progression du jeu
    public void gameProgress(Board board) {
        Dice dice = new Dice();
        int diceValue = dice.diceRoll();
        System.out.println("Vous avez lancé le dé et obtenu : " + Colors.DICE_MAGENTA + diceValue + Colors.RESET + " !");
        board.movePlayer(diceValue, board, menuGame);
    }
}