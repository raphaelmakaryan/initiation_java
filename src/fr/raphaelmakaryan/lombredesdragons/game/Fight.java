package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.Enemie;
import fr.raphaelmakaryan.lombredesdragons.verifications.EndGame;
import fr.raphaelmakaryan.lombredesdragons.verifications.Enemies;

public class Fight {

    public void playerAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber) {
        Character character = user.getCharacterPlayer();
        int attackLevel = character.getAttackLevel();
        int lifePoints = enemie.getLifePoints();
        menu.displayFightPlayerAttack();
        verifiedPerson(attackLevel, lifePoints, enemie, "player", user, menu, game, boardClass, boardInt, caseNumber);
    }

    public void enemyAttack(User user, Menu menu, Enemie enemie, Board boardClass, Game game, int[] boardInt, int caseNumber) {
        Character character = user.getCharacterPlayer();
        int attackLevel = enemie.getAttackLevel();
        int lifePoints = character.getLifePoints();
        menu.displayFightEnemyAttack();
        verifiedPerson(attackLevel, lifePoints, enemie, "enemy", user, menu, game, boardClass, boardInt, caseNumber);
    }

    public void espace(Menu menu, Game game, User user, Board boardClass, int[] boardInt, int caseNumber) {
        menu.displayEscape();
        boardClass.setNewCellPlayer(boardInt, caseNumber);
        game.playTurn(boardClass, user, game);
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
            espace(menu, game, user, boardClass, boardInt, caseNumber);
        }
    }

    public void progressesFight(Menu menu, Board boardClass, Enemie enemies, User user, Game game, int[] boardInt, int caseNumber) {
        menu.displayEnemyFight(enemies);
        playerAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber);
        enemyAttack(user, menu, enemies, boardClass, game, boardInt, caseNumber);
    }

    public void deadPerson(String type, User user, Menu menu, Game game, Board boardClass) {
        if (type.equals("enemy")) {
            // Appel de la fonction pour mettre a jour la base de donnée
            EndGame.endGame("dead", menu);
        } else {
            System.out.println("L'ennemi est mort !");
            game.playTurn(boardClass, user, game);
        }
    }

    public void displayAttack(String type, int degat) {
        if (type.equals("player")) {
            System.out.println("Vous avez infligé " + degat + " points de dégâts à l'ennemi !");
        } else {
            System.out.println("L'ennemi vous a infligé " + degat + " points de dégâts !");
        }
    }

}
