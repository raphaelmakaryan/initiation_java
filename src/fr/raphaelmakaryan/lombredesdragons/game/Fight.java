package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;

import java.sql.Connection;

public class Fight {

    /**
     * Combat logic for the player
     *
     * @param user
     * @param menu
     * @param enemie
     * @param boardClass
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void playerAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber, Connection connection, Database database) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackBase = character.getAttackLevel();
        int attackWithObject = verificationHaveOffensive(attackBase, character, enemie);
        int[][] newAttackLevel = dice.dice20(attackWithObject);
        int lifePoints = enemie.getLifePoints();
        //menu.displayChoiceWeapon(boardClass, user, game, this, newAttackLevel, lifePoints, enemie, "player", menu, boardInt, caseNumber, connection, database);
        menu.displayChoicePlayerAttack(boardClass, user, game, this, newAttackLevel, lifePoints, enemie, "player", menu, boardInt, caseNumber, connection, database);
    }

    /**
     * Combat logic for the enemy
     *
     * @param user
     * @param menu
     * @param enemie
     * @param boardClass
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void enemyAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber, Connection connection, Database database) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackLevel = enemie.getAttackLevel();
        int[][] newAttackLevel = dice.dice20(attackLevel);
        int lifePoints = character.getLifePoints();
        menu.displayFightCritical(newAttackLevel, "enemy");
        verifiedPerson(newAttackLevel[0][0], lifePoints, enemie, "enemy", user, menu, game, boardClass, boardInt, caseNumber, connection, database);
    }

    /**
     * Escape logic for the player
     *
     * @param menu
     * @param game
     * @param user
     * @param boardClass
     * @param boardInt
     * @param caseNumber
     */
    public void espace(Menu menu, Game game, User user, Board boardClass, int[] boardInt, int caseNumber, Connection connection, Database database) {
        int escape = 2;
        menu.displayEscape(escape);
        boardClass.setNewCellPlayer(boardInt, caseNumber - escape, true, connection, database, user);
        menu.choiceGameProgress(boardClass, user, game, connection, database);
    }

    /**
     * Verification logic if the receiver is dead or still alive but modify his life points
     *
     * @param attacker
     * @param receiving
     * @param enemie
     * @param type
     * @param user
     * @param menu
     * @param game
     * @param boardClass
     * @param boardInt
     * @param caseNumber
     */
    public void verifiedPerson(int attacker, int receiving, Enemie enemie, String type, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber, Connection connection, Database database) {
        int difference = receiving - attacker;
        int damageDealt = Math.max(0, difference);
        displayAttack(type, attacker);
        if (damageDealt == 0) {
            deadPerson(type, user, menu, game, boardClass, connection, database);
        } else {
            modifyLifePoints(type, enemie, difference, user, menu, game, boardClass, boardInt, caseNumber, connection, database);
        }
    }

    /**
     * Logic for modifying health points
     *
     * @param type
     * @param enemie
     * @param difference
     * @param user
     * @param menu
     * @param game
     * @param boardClass
     * @param boardInt
     * @param caseNumber
     */
    public void modifyLifePoints(String type, Enemie enemie, int difference, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber, Connection connection, Database database) {
        Character character = user.getCharacterPlayer();
        if (type == "player") {
            enemie.setLifePoints(difference);
        } else {
            character.setLifePoints(difference);
            database.changeLifePoints(connection, user, difference);
            progressesFight(menu, boardClass, enemie, user, game, boardInt, caseNumber, connection, database);
        }
    }

    /**
     * Outcome of a fight
     *
     * @param menu
     * @param boardClass
     * @param enemies
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void progressesFight(Menu menu, Board boardClass, Enemie enemies, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database) {
        boolean canAttack = verificationPlayerCanAttack(user, enemies);
        if (!canAttack) {
            menu.playerCantAttackFight();
            menu.choiceGameProgress(boardClass, user, game, connection, database);
        }
        playerAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber, connection, database);
        enemyAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber, connection, database);
    }

    /**
     * Logic if the player or the enemy is dead
     *
     * @param type
     * @param user
     * @param menu
     * @param game
     * @param boardClass
     */
    public void deadPerson(String type, User user, Menu menu, Game game, Board boardClass, Connection connection, Database database) {
        if (type.equals("enemy")) {
            database.changeLifePoints(connection, user, 0);
            EndGame.endGame("dead", menu);
        } else {
            System.out.println("L'ennemi est mort !");
            menu.choiceGameProgress(boardClass, user, game, connection, database);
        }
    }

    /**
     * Display if the player an enemy attacks
     *
     * @param type
     * @param degat
     */
    public void displayAttack(String type, int degat) {
        if (type.equals("player")) {
            System.out.println("Vous avez infligé " + degat + " points de dégâts à l'ennemi !");
        } else {
            System.out.println("L'ennemi vous a infligé " + degat + " points de dégâts !");
        }
    }

    /**
     * Verification if the player has an offensive object
     *
     * @param attackBase
     * @param character
     * @return
     */
    public int verificationHaveOffensive(int attackBase, Character character, Enemie enemie) {
        OffensiveEquipment offensivePlayer = character.getOffensiveEquipment();
        String enemiRival = enemie.getName();
        if (offensivePlayer != null) {
            String weapon = offensivePlayer.getName();
            int valueSpecialWeapon = haveWeaponSpecial(weapon, enemiRival, attackBase);
            if (valueSpecialWeapon == 0) {
                int valueAttackObject = offensivePlayer.getLevelAttack();
                return attackBase + valueAttackObject;
            }
            return valueSpecialWeapon;
        }
        return attackBase;
    }

    /**
     * Verification if the player has an defensive object
     *
     * @param user
     * @return
     */
    public boolean verificationHaveDefensive(User user) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment defensiveEquipment = character.getDefensiveEquipment();
        if (defensiveEquipment != null) {
            return true;
        }
        return false;
    }

    /**
     * Verification if the player have a potion
     *
     * @param user
     * @param menu
     * @param borderClass
     * @param game
     * @param attackLevel
     * @param lifePoints
     * @param enemie
     * @param type
     * @param boardInt
     * @param caseNumber
     */
    public void havePotion(User user, Menu menu, Board borderClass, Game game, int[][] attackLevel, int lifePoints, Enemie enemie, String type, int[] boardInt, int caseNumber, Connection connection, Database database) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment defensiveEquipment = character.getDefensiveEquipment();
        if (defensiveEquipment != null) {
            int valuePotion = defensiveEquipment.getLevelDefense();
            int health = character.getLifePoints();
            int calcul = health + valuePotion;
            int defaultHealth = character.getLifeDefault();
            if (health == defaultHealth) {
                menu.haveAlreadyMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, connection, database);
            } else if (calcul > defaultHealth) {
                character.setLifePoints(defaultHealth);
                database.changeLifePoints(connection, user, defaultHealth);
                deletePotion(character);
                menu.haveMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, connection, database);
            } else {
                character.setLifePoints(calcul);
                database.changeLifePoints(connection, user, calcul);
                deletePotion(character);
                menu.haveRegenerationFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, calcul, connection, database);
            }
        }
    }

    /**
     * Delete potion after using
     *
     * @param character
     */
    public void deletePotion(Character character) {
        character.setDefensiveEquipment(null);
    }

    public boolean verificationPlayerCanAttack(User user, Enemie enemie) {
        Character character = user.getCharacterPlayer();
        String typePlayer = character.getType();
        String whoCanAttackEnemie = enemie.getWhoCanAttack();
        return whoCanAttackEnemie.equals("ALL") || typePlayer.equals(whoCanAttackEnemie);
    }

    public int haveWeaponSpecial(String name, String enemie, int attackDefault) {
        if (name == "Bow") {
            if (enemie == "Dragon") {
                return 6;
            }
            return 4;
        } else if (name == "Invisibility") {
            if (enemie == "Evil spirits") {
                return 8;
            }
            return 5;
        } else if (name == "Thunderclap") {
            return attackDefault * 2;
        }
        return 0;
    }

    public void verificationHaveThunderclap(Character character) {
        OffensiveEquipment weapon = character.getOffensiveEquipment();
        if (weapon != null) {
            String nameWeapon = weapon.getName();
            if (nameWeapon == "Thunderclap") {
                character.setOffensiveEquipment(null);
            }
        }
    }
}
