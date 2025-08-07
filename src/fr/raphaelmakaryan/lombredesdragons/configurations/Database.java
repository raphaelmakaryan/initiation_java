package fr.raphaelmakaryan.lombredesdragons.configurations;

import fr.raphaelmakaryan.lombredesdragons.game.User;

import java.sql.*;
import java.util.Arrays;

public class Database extends Admin {

    /**
     * Connection to the database
     *
     * @return
     * @throws SQLException
     */
    public Connection connectDatabase() throws SQLException {
        if (usingDatabase) {
            try {
                String urlDB = "jdbc:mysql://localhost:3306/lombredesdragons";
                Connection con = DriverManager.getConnection(urlDB, "root", "");
                return con;
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de la connexion dans la base de données.");
                System.out.println("N'en prenez pas compte, vous pouvez continuer à jouer.");
            }
        }
        return null;
    }

    /**
     * Find all the database heroes for the admin
     *
     * @param connection
     * @throws SQLException
     */
    public void getHeroes(Connection connection) throws SQLException {
        if (usingDatabase) {
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
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de la récuperation des personnages dans la base de données.");
            }
        }
    }

    /**
     * Create a hero in the database
     *
     * @param connection
     * @param user
     * @throws SQLException
     */
    public void createHero(Connection connection, User user) throws SQLException {
        if (usingDatabase) {
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
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion de votre personnage dans la base de données.");
                System.out.println("N'en prenez pas compte, vous pouvez continuer à jouer sans sauvegarder votre personnage.");
            }
        }
    }

    /**
     * Edit a hero in the database
     *
     * @param connection
     * @param oldName
     * @param newName
     * @param user
     * @throws SQLException
     */
    public void editHero(Connection connection, String oldName, String newName, User user) {
        if (usingDatabase) {
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
    }

    /**
     * Edit a life a hero in the database
     *
     * @param connection
     * @param user
     * @param lifePoints
     */
    public void changeLifePoints(Connection connection, User user, int lifePoints) {
        if (usingDatabase) {
            Character character = user.getCharacterPlayer();
            int ID = user.getIDPlayerDatabase();
            String query = "UPDATE `Character` SET `lifePoints` = ? WHERE `name` = ? AND ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, lifePoints);
                pstmt.setString(2, character.getName());
                pstmt.setInt(3, ID);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion du plateau de jeu dans la base de données.");
                System.out.println("N'en prenez pas compte, vous pouvez continuer à jouer sans sauvegarder votre plateau de jeu.");
            }
        }
    }

    /**
     * Add a baord in the database
     *
     * @param connection
     * @param boardClass
     * @param user
     * @throws SQLException
     */
    public void addBoard(Connection connection, Board boardClass, User user) throws SQLException {
        if (usingDatabase && !boardClass.isSurvival) {
            String query = "INSERT INTO `Board` (`IDCharacter`, `Board`) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, user.getIDPlayerDatabase());
                pstmt.setString(2, Arrays.toString(boardClass.getBoard()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion du plateau de jeu dans la base de données.");
                System.out.println("N'en prenez pas compte, vous pouvez continuer à jouer sans sauvegarder votre plateau de jeu.");
            }
        }
    }

    /**
     * Update the game board
     *
     * @param connection
     * @param boardInt
     * @param user
     */
    public void updateBoard(Connection connection, int[] boardInt, User user) {
        if (usingDatabase) {
            int id = user.getIDPlayerDatabase();
            String query = "UPDATE `Board` SET `Board`=? WHERE `idCharacter`= ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, Arrays.toString(boardInt));
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de la mise a jour du plateau de jeu dans la base de données.");
                System.out.println("N'en prenez pas compte, vous pouvez continuer à jouer sans sauvegarder votre plateau de jeu.");
            }
        }
    }

    /**
     * Adds a player’s defense weapon in the database
     *
     * @param connection
     * @param user
     * @param defensiveEquipment
     */
    public void addDefensiveEquipment(Connection connection, User user, DefensiveEquipment defensiveEquipment) {
        if (usingDatabase) {
            Character character = user.getCharacterPlayer();
            if (defensiveEquipment != null) {
                int ID = defensiveEquipment.getIdObject();
                String query = "UPDATE `Character` SET `DefensiveEquipment` = ? WHERE `name` = ? AND ID = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, ID);
                    pstmt.setString(2, character.getName());
                    pstmt.setInt(3, user.getIDPlayerDatabase());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Une anomalie est survenue lors de l'insertion/mise a jour de l'ajout d'une arme de defense dans la base de données.");
                }
            }
            String query = "UPDATE `Character` SET `DefensiveEquipment` = ? WHERE `name` = ? AND ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setNull(1, 0);
                pstmt.setString(2, character.getName());
                pstmt.setInt(3, user.getIDPlayerDatabase());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion/mise a jour de l'ajout d'une arme de defense dans la base de données.");
            }
        }
    }

    /**
     * Adds a player’s attack weapon in the database
     *
     * @param connection
     * @param user
     * @param offensiveEquipment
     */
    public void addOffensiveEquipment(Connection connection, User user, OffensiveEquipment offensiveEquipment) {
        if (usingDatabase) {
            Character character = user.getCharacterPlayer();
            if (offensiveEquipment != null) {
                int ID = offensiveEquipment.getIdObject();
                int attackPlayer = character.getAttackLevel();
                int attackObject = offensiveEquipment.getLevelAttack();
                int newAttackPlayer = attackPlayer + attackObject;
                String query = "UPDATE `Character` SET `OffensiveEquipment` = ?, `Strength` = ? WHERE `name` = ? AND ID = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setInt(1, ID);
                    pstmt.setInt(2, newAttackPlayer);
                    pstmt.setString(3, character.getName());
                    pstmt.setInt(4, user.getIDPlayerDatabase());
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Une anomalie est survenue lors de l'insertion/mise a jour de l'ajout d'une arme d'attaque dans la base de données.");
                }
            }
            String query = "UPDATE `Character` SET `OffensiveEquipment` = ? WHERE `name` = ? AND ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setNull(1, 0);
                pstmt.setString(3, character.getName());
                pstmt.setInt(4, user.getIDPlayerDatabase());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion/mise a jour de l'ajout d'une arme d'attaque dans la base de données.");
            }
        }
    }

    /**
     * Database update function for the survival mod
     * @param user
     * @param connection
     * @param position
     */
    public void modSurvival(User user, Connection connection, int position) {
        if (usingDatabase) {
            String query = "UPDATE `Survival` SET `position` = ? WHERE idUser = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, position);
                pstmt.setInt(2, user.getIDPlayerDatabase());
                pstmt.executeUpdate();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Player addition function for the survival mod
     * @param user
     * @param connection
     * @param position
     */
    public void addPlayerModSurvival(User user, Connection connection, int position) {
        if (usingDatabase) {
            String query = "INSERT INTO `Survival`(`idUser`, `position`) VALUES (?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, user.getIDPlayerDatabase());
                pstmt.setInt(2, position);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Une anomalie est survenue lors de l'insertion de votre joueur dans la base de données.");
            }
        }
    }
}
