package fr.raphaelmakaryan.lombredesdragons.Verifications;

import fr.raphaelmakaryan.lombredesdragons.Configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

public class EndGame {
    /**
     * Ends the game according to the specified ending type.
     *
     * @param type
     * @param menu
     */
    public static void endGame(String type, Menu menu) {
        if (type.equals("exit")) {
            System.out.println(Colors.END_PURPLE + "Merci d'avoir joué ! À bientôt !");
            System.exit(0);
        } else if (type.equals("fin")) {
            menu.endGameCase();
        } else {
            System.out.println("Commande non reconnue. Veuillez réessayer.");
        }
    }
}
