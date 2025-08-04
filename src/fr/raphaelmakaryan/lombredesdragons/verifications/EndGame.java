package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;

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
        } else if (type.equals("dead")) {
            menu.endGameDead();
            System.exit(0);
        } else {
            System.out.println("Fin demandé non existante. Fin du processus");
            System.exit(0);
        }
    }
}
