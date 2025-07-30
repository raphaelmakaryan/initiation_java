package fr.raphaelmakaryan.lombredesdragons.Tools;


import fr.raphaelmakaryan.lombredesdragons.Game.Menu;

import java.util.Arrays;

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
                System.err.print("Vous avez entré un nombre alors qu'un texte était attendu ! Veuillez réessayer.");
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
                System.err.print("Le type de personnage que vous avez entré n'est pas valide ! Fin du processus.");
                System.exit(1);
        }
        return false;
    }

    public void maintenance(String type) {
        if (type.equals("commande")) {
            System.out.println("Cet commande n'est pas implémentée.");
        }
    }

    public static void displayAArrayint(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public void clearLine() {
        System.out.println("\n" + "-".repeat(40) + "\n");
    }

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

    public void verificationChoiceNotWhile(String function, Menu menu, Object... args) {
        try {
            clearLine();
            System.out.println("Veuillez choisir un choix valide !");
            if (args.length == 0) {
                java.lang.reflect.Method method = menu.getClass().getMethod(function);
                method.invoke(menu);
            } else {
                java.lang.reflect.Method method = menu.getClass().getMethod(function, Arrays.stream(args)
                        .map(Object::getClass)
                        .toArray(Class[]::new));
                method.invoke(menu, args);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel de la fonction " + function + " : " + e.getMessage());
        }
    }
}