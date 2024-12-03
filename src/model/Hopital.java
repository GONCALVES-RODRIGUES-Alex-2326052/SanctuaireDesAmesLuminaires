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

    public ServiceMedical ajouterService(ServiceMedical service) {
        if (service == null) {
            System.out.println("Le service passé est nul.");
            return null;
        }

        if (!services.contains(service)) {
            if (services.size() < nombreMaxServices) {
                services.add(service);
                return service;
            } else {
                System.out.println("Nombre maximum de services atteint !");
            }
        } else {
            System.out.println("Service déjà ajouté.");
        }
        return null;
    }

    private boolean partiePerdue = false;

    public boolean isPartiePerdue() {
        return partiePerdue;
    }

    public void setPartiePerdue(boolean partiePerdue) {
        this.partiePerdue = partiePerdue;
    }

    // Si un nom est fourni, crée un nouveau service et l'ajoute
    public ServiceMedical ajouterService(String nom) {
        return ajouterService(new ServiceMedical(nom));
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
    
    public void genererCreaturesAleatoires(int nombreCreatures) {
        Random random = new Random();
        ServiceMedical serviceMedical = services.isEmpty() ? new ServiceMedical("Service Général") : services.get(0);

        // Ajouter des créatures
        List<String> typesCreatures = List.of(
            "elfe", "nain", "orque", "vampire", "zombie", "hommebete", "lycanthrope", "reptilien"
        );
        for (int i = 0; i < nombreCreatures; i++) {
        	String typeAleatoire = typesCreatures.get(random.nextInt(typesCreatures.size()));
        	boolean ajoute = false;
            try {
                Creature creature = CreationCreature.creerCreature(typeAleatoire);
                for (ServiceMedical service : services) {
                    if (service.ajouterCreatureSiPossible(creature)) {
                        ajoute = true;
                        break;
                    }
                }
                
             // Si aucun service existant ne convient, créer un nouveau service si possible
                if (!ajoute) {
                    if (services.size() < nombreMaxServices) {
                        ServiceMedical nouveauService = new ServiceMedical("Service " + typeAleatoire);
                        nouveauService.ajouterCreature(creature);
                        ajouterService(nouveauService);
                        ajoute = true;
                    }
                }
                if (!ajoute) {
                    System.out.println("L'hôpital est plein, impossible d'accueillir une nouvelle créature !");
                    this.setPartiePerdue(true); // Déclare la partie comme perdue
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Erreur lors de la création de la créature : " + e.getMessage());
            }
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
