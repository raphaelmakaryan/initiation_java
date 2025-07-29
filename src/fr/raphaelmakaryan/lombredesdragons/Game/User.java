package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Characters.Warrior;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Characters.Wizard;

import java.util.Objects;

public class User {

    private String name;
    private String typeChoice;
    private Character characterPlayer;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void createCharacter() {
        if (Objects.equals(this.typeChoice, "magicien")) {
            characterPlayer = new Wizard(this.name);
        } else if (Objects.equals(this.typeChoice, "guerrier")) {
            characterPlayer = new Warrior(this.name);
        }
    }

    public Character getCharacter() {
        return characterPlayer;
    }

    public void setTypeChoice(String typeChoice) {
        this.typeChoice = typeChoice;
    }

    public String getTypeChoice() {
        return this.typeChoice;
    }

}