package model;

import model.ServiceMedical;

public class CentreDeQuarantaine extends ServiceMedical {
    private boolean isolation; // Caractéristique spécifique

    public CentreDeQuarantaine(String nom, int limitePatients, boolean isolation) {
        super(nom, limitePatients);
        this.isolation = isolation;
    }

    public CentreDeQuarantaine(String nom, boolean isolation) {
        super(nom, 20);
        this.isolation = isolation;
    }

    @Override
    public boolean ajouterCreatureSiPossible(Creature creature) {
        // Vérifie si la créature est contagieuse avant de l'ajouter
        if (creature.estContagieuse() && super.ajouterCreatureSiPossible(creature)) {
            return true;
        } else {
            System.out.println("La créature " + creature.getNom() + " n'est pas contagieuse et ne peut pas être admise dans le Centre de Quarantaine.");
            return false;
        }
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    @Override
    public void afficherRapport() {
        System.out.println("\n📋 Rapport du Centre de Quarantaine " + getNom() + " :");
        System.out.println("Isolation : " + (isolation ? "Active" : "Inactive"));
        super.afficherRapport();
    }
    
    public void reviserBudget() {
        int coutIsolation = isolation ? 500 : 0;
        int budgetTotal = coutIsolation;

        System.out.println("Révision du budget du centre de quarantaine " + getNom() + ": " +
                           "Isolation (" + (isolation ? "Active" : "Inactive") + ") = " +
                           budgetTotal + " crédits.");
    }
}
