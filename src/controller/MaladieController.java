package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Maladie;

/**
 * Le contrôleur de la classe Maladie gère la génération de maladies aléatoires.
 */
public class MaladieController {

    /**
     * Génère une liste de maladies aléatoires.
     * La liste contiendra entre 1 et 3 maladies, choisies au hasard parmi une liste pré-définie.
     * 
     * @return Une liste de maladies aléatoires.
     */
    public static List<Maladie> genererMaladiesAleatoires() {
        Random random = new Random();  // Générateur de nombres aléatoires
        Set<Maladie> maladiesSet = new HashSet<>();  // Utilisé pour éviter les doublons
        List<Maladie> maladies = new ArrayList<>();  // Liste finale des maladies

        // Déterminer un nombre aléatoire de maladies entre 1 et 3
        int nbMaladies = random.nextInt(3) + 1;  // 0 à 3 maladies, donc entre 1 et 3

        // Ajouter des maladies aléatoires dans le Set (pas de doublons)
        while (maladiesSet.size() < nbMaladies) {
            Maladie maladie = Maladie.genererMaladieAleatoire();  // Génération d'une maladie aléatoire
            if (!maladiesSet.contains(maladie)) {
                maladiesSet.add(maladie);  // Ajouter la maladie si elle n'est pas déjà présente
            }
        }

        // Ajouter les maladies du Set à la liste finale
        maladies.addAll(maladiesSet);
        
        return maladies;  // Retourner la liste de maladies générées
    }
}
