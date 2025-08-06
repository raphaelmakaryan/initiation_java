package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Admin;
import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.sql.Connection;

public class Hostel extends Admin {
    Tools tools = new Tools();

    public void haveHostel(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        menu.cellHostel(boardClass, user, game, boardInt, caseNumber, connection, database, level, this);
    }

    public void playerChoseRest(User user) {
        int healthActual = user.getCharacterPlayer().getLifePoints();
        int healthMax = user.getCharacterPlayer().getMaxHealth();
        int money = user.getCharacterPlayer().getMoney();
        tools.clearLine();
        if (healthMax == healthActual) {
            System.out.println("Vous avez deja votre vie au maximum !");
        } else {
            if (money >= priceHostel) {
                System.out.println("Vous vous etes reposez (régénération de votre vie au maximum)");
                user.getCharacterPlayer().setMoney(money - priceHostel);
                user.getCharacterPlayer().setLifePoints(healthMax);
            } else {
                System.out.println("Vous n'avez pas assez d'argent !");
            }
        }
        tools.clearLine();
    }
}
