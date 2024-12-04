package model;

import java.util.List;
import java.util.Random;

public class Orque extends Creature {
	private List<Maladie> maladies;
	
	/**
	 * Constructeur de la classe Orque qui appelle le constructeur de Creature
	 * 
	 * @param nom
	 * @param sexe
	 * @param poids
	 * @param taille
	 * @param age
	 * @param moral
	 * @param maladies
	 */
	public Orque(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
		super(nom, sexe, poids, taille, age, moral);
		this.maladies = maladies;
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
	 * Setter de maladies maladies
	 * 
	 * @param maladies
	 */
	public void setMaladies(List<Maladie> maladies) {
		this.maladies = maladies;
	}
	
	/**
	 * @return une maladie 	
	 */
	public Maladie contaminer() {
		Random random = new Random();
		return maladies.get(random.nextInt(maladies.size()));
	}
	
	/**
	 * Méthode spécifique pour afficher les maladies de l'Orque
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
