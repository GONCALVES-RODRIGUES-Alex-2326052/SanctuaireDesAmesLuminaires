package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ServiceMedical {
    private String nom; 
    private List<Medecin> medecins;
    private List<Creature> creatures;
    private Random rand;
    private boolean enCrise = false;

    public ServiceMedical(String nom) {
        this.nom = nom;
        this.medecins = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.rand = new Random();
    }

    public void ajouterCreature(Creature creature) {
        creatures.add(creature);
    }
    
    public boolean ajouterCreatureSiPossible(Creature creature) {
        int nombreMaxCreatures = 30;
		if (creatures.size() < nombreMaxCreatures) {
            creatures.add(creature);
            return true;
        }
        return false;
    }

    public void verifierEtSupprimerCreatures() {
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
    }

    public void afficherCreatures() {
        if (creatures.isEmpty()) {
            System.out.println("Aucune créature dans ce service.");
        } else {
            for (Creature creature : creatures) {
                System.out.println("Créature : " + creature.getNom() + " | État : " + creature.getEtat());
            }
        }
    }

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    public void retirerMedecin(Medecin medecin) {
        medecins.remove(medecin);
    }

    public void afficherMedecins() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin dans ce service.");
        } else {
            for (Medecin medecin : medecins) {
                System.out.println("Médecin : " + medecin.getNom() + " | Spécialité : " + medecin.getSpecialite());
            }
        }
    }

    public void modifierEtatAleatoireDesCreatures() {
        for (Creature creature : creatures) {
            if (rand.nextInt(100) < 10) {  // 10% de chance d'améliorer l'état
                creature.ameliorerEtat(10);
            }
        }
    }

    public void modifierEtatService() {
        if (rand.nextInt(100) < 10) {  // 10% de chance d'un changement
            System.out.println("Le service " + nom + " est en crise !");
        } else {
            System.out.println("Le service " + nom + " fonctionne normalement.");
        }
    }
    
    public void verifierEtDeclencherCrise() {
        if (rand.nextInt(100) < 10) { // 10% de chance de déclencher une crise
            enCrise = true;
            System.out.println("⚠️ Le service " + nom + " est en crise ! Soins moins efficaces.");
        } else {
            enCrise = false;
            System.out.println("✅ Le service " + nom + " fonctionne normalement.");
        }
    }
    
    public int getNombreDeCreatures() {
        return creatures.size();
    }
    
    public String getNom() {
        return nom;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void afficherService() {
        System.out.println("Service : " + nom);
        System.out.println("Nombre de créatures : " + creatures.size());
        System.out.println("Nombre de médecins : " + medecins.size());
    }
    
    private Medecin trouverMedecinDisponible() {
        for (Medecin medecin : medecins) {
            if (medecin.estDisponible(5)) {
                return medecin;
            }
        }
        return null;
    }
    
    public void afficherEtTraiterPatientsParPriorite() {
    	verifierEtDeclencherCrise();
    	
        // Trier les créatures par état croissant (les plus malades en premier)
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
                        System.out.println("En crise : Soins réduits pour " + ((Creature) creatures).getNom());
                        ((Creature) creatures).etreSoignee(medecinDisponible.getExperience() * 5); // Soins réduits
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
    
    public void afficherRapport() {
        System.out.println("\n📋 Rapport du service " + nom + " :");
        System.out.println("Nombre de créatures prises en charge : " + creatures.size());
        System.out.println("Nombre de médecins disponibles : " + medecins.size());
        System.out.println("En crise : " + (enCrise ? "Oui" : "Non"));
        afficherCreatures();
        afficherMedecins();
    }
}