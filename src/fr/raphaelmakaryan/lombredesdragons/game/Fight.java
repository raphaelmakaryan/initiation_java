package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;

public class Fight {

    public void playerAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackBase = character.getAttackLevel();
        int attackWithObject = verificationHaveOffensive(attackBase, character);
        int[][] newAttackLevel = dice.dice20(attackWithObject);
        int lifePoints = enemie.getLifePoints();
        menu.displayChoicePlayerAttack(boardClass, user, game, this, newAttackLevel, lifePoints, enemie, "player", menu, boardInt, caseNumber);
    }

    public void enemyAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber) {
        Dice dice = new Dice();
        Character character = user.getCharacterPlayer();
        int attackLevel = enemie.getAttackLevel();
        int[][] newAttackLevel = dice.dice20(attackLevel);
        int lifePoints = character.getLifePoints();
        menu.displayFightCritical(newAttackLevel, "enemy");
        verifiedPerson(attackLevel, lifePoints, enemie, "enemy", user, menu, game, boardClass, boardInt, caseNumber);
    }

    public void espace(Menu menu, Game game, User user, Board boardClass, int[] boardInt, int caseNumber) {
        Dice dice = new Dice();
        int escape = dice.dice6();
        menu.displayEscape(escape);
        boardClass.setNewCellPlayer(boardInt, caseNumber);
        menu.choiceGameProgress(boardClass, user, game);
    }

    public void verifiedPerson(int attacker, int receiving, Enemie enemie, String type, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber) {
        int difference = receiving - attacker;
        int damageDealt = Math.max(0, difference);
        displayAttack(type, attacker);
        if (damageDealt == 0) {
            deadPerson(type, user, menu, game, boardClass);
        } else {
            modifyLifePoints(type, enemie, difference, user, menu, game, boardClass, boardInt, caseNumber);
        }
    }

    public void modifyLifePoints(String type, Enemie enemie, int difference, User user, Menu menu, Game game, Board boardClass, int[] boardInt, int caseNumber) {
        Character character = user.getCharacterPlayer();
        if (type == "player") {
            enemie.setLifePoints(difference);
        } else {
            character.setLifePoints(difference);
            progressesFight(menu, boardClass, enemie, user, game, boardInt, caseNumber);
            //espace(menu, game, user, boardClass, boardInt, caseNumber);
        }
    }

    public void progressesFight(Menu menu, Board boardClass, Enemie enemies, User user, Game game, int[] boardInt, int caseNumber) {
        playerAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber);
        enemyAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber);
    }

    public void deadPerson(String type, User user, Menu menu, Game game, Board boardClass) {
        if (type.equals("enemy")) {
            // Appel de la fonction pour mettre a jour la base de donnée
            EndGame.endGame("dead", menu);
        } else {
            System.out.println("L'ennemi est mort !");
            menu.choiceGameProgress(boardClass, user, game);
        }
    }

    public void displayAttack(String type, int degat) {
        if (type.equals("player")) {
            System.out.println("Vous avez infligé " + degat + " points de dégâts à l'ennemi !");
        } else {
            System.out.println("L'ennemi vous a infligé " + degat + " points de dégâts !");
        }
    }

    public int verificationHaveOffensive(int attackBase, Character character) {
        OffensiveEquipment offensivePlayer = character.getOffensiveEquipment();
        if (offensivePlayer != null) {
            int valueAttackObject = offensivePlayer.getLevelAttack();
            return attackBase + valueAttackObject;
        }
        return attackBase;
    }

    public boolean verificationHaveDefensive(User user) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment defensiveEquipment = character.getDefensiveEquipment();
        if (defensiveEquipment != null) {
            return true;
        }
        return false;
    }

    public void havePotion(User user, Menu menu, Board borderClass, Game game, Fight fight, int[][] attackLevel, int lifePoints, Enemie enemie, String type, int[] boardInt, int caseNumber) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment defensiveEquipment = character.getDefensiveEquipment();
        if (defensiveEquipment != null) {
            int valuePotion = defensiveEquipment.getLevelDefense();
            int health = character.getLifePoints();
            int calcul = health + valuePotion;
            int defaultHealth = character.getLifeDefault();
            if (health == defaultHealth) {
                menu.haveAlreadyMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber);
            } else if (calcul > defaultHealth) {
                character.setLifePoints(defaultHealth);
                deletePotion(character);
                menu.haveMaxHealthFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber);
            } else {
                character.setLifePoints(calcul);
                deletePotion(character);
                menu.haveRegenerationFight(borderClass, user, game, this, attackLevel, lifePoints, enemie, type, menu, boardInt, caseNumber, calcul);
            }
        }
    }

    public void deletePotion(Character character) {
        character.setDefensiveEquipment(null);
    }

}
