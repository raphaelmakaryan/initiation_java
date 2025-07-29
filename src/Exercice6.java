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
        while (cartons > 0) {
            // Récupere le nombre de cartons qui est la valeur minimum entre cartons et le camion
            int difference = Math.min(cartons, maxCamion);
            // Calcul les cartons restants selon les cartons donner et la difference récuperé avant
            cartons = cartons - difference;
            voyages++;
            System.out.println("Le voyage de " + difference + " cartons pour le " + voyages + " eme voyage, il y'a maintenant " + cartons + " cartons");
        }
    }
}