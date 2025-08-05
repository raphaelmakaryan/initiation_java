package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.Main;
import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;
import fr.raphaelmakaryan.lombredesdragons.verifications.Enemies;

import static fr.raphaelmakaryan.lombredesdragons.verifications.EndGame.endGame;
import static fr.raphaelmakaryan.lombredesdragons.tools.Tools.*;

import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu extends Admin {
    Tools toolsMain = new Tools();
    Scanner clavier = new Scanner(System.in);
    Objects objects = new Objects();


    /**
     * Menu of game presentation
     *
     * @param user
     */
    public void startMenu(User user) {
        String typeUser;
        String nameUser;
        String type;
        boolean verifType;
        String name;
        toolsMain.clearLine();
        System.out.println(Colors.RESET + "Quel type de personnage souhaitez-vous créer ? (Magicien / Guerrier)");
        typeUser = clavier.next();
        type = itIsString(typeUser, false);
        if (type instanceof String && type != "") {
            verifType = Boolean.parseBoolean(String.valueOf(isACharacter(type)));
            if (verifType) {
                user.setTypeChoice(type);
                System.out.println("Proposez-lui un petit nom !");
                nameUser = clavier.next();
                name = itIsString(nameUser, false);
                if (name instanceof String && name != "") {
                    user.setName(name);
                    toolsMain.clearLine();
                }
            } else {
                toolsMain.clearLine();
                startMenu(user);
            }
        } else {
            startMenu(user);
        }
    }

    /**
     * Validation menu for creating the player
     *
     * @param user
     * @param connection
     * @param database
     * @throws SQLException
     */
    public void creationPlayerMenu(User user, Connection connection, Database database) throws SQLException {
        toolsMain.setTimeout(2);
        user.createCharacter(user, connection, database);
        System.out.println(Colors.START_WHITE + "Votre personnage a été créé avec succès !" + Colors.RESET);
        toolsMain.clearLine();
    }


    /**
     * Player choice menu after the creation of the player
     *
     * @param user
     * @param menu
     * @param database
     * @param connection
     * @param game
     * @throws SQLException
     */
    public void afterCreationPlayerMenu(User user, Menu menu, Database database, Connection connection, Game game) throws SQLException {
        int choiceUser;
        int choice;
        toolsMain.setTimeout(2);
        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Démarrer une nouvelle partie");
        System.out.println("2. Afficher toutes les infos de votre personnage");
        System.out.println("3. Modifier ses informations");
        System.out.println("4. Quitter le jeu");
        if (debugViewHeroMenu) {
            System.out.println("5. Affichez tout les héros de la base de données");
        }
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            toolsMain.setTimeout(1);
            chooseDifficulty(game, connection, database);
        } else if (choice == 2) {
            toolsMain.setTimeout(2);
            displayInformationCharacter(user);
            toolsMain.clearLine();
            afterCreationPlayerMenu(user, menu, database, connection, game);
        } else if (choice == 3) {
            toolsMain.setTimeout(2);
            displayModifyInformationCharacter(user, database, connection);
            toolsMain.clearLine();
            afterCreationPlayerMenu(user, menu, database, connection, game);
        } else if (choice == 4) {
            endGame("exit", this);
        } else if (choice == 5 && debugViewHeroMenu) {
            toolsMain.setTimeout(2);
            displayAdminGetHeros(database, connection);
            toolsMain.clearLine();
            afterCreationPlayerMenu(user, menu, database, connection, game);
        } else {
            toolsMain.setTimeout(2);
            toolsMain.clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            afterCreationPlayerMenu(user, menu, database, connection, game);
        }
    }

    public void chooseDifficulty(Game game, Connection connection, Database database) {
        try {
            int choiceUser;
            int choice;
            System.out.println(Colors.CHOICE_YELLOW + "Le niveau de difficulté ? " + Colors.RESET);
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            System.out.println("Veuillez entrer le numéro de votre choix !");
            choiceUser = clavier.nextInt();
            choice = itIsInt(String.valueOf(choiceUser), false);
            toolsMain.clearLine();

            if (choice == 1) {
                game.playerWantPlay(connection, database, game, "easy");
            } else if (choice == 2) {
                game.playerWantPlay(connection, database, game, "medium");
            } else if (choice == 3) {
                game.playerWantPlay(connection, database, game, "hard");
            } else {
                toolsMain.clearLine();
                System.out.println("Veuillez choisir un choix valide !");
                chooseDifficulty(game, connection, database);
            }
        } catch (SQLException e) {
        }
    }

    /**
     * Menu of choice for the general player of the entire game
     *
     * @param boardClass
     * @param user
     * @param game
     */
    public void choiceGameProgress(Board boardClass, User user, Game game, Connection connection, Database database, Level level) {
        int choiceUser;
        int choice;

        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Lancer le dé");
        System.out.println("2. Voir ma position actuelle");
        System.out.println("3. Quitter le jeu");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        toolsMain.clearLine();

        if (choice == 1) {
            game.playTurn(boardClass, user, game, connection, database, level);
        } else if (choice == 2) {
            boardClass.displayBoard();
            toolsMain.clearLine();
            choiceGameProgress(boardClass, user, game, connection, database, level);
        } else if (choice == 3) {
            endGame("exit", this);
        } else {
            toolsMain.clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            choiceGameProgress(boardClass, user, game, connection, database, level);
        }
    }

    /**
     * End of the game menu if the player reaches the last slot
     */
    public void endGameCase() {
        int choiceUser;
        int choice;
        System.out.println(Colors.END_PURPLE + "Vous avez gagné !" + Colors.RESET);
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
            try {
                Main.main(new String[]{"retour"});
            } catch (SQLException e) {
            }
        } else {
            toolsMain.verificationChoiceNotWhile("endGameCase", this, (Object) null);
        }
    }

    /**
     * Menu where the player is in a cell of an enemy
     *
     * @param boardClass
     * @param menu
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void enemiesCell(Board boardClass, Menu menu, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        Enemies enemy = new Enemies();
        Fight fight = new Fight();
        int choiceUser;
        int choice;
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println(Colors.ENEMY_RED + "Vous êtes tomber sur un ennemi !" + Colors.RESET);
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. Se battre contre l'ennemi");
        System.out.println("2. Fuir l'ennemi");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            toolsMain.setTimeout(1);
            enemy.chooseFight(menu, boardClass, user, game, boardInt, caseNumber, connection, database, level);
        } else if (choice == 2) {
            toolsMain.setTimeout(2);
            fight.espace(menu, game, user, boardClass, boardInt, caseNumber, connection, database, level);
        } else {
            toolsMain.setTimeout(1);
            toolsMain.clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            enemiesCell(boardClass, menu, user, game, boardInt, caseNumber, connection, database, level);
        }
    }

    /**
     * Menu where the player is in a cell a box
     *
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void boxCell(Board boardClass, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        int choiceUser;
        int choice;
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println(Colors.BOX_GREEN + "Vous êtes tombé sur une boîte !" + Colors.RESET);
        System.out.println("Que voulez-vous faire maintenant ?");
        System.out.println("1. L'ouvrir");
        System.out.println("2. Laisser la boîte");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            toolsMain.setTimeout(1);
            objects.openBox(boardClass, user, boardInt, caseNumber, this, game, connection, database, level);
        } else if (choice == 2) {
            toolsMain.setTimeout(1);
            choiceGameProgress(boardClass, user, game, connection, database, level);
        } else {
            toolsMain.clearLine();
            toolsMain.setTimeout(1);
            System.out.println("Veuillez choisir un choix valide !");
            boxCell(boardClass, user, game, boardInt, caseNumber, connection, database, level);
        }
    }

    /**
     * Menu or if the object can be taken
     *
     * @param boardClass
     * @param user
     * @param game
     * @param objects
     * @param allData
     */
    public void displayObjectOpenBox(Board boardClass, User user, Game game, Objects objects, String[][][] allData, Connection connection, Database database, Level level) {
        int choiceUser;
        int choice;
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println("Vous pouvez le prendre ! Que voulez-vous faire maintenant ?");
        System.out.println("1. Le prendre");
        System.out.println("2. Le laisser");
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            toolsMain.setTimeout(1);
            objects.verificationGiveObjectToPlayer(user, allData, this, boardClass, game, connection, database, level);
        } else if (choice == 2) {
            toolsMain.setTimeout(1);
            choiceGameProgress(boardClass, user, game, connection, database, level);
        } else {
            toolsMain.setTimeout(1);
            toolsMain.clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            displayObjectOpenBox(boardClass, user, game, objects, allData, connection, database, level);
        }
    }

    /**
     * Menu or the object cannot be taken
     *
     * @param boardClass
     * @param user
     * @param game
     */
    public void displayCantGetObjectOpenBox(Board boardClass, User user, Game game, Connection connection, Database database, Level level) {
        toolsMain.clearLine();
        toolsMain.setTimeout(1);
        System.out.println("Malheureusement, vous ne pouvez pas prendre cet objet.");
        System.out.println("Cet objet n'est pas disponible pour votre classe.");
        System.out.println("Vous fermez donc la boîte et vous reprenez votre route.");
        toolsMain.setTimeout(1);
        choiceGameProgress(boardClass, user, game, connection, database, level);
    }

    /**
     * Object display menu
     *
     * @param objet
     */
    public void displayObjectOnBox(String objet) {
        toolsMain.clearLine();
        toolsMain.setTimeout(1);
        System.out.println("Dans le coffre, il contenait " + Colors.BOX_GREEN + objet + " !" + Colors.RESET);
    }

    /**
     * Menu where the player took the object
     *
     * @param boardClass
     * @param user
     * @param game
     * @param objet
     */
    public void displayObjectAddToPlayer(Board boardClass, User user, Game game, String objet, Connection connection, Database database, Level level) {
        toolsMain.clearLine();
        toolsMain.setTimeout(1);
        System.out.println("Vous avez pris " + Colors.BOX_GREEN + objet + " !" + Colors.RESET);
        System.out.println("Vous continuez votre aventure.");
        toolsMain.setTimeout(1);
        choiceGameProgress(boardClass, user, game, connection, database, level);
    }

    /**
     * Menu where the player cannot take an object equal to or lower than what he already has in his inventory
     *
     * @param boardClass
     * @param user
     * @param game
     * @param objet
     */
    public void displayObjectCantAddToPlayer(Board boardClass, User user, Game game, String objet, Connection connection, Database database, Level level) {
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println("Vous ne pouvez pas prendre cet objet, vous avez déjà un objet et/ou qui est supérieur à cet objet.");
        System.out.println("Vous continuez votre aventure.");
        toolsMain.setTimeout(1);
        choiceGameProgress(boardClass, user, game, connection, database, level);
    }

    /**
     * Menu where the player chooses to display their information in the presence
     *
     * @param user
     */
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

    /**
     * Menu where the player wants to change their name in the prestart
     *
     * @param user
     * @param database
     * @param connection
     * @throws SQLException
     */
    public void displayModifyInformationCharacter(User user, Database database, Connection connection) throws SQLException {
        String nameUserChoice;
        String newName;
        Character character = user.getCharacterPlayer();
        String oldName = character.getName();
        System.out.println("Donnez-lui son nouveau nom :");
        nameUserChoice = clavier.next();
        newName = itIsString(nameUserChoice, true);
        user.setName(newName);
        character.setName(newName);
        database.editHero(connection, oldName, newName, user);
        toolsMain.clearLine();
    }

    /**
     * Menu or the enemy at which the player wishes to beat is displayed
     *
     * @param enemies
     */
    public void displayEnemyFight(Enemie enemies) {
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println(Colors.ENEMY_RED + "Vous êtes en train de vous battre contre un " + enemies.getName() + " !" + Colors.RESET);
        toolsMain.clearLine();
    }

    /**
     * Critical attack display menu
     *
     * @param attackLevel
     * @param type
     */
    public void displayFightCritical(int[][] attackLevel, String type) {
        toolsMain.setTimeout(1);
        if (type == "player") {
            if (attackLevel[0][1] == 1) {
                System.out.println("Vous avez fait un " + Colors.DICE_MAGENTA + "échec critique" + Colors.RESET + " !");
            } else if (attackLevel[0][1] == 20) {
                System.out.println("Vous avez fait un " + Colors.DICE_MAGENTA + "critique" + Colors.RESET + " !");
            }
        } else {
            if (attackLevel[0][1] == 1) {
                System.out.println("L'ennemi a fait un " + Colors.DICE_MAGENTA + "échec critique" + Colors.RESET + " !");
            } else if (attackLevel[0][1] == 20) {
                System.out.println("L'ennemi a fait un " + Colors.DICE_MAGENTA + "critique" + Colors.RESET + " !");
            }
        }
    }

    /**
     * Menu or the player choose action has a fight
     *
     * @param boardClass
     * @param user
     * @param game
     * @param fight
     * @param attackLevel
     * @param lifePoints
     * @param enemie
     * @param type
     * @param menu
     * @param boardInt
     * @param caseNumber
     */
    public void displayChoicePlayerAttack(Board boardClass, User user, Game game, Fight fight, int[][] attackLevel, int lifePoints, Enemie enemie, String type, Menu menu, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        int choiceUser;
        int choice;
        boolean havePotion = fight.verificationHaveDefensive(user);
        toolsMain.setTimeout(1);
        System.out.println(Colors.CHOICE_YELLOW + "Que voulez-vous faire maintenant ?" + Colors.RESET);
        System.out.println("1. Attaquer l'ennemi");
        System.out.println("2. Fuir l'ennemi");
        if (havePotion) {
            System.out.println("3. Utiliser une potion");
        }
        System.out.println("Veuillez entrer le numéro de votre choix !");
        choiceUser = clavier.nextInt();
        choice = itIsInt(String.valueOf(choiceUser), false);
        if (choice == 1) {
            displayFightCritical(attackLevel, "player");
            displayFightPlayerAttack();
            fight.verifiedPerson(attackLevel[0][0], lifePoints, enemie, "player", user, menu, game, boardClass, boardInt, caseNumber, connection, database, level);
            fight.verificationHaveThunderclap(user.getCharacterPlayer());
        } else if (choice == 2) {
            fight.espace(menu, game, user, boardClass, boardInt, caseNumber, connection, database, level);
        } else if (choice == 3 && havePotion) {
            fight.havePotion(user, this, boardClass, game, attackLevel, lifePoints, enemie, type, boardInt, caseNumber, connection, database, level);
        } else {
            toolsMain.setTimeout(1);
            toolsMain.clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            displayChoicePlayerAttack(boardClass, user, game, fight, attackLevel, lifePoints, enemie, type, this, boardInt, caseNumber, connection, database, level);
        }
    }

    /**
     * Menu where the player has life already full
     *
     * @param boardClass
     * @param user
     * @param game
     * @param fight
     * @param attackLevel
     * @param lifePoints
     * @param enemie
     * @param type
     * @param menu
     * @param boardInt
     * @param caseNumber
     */
    public void haveAlreadyMaxHealthFight(Board boardClass, User user, Game game, Fight fight, int[][] attackLevel, int lifePoints, Enemie enemie, String type, Menu menu, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println("Vous avez deja toute votre vie pleine !");
        System.out.println("Vous reprenez le combat !");
        displayChoicePlayerAttack(boardClass, user, game, fight, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, connection, database, level);
    }

    /**
     * Menu where the player has full life
     *
     * @param boardClass
     * @param user
     * @param game
     * @param fight
     * @param attackLevel
     * @param lifePoints
     * @param enemie
     * @param type
     * @param menu
     * @param boardInt
     * @param caseNumber
     */
    public void haveMaxHealthFight(Board boardClass, User user, Game game, Fight fight, int[][] attackLevel, int lifePoints, Enemie enemie, String type, Menu menu, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println("Vous avez régénérer votre vie au max !");
        System.out.println("Vous n'avez plus de potion !");
        System.out.println("Vous reprenez le combat !");
        displayChoicePlayerAttack(boardClass, user, game, fight, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, connection, database, level);
    }

    /**
     * Menu where the player has regenerated
     *
     * @param boardClass
     * @param user
     * @param game
     * @param fight
     * @param attackLevel
     * @param lifePoints
     * @param enemie
     * @param type
     * @param menu
     * @param boardInt
     * @param caseNumber
     * @param nbreRegen
     */
    public void haveRegenerationFight(Board boardClass, User user, Game game, Fight fight, int[][] attackLevel, int lifePoints, Enemie enemie, String type, Menu menu, int[] boardInt, int caseNumber, int nbreRegen, Connection connection, Database database, Level level) {
        toolsMain.setTimeout(1);
        toolsMain.clearLine();
        System.out.println("Vous avez régénérer votre vie de " + nbreRegen + " !");
        System.out.println("Vous n'avez plus de potion !");
        System.out.println("Vous reprenez le combat !");
        displayChoicePlayerAttack(boardClass, user, game, fight, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, connection, database, level);
    }

    /**
     * Admin menu to display all the heroes of the database
     *
     * @param database
     * @param connection
     * @throws SQLException
     */
    public void displayAdminGetHeros(Database database, Connection connection) throws SQLException {
        database.getHeroes(connection);
    }

    /**
     * Menu displaying the beginning of the fight
     */
    public void displayFightPlayerAttack() {
        System.out.println("Vous avez attaqué l'ennemi !" + Colors.RESET);
        toolsMain.clearLine();
    }

    /**
     * End of the game menu if the player died in a fight
     */
    public void endGameDead() {
        toolsMain.setTimeout(1);
        System.out.println("GAME OVER !");
        System.out.println("Vous avez été vaincu par l'ennemi.");
        System.out.println("Merci d'avoir joué. À bientôt !");
    }

    /**
     * Escape menu
     *
     * @param escape
     */
    public void displayEscape(int escape) {
        toolsMain.setTimeout(1);
        System.out.println("Vous avez réussi à fuir l'ennemi !");
        System.out.println("Par contre, vous avez lancé le dé pour savoir combien de cases vous devez reculer.");
        System.out.println("Vous reculez de " + Colors.DICE_MAGENTA + escape + Colors.RESET + " cases.");
        System.out.println("Vous pouvez continuer votre chemin.");
        toolsMain.setTimeout(2);
        toolsMain.clearLine();
    }

    public void playerCantAttackFight() {
        toolsMain.setTimeout(1);
        System.out.println("Vous ne pouvez pas vous battre contre cet ennemi !");
        System.out.println("Vous partez et continuer votre chemin.");
        toolsMain.setTimeout(2);
        toolsMain.clearLine();
    }
}