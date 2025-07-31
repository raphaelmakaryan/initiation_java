package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;
import fr.raphaelmakaryan.lombredesdragons.verifications.Enemies;

import static fr.raphaelmakaryan.lombredesdragons.verifications.EndGame.endGame;
import static fr.raphaelmakaryan.lombredesdragons.tools.Tools.*;

import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Tools toolsMain = new Tools();
    Scanner clavier = new Scanner(System.in);
    public boolean isAdmin = true;

    // Menu a la creation du personnage
    public void startMenu(User user) {
        String typeUser;
        String nameUser;
        String type;
        boolean verifType;
        String name;

        System.out.println(Colors.RESET + "Quel type de personnage souhaitez-vous créer ? (Magicien / Guerrier)");
        typeUser = clavier.next();
        type = itIsString(typeUser, true);
        verifType = Boolean.parseBoolean(String.valueOf(isACharacter(type)));
        if (verifType) {
            user.setTypeChoice(type);
        }

        System.out.println("Proposez lui un petit nom !");
        nameUser = clavier.next();
        name = itIsString(nameUser, true);
        user.setName(name);
        toolsMain.clearLine();
    }

    // Menu a la validation du creation du personnage
    public void creationPlayerMenu(User user, Connection connection, Database database) throws SQLException {
        user.createCharacter(user, connection, database);
        System.out.println(Colors.START_WHITE + "Votre personnage a été créé avec succès !" + Colors.RESET);
        toolsMain.clearLine();
    }

    // Choix apres la creation du personnage
    public boolean afterCreationPlayerMenu(User user, Menu menu, Database database, Connection connection) throws SQLException {
        int choiceUser;
        int choice;

        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Démarrer une nouvelle partie");
        System.out.println("2. Afficher toutes les infos de votre personnage");
        System.out.println("3. Modifier ses informations");
        System.out.println("4. Quitter le jeu");
        if (isAdmin) {
            System.out.println("5. Affichez tout les héros de la base de données");
        }
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            return true;
        } else if (choice == 2) {
            displayInformationCharacter(user);
            toolsMain.clearLine();
            menu.afterCreationPlayerMenu(user, menu, database, connection);
        } else if (choice == 3) {
            displayModifyInformationCharacter(user, database, connection);
            toolsMain.clearLine();
            menu.afterCreationPlayerMenu(user, menu, database, connection);
        } else if (choice == 4) {
            endGame("exit", this);
            return false;
        } else if (choice == 5 && isAdmin) {
            displayAdminGetHeros(database, connection);
            toolsMain.clearLine();
            menu.afterCreationPlayerMenu(user, menu, database, connection);
        }
        return false;
    }

    /**
     * Method to manage user choices during game progress
     *
     * @param boardClass
     */
    // Choix pendant la progression du jeu
    public void choiceGameProgress(Board boardClass) {
        int choiceUser;
        int choice;
        Game Game = new Game();

        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Lancer le dé");
        System.out.println("2. Voir ma position actuelle");
        System.out.println("3. Quitter le jeu");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();

        if (choice == 1) {
            Game.playTurn(boardClass);
        } else if (choice == 2) {
            boardClass.displayBoard();
            toolsMain.clearLine();
            choiceGameProgress(boardClass);
        } else if (choice == 3) {
            endGame("exit", this);
        } else {
            toolsMain.verificationChoiceNotWhile("choiceGameProgress", this, boardClass);
        }
    }

    /**
     * Method to handle user selection at the end of the game
     */
    // Choix a la fin du jeu si le joueur est aller au bout du plateau
    public void endGameCase() {
        int choiceUser;
        int choice;
        System.out.println(Colors.END_PURPLE + "Vous avez gagné !");
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Quitter le jeu");
        System.out.println("2. Recommencer une nouvelle partie");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();
        if (choice == 1) {
            EndGame.endGame("exit", this);
        } else if (choice == 2) {
            System.out.println("Recommençons une nouvelle partie !");
        } else {
            toolsMain.verificationChoiceNotWhile("endGameCase", this, (Object) null);
        }
    }

    public void enemiesCell(Board boardClass, Menu menu) {
        Enemies enemy = new Enemies();
        int choiceUser;
        int choice;
        toolsMain.clearLine();
        System.out.println(Colors.ENEMY_RED + "Vous etes tomber sur un ennemi !" + Colors.RESET);
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Se battre contre l'ennemi");
        System.out.println("2. Fuir l'ennemi");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();
        if (choice == 1) {
            enemy.chooseFight(menu, boardClass);
        } else if (choice == 2) {
            choiceGameProgress(boardClass);
        } else {
            toolsMain.verificationChoiceNotWhile("enemiesCell", this, (Object) null);
        }
    }

    public void boxCell(Board boardClass) {
        int choiceUser;
        int choice;
        toolsMain.clearLine();
        System.out.println(Colors.BOX_GREEN + "Vous etes tomber sur une boîte !" + Colors.RESET);
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. L'ouvrir");
        System.out.println("2. Laisser la boîte");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();
        if (choice == 1) {
            toolsMain.maintenance("commande");
        } else if (choice == 2) {
            choiceGameProgress(boardClass);
        } else {
            toolsMain.verificationChoiceNotWhile("boxCell", this, (Object) null);
        }
    }

    public void displayInformationCharacter(User user) {
        Character character = user.getCharacterPlayer();
        toolsMain.clearLine();
        System.out.println("Voici les informations de votre personnage :");
        System.out.println("Nom : " + character.getName());
        System.out.println("Type : " + character.getType());
        System.out.println("Points de vie : " + character.getLifePoints());
        System.out.println("Niveau d'attaque : " + character.getAttackLevel());
        System.out.println("Equipement offensif : " + character.getOffensiveEquipment());
        System.out.println("Equipement defensif : " + character.getDefensiveEquipment());
        toolsMain.clearLine();
    }

    public void displayModifyInformationCharacter(User user, Database database, Connection connection) throws SQLException {
        String nameUserChoice;
        String newName;
        Character character = user.getCharacterPlayer();
        String oldName = character.getName();
        System.out.println("Donnez lui son nouveau nom :");
        nameUserChoice = clavier.next();
        newName = itIsString(nameUserChoice, true);
        user.setName(newName);
        character.setName(newName);
        database.editHero(connection, oldName, newName, user);
        toolsMain.clearLine();
    }

    public void displayEnemyFight(Enemie enemies) {
        toolsMain.clearLine();
        System.out.println(Colors.ENEMY_RED + "Vous etes en train de vous battre contre un " + enemies.getName() + " !" + Colors.RESET);
        System.out.println("Vous l'attaquez en premier !");
        toolsMain.clearLine();
    }

    public void displayAdminGetHeros(Database database, Connection connection) throws SQLException {
        database.getHeroes(connection);
    }

}
