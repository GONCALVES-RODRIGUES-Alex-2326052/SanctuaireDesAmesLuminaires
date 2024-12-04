package model;

import model.ServiceMedical;

public class Crypte extends ServiceMedical {
    private int niveauVentilation; // Caractéristique spécifique
    private int temperature; // Caractéristique spécifique

    public Crypte(String nom, int limitePatients, int niveauVentilation, int temperature) {
        super(nom, limitePatients);
        this.niveauVentilation = niveauVentilation;
        this.temperature = temperature;
    }

    public Crypte(String nom, int niveauVentilation, int temperature) {
        super(nom, 20);
        this.niveauVentilation = niveauVentilation;
        this.temperature = temperature;
    }

    @Override
    public boolean ajouterCreatureSiPossible(Creature creature) {
        // Vérifie si la créature est régénérante avant de l'ajouter
        if (creature.estRegenerante() && super.ajouterCreatureSiPossible(creature)) {
            return true;
        } else {
            System.out.println("La créature " + creature.getNom() + " n'est pas régénérante et ne peut pas être admise dans la Crypte.");
            return false;
        }
    }

    public int getNiveauVentilation() {
        return niveauVentilation;
    }

    public void setNiveauVentilation(int niveauVentilation) {
        this.niveauVentilation = niveauVentilation;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void afficherRapport() {
        System.out.println("\n📋 Rapport de la Crypte " + getNom() + " :");
        System.out.println("Niveau de ventilation : " + niveauVentilation);
        System.out.println("Température : " + temperature + "°C");
        super.afficherRapport();
    }
    
    public void reviserBudget() {
        int coutVentilation = niveauVentilation * 100;
        int coutTemperature = Math.abs(22 - temperature) * 50; // Exemple de coût selon une température optimale de 22°C
        int budgetTotal = coutVentilation + coutTemperature;

        System.out.println("Révision du budget de la crypte " + getNom() + ": " +
                           "Ventilation (" + niveauVentilation + "), Température (" + temperature + 
                           "°C) = " + budgetTotal + " crédits.");
    }
}
