package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceMedical {
    private String nom;  // Nom du service
    private List<Medecin> medecins;  // Liste des médecins dans ce service
    private List<Creature> creatures;  // Liste des créatures prises en charge dans ce service
    private Random rand;

    // Constructeur
    public ServiceMedical() {
        this.nom = nom;
        this.medecins = new ArrayList<>();
        this.creatures = new ArrayList<>();
        this.rand = new Random();
    }

    // --- Méthodes pour gérer les créatures ---

    // Ajouter une créature au service
    public void ajouterCreature(Creature creature) {
        creatures.add(creature);
    }

    // Supprimer une créature du service
    public void retirerCreature(Creature creature) {
        creatures.remove(creature);
    }

    // Afficher les créatures du service
    public void afficherCreatures() {
        if (creatures.isEmpty()) {
            System.out.println("Aucune créature dans ce service.");
        } else {
            for (Creature creature : creatures) {
                System.out.println("Créature : " + creature.getNom() + " | État : " + creature.getEtat());
            }
        }
    }

    // Obtenir le nombre de créatures dans ce service
    public int getNombreDeCreatures() {
        return creatures.size();
    }

    // --- Méthodes pour gérer les médecins ---

    // Ajouter un médecin au service
    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    // Supprimer un médecin du service
    public void retirerMedecin(Medecin medecin) {
        medecins.remove(medecin);
    }

    // Afficher les informations des médecins dans ce service
    public void afficherMedecins() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin dans ce service.");
        } else {
            for (Medecin medecin : medecins) {
                System.out.println("Médecin : " + medecin.getNom() + " | Spécialité : " + medecin.getSpecialite());
            }
        }
    }

    // --- Méthodes de gestion de l'état du service ---

    // Afficher le nom du service
    public String getNom() {
        return nom;
    }

    // Modifier l'état des créatures de façon aléatoire (exemple : 10% de chance d'améliorer l'état)
    public void modifierEtatAleatoireDesCreatures() {
        for (Creature creature : creatures) {
            if (rand.nextInt(100) < 10) {  // 10% de chance d'améliorer l'état
                creature.ameliorerEtat(10);
            }
        }
    }

    // Modifier l'état du service médical de façon aléatoire (par exemple, 10% de chance que le service soit en "crise")
    public void modifierEtatService() {
        if (rand.nextInt(100) < 10) {  // 10% de chance d'un changement
            System.out.println("Le service " + nom + " est en crise !");
        } else {
            System.out.println("Le service " + nom + " fonctionne normalement.");
        }
    }

    // --- Getter pour les créatures et médecins ---

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    // Affichage des caractéristiques du service
    public void afficherService() {
        System.out.println("Service : " + nom);
        System.out.println("Nombre de créatures : " + creatures.size());
        System.out.println("Nombre de médecins : " + medecins.size());
    }
}