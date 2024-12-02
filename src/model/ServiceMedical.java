package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceMedical {
    private String nom; 
    private List<Medecin> medecins;
    private List<Creature> creatures;
    private Random rand;

    public ServiceMedical(String nom) {
        this.nom = nom;
        this.medecins = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.rand = new Random();
    }

    public void ajouterCreature(Creature creature) {
        creatures.add(creature);
    }

    public void retirerCreature(Creature creature) {
        creatures.remove(creature);
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
    public int getNombreDeCreatures() {
        return creatures.size();
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

    public String getNom() {
        return nom;
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

        creatures.sort((c1, c2) -> Integer.compare(c1.getEtat(), c2.getEtat()));
        afficherCreatures();

        if (!creatures.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            int choix = scanner.nextInt();

            if (choix > 0 && choix <= creatures.size()) {
                Creature creatureChoisie = creatures.get(choix - 1);
                Medecin medecinDisponible = trouverMedecinDisponible();

                if (medecinDisponible != null) {
                    if (enCrise) {
                        creatureChoisie.diminuerEtat(5);
                        System.out.println("Service en crise, soins limités.");
                    }
                    medecinDisponible.soigner(creatureChoisie);
                }
            }
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