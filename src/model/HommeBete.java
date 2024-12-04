package model;

import java.util.List;

public class HommeBete extends Creature{
	 private List<Maladie> maladies;  // Liste de maladies spécifiques aux HommeBetes

	    /**
	     * Constructeur de la classe HommeBete qui appelle le constructeur de Creature
	     * 
	     * @param nom
	     * @param sexe
	     * @param poids
	     * @param taille
	     * @param age
	     * @param moral
	     * @param maladies
	     */
	    public HommeBete(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
	        // Appel du constructeur de la classe mère (Creature)
	        super(nom, sexe, poids, taille, age, moral);
	        this.maladies = maladies; // Initialisation de la liste de maladies
	    }

	    /**
	     * Getter de maladies
	     * 
	     * @return la liste de maladies de l'HommeBete
	     */
	    public List<Maladie> getMaladies() {
	        return maladies;
	    }

	    /**
	     * Setter de maladies
	     * 
	     * @param maladies
	     */
	    public void setMaladies(List<Maladie> maladies) {
	        this.maladies = maladies;
	    }

	    /**
	     * Méthode spécifique pour afficher les maladies de l'HommeBete
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
