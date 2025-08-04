package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class Game extends Admin {
    Menu menuGame = new Menu();
    User user = new User();

    /**
     * Logic of after game launch
     *
     * @param game
     * @throws SQLException
     */
    public void preStart(Game game) throws SQLException {
        Database database = new Database();
        Connection connection = database.connectDatabase();
        System.out.printf(Colors.RUNGAME_CYAN + " Lancement du jeu : L'Ombre des Dragons !\n" + Colors.RESET);
        start(menuGame, user);
        creationPlayer(menuGame, user, connection, database);
        afterCreationPlayer(menuGame, user, database, connection, game);
    }

    /**
     * Game launch logic
     *
     * @param menu
     * @param user
     */
    public void start(Menu menu, User user) {
        menu.startMenu(user);
    }

    /**
     * The player creates their character
     *
     * @param menu
     * @param user
     * @param connection
     * @param database
     * @throws SQLException
     */
    public void creationPlayer(Menu menu, User user, Connection connection, Database database) throws SQLException {
        menu.creationPlayerMenu(user, connection, database);
    }

    /**
     * Method to launch the game after creating the player
     *
     * @param menu
     * @param user
     * @param database
     * @param connection
     * @param game
     * @throws SQLException
     */
    public void afterCreationPlayer(Menu menu, User user, Database database, Connection connection, Game game) throws SQLException {
        menu.afterCreationPlayerMenu(user, menu, database, connection, game);
    }

    /**
     * Actual launch of the game after the player has decided to start the adventure
     *
     * @param connection
     * @param database
     * @param game
     * @throws SQLException
     */
    public void playerWantPlay(Connection connection, Database database, Game game) throws SQLException {
        Board board = new Board();
        database.addBoard(connection, board, user);
        startGame(board, user, game, connection, database);
    }

    /**
     * Real launch of the game
     *
     * @param board
     * @param user
     * @param game
     */
    public void startGame(Board board, User user, Game game, Connection connection, Database database) {
        playTurn(board, user, game, connection, database);
    }

    /**
     * Logic of the game
     *
     * @param board
     * @param user
     * @param game
     */
    public void playTurn(Board board, User user, Game game, Connection connection, Database database) {
        Dice dice = new Dice();
        int diceValue = dice.dice6();
        board.movePlayer(diceValue, board, menuGame, user, game, connection, database);
    }
}