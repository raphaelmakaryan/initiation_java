package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Enemie;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Dragon;
import fr.raphaelmakaryan.lombredesdragons.game.Fight;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;

public class Enemies {
    private Enemie enemiesPlayer;

    public void haveEnemies(Menu menu, Board boardClass) {
        menu.enemiesCell(boardClass, menu);
    }

    public void chooseFight(Menu menu, Board boardClass) {
        Fight fight = new Fight();
        createEnemy();
        fight.progressesFight(menu, boardClass, this.enemiesPlayer);
    }

    public void createEnemy() {
        enemiesPlayer = new Dragon();
    }
}
