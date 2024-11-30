import controller.SanctuaireDesAmesLuminairesController;
import controller.CreationCreature;
import controller.MaladieController;
import model.*;
import view.ConsoleView;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

        ServiceMedical serviceMedical = new ServiceMedical();
        hopital.ajouterService(serviceMedical);


        for (int i = 0; i < 2; i++) {
            List<Maladie> maladies = MaladieController.genererMaladiesAleatoires();

            Creature creature;
            int typeCreature = random.nextInt(3); // 0 = Nain, 1 = Elfe, 2 = Loup-Garou
            switch (typeCreature) {
                case 0:
                    creature = CreationCreature.creerNain();
                    break;
                case 1:
                    creature = CreationCreature.creerElfe();
                    break;
                case 2:
                    creature = CreationCreature.creerLycanthrope();
                	break;
                default:
                    throw new IllegalStateException("Type de créature inconnu");
            }

            serviceMedical.ajouterCreature(creature);
        }
    }
}