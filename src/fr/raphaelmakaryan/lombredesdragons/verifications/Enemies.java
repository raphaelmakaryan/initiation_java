package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Enemie;
import fr.raphaelmakaryan.lombredesdragons.configurations.enemies.Dragon;
import fr.raphaelmakaryan.lombredesdragons.game.Fight;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

public class Enemies {
    private Enemie enemiesPlayer;

    public void haveEnemies(Menu menu, Board boardClass, User user, Game game) {
        menu.enemiesCell(boardClass, menu, user, game);
    }

    public void chooseFight(Menu menu, Board boardClass, User user, Game game) {
        Fight fight = new Fight();
        createEnemy();
        fight.progressesFight(menu, boardClass, this.enemiesPlayer, user, game);
    }

    public void createEnemy() {
        enemiesPlayer = new Dragon();
    }
}
