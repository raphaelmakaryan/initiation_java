package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.game.User;

import java.sql.*;
import java.util.Arrays;

public class Database {

    public Connection connectDatabase() throws SQLException {
        try {
            String urlDB = "jdbc:mysql://localhost:3306/lombredesdragons";
            Connection con = DriverManager.getConnection(urlDB, "root", "");
            return con;
        } catch (SQLException e) {
            System.out.println("Connection à la base de données impossible");
        }
        return null;
    }

    public void getHeroes(Connection connection) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultats = stmt.executeQuery("SELECT * FROM `Character`");
            ResultSetMetaData rsmd = resultats.getMetaData();
            int nbCols = rsmd.getColumnCount();
            boolean encore = resultats.next();

            System.out.println("ID | Type | Nom | LifePoints | Strength | OffensiveEquipment | DefensiveEquipment");
            System.out.println("---+------+----+------------+----------+-------------------+------------------");

            while (encore) {
                for (int i = 1; i <= nbCols; i++) {
                    if (i > 1) {
                        System.out.print(" | ");
                    }
                    System.out.print(resultats.getString(i));
                }
                System.out.println();
                encore = resultats.next();
            }

        } catch (
                SQLException e) {
            System.out.println("Anomalie lors de l'execution de la requête");
        }
    }

    public void createHero(Connection connection, User user, Database database) throws SQLException {
        String query = "INSERT INTO `Character` (`Type`, `Name`, `LifePoints`, `Strength`, `OffensiveEquipment`, `DefensiveEquipment`) VALUES (?, ?, ?, ?, NULL, NULL)";
        Character character = user.getCharacterPlayer();
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, character.type);
            pstmt.setString(2, character.name);
            pstmt.setInt(3, character.lifePoints);
            pstmt.setInt(4, character.attackLevel);
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int insertedId = generatedKeys.getInt(1);
                    user.setIDPlayerDatabase(insertedId);
                }
            }
        }
    }

    public void editHero(Connection connection, String oldName, String newName, User user) throws SQLException {
        String query = "UPDATE `Character` SET `Name` = ? WHERE ID = ? AND `Name` = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, user.getIDPlayerDatabase());
            pstmt.setString(3, oldName);
            pstmt.executeUpdate();
            System.out.println("Nom du personnage modifié avec succès !");
        } catch (SQLException e) {
            System.out.println("Anomalie lors de l'execution de la requête");
        }
    }

    public void changeLifePoints(Connection connection, Character character) throws SQLException {
        /*
        String query = "UPDATE `Character` SET `lifePoints` = ? WHERE `name` = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, lifePoints);
            pstmt.setString(2, character.getName());
            pstmt.executeUpdate();
        }
        */
    }

    public void addBoard(Connection connection, Board boardClass, User user) throws SQLException {
        String query = "INSERT INTO `Board` (`IDCharacter`, `Board`) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, user.getIDPlayerDatabase());
            pstmt.setString(2, Arrays.toString(boardClass.getBoard()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Une anomalie est survenue lors de l'insertion du plateau de jeu dans la base de données.");
        }
    }
}
