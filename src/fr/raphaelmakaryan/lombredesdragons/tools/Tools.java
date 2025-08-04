package fr.raphaelmakaryan.lombredesdragons.tools;

import fr.raphaelmakaryan.lombredesdragons.configurations.Colors;
import fr.raphaelmakaryan.lombredesdragons.game.Menu;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tools {

    public static int itIsInt(String s, boolean fatal) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            if (fatal) {
                System.err.print("La valeur que vous avez défini n'est pas un INT/Integer/Nombre ! Fin du processus.");
                System.exit(1);
            } else {
                System.err.print("La valeur que vous avez défini n'est pas un INT/Integer/Nombre ! Veuillez réessayer.");
            }
        }
        return 0;
    }

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
     * Maintenance method for the command.
     * This method is not implemented yet.
     *
     * @param type: the type of command to maintain | commande
     */
    public void maintenance(String type) {
        if (type.equals("commande")) {
            System.out.println("Cet commande n'est pas implémentée.");
        }
    }

    /**
     * Displays an array of integers as a string.
     *
     * @param array: the array of integers to display
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
     * Choice verification loop for menus.
     *
     * @param condition: the condition to check
     * @param type:      the type of condition to check (true or false)
     * @param menu:      the instance of the Menu class to call the methods
     * @param function:  the name of the method to call in the Menu class
     */
    public void verificationChoiceWhile(boolean condition, boolean type, Menu menu, String function) {
        while (condition != type) {
            clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            try {
                java.lang.reflect.Method method = menu.getClass().getMethod(function);
                method.invoke(menu);
            } catch (Exception e) {
                System.err.println("Erreur lors de l'appel de la fonction " + function + " : " + e.getMessage());
            }
        }
    }

    /**
     * Choice verification loop for menus with arguments.
     *
     * @param function: the name of the method to call in the Menu class
     * @param menu:     the instance of the Menu class to call the methods on
     * @param args:     the arguments to pass to the method
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

    public void verificationChoiceNotWhilev2(String function, Menu menu, Object... args) {
        try {
            clearLine();
            System.out.println("Veuillez choisir un choix valide !");

            Class<?>[] paramTypes = Arrays.stream(args).map(arg -> {
                if (arg instanceof Integer) return int.class;
                else if (arg instanceof Boolean) return boolean.class;
                else if (arg instanceof Double) return double.class;
                else if (arg instanceof Float) return float.class;
                else if (arg instanceof Long) return long.class;
                else return arg.getClass();
            }).toArray(Class[]::new);

            Method method = menu.getClass().getMethod(function, paramTypes);
            method.invoke(menu, args);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel de la fonction " + function + " : " + e.getMessage());
            e.printStackTrace(); // utile pour le debug
        }
    }

    public void setTimeout(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}