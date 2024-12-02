package model;

import java.util.List;
import java.util.Random;

public abstract class Creature {
    private String nom;
    private String sexe;
    private double poids;
    private double taille;
    private int age;
    private int moral;
    private int etat;  // Nouvel attribut pour l'état de la créature
    private List<Maladie> maladies;
    private Random random;

    // Constructeur de la classe Creature
    public Creature(String nom, String sexe, double poids, double taille, int age, int moral) {
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = moral;
        this.etat = 100;  // Par défaut, l'état est à 100 (parfaitement en bonne santé)
    }

 // Méthode pour améliorer l'état de la créature
    public void ameliorerEtat(int amelioration) {
        this.etat += amelioration;
        if (this.etat > 100) {
            this.etat = 100;  // L'état ne doit pas dépasser 100
        }
    }

    //Méthode pour diminuer l'état de la créature
    public void diminuerEtat(int diminution) {
        this.etat -= diminution;
        if (this.etat < 0)
            etat = 0;
    }

    // Méthode pour diminuer le moral de la créature
    public void diminuerMoral(int diminution) {
        this.moral -= diminution;
        if(this.moral < 0)
            this.moral = 0;
    }

    // Méthode pour améliorer le moral de la créature
    public void ameliorerMoral(int ameliorer) {
        this.moral += ameliorer;
        if (this.moral > 100)
            this.moral = 100;
    }

    // Méthode pour afficher les caractéristiques de la créature
    public void afficherCaracteristiques() {
        System.out.println("Nom : " + nom);
        System.out.println("Sexe : " + sexe);
        System.out.println("Poids : " + poids + " kg");
        System.out.println("Taille : " + taille + " m");
        System.out.println("Âge : " + age + " ans");
        System.out.println("Moral : " + moral + "/100");
        System.out.println("État : " + etat + "/100");  // Affichage de l'état
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public String getSexe() {
        return sexe;
    }

    public double getPoids() {
        return poids;
    }

    public double getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getMoral() {
        return moral;
    }

    public int getEtat() {
        return etat;
    }

    public List<Maladie> getMaladies() {
        return maladies;
    }
    
    // Methode pour l'attente
    public String attendre() {
    	diminuerMoral(random.nextInt(5));
    	return getNom() + " est en train d'attendre.";
    }
    
    // Methode pour l'hurlement
    public String hurler() {
    	return getNom() + " hurle parce que son moral est au plus bas !";
    }
    
    // Methode pour s'emporter 
    public String emporter() {
    	if (random.nextBoolean()) {
    		contaminer();
    		return getNom() + " a contaminer une autres créature en s'emportant apres multiples hurlement !";
    	}
    	else return getNom() + " s'est emporter a cause de hurlement consecutive !";
    }
    
    // Methode pour tomber malade
    public String tomberMalade(Maladie maladie) {
    	return getNom() + " a attraper " + maladie.getNom() + " !";
    }
    
    // Methode pour trepasser (mourir)
    public String trepasser() {
    	return getNom() + " est mort !";
    }
    
    // Methode pour soigner
    public String etreSoignee(int soin) {
    	ameliorerEtat(soin);
    	ameliorerMoral(soin);
    	return getNom() + " a été soignée !";
    }
    

	 // Methode pour contaminer les autres Créature
		public Maladie contaminer() {
			random = new Random();
			return maladies.get(random.nextInt(maladies.size()));
		}

}