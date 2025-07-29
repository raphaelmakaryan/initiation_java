import java.util.Scanner;

public class Exercice6 {
    public static int itIsInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.err.print("La valeur que vous avez défini n'est pas un INT/Integer/Nombre ! Fin du processus.");
            System.exit(1);
        }
        return 0;
    }

    public static void main(String[] args) {
        int cartons;
        int maxCamion;
        Scanner clavier = new Scanner(System.in);

        System.out.print("Saisissez le nombre de carton : ");
        String cartonsUser = clavier.nextLine();
        cartons = itIsInt(cartonsUser);

        System.out.print("Saisissez le nombre maximum que le camion peut transporter en carton : ");
        String maxCamionUser = clavier.nextLine();
        maxCamion = itIsInt(maxCamionUser);
        clavier.close();

        int voyages = 0;
        while (cartons > 1) {
            int difference = cartons - maxCamion;
            if (difference >= 0) {
                cartons = difference;
                voyages++;
                System.out.println("La difference est positif, donc le voyage de " + maxCamion + " cartons pour le " + voyages + " eme voyage, il y'a maintenant " + cartons + " cartons");
            } else {
                cartons = difference - (cartons - maxCamion);
                voyages++;
                System.out.println("La difference est négative, donc le voyage de " + difference + " cartons pour le " + voyages + " eme voyage, il y'a maintenant " + cartons + " cartons");
            }
        }
    }
}