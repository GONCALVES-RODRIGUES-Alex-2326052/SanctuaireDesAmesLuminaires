package model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Maladie {

    private String nom;

    // Liste statique de maladies possibles
    private static final List<String> MALADIES = Arrays.asList("Grippe", "Rhume", "Blessure", "Infection");

    public Maladie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public static Maladie genererMaladieAleatoire() {
        Random random = new Random();
        // Sélectionner une maladie aléatoire dans la liste
        String maladie = MALADIES.get(random.nextInt(MALADIES.size()));
        return new Maladie(maladie);
    }

    @Override
    public String toString() {
        return nom;
    }
}