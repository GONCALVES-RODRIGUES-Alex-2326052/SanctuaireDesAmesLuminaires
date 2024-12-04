package controller;

import model.*;
import view.ConsoleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SanctuaireDesAmesLuminairesController {
    private final Hopital hopital;
    private final ConsoleView view;
    private final MedecinController medecinController;
    private int jourActuel = 1;
    private List<Creature> creatures;

    public SanctuaireDesAmesLuminairesController(Hopital hopital, ConsoleView view) {
        this.hopital = hopital;
        this.view = view;
        this.medecinController = new MedecinController(view);
    }

    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
        	afficherMenuPrincipal();

            int choix = view.demanderChoix("Sélectionnez une option :", 1, 6);

            switch (choix) {
                case 1 -> afficherEtatHopital();
                case 2 -> gererService();
                case 3 -> afficherCreatures();
                case 4 -> afficherRapportDuService();
                case 5 -> passerAuJourSuivant();
                case 6 -> continuer = false;
            }
        }
    }
    
    private void afficherMenuPrincipal() {
        view.afficherMessage("\n---- Jour " + jourActuel + " ----");
        view.afficherMessage("1. Afficher l'état de l'hôpital");
        view.afficherMessage("2. Gérer un service");
        view.afficherMessage("3. Afficher les créatures");
        view.afficherMessage("4. Afficher un rapport du service médical");
        view.afficherMessage("5. Passer au jour suivant");
        view.afficherMessage("6. Quitter");
    }
    
    private void afficherEtatHopital() {
        hopital.afficherEtat();
    }

    private void gererService() {
        List<ServiceMedical> services = hopital.getServices();
        if (services.isEmpty()) {
            view.afficherMessage("Aucun service médical disponible.");
            return;
        }
        view.afficherMessage("\nListe des services disponibles :");
        for (int i = 0; i < services.size(); i++) {
        	ServiceMedical service = services.get(i);
            view.afficherMessage((i + 1) + ". " + services.get(i).getNom() + " - "
                + service.getNombreDeCreatures());
        }
        
        int index = view.demanderChoix("Choisissez un service à gérer:", 1, services.size()) - 1;
        ServiceMedical service = services.get(index);

        view.afficherMessage("1. Soigner les créatures");
        view.afficherMessage("2. Afficher les créatures");
        view.afficherMessage("3. Afficher les informations des médecins");
        view.afficherMessage("4. Traiter les créatures prioritaires");

        int choix = view.demanderChoix("Sélectionnez une action :", 1, 4);

        switch (choix) {
            case 1 -> afficherEtChoisirMedecin(service);
            case 2 -> service.afficherService();
            case 3 -> afficherMedecins(service);
            case 4 -> service.afficherEtTraiterPatientsParPriorite();
        }
    }
    
    private void afficherEtChoisirMedecin(ServiceMedical service) {
        List<Medecin> medecins = service.getMedecins();
        if (medecins.isEmpty()) {
            view.afficherMessage("Aucun médecin disponible dans ce service.");
            return;
        }

        // Liste des médecins disponibles
        List<Medecin> medecinsDisponibles = medecins.stream()
            .filter(medecin -> medecin.estDisponible(service.getLimitePatients()))
            .toList();

        if (medecinsDisponibles.isEmpty()) {
            view.afficherMessage("Aucun médecin n'est actuellement disponible pour soigner une créature.");
            return;
        }

        view.afficherMessage("Médecins disponibles pour soigner une créature :");
        for (int i = 0; i < medecinsDisponibles.size(); i++) {
            Medecin medecin = medecinsDisponibles.get(i);
            view.afficherMessage((i + 1) + ". " + medecin.getNom() + " - Spécialité : " 
                + medecin.getSpecialite() + " (Patients : " + medecin.getPatients().size() + ")");
        }

        int index = view.demanderChoix("Choisissez un médecin pour soigner la créature :", 1, medecinsDisponibles.size()) - 1;
        Medecin medecinChoisi = medecinsDisponibles.get(index);

        // Choisir une créature à soigner
        List<Creature> creatures = service.getCreatures();
        if (creatures.isEmpty()) {
            view.afficherMessage("Aucune créature n'est actuellement disponible dans ce service.");
            return;
        }

        view.afficherMessage("Liste des créatures disponibles :");
        for (int i = 0; i < creatures.size(); i++) {
            Creature creature = creatures.get(i);
            view.afficherMessage((i + 1) + ". " + creature.getNom() + " (État : " + creature.getEtat() + ")");
        }

        int creatureIndex = view.demanderChoix("Choisissez une créature à soigner :", 1, creatures.size()) - 1;
        Creature creatureChoisie = creatures.get(creatureIndex);

        medecinChoisi.soigner(creatureChoisie); // Le médecin soigne la créature
        if (!medecinChoisi.getPatients().contains(creatureChoisie)) {
            medecinChoisi.getPatients().add(creatureChoisie); // Ajoute la créature à la liste des patients
        }
    }

    
    private void afficherMedecins(ServiceMedical service) {
        List<Medecin> medecins = service.getMedecins();
        if (medecins.isEmpty()) {
            view.afficherMessage("Aucun médecin disponible dans ce service.");
            return;
        }

        int medecinIndex = view.demanderChoix("Choisissez un médecin pour afficher ses infos :", 1, medecins.size()) - 1;
        Medecin medecin = medecins.get(medecinIndex);
        medecinController.afficherInfoMedecin(medecin);
    }
    
    private void afficherCreatures() {
    	System.out.println("Liste des créatures présentes dans l'hôpital :");
        hopital.afficherCaracteristiques();
    }
    
    private void passerAuJourSuivant() {
        jourActuel++;
        List<Creature> creaturesQuiOntQuitte = new ArrayList<>();
        for (ServiceMedical service : hopital.getServices()) {
            service.modifierEtatAleatoireDesCreatures();
            service.verifierEtDeclencherCrise();
            creaturesQuiOntQuitte.addAll(service.verifierEtSupprimerCreatures());// Récupérer les créatures supprimées
            service.assignerPatientsAuxMedecins();
        }
        // Afficher un message si des créatures ont quitté l'hôpital
        if (!creaturesQuiOntQuitte.isEmpty()) {
            view.afficherMessage("\nCréatures qui ont quitté l'hôpital aujourd'hui :");
            for (Creature creature : creaturesQuiOntQuitte) {
                view.afficherMessage("- " + creature.getNom() + " (" + creature.getClass().getSimpleName() + ")");
            }
        } else {
            view.afficherMessage("\nAucune créature n'a quitté l'hôpital aujourd'hui.");
        }
        if (jourActuel == 4) {
            int nombreNouvellesCreatures = 1 + new Random().nextInt(5); // 1 à 5 créatures
            hopital.genererCreaturesAleatoires(nombreNouvellesCreatures);
        } else if (jourActuel % 2 == 0) {
            hopital.genererCreaturesAleatoires(1); // Une nouvelle créature tous les 2 jours
        }

        // Vérifie si la partie est perdue
        if (hopital.isPartiePerdue()) {
            view.afficherMessage("L'hôpital est plein ! Vous avez perdu.");
            System.exit(0); // Arrête l'exécution
        }
        view.afficherMessage("Le jour " + jourActuel + " commence.");
    }
    
    private void afficherRapportDuService() {
        view.afficherMessage("\n---- Rapport du service médical ----");
        for (ServiceMedical service : hopital.getServices()) {
            service.afficherRapport();
        }
    }
}