package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * La classe Maladie représente une maladie pouvant affecter une créature.
 * Chaque maladie est identifiée par un nom et peut être générée aléatoirement
 * à partir d'une liste prédéfinie de maladies possibles.
 */
public class Maladie {

    private String nom; // Nom de la maladie

    // Liste statique de maladies possibles
    private static final List<String> MALADIES = Arrays.asList(
        "Maladie débilitante chronique",
        "Syndrome fear of missing out",
        "Dépendance aux réseaux sociaux",
        "Porphyrie érythropoïétique congénitale",
        "Zoopathie paraphrénique lycanthropique",
        "Obésité"
    );

    /**
     * Constructeur de la classe Maladie.
     *
     * @param nom Le nom de la maladie.
     */
    public Maladie(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le nom de la maladie.
     *
     * @return Le nom de la maladie.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la maladie.
     *
     * @param nom Le nouveau nom de la maladie.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Génère une maladie aléatoire à partir de la liste des maladies possibles.
     *
     * @return Une instance de la classe Maladie représentant une maladie aléatoire.
     */
    public static Maladie genererMaladieAleatoire() {
        Random random = new Random();
        String maladie = MALADIES.get(random.nextInt(MALADIES.size()));
        return new Maladie(maladie);
    }

    /**
     * Renvoie une représentation sous forme de chaîne de caractères de la maladie.
     *
     * @return Le nom de la maladie sous forme de chaîne.
     */
    @Override
    public String toString() {
        return nom;
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
