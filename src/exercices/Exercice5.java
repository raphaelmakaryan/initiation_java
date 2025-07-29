package exercices;

public class Exercice5 {
    public static void main(String[] args) {
        int cartons = 34;
        int maxCamion = 9;
        int voyages = 0;
        while (cartons > 1) {
            int difference = cartons - maxCamion;
            if (difference > 0) {
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