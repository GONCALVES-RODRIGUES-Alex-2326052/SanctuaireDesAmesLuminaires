package model;

import java.util.List;
import java.util.Random;

public class LoupGarous extends Creature{
	private List<Maladie> maladies;
	
	private String categorieAge;
    private int force;
    private int facteurDomination;
    private int rang;
    private double niveau;
    private double facteurImpetuosite;
    private String meute;
    private boolean humain;
    
    /**
	 * @param nom
	 * @param sexe
	 * @param poids
	 * @param taille
     * @param tailleHumain 
     * @param tailleLoup 
	 * @param age
	 * @param moral
	 * @param categorieAge
	 * @param force
	 * @param facteurDomination
	 * @param rang
	 * @param niveau
	 * @param facteurImpetuosite
	 * @param meute
	 */
	
    public LoupGarous(String nom, String sexe, double poids, double taille, int age, int moral,
        String categorieAge, int force, int facteurDomination, int rang,
        double facteurImpetuosite, String meute, List<Maladie> maladies) {
		super(nom, sexe, poids, taille, age, moral);
		this.categorieAge = categorieAge;
		this.force = force;
		this.facteurDomination = facteurDomination;
		this.rang = rang;
		this.facteurImpetuosite = facteurImpetuosite;
		this.meute = meute;
		this.humain = true;
		this.maladies = maladies;
		this.niveau = calculerNiveau();
}
	
    @Override
	public void afficherCaracteristiques() {
        super.afficherCaracteristiques();
        System.out.println("Catégorie d'âge : " + categorieAge);
        System.out.println("Force : " + force);
        System.out.println("Facteur de domination : " + facteurDomination);
        System.out.println("Rang : " + rang);
        System.out.println("Niveau : " + niveau);
        System.out.println("Facteur d'impétuosité : " + facteurImpetuosite);
        System.out.println("Meute : " + (meute != null ? meute : "Solitaire"));
        System.out.println("Forme actuelle : " + (humain ? "Humaine" : "Loup-garou"));
    }
	
	@Override
    public String getType() {
    	return " Lycanthrope";
    }
	
	public void hurler() {
		if(humain) {
			System.out.println("Ne peux pas hurler sous forme humaine !");
		}
		else {
			System.out.println("Pousse un puissant hurlement !");
		}
	}
	
	/**
	 * @return maladies
	 */
	
	public List<Maladie> getMaladies() {
        return maladies;
    }
	
	/**
	 * 
	 * @param maladies
	 */

    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }
    
	/**
	 * @return CatégorieAge
	 */
	
	public String getCategorieAge() {
		return categorieAge;
	}
	
	/**
	 * @param Set une catégorie d'age
	 */
	
	public void setCategorieAge(String categorieAge) {
		this.categorieAge = categorieAge;
		this.niveau = calculerNiveau();
	}
	
	/**
	 * @return La force
	 */
	
	public int getForce() {
		return force;
	}
	
	/**
	 * @param set La force
	 */
	
	public void setForce(int force) {
		this.force = force;
		this.niveau = calculerNiveau();
	}
	
	/**
	 * @return Le facteur de domination
	 */
	
	public int getFacteurDomination() {
		return facteurDomination;
	}
	
	/**
	 * @param set le facteur de domination
	 */
	
	public void setFacteurDomination(int facteurDomination) {
		this.facteurDomination = facteurDomination;
		this.niveau = calculerNiveau();
	}
	
	/**
	 * @return le rang
	 */
	
	public int getRang() {
		return rang;
	}
	
	/**
	 * @param set Le rang
	 */
	
	public void setRang(int rang) {
		this.rang = rang;
		this.niveau = calculerNiveau();
	}
	
	/**
	 * @return le niveau
	 */
	
	public double getNiveau() {
		return niveau;
	}
	
	/**
	 * @param set Le niveau
	 */
	
	public void setNiveau(double niveau) {
		this.niveau = niveau;
	}
	
	/**
	 * @return Le facteur d'impétuosité
	 */
	
	public double getFacteurImpetuosite() {
		return facteurImpetuosite;
	}
	
	/**
	 * @param set le facteur d'impétuosité
	 */
	
	public void setFacteurImpetuosite(double facteurImpetuosite) {
		this.facteurImpetuosite = facteurImpetuosite;
		this.niveau = calculerNiveau();
	}
	
	/**
	 * @return la meute
	 */
	
	public String getMeute() {
		return meute;
	}
	
	/**
	 * @param set la meute
	 */
	
	public void setMeute(String meute) {
		this.meute = meute;
	}
	
	
	/**
	 * @param min
	 * @param max
	 * @return un coefficiant aléatoire pour calculerNiveau
	 */
	
	private double coeffAléatoire(double min, double max) {
		Random random = new Random();
		return min * (max - min) * random.nextDouble() ;
	}
	
	/**
	 * 
	 * @return le niveau d'un loup-garou (en double) en fonction de son âge.
	 */
	
	private double calculerNiveau() {
        double coeffAge;
        switch (categorieAge.toLowerCase()) {
            case "jeune":
                coeffAge = coeffAléatoire(0.3, 0.6);
                break;
            case "adulte":
                coeffAge = coeffAléatoire(1.1, 2);
                break;
            case "vieux":
                coeffAge = coeffAléatoire(0.7, 1.1);
                break;
            default:
                coeffAge = 1.0;
        }
        return coeffAge * (facteurDomination * 2 + rang * 10 + force) * (1 + facteurImpetuosite);
    }
	
	/**
	 * 
	 * @return l'info si le loup-garou fait partie d'une meute ou pas.
	 */
	public boolean Solitaire() {
		return meute.equalsIgnoreCase("solitaire");
	}
	
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