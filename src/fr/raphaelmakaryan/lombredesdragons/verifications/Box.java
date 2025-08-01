package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;

public class Box {
    public void haveBox(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber) {
        menu.boxCell(boardClass, user, game, boardInt, caseNumber);
    }
}
