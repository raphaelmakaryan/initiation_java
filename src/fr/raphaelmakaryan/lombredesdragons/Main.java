package fr.raphaelmakaryan.lombredesdragons;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.game.Game;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Lancement du jeu
        Database database = new Database();
        Connection connection = database.connectDatabase();
        System.out.printf(Colors.RUNGAME_CYAN + " Lancement du jeu : L'ombre des dragons !\n" + Colors.RESET);
        new Game().preStart(connection, database);
    }
}