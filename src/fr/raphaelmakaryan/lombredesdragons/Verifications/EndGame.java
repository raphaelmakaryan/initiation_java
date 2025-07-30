package fr.raphaelmakaryan.lombredesdragons.Verifications;

public class EndGame {
    public static void endGame(String type) {
        if (type.equals("exit")) {
            System.out.println("Merci d'avoir joué ! À bientôt !");
            System.exit(0);
        } else if (type.equals("fin")) {
            System.out.println("Vous avez gagné ! Merci d'avoir joué ! À bientôt !");
            System.exit(0);
        }
        else {
            System.out.println("Commande non reconnue. Veuillez réessayer.");
        }
    }
}
