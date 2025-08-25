package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;

import java.sql.Connection;
import java.util.Objects;

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
     * @param database
     * @param level
     */
    public void playerAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber, Database database, Level level) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackBase = character.getAttackLevel();
        int attackWithObject = verificationHaveOffensive(attackBase, character, enemie);
        int[][] newAttackLevel = dice.dice20(attackWithObject);
        int lifePoints = enemie.getLifePoints();
        menu.displayChoicePlayerAttack(boardClass, user, game, this, newAttackLevel, lifePoints, enemie, "player", menu, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void enemyAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber, Database database, Level level) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackLevel = enemie.getAttackLevel();
        int[][] newAttackLevel = dice.dice20(attackLevel);
        int lifePoints = character.getLifePoints();
        menu.displayFightCritical(newAttackLevel, "enemy");
        verifiedPerson(newAttackLevel[0][0], lifePoints, enemie, "enemy", user, menu, game, boardClass, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void espace(Menu menu, Game game, User user, Board boardClass, int[] boardInt, int caseNumber, Database database, Level level) {
        int escape = 2;
        menu.displayEscape(escape);
        boardClass.setNewCellPlayer(boardInt, caseNumber - escape, true, database, user);
        menu.choiceGameProgress(boardClass, user, game, database, level);
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
     * @param database
     * @param level
     */
    public void verifiedPerson(int attacker, int receiving, Enemie enemie, String type, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber, Database database, Level level) {
        int difference = receiving - attacker;
        int damageDealt = Math.max(0, difference);
        displayAttack(type, attacker);
        verificationLifeTimeWeapon(user);
        if (damageDealt == 0) {
            deadPerson(type, user, menu, game, boardClass, database, level);
        } else {
            modifyLifePoints(type, enemie, difference, user, menu, game, boardClass, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void modifyLifePoints(String type, Enemie enemie, int difference, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber, Database database, Level level) {
        Character character = user.getCharacterPlayer();
        if (type.equals("player")) {
            enemie.setLifePoints(difference);
        } else {
            character.setLifePoints(difference);
            database.changeLifePoints(user, difference);
            progressesFight(menu, boardClass, enemie, user, game, boardInt, caseNumber, database, level);
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
     * @param database
     * @param level
     */
    public void progressesFight(Menu menu, Board boardClass, Enemie enemies, User user, Game game, int[] boardInt, int caseNumber, Database database, Level level) {
        boolean canAttack = verificationPlayerCanAttack(user, enemies);
        if (!canAttack) {
            menu.playerCantAttackFight();
            menu.choiceGameProgress(boardClass, user, game, database, level);
        }
        playerAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber, database, level);
        enemyAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber, database, level);
    }

    /**
     * Logic if the player or the enemy is dead
     *
     * @param type
     * @param user
     * @param menu
     * @param game
     * @param boardClass
     * @param database
     * @param level
     */
    public void deadPerson(String type, User user, Menu menu, Game game, Board boardClass, Database database, Level level) {
        if (type.equals("enemy")) {
            database.changeLifePoints(user, 0);
            EndGame.endGame("dead", menu, game, database);
        } else {
            System.out.println("L'ennemi est mort !");
            level.addExp(user, menu);
            menu.choiceGameProgress(boardClass, user, game, database, level);
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
     * @param enemie
     * @return
     */
    public int verificationHaveOffensive(int attackBase, Character character, Enemie enemie) {
        OffensiveEquipment offensivePlayer = character.getOffensiveEquipment();
        String enemyRival = enemie.getName();
        if (offensivePlayer != null) {
            String weapon = offensivePlayer.getName();
            int valueSpecialWeapon = haveWeaponSpecial(weapon, enemyRival, attackBase);
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
     * @param database
     * @param level
     */
    public void havePotion(User user, Menu menu, Board borderClass, Game game, int[][] attackLevel, int lifePoints, Enemie enemie, String type, int[] boardInt, int caseNumber, Database database, Level level) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment defensiveEquipment = character.getDefensiveEquipment();
        if (defensiveEquipment != null) {
            int valuePotion = defensiveEquipment.getLevelDefense();
            int health = character.getLifePoints();
            int calcul = health + valuePotion;
            int maxHealth = character.getMaxHealth();
            if (health == maxHealth) {
                menu.haveAlreadyMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, database, level);
            } else if (calcul > maxHealth) {
                character.setLifePoints(maxHealth);
                database.changeLifePoints(user, maxHealth);
                deletePotion(character);
                menu.haveMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, database, level);
            } else {
                character.setLifePoints(calcul);
                database.changeLifePoints(user, calcul);
                deletePotion(character);
                menu.haveRegenerationFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, calcul, database, level);
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

    /**
     * Check if the player can attack the enemy in front of him
     *
     * @param user
     * @param enemie
     * @return
     */
    public boolean verificationPlayerCanAttack(User user, Enemie enemie) {
        Character character = user.getCharacterPlayer();
        String typePlayer = character.getType();
        String whoCanAttackEnemie = enemie.getWhoCanAttack();
        return whoCanAttackEnemie.equals("ALL") || typePlayer.equals(whoCanAttackEnemie);
    }

    /**
     * Check if the player has a special weapon as well as according to the enemy the degat changes
     *
     * @param name
     * @param enemie
     * @param attackDefault
     * @return
     */
    public int haveWeaponSpecial(String name, String enemie, int attackDefault) {
        if (name.equals("Bow")) {
            if (enemie.equals("Dragon")) {
                return 6;
            }
            return 4;
        } else if (name.equals("Invisibility")) {
            if (enemie.equals("Evil spirits")) {
                return 8;
            }
            return 5;
        } else if (name.equals("Thunderclap")) {
            return attackDefault * 2;
        } else {
            return 0;
        }
    }

    /**
     * Check if the player has the thunderclap
     *
     * @param character
     */
    public void verificationHaveThunderclap(Character character) {
        OffensiveEquipment weapon = character.getOffensiveEquipment();
        if (weapon != null) {
            String nameWeapon = weapon.getName();
            if (nameWeapon.equals("Thunderclap")) {
                character.setOffensiveEquipment(null);
            }
        }
    }

    /**
     * Check if the player has a weapon soon broken to reduce his life by removing it
     *
     * @param user
     */
    public void verificationLifeTimeWeapon(User user) {
        OffensiveEquipment offensiveEquipment = user.getCharacterPlayer().getOffensiveEquipment();
        if (offensiveEquipment != null) {
            int lifeTimeWeapon = offensiveEquipment.getLifetime();
            if (lifeTimeWeapon != 0) {
                user.getCharacterPlayer().getOffensiveEquipment().setLifetime(lifeTimeWeapon - 1);
            } else {
                user.getCharacterPlayer().setOffensiveEquipment(null);
                System.out.println("Votre arme est cassée !");
            }
        }
    }
}
