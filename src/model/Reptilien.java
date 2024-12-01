package model;

import java.util.List;

public class Reptilien extends Creature{
	private List<Maladie> maladies;  // Liste de maladies spécifiques aux Reptiliens

    // Constructeur de la classe HommeBete qui appelle le constructeur de Creature
    public Reptilien(String nom, String sexe, double poids, double taille, int age, int moral, List<Maladie> maladies) {
        // Appel du constructeur de la classe mère (Creature)
        super(nom, sexe, poids, taille, age, moral);
        this.maladies = maladies; // Initialisation de la liste de maladies
    }

    // Getter et Setter pour maladies
    public List<Maladie> getMaladies() {
        return maladies;
    }

    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    // Méthode spécifique pour afficher les maladies du Reptilien
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

    // Méthode pour afficher les caractéristiques du Reptilien
    @Override
    public void afficherCaracteristiques() {
        super.afficherCaracteristiques();  // Appel de la méthode de Creature
        afficherMaladies();  // Affiche les maladies du Reptilien
    }
    
    // Methode retournant l'espece de notre créature
    @Override
    public String getEspece() {
    	return "Reptilien";
    }
}
