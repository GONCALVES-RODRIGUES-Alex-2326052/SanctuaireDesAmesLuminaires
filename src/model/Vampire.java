package model;

import java.util.List;
import java.util.Random;

public class Vampire extends Creature {
	 private List<Maladie> maladies;  // Liste de maladies spécifiques aux Vampires
	 private Random random;

	    /**
	     * Constructeur de la classe Vampire qui appelle le constructeur de Creature
	     * 
	     * @param nom
	     * @param sexe
	     * @param poids
	     * @param taille
	     * @param age
	     * @param moral
	     * @param maladies
	     */
	    public Vampire(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
	        // Appel du constructeur de la classe mère (Creature)
	        super(nom, sexe, poids, taille, age, moral);
	        this.maladies = maladies; // Initialisation de la liste de maladies
	    }

	    /**
	     * Getter de maladies
	     * 
	     * @return la liste des maladies
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
	     * Methode pour demoraliser les autres Créature
	     * 
	     * @return un entier aléatoire
	     */
	    public int demoraliser() {
	    	 return random.nextInt(10);
	    }
	    
	    /**
	     * Methode pour contaminer les autres Créature
	     * 
	     * @return une maladie
	     */
		public Maladie contaminer() {
			return maladies.get(random.nextInt(maladies.size()));
		}
		
		 /**
		  * Methode de regeneration de quand les vampires meurt
		  */
	    public void regeneration() {
	    	ameliorerEtat(random.nextInt(21));
	    }

	    /**
	     * Méthode spécifique pour afficher les maladies du Vampire
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
