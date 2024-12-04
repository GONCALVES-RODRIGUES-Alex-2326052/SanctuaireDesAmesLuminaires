package model;

import java.util.List;
import java.util.Random;

public class Elfe extends Creature {
    private List<Maladie> maladies;  // Liste de maladies spécifiques aux Elfes
    Random random;

    /**
     * Contructeur de la classe Elfe
     * 
     * @param nom
     * @param sexe
     * @param poids
     * @param taille
     * @param age
     * @param moral
     * @param maladies
     */
    public Elfe(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
        // Appel du constructeur de la classe mère (Creature)
        super(nom, sexe, poids, taille, age, moral);
        this.maladies = maladies; // Initialisation de la liste de maladies
    }

    /**
     * @return la liste des maladies de l'Elfe
     */
    public List<Maladie> getMaladies() {
        return maladies;
    }

    /**
     * Setter pour la liste des maladies
     * 
     * @param maladies
     */
    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }
    
    /**
     * 
     * @return un entier aléatoire pour demoraliser
     */
    public int demoraliser() {
    	 return random.nextInt(10);
    }

    /**
     * Méthode spécifique pour afficher les maladies de l'Elfe
     */
    public void afficherMaladies() {
        if (maladies != null && !maladies.isEmpty()) {
            System.out.println("Maladies de " + getNom() + ":");
            for (Maladie maladie : maladies) {
                System.out.println("- " + maladie.getNom());
            }
        } else {
            System.out.println(getNom() + " n'a pas de maladies.");
        }
    }
}