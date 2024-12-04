package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe ServiceMedical représente un service médical dans un hôpital.
 * Elle gère les créatures (patients), les médecins et leurs interactions,
 * ainsi que les crises potentielles au sein du service.
 */
public class ServiceMedical {

    /** Nom du service médical. */
    private String nom;

    /** Liste des médecins affectés à ce service. */
    private List<Medecin> medecins;

    /** Liste des créatures (patients) dans ce service. */
    private List<Creature> creatures;

    /** Générateur de nombres aléatoires pour simuler des événements aléatoires. */
    private Random rand;

    /** Indique si le service est en crise. */
    private boolean enCrise = false;

    /** Limite maximale de patients que le service peut gérer efficacement. */
    private int limitePatients;

    /**
     * Constructeur principal de la classe ServiceMedical.
     * 
     * @param nom            Nom du service médical.
     * @param limitePatients Nombre maximum de patients que le service peut gérer.
     */
    public ServiceMedical(String nom, int limitePatients) {
        this.nom = nom;
        this.medecins = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.rand = new Random();
        this.limitePatients = limitePatients;
    }

    /**
     * Constructeur alternatif avec une limite de patients par défaut.
     * 
     * @param nom Nom du service médical.
     */
    public ServiceMedical(String nom) {
        this(nom, 10); // Limite par défaut de 10 patients.
    }

    /**
     * Ajoute une créature à la liste des créatures du service.
     * 
     * @param creature La créature à ajouter.
     */
    public void ajouterCreature(Creature creature) {
        creatures.add(creature);
    }

    /**
     * Tente d'ajouter une créature au service, en respectant une limite maximale.
     * 
     * @param creature La créature à ajouter.
     * @return true si la créature a été ajoutée avec succès, sinon false.
     */
    public boolean ajouterCreatureSiPossible(Creature creature) {
        int nombreMaxCreatures = 30;
        if (creatures.size() < nombreMaxCreatures) {
            creatures.add(creature);
            return true;
        }
        return false;
    }

    /**
     * Vérifie les créatures dans le service et supprime celles dont l'état est à 100.
     * 
     * @return La liste des créatures supprimées.
     */
    public List<Creature> verifierEtSupprimerCreatures() {
        List<Creature> aSupprimer = new ArrayList<>();
        for (Creature creature : creatures) {
            if (creature.getEtat() >= 100) {
                aSupprimer.add(creature);
            }
        }
        for (Creature creature : aSupprimer) {
            System.out.println("Suppression de la créature : " + creature.getNom() + " car son état est à 100.");
            creatures.remove(creature);
        }
        return aSupprimer;
    }

    /**
     * Affiche les informations des créatures du service.
     */
    public void afficherCreatures() {
        if (creatures.isEmpty()) {
            System.out.println("Aucune créature dans ce service.");
        } else {
            for (Creature creature : creatures) {
                System.out.println("Créature : " + creature.getNom() + " | État : " + creature.getEtat());
            }
        }
    }

    /**
     * Ajoute un médecin à la liste des médecins du service.
     * 
     * @param medecin Le médecin à ajouter.
     */
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    /**
     * Retire un médecin de la liste des médecins du service.
     * 
     * @param medecin Le médecin à retirer.
     */
    public void retirerMedecin(Medecin medecin) {
        medecins.remove(medecin);
    }

    /**
     * Affiche les informations des médecins affectés au service.
     */
    public void afficherMedecins() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin dans ce service.");
        } else {
            for (Medecin medecin : medecins) {
                System.out.println("Médecin : " + medecin.getNom() + " | Spécialité : " + medecin.getSpecialite());
            }
        }
    }

    /**
     * Modifie aléatoirement l'état des créatures, avec une faible probabilité d'amélioration.
     */
    public void modifierEtatAleatoireDesCreatures() {
        for (Creature creature : creatures) {
            if (rand.nextInt(100) < 10) { // 10% de chance d'améliorer l'état.
                creature.ameliorerEtat(10);
            }
        }
    }

    /**
     * Modifie l'état général du service, en simulant des crises potentielles.
     */
    public void modifierEtatService() {
        if (rand.nextInt(100) < 10) { // 10% de chance d'une crise.
            System.out.println("Le service " + nom + " est en crise !");
        } else {
            System.out.println("Le service " + nom + " fonctionne normalement.");
        }
    }

    /**
     * Vérifie et déclenche une crise dans le service avec une probabilité donnée.
     */
    public void verifierEtDeclencherCrise() {
        if (rand.nextInt(100) < 10) { // 10% de chance de déclencher une crise.
            enCrise = true;
            System.out.println("⚠️ Le service " + nom + " est en crise ! Soins moins efficaces.");
        } else {
            enCrise = false;
            System.out.println("✅ Le service " + nom + " fonctionne normalement.");
        }
    }

    /**
     * Retourne le nombre de créatures présentes dans le service.
     * 
     * @return Nombre de créatures.
     */
    public int getNombreDeCreatures() {
        return creatures.size();
    }

    /**
     * Retourne le nom du service médical.
     * 
     * @return Nom du service.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste des médecins du service.
     * 
     * @return Liste des médecins.
     */
    public List<Medecin> getMedecins() {
        return medecins;
    }

    /**
     * Retourne la liste des créatures du service.
     * 
     * @return Liste des créatures.
     */
    public List<Creature> getCreatures() {
        return creatures;
    }

    /**
     * Retourne la limite maximale de patients pour ce service.
     * 
     * @return Limite maximale de patients.
     */
    public int getLimitePatients() {
        return limitePatients;
    }

    /**
     * Modifie la limite maximale de patients pour ce service.
     * 
     * @param limitePatients Nouvelle limite de patients.
     */
    public void setLimitePatients(int limitePatients) {
        this.limitePatients = limitePatients;
    }

    /**
     * Affiche un résumé de l'état actuel du service.
     */
    public void afficherService() {
        System.out.println("Service : " + nom);
        System.out.println("Nombre de créatures : " + creatures.size());
        System.out.println("Nombre de médecins : " + medecins.size());
    }

    /**
     * Trouve un médecin disponible dans le service pour soigner un patient.
     * 
     * @return Un médecin disponible ou null si aucun n'est disponible.
     */
    private Medecin trouverMedecinDisponible() {
        for (Medecin medecin : medecins) {
            if (medecin.estDisponible(5)) {
                return medecin;
            }
        }
        return null;
    }

    /**
     * Affiche une liste des patients à traiter par priorité (les plus malades en premier).
     * Propose à l'utilisateur de choisir un patient à soigner.
     */
    public void afficherEtTraiterPatientsParPriorite() {
        verifierEtDeclencherCrise();

        // Trier les créatures par état croissant (les plus malades en premier).
        creatures.sort((c1, c2) -> Integer.compare(c1.getEtat(), c2.getEtat()));

        System.out.println("\n--- Créatures nécessitant des soins ---");
        for (int i = 0; i < creatures.size(); i++) {
            Creature creature = creatures.get(i);
            System.out.println((i + 1) + ". " + creature.getNom() + " (État : " + creature.getEtat() + ")");
        }

        if (!creatures.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nChoisissez une créature à soigner (1-" + creatures.size() + ") ou 0 pour annuler : ");
            int choix = scanner.nextInt();

            if (choix > 0 && choix <= creatures.size()) {
                Creature creatureChoisie = creatures.get(choix - 1);
                Medecin medecinDisponible = trouverMedecinDisponible();

                if (medecinDisponible != null) {
                    if (enCrise) {
                        System.out.println("En crise : Soins réduits pour " + creatureChoisie.getNom());
                        creatureChoisie.etreSoignee(medecinDisponible.getExperience() * 5); // Soins réduits
                    } else {
                        System.out.println("\n" + medecinDisponible.getNom() + " soigne " + creatureChoisie.getNom() + "...");
                        medecinDisponible.soigner(creatureChoisie);
                    }
                } else {
                    System.out.println("Pas de médecin disponible pour soigner " + creatureChoisie.getNom());
                }
            } else if (choix == 0) {
                System.out.println("Aucune créature soignée.");
            } else {
                System.out.println("Choix invalide.");
            }
        } else {
            System.out.println("Aucune créature nécessitant des soins.");
        }
    }

    /**
     * Assigne les patients (créatures) aux médecins disponibles dans le service.
     */
    public void assignerPatientsAuxMedecins() {
        for (Creature creature : creatures) {
            boolean prisEnCharge = false;

            // Vérifier si la créature est déjà prise en charge.
            for (Medecin medecin : medecins) {
                if (medecin.getPatients().contains(creature)) {
                    prisEnCharge = true;
                    break;
                }
            }

            // Si la créature n'est pas prise en charge, assigner un médecin disponible.
            if (!prisEnCharge) {
                for (Medecin medecin : medecins) {
                    if (medecin.estDisponible(limitePatients)) {
                        medecin.getPatients().add(creature);
                        System.out.println("Créature " + creature.getNom() + " assignée au médecin " + medecin.getNom());
                        break;
                    }
                }
            }
        }
    }

    /**
     * Affiche un rapport détaillé sur l'état actuel du service.
     */
    public void afficherRapport() {
        System.out.println("\n📋 Rapport du service " + nom + " :");
        System.out.println("Nombre de créatures prises en charge : " + creatures.size());
        System.out.println("Nombre de médecins disponibles : " + medecins.size());
        System.out.println("En crise : " + (enCrise ? "Oui" : "Non"));
        afficherCreatures();
        afficherMedecins();
    }
}
