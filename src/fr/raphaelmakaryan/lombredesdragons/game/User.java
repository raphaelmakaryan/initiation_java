package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.configurations.characters.Wizard;
import fr.raphaelmakaryan.lombredesdragons.configurations.characters.Warrior;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class User {

    private String name;
    private String typeChoice;
    private int IDPlayerDatabase;
    private Character characterPlayer;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Character getCharacterPlayer() {
        return this.characterPlayer;
    }

    public void createCharacter(User user, Connection connection, Database database) throws SQLException {
        if (Objects.equals(this.typeChoice.toLowerCase(), "magicien")) {
            characterPlayer = new Wizard(this.name);
        } else if (Objects.equals(this.typeChoice.toLowerCase(), "guerrier")) {
            characterPlayer = new Warrior(this.name);
        }
        database.createHero(connection, user);
    }

    public void setTypeChoice(String typeChoice) {
        this.typeChoice = typeChoice;
    }

    public int getIDPlayerDatabase() {
        return IDPlayerDatabase;
    }

    public void setIDPlayerDatabase(int IDPlayerDatabase) {
        this.IDPlayerDatabase = IDPlayerDatabase;
    }
}