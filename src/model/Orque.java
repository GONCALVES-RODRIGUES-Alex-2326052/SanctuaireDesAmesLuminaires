package model;

import java.util.List;

public class Orque extends Creature {
	private List<Maladie> maladies;

	public Orque(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
		super(nom, sexe, poids, taille, age, moral);
		this.maladies = maladies;
	}
	
	public List<Maladie> getMaladies() {
		return maladies;
	}

	public void setMaladies(List<Maladie> maladies) {
		this.maladies = maladies;
	}
	
	// Méthode spécifique pour afficher les maladies de l'Orque
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

    // Méthode pour afficher les caractéristiques de l'Orque
    @Override
    public void afficherCaracteristiques() {
        super.afficherCaracteristiques();  // Appel de la méthode de Creature
        afficherMaladies();  // Affiche les maladies de l'Orque
    }
}