package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.Configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;

import static fr.raphaelmakaryan.lombredesdragons.Verifications.EndGame.endGame;
import static fr.raphaelmakaryan.lombredesdragons.Tools.Tools.*;

import fr.raphaelmakaryan.lombredesdragons.Verifications.EndGame;

import java.util.Scanner;

public class Menu {
    Tools toolsMain = new Tools();
    Scanner clavier = new Scanner(System.in);

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
    public void creationPlayerMenu(User user) {
        user.createCharacter();
        System.out.println(Colors.START_WHITE + "Votre personnage a été créé avec succès !" + Colors.RESET);
        toolsMain.clearLine();
    }

    // Choix apres la creation du personnage
    public boolean afterCreationPlayerMenu() {
        int choiceUser;
        int choice;

        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Démarrer une nouvelle partie");
        System.out.println("2. Afficher toutes les infos de votre personnage");
        System.out.println("3. Modifier ses informations");
        System.out.println("4. Quitter le jeu");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            return true;
        } else if (choice == 3 || choice == 2) {
            toolsMain.maintenance("commande");
            return false;
        } else if (choice == 4) {
            endGame("exit", this);
            return false;
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
            Game.gameProgress(boardClass);
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

    public void enemiesCell(Board boardClass) {
        int choiceUser;
        int choice;
        System.out.println(Colors.ENEMY_RED + "Vous etes tomber sur un ennemi !" + Colors.RESET);
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Se battre contre l'ennemi");
        System.out.println("2. Fuir l'ennemi");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();
        if (choice == 1) {
            toolsMain.maintenance("commande");
        } else if (choice == 2) {
            choiceGameProgress(boardClass);
        } else {
            toolsMain.verificationChoiceNotWhile("enemiesCell", this, (Object) null);
        }
    }

    public void boxCell(Board boardClass) {
        int choiceUser;
        int choice;
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
}
