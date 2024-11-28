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

    public List<Medecin> getMedecins() {
        return medecins;
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
}
