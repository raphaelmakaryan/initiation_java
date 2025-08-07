package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Database;
import fr.raphaelmakaryan.lombredesdragons.configurations.OffensiveEquipment;
import fr.raphaelmakaryan.lombredesdragons.game.Game;
import fr.raphaelmakaryan.lombredesdragons.game.Level;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;
import fr.raphaelmakaryan.lombredesdragons.game.User;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.sql.Connection;

public class Blacksmith {
    Tools tools = new Tools();

    /**
     * Function when the player has fallen into the forge box
     *
     * @param menu
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     * @param connection
     * @param database
     * @param level
     */
    public void haveBlacksmith(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber, Connection connection, Database database, Level level) {
        menu.cellBlacksmith(boardClass, user, game, boardInt, caseNumber, connection, database, level, this);
    }

    /**
     * Function when the player has chosen to repair a weapon
     *
     * @param user
     * @param offensiveEquipment
     */
    public void playerChoseRepair(User user, OffensiveEquipment offensiveEquipment) {
        int lifeTimeActual = offensiveEquipment.getLifetime();
        int lifeTimeMax = offensiveEquipment.getLifetimeDefault();
        int money = user.getCharacterPlayer().getMoney();
        int priceRepair = offensiveEquipment.getPriceRepair();
        tools.clearLine();
        if (lifeTimeMax == lifeTimeActual) {
            System.out.println("Votre arme n'est pas cassé !");
        } else {
            if (money >= priceRepair) {
                System.out.println("Vous avez reparez votre arme");
                user.getCharacterPlayer().setMoney(money - priceRepair);
                offensiveEquipment.setLifetime(lifeTimeMax);
            } else {
                System.out.println("Vous n'avez pas assez d'argent !");
            }
        }
        tools.clearLine();
    }
}