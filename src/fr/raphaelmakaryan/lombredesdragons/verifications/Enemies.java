package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Enemie;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Dragon;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Goblin;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Wizard;
import fr.raphaelmakaryan.lombredesdragons.game.Fight;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

public class Enemies extends Admin {
    private Enemie enemiesPlayer;

    /**
     * Player is on an enemy cell
     * @param menu
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void haveEnemies(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber) {
        menu.enemiesCell(boardClass, menu, user, game, boardInt, caseNumber);
    }

    /**
     * Player wishes this beat
     * @param menu
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     */
    public void chooseFight(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber) {
        Fight fight = new Fight();
        createEnemy(boardClass);
        menu.displayEnemyFight(this.enemiesPlayer);
        fight.progressesFight(menu, boardClass, this.enemiesPlayer, user, game, boardInt, caseNumber);
    }

    /**
     * Creation of the enemy according to the case index and the enemy’s id
     * @param board
     */
    public void createEnemy(Board board) {
        int indexPlayer = board.getCurrentCasePlayers() + 1;
        int cellPlayer = board.getBoard()[indexPlayer];
        if (debugEnemiesCell) {
            System.out.println("DEBUG : ID de la case sur ou est le joueur : " + cellPlayer);
        }
        int calculatedCell = cellPlayer - 20;
        if (calculatedCell == 0) {
            enemiesPlayer = new Wizard();
        } else if (calculatedCell == 1) {
            enemiesPlayer = new Goblin();
        } else {
            enemiesPlayer = new Dragon();
        }
    }
}
