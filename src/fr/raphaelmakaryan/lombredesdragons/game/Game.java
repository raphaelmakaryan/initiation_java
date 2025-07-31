package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.sql.Connection;
import java.sql.SQLException;

public class Game extends Admin {
    Menu menuGame = new Menu();
    User user = new User();

    // Méthode principale pour la creation du joueur
    public void preStart(Connection connection, Database database, Game game) throws SQLException {
        start(menuGame, user);
        creationPlayer(menuGame, user, connection, database);
        afterCreationPlayer(menuGame, user, database, connection, game);
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
    public void creationPlayer(Menu menu, User user, Connection connection, Database database) throws SQLException {
        menu.creationPlayerMenu(user, connection, database);
    }

    /**
     * Method to launch the game after creating the player
     *
     * @param menu The menu to launch the game
     */
    // Après la création du joueur, on lance le systeme du jeu
    public void afterCreationPlayer(Menu menu, User user, Database database, Connection connection, Game game) throws SQLException {
        Tools tools = new Tools();
        boolean startGame = menu.afterCreationPlayerMenu(user, menu, database, connection);
        tools.verificationChoiceWhile(startGame, true, menu, "afterCreationPlayerMenu");
        Board board = new Board();
        database.addBoard(connection, board, user);
        startGame(board, user, game);
    }

    /**
     * Method to start the game
     *
     * @param board The game board
     */
    // Démarrage du jeu
    public void startGame(Board board, User user, Game game) {
        playTurn(board, user, game);
    }

    // Méthode pour gérer la progression du jeu
    public void playTurn(Board board, User user, Game game) {
        Dice dice = new Dice();
        int diceValue = dice.diceRoll();
        board.movePlayer(diceValue, board, menuGame, user, game);
    }
}