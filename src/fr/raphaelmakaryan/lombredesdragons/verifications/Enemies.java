package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Enemie;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Dragon;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Gobelin;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Wizard;
import fr.raphaelmakaryan.lombredesdragons.game.Fight;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

public class Enemies extends Admin {
    private Enemie enemiesPlayer;

    public void haveEnemies(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber) {
        menu.enemiesCell(boardClass, menu, user, game, boardInt, caseNumber);
    }

    public void chooseFight(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber) {
        Fight fight = new Fight();
        createEnemy(boardClass);
        fight.progressesFight(menu, boardClass, this.enemiesPlayer, user, game, boardInt, caseNumber);
    }

    public void createEnemy(Board board) {
        int indexPlayer = board.getCurrentCasePlayers() + 1;
        int cellPlayer = board.getBoard()[indexPlayer];
        if (debugEnemiesCell) {
            System.out.println("DEBUG : ID de la case sur ou est le joueur : " + cellPlayer);
        }
        int calculatedCell = 20 - cellPlayer;
        if (calculatedCell == 0) {
            enemiesPlayer = new Wizard();
        } else if (calculatedCell == 1) {
            enemiesPlayer = new Gobelin();
        } else {
            enemiesPlayer = new Dragon();
        }
    }
}
