package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Maladie {

    private String nom;

    // Liste statique de maladies possibles
    private static final List<String> MALADIES = Arrays.asList("Maladie débilitante chronique", "Syndrome fear of missing out", "Dépendance aux réseaux sociaux",
    															"Porphyrie érythropoïétique congénitale", "Zoopathie paraphrénique lycanthropique",
    															"Obésité");

    public Maladie(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Maladie genererMaladieAleatoire() {
        Random random = new Random();
        String maladie = MALADIES.get(random.nextInt(MALADIES.size()));
        return new Maladie(maladie);
    }

    @Override
    public String toString() {
        return nom;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maladie maladie = (Maladie) obj;
        return Objects.equals(nom, maladie.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}