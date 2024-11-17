import controller.SanctuaireDesAmesLuminairesController;
import controller.CreationCreature;
import model.*;
import view.ConsoleView;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SanctuaireDesAmesLuminaires {

    public static void main(String[] args) {
        Hopital hopital = new Hopital("Sanctuaire des Âmes Luminaires", 10);
        ConsoleView view = new ConsoleView();

        SanctuaireDesAmesLuminairesController controller = new SanctuaireDesAmesLuminairesController(hopital, view);
        genererCreaturesAleatoires(hopital);
        controller.demarrer();
    }

    // Méthode pour générer des créatures aléatoires et les ajouter à l'hôpital
    public static void genererCreaturesAleatoires(Hopital hopital) {
        Random random = new Random();

        // Création d'un service médical pour accueillir les créatures
        ServiceMedical serviceMedical = new ServiceMedical();
        hopital.ajouterService(serviceMedical);


        for (int i = 0; i < 2; i++) {
            String nom = CreationCreature.genererNom();
            String sexe = random.nextBoolean() ? "Mâle" : "Femelle";
            double poids = 50 + random.nextDouble() * 50;  // Poids entre 50 et 100 kg
            double taille = 1.5 + random.nextDouble() * 0.5;  // Taille entre 1.5 et 2.0 m
            int age = 10 + random.nextInt(50);  // Âge entre 10 et 60 ans
            int moral = 50 + random.nextInt(51); 

            List<Maladie> maladies = genererMaladiesAleatoires();

            Creature creature;
            if (random.nextBoolean()) {
                creature = new Nain(nom, sexe, poids, taille, age, moral, maladies);
            } else {
                creature = new Elfe(nom, sexe, poids, taille, age, moral, maladies);
            }

            // Ajouter la créature au service
            serviceMedical.ajouterCreature(creature);
        }
    }

    // Méthode pour générer une liste de maladies aléatoires
    public static List<Maladie> genererMaladiesAleatoires() {
        Random random = new Random();
        List<Maladie> maladies = new ArrayList<>();

        // Exemple de générer entre 0 et 3 maladies aléatoires
        int nbMaladies = random.nextInt(4);  // 0 à 3 maladies
        for (int i = 0; i < nbMaladies; i++) {
            // Générer une maladie aléatoire à partir de la classe Maladie
            Maladie maladie = Maladie.genererMaladieAleatoire();
            maladies.add(maladie);
        }

        return maladies;
    }
}