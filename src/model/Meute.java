package model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Meute {


    private List<LoupGarous> loupsGarous;
    private String nomMeute;

    public Meute(String nomMeute) {
        this.loupsGarous = new ArrayList<>();
        this.nomMeute = nomMeute;
        }

    public void ajouterLoup(LoupGarous loup) {
    }

    public void organiserHiérarchie() {
        // Vérifie qu'il y a des loups dans la meute
        if (loupsGarous.isEmpty()) {
            System.out.println("Aucun loup dans la meute !");
            return;
        }

        // Trier les loups par niveau décroissant
        loupsGarous.sort(Comparator.comparingDouble(LoupGarous::getNiveau).reversed());
    }
    
    public String getNomMeute() {
        return nomMeute;
    }
        
        public void afficherHiérarchie() {
    System.out.println("\nHiérarchie de la meute :\n");
    for (LoupGarous loup : loupsGarous) {
        System.out.println(loup.getNom() + " - Rang : " + loup.getRangGrec() + " - Niveau : " + loup.getNiveau() + "\n");
    }
}
}

