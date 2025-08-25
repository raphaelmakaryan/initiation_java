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
        System.out.printf(Colors.RUNGAME_CYAN + " Lancement du jeu : L'Ombre des Dragons !\n" + Colors.RESET);
        start(menuGame, user);
        creationPlayer(menuGame, user, database);
        afterCreationPlayer(menuGame, user, database, game);
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
     * @param database
     * @throws SQLException
     */
    public void creationPlayer(Menu menu, User user, Database database) throws SQLException {
        menu.creationPlayerMenu(user, database);
    }

    /**
     * Method to launch the game after creating the player
     *
     * @param menu
     * @param user
     * @param database
     * @param game
     * @throws SQLException
     */
    public void afterCreationPlayer(Menu menu, User user, Database database, Game game) throws SQLException {
        menu.afterCreationPlayerMenu(user, database, game);
    }

    /**
     * Actual launch of the game after the player has decided to start the adventure
     *
     * @param database
     * @param game
     * @param difficulty
     * @throws SQLException
     */
    public void playerWantPlay(Database database, Game game, String difficulty) throws SQLException {
        Board board = new Board(difficulty);
        Level level = new Level();
        database.addBoard(board, user);
        if (board.isSurvival) {
            database.addPlayerModSurvival(user, 0);
        }
        startGame(board, user, game, database, level);
    }

    /**
     * Real launch of the game
     *
     * @param board
     * @param user
     * @param game
     * @param database
     * @param level
     */
    public void startGame(Board board, User user, Game game, Database database, Level level) {
        playTurn(board, user, game, database, level);
    }

    /**
     * Logic of the game
     *
     * @param board
     * @param user
     * @param game
     * @param database
     * @param level
     */
    public void playTurn(Board board, User user, Game game, Database database, Level level) {
        Dice dice = new Dice();
        int diceValue = dice.dice6();
        board.movePlayer(diceValue, board, menuGame, user, game, database, level);
    }
}