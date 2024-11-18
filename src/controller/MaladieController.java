package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Maladie;

public class MaladieController {
	public static List<Maladie> genererMaladiesAleatoires() {
        Random random = new Random();
        Set<Maladie> maladiesSet = new HashSet<>();
        List<Maladie> maladies = new ArrayList<>();

        int nbMaladies = random.nextInt(3)+1;  // 0 à 3 maladies
        while (maladiesSet.size() < nbMaladies) {
            Maladie maladie = Maladie.genererMaladieAleatoire();
            if (!maladiesSet.contains(maladie)) {
                maladiesSet.add(maladie);  // Ajouter la maladie si elle n'est pas déjà présente
            }
        }
        maladies.addAll(maladiesSet);
        return maladies;
    }
}
