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
    private boolean contagieuse;
    private boolean regenerante;

    /**
     * Constructeur de la classe Creature
     * 
     * @param nom
     * @param sexe
     * @param poids
     * @param taille
     * @param age
     * @param moral
     * @param contagieuse
     * @param regenerante
     */
    public Creature(String nom, String sexe, double poids, double taille, int age, int moral) {
    	this.random = new Random();
        this.nom = nom;
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.moral = moral;
        this.etat = random.nextInt(30)+60;
        this.contagieuse = contagieuse;
        this.regenerante = regenerante;
    }

 	/**
 	 * Methode qui augmente l'état et evite qu'il ne depasse 100
 	 * 
 	 * @param amelioration
 	 * 
 	 */
    public void ameliorerEtat(int amelioration) {
        this.etat += amelioration;
        if (this.etat > 100) {
            this.etat = 100;  // L'état ne doit pas dépasser 100
        }
    }

    /**
     * Methode qui diminue l'état et evite qu'il ne descende en dessous de 0
     * 
     * @param diminution
     * 
     */
    public void diminuerEtat(int diminution) {
        this.etat -= diminution;
        if (this.etat < 0)
            etat = 0;
    }

    /**
     * Methode qui diminue le moral et evite qu'il ne descende en dessous de 0
     * 
     * @param diminution
     * 
     */
    public void diminuerMoral(int diminution) {
        this.moral -= diminution;
        if(this.moral < 0)
            this.moral = 0;
    }

    /**
     * Methode qui ameliore l'etat et evite qu'il depasse 100
     * 
     * @param ameliorer
     * 
     */
    public void ameliorerMoral(int ameliorer) {
        this.moral += ameliorer;
        if (this.moral > 100)
            this.moral = 100;
    }
    
    /**
     * Vérifie si la créature est contagieuse.
     * 
     * @return true si contagieuse, sinon false.
     */
    public boolean estContagieuse() {
        return contagieuse;
    }

    /**
     * Vérifie si la créature est régénérante.
     * 
     * @return true si régénérante, sinon false.
     */
    public boolean estRegenerante() {
        return regenerante;
    }

    /**
     * Définit si une créature est contagieuse.
     * 
     * @param contagieuse
     */
    public void setContagieuse(boolean contagieuse) {
        this.contagieuse = contagieuse;
    }

    /**
     * Définit si une créature est régénérante.
     * 
     * @param regenerante
     */
    public void setRegenerante(boolean regenerante) {
        this.regenerante = regenerante;
    }
    
    /**
     * 
     * @return le nom de créature et comme quoi elle attend
     */
    public String attendre() {
    	diminuerMoral(random.nextInt(5));
    	return getNom() + " est en train d'attendre.";
    }
    
    /**
     * 
     * @return le nom de la créature et le fait qu'elle hurle
     */
    public String hurler() {
    	return getNom() + " hurle parce que son moral est au plus bas !";
    }
    
    /**
     *  Methode quand une créature s'emporte a une chance de contaminer une autres créature
     *  
     * @return un boolean 
     */
    public boolean emporter() {
    	System.out.println(getNom() + " s'est emporter et a une chance de contaminer un autres monstre");
    	return random.nextBoolean();
    }
    
    /**
     * 
     * @param maladie
     * @return le nom de la créature et la maladie qu'elle a attrapé
     */
    public String tomberMalade(Maladie maladie) {
    	return getNom() + " a attraper " + maladie.getNom() + " !";
    }
    
    /**
     * 
     * @return le nom de la créature et le fait qu'elle soit morte
     */
    public String trepasser() {
    	return getNom() + " est mort !";
    }
    
    /**
     * 
     * @param soin
     * @return le nom de la créature et le fait qu'elle soit soigné
     */
    public String etreSoignee(int soin) {
    	ameliorerEtat(soin);
    	ameliorerMoral(soin);
    	return getNom() + " a été soignée !";
    }
    

	 /**
	  * 
	  * @return une maladie choisie aléatoirement parmis ce possédé par la créature
	  */
	public Maladie contaminer() {
		random = new Random();
		return maladies.get(random.nextInt(maladies.size()));
	}
	
	/**
	 * 
	 * @return le nom de la créature
	 */
    public String getNom() {
        return nom;
    }

    /**
     * 
     * @return le sexe de la créature (Mâle ou Femelle)
     */
    public String getSexe() {
        return sexe;
    }
    
    /**
     * 
     * @return le poids de la créature
     */
    

    public double getPoids() {
        return poids;
    }

    /**
     * 
     * @return la taille de la créature
     */
    public double getTaille() {
        return taille;
    }
    
    /**
     * 
     * @return l'âge de la créature
     */
    public int getAge() {
        return age;
    }

    /**
     * 
     * @return le moral de créature
     */
    public int getMoral() {
        return moral;
    }
    
    /**
     * 
     * @param etat
     * setters de etat
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * 
     * @return l'etat de la créature
     */
    public int getEtat() {
        return etat;
    }

    /**
     * 
     * @return la liste des maladies que la créature possède
     */
    public List<Maladie> getMaladies() {
        return maladies;
    }
}