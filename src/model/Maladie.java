package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * La classe Maladie représente une maladie pouvant affecter une créature.
 * Chaque maladie est identifiée par un nom complet, un nom abrégé,
 * un niveau actuel et un niveau maximum.
 */
public class Maladie {

    private String nom;  // Nom complet de la maladie
    private String nomAbrege; // Nom abrégé de la maladie
    private int niveauActuel; // Niveau actuel de la maladie
    private int niveauMax; // Niveau maximum avant trépas

    // Liste statique de maladies possibles avec leurs noms abrégés
    private static final List<Maladie> MALADIES = Arrays.asList(
        new Maladie("Maladie débilitante chronique", "MDC", 0, 100),
        new Maladie("Syndrome fear of missing out", "FOMO", 0, 50),
        new Maladie("Dépendance aux réseaux sociaux", "DRS", 0, 60),
        new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 0, 80),
        new Maladie("Zoopathie paraphrénique lycanthropique", "ZPL", 0, 120),
        new Maladie("Obésité", "OBS", 0, 100)
    );

    /**
     * Constructeur de la classe Maladie.
     *
     * @param nom Le nom complet de la maladie.
     * @param nomAbrege Le nom abrégé de la maladie.
     * @param niveauActuel Le niveau actuel de la maladie.
     * @param niveauMax Le niveau maximum avant trépas.
     */
    public Maladie(String nom, String nomAbrege, int niveauActuel, int niveauMax) {
        this.nom = nom;
        this.nomAbrege = nomAbrege;
        this.niveauActuel = niveauActuel;
        this.niveauMax = niveauMax;
    }

    /**
     * Récupère le nom complet de la maladie.
     *
     * @return Le nom complet de la maladie.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le nom abrégé de la maladie.
     *
     * @return Le nom abrégé de la maladie.
     */
    public String getNomAbrege() {
        return nomAbrege;
    }

    /**
     * Récupère le niveau actuel de la maladie.
     *
     * @return Le niveau actuel de la maladie.
     */
    public int getNiveauActuel() {
        return niveauActuel;
    }

    /**
     * Récupère le niveau maximum de la maladie.
     *
     * @return Le niveau maximum de la maladie.
     */
    public int getNiveauMax() {
        return niveauMax;
    }

    /**
     * Modifie le niveau actuel de la maladie.
     *
     * @param delta Le changement de niveau (peut être positif ou négatif).
     */
    public void changerNiveau(int delta) {
        this.niveauActuel = Math.min(Math.max(niveauActuel + delta, 0), niveauMax);
    }

    /**
     * Détermine si le niveau actuel de la maladie est létal.
     *
     * @return true si le niveau actuel est égal ou supérieur au niveau maximum (létal).
     */
    public boolean estLethal() {
        return niveauActuel >= niveauMax;
    }

    /**
     * Génère une maladie aléatoire à partir de la liste des maladies possibles.
     *
     * @return Une instance de la classe Maladie représentant une maladie aléatoire.
     */
    public static Maladie genererMaladieAleatoire() {
        Random random = new Random();
        return MALADIES.get(random.nextInt(MALADIES.size()));
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la maladie.
     *
     * @return Le nom complet de la maladie sous forme de chaîne.
     */
    @Override
    public String toString() {
        return nom + " (" + nomAbrege + ")";
    }

    /**
     * Vérifie si deux objets sont égaux en comparant leurs noms.
     *
     * @param obj L'objet à comparer.
     * @return true si les noms des deux maladies sont identiques, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maladie maladie = (Maladie) obj;
        return Objects.equals(nom, maladie.nom);
    }

    /**
     * Calcule le hachage basé sur le nom de la maladie.
     *
     * @return Le code de hachage de l'objet.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
