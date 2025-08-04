package fr.raphaelmakaryan.lombredesdragons;

import fr.raphaelmakaryan.lombredesdragons.game.Game;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Game game = new Game();
        game.preStart(game);
    }
}