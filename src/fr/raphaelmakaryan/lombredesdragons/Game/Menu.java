package fr.raphaelmakaryan.lombredesdragons.Game;

import fr.raphaelmakaryan.lombredesdragons.Tools.Tools;

import static fr.raphaelmakaryan.lombredesdragons.Verifications.EndGame.endGame;
import static fr.raphaelmakaryan.lombredesdragons.Tools.Tools.*;

import java.util.Scanner;

public class Menu {
    Tools toolsMain = new Tools();
    Scanner clavier = new Scanner(System.in);

    public void startMenu(User user) {
        String typeUser;
        String nameUser;
        String type;
        boolean verifType;
        String name;

        System.out.println("Quel type de personnage souhaitez-vous créer ? (Magicien / Guerrier)");
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
    }

    public void creationPlayerMenu(User user) {
        user.createCharacter();
        System.out.println("Votre personnage a été créé avec succès !");
    }

    public boolean afterCreationPlayerMenu() {
        int choiceUser;
        int choice;

        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Démarrer une nouvelle partie");
        System.out.println("2. Afficher toutes les infos de votre personnage");
        System.out.println("3. Modifier ses informations");
        System.out.println("4. Quitter le jeu");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            return true;
        } else if (choice == 3) {
            endGame("exit");
        }
        toolsMain.maintenance("commande");
        return false;
    }

    public void choiceGameProgress() {
        int choiceUser;
        int choice;

        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Lancer le dé");
        System.out.println("2. Quitter le jeu");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);

        if (choice == 1) {

        } else {
            endGame("exit");
        }
    }
}
