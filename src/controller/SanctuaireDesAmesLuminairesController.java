package controller;

import model.*;
import view.ConsoleView;
import java.util.List;

public class SanctuaireDesAmesLuminairesController {
    private final Hopital hopital;
    private final ConsoleView view;
    private final MedecinController medecinController;

    public SanctuaireDesAmesLuminairesController(Hopital hopital, ConsoleView view) {
        this.hopital = hopital;
        this.view = view;
        this.medecinController = new MedecinController(view);
    }

    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            view.afficherMessage("1. Afficher l'état de l'hôpital");
            view.afficherMessage("2. Gérer un service");
            view.afficherMessage("3. Afficher les créatures");
            view.afficherMessage("4. Quitter");

            int choix = view.demanderChoix("Sélectionnez une option :", 1, 4);

            switch (choix) {
                case 1 -> afficherEtatHopital();
                case 2 -> gererService();
                case 3 -> afficherCreatures();  // Nouvelle option
                case 4 -> continuer = false;
            }
        }
    }

    private void afficherEtatHopital() {
        hopital.afficherEtat();
    }

    private void gererService() {
        List<ServiceMedical> services = hopital.getServices();
        int index = view.demanderChoix("Choisissez un service à gérer:", 1, services.size()) - 1;
        ServiceMedical service = services.get(index);

        view.afficherMessage("1. Soigner les créatures");
        view.afficherMessage("2. Afficher les créatures");
        view.afficherMessage("3. Afficher les informations des médecins");

        int choix = view.demanderChoix("Sélectionnez une action :", 1, 3);

        switch (choix) {
            case 1 -> medecinController.soigner(service);
            case 2 -> service.afficherService();
            case 3 -> {
                // Afficher les informations des médecins
                List<Medecin> medecins = service.getMedecins();
                int medecinIndex = view.demanderChoix("Choisissez un médecin pour afficher ses infos :", 1, medecins.size()) - 1;
                Medecin medecin = medecins.get(medecinIndex);
                medecinController.afficherInfoMedecin(medecin);
            }
        }
    }
    private void afficherCreatures() {
    	System.out.println("Liste des créatures présentes dans l'hôpital :");
        hopital.afficherCaracteristiques();
    }

    private void passerAuJourSuivant() {
        jourActuel++;
        hopital.getServices().forEach(service -> {
            service.modifierEtatAleatoireDesCreatures();
            service.verifierEtDeclencherCrise();
        });
        view.afficherMessage("Le jour " + jourActuel + " commence avec de nouveaux défis !");
    }
    
    private void afficherRapportDuService() {
        view.afficherMessage("\n---- Rapport du service médical ----");
        for (ServiceMedical service : hopital.getServices()) {
            service.afficherRapport();
        }
    }
}