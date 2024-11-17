package model;

import java.util.ArrayList;
import java.util.List;

public class Hopital {
    private String nom;
    private int nombreMaxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;

    public Hopital(String nom, int nombreMaxServices) {
        this.nom = nom;
        this.nombreMaxServices = nombreMaxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
    }

    // --- Méthodes pour gérer les services médicaux ---

    public boolean ajouterService(ServiceMedical service) {
        if (services.size() < nombreMaxServices) {
            services.add(service);
            return true;
        } else {
            System.out.println("Nombre maximum de services atteint !");
            return false;
        }
    }

    public boolean supprimerService(ServiceMedical service) {
        return services.remove(service);
    }

    public List<ServiceMedical> getServices() {
        return services;
    }

    public void afficherEtat() {
        System.out.println("État de l'hôpital : " + nom);
        System.out.println("Nombre de services : " + (services != null ? services.size() : 0));
        if (services != null && !services.isEmpty()) {
            for (ServiceMedical service : services) {
                service.afficherService();  // Affiche l'état de chaque service médical
            }
        } else {
            System.out.println("Aucun service disponible.");
        }
    }

    // --- Méthodes pour gérer les médecins ---

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    public boolean supprimerMedecin(Medecin medecin) {
        return medecins.remove(medecin);
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    // --- Méthodes pour gérer l'hôpital ---

    /**
     * Affiche le nombre total de créatures dans tous les services de l'hôpital.
     */
    public void afficherNombreDeCreatures() {
        int total = 0;
        for (ServiceMedical service : services) {
            total += service.getNombreDeCreatures();
        }
        System.out.println("Nombre total de créatures dans l'hôpital : " + total);
    }

    /**
     * Affiche les caractéristiques de toutes les créatures dans tous les services.
     */
    public void afficherCreatures() {
        for (ServiceMedical service : services) {
            System.out.println("Service : " + service.getNom());
            service.afficherCreatures();
        }
    }

    // --- Simulation temporelle ---
    /**
     * Simule les événements de l'hôpital (modifications aléatoires d'état des créatures et services).
     */
    public void simulationEvenements() {
        // Modification aléatoire des créatures dans chaque service
        for (ServiceMedical service : services) {
            service.modifierEtatAleatoireDesCreatures();
        }

        // Modification aléatoire des états des services médicaux
        for (ServiceMedical service : services) {
            service.modifierEtatService();
        }
    }

    // --- Méthode d'affichage des caractéristiques ---
    public void afficherCaracteristiques() {
        for (ServiceMedical service : services) {
            System.out.println("Service : " + service.getNom());  // Affiche le nom du service
            List<Creature> creatures = service.getCreatures();  // Récupère la liste des créatures dans le service

            for (Creature creature : creatures) {
                // Affiche les caractéristiques complètes de la créature (nom, sexe, type, état, etc.)
                System.out.println("Créature : " + creature.getNom());
                System.out.println("Type : " + creature.getClass().getSimpleName());  // Affiche le type (Elfe, Nain, etc.)
                System.out.println("Sexe : " + creature.getSexe());
                System.out.println("Poids : " + creature.getPoids());
                System.out.println("Taille : " + creature.getTaille());
                System.out.println("Âge : " + creature.getAge());
                System.out.println("Moral : " + creature.getMoral());
                System.out.println("État : " + creature.getEtat());  // Affiche l'état de la créature
                System.out.println("-----------");
            }
        }
    }
}
