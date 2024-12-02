package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.CreationCreature;
import controller.MaladieController;

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

    public boolean ajouterService(ServiceMedical service) {
    	if (!services.contains(service)) {
            if (services.size() < nombreMaxServices) {
                services.add(service);
                return true;
            } else {
                System.out.println("Nombre maximum de services atteint !");
                return false;
            }
        } else {
            System.out.println("Service déjà ajouté.");
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
                service.afficherService();
            }
        } else {
            System.out.println("Aucun service disponible.");
        }
    }

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }
    
    public boolean supprimerMedecin(Medecin medecin) {
        return medecins.remove(medecin);
    }

    public void genererMedecinsParDefaut() {
        for (ServiceMedical service : services) {
            if (!service.getCreatures().isEmpty()) {
                for (int i = 0; i < 2; i++) {
                    String nom = "Médecin-" + (i + 1);
                    String specialite = i % 2 == 0 ? "Guérisseur" : "Chirurgien";
                    Medecin medecin = new Medecin(nom, specialite, 10 + i * 5);
                    service.ajouterMedecin(medecin);
                }
            }
        }
    }
    
    public List<Medecin> getMedecins() {
        return medecins;
    }

    // Méthode pour générer des créatures aléatoires et les ajouter à l'hôpital
    public static void genererCreaturesParDefaut(Hopital hopital) {
        Random random = new Random();

        ServiceMedical serviceMedical = new ServiceMedical();
        hopital.ajouterService(serviceMedical);
        
        Class<? extends Creature>[] typesCreatures = new Class[] {
                Elfe.class, HommeBete.class, Nain.class, Orque.class, Reptilien.class, Vampire.class, Zombie.class
            };
        for (int i = 0; i < 2; i++) {
            String nom = CreationCreature.genererNom();
            String sexe = random.nextBoolean() ? "Mâle" : "Femelle";
            double poids = 50 + random.nextDouble() * 50;  // Poids entre 50 et 100 kg
            double taille = 1.5 + random.nextDouble() * 0.5;  // Taille entre 1.5 et 2.0 m
            int age = 10 + random.nextInt(50);  // Âge entre 10 et 60 ans
            int moral = 50 + random.nextInt(51); 

            List<Maladie> maladies = MaladieController.genererMaladiesAleatoires();

            // Sélectionner aléatoirement une classe de créature
            Class<? extends Creature> creatureClass = typesCreatures[random.nextInt(typesCreatures.length)];

            // Créer la créature en fonction de la classe sélectionnée
            Creature creature = null;
            try {
                if (creatureClass.equals(Elfe.class)) {
                    creature = new Elfe(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(HommeBete.class)) {
                    creature = new HommeBete(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(Nain.class)) {
                    creature = new Nain(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(Orque.class)) {
                    creature = new Orque(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(Reptilien.class)) {
                    creature = new Reptilien(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(Vampire.class)) {
                    creature = new Vampire(nom, sexe, poids, taille, age, moral, maladies);
                } else if (creatureClass.equals(Zombie.class)) {
                    creature = new Zombie(nom, sexe, poids, taille, age, moral, maladies);
                }
            } catch (Exception e) {
                e.printStackTrace();  // En cas d'erreur de création de la créature
            }
            serviceMedical.ajouterCreature(creature);
        }
    }
    
    public void afficherNombreDeCreatures() {
        int total = 0;
        for (ServiceMedical service : services) {
            total += service.getNombreDeCreatures();
        }
        System.out.println("Nombre total de créatures dans l'hôpital : " + total);
    }

    public void afficherCreatures() {
        for (ServiceMedical service : services) {
            System.out.println("Service : " + service.getNom());
            service.afficherCreatures();
        }
    }

    public void simulationEvenements() {
        for (ServiceMedical service : services) {
            service.modifierEtatAleatoireDesCreatures();
        }
        for (ServiceMedical service : services) {
            service.modifierEtatService();
        }
    }

    public void assignerPatientsMedecins() {
    	for (ServiceMedical service : services) {
    		for(Creature creature : service.getCreatures()) {
    			boolean prisEnCharge = false;
    			for(Medecin medecin : service.getMedecins()) {
    				if(medecin.getPatients().contains(creature)) {
    					prisEnCharge= true;
    					break;
    				}
    			}
    			if(!prisEnCharge) {
    				for(Medecin medecin : service.getMedecins()) {
    					medecin.getPatients().add(creature);
    					System.out.println("Creature "+creature.getNom()+" assignee au medecin "+medecin.getNom());
    					break;
    				}
    			}
    		}
    	}
    }
   
    public void afficherCaracteristiques() {
        for (ServiceMedical service : services) {
            //System.out.println("Service : " + service.getNom());
            List<Creature> creatures = service.getCreatures();

            for (Creature creature : creatures) {
                System.out.println("Créature : " + creature.getNom());
                System.out.println("Type : " + creature.getClass().getSimpleName());
                System.out.println("Sexe : " + creature.getSexe());
                System.out.println("Poids : " + creature.getPoids());
                System.out.println("Taille : " + creature.getTaille());
                System.out.println("Âge : " + creature.getAge());
                System.out.println("Moral : " + creature.getMoral());
                System.out.println("État : " + creature.getEtat());
                System.out.println("-----------");
            }
        }
    }
    
    public void evaluerHopital() {
        int totalCreatures = 0;
        int totalEtat = 0;
        int creaturesGueries = 0;

        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                totalCreatures++;
                totalEtat += creature.getEtat();
                if (creature.getEtat() == 100) {
                    creaturesGueries++;
                }
            }
        }

        double tauxMoyenEtat = totalCreatures > 0 ? (double) totalEtat / totalCreatures : 0;

        System.out.println("Évaluation de l'hôpital :");
        System.out.println("- Nombre total de créatures : " + totalCreatures);
        System.out.println("- Taux moyen d'état : " + tauxMoyenEtat);
        System.out.println("- Nombre de créatures guéries : " + creaturesGueries);
    }
}
