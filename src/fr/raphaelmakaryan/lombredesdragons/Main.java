package fr.raphaelmakaryan.lombredesdragons;
import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.game.Game;

public class Main {
    public static void main(String[] args) {
        // Lancement du jeu
        System.out.printf(Colors.RUNGAME_CYAN + " Lancement du jeu : L'ombre des dragons !\n" + Colors.RESET);
        new Game().main();
    }
}