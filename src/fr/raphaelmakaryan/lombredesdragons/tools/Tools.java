package fr.raphaelmakaryan.lombredesdragons.tools;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Tools {

    /**
     * Verification if the value is an string
     *
     * @param s
     * @param fatal
     * @return
     */
    public static String itIsString(String s, boolean fatal) {
        try {
            Integer.parseInt(s);
            if (fatal) {
                System.err.print("Vous avez entré un nombre alors qu'un texte était attendu ! Fin du processus.");
                System.exit(1);
            } else {
                System.err.print("Vous avez entré un nombre alors qu'un texte était attendu ! Veuillez recommencer.");
            }
        } catch (NumberFormatException e) {
            return s;
        }
        return "";
    }

    /**
     * Verification if the value to be given is indeed an existing type
     *
     * @param s
     * @return
     */
    public static boolean isACharacter(String s) {
        switch (s.toLowerCase()) {
            case "magicien":
                return true;
            case "guerrier":
                return true;
            default:
                System.err.print("Le type de personnage que vous avez entré n'est pas valide ! Veuillez recommencer !");
        }
        return false;
    }

    /**
     * Maintenance command
     *
     * @param type
     */
    public void maintenance(String type) {
        if (type.equals("commande")) {
            System.out.println("Cet commande n'est pas implémentée.");
        }
    }

    /**
     * Displays an array of integers as a string
     *
     * @param array
     */
    public static void displayAArrayint(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    /**
     * Displays the line separation in the console.
     */
    public void clearLine() {
        System.out.println(Colors.SEPARATION_BLACK + "\n" + "-".repeat(40) + "\n" + Colors.RESET);
    }

    /**
     * No loop but verification of choices for menus with arguments
     *
     * @param function
     * @param menu
     * @param args
     */
    public void verificationChoiceNotWhile(String function, Menu menu, Object... args) {
        try {
            clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            if (args.length == 0 || args[0] == null) {
                java.lang.reflect.Method method = menu.getClass().getMethod(function);
                method.invoke(menu);
            } else {
                java.lang.reflect.Method method = menu.getClass().getMethod(function, Arrays.stream(args).map(Object::getClass).toArray(Class[]::new));
                method.invoke(menu, args);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel de la fonction " + function + " : " + e.getMessage());
        }
    }

    /**
     * Waiting function
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Checking the scanner to bring out an int
     * @param scanner
     * @param message
     * @param min
     * @param max
     * @return
     */
    public static int askInt(Scanner scanner, String message, int min, int max) {
        int number;
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim(); // lire toute la ligne
            try {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number; // ok
                } else {
                    System.out.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez entrer un nombre.");
            }
        }
    }
}