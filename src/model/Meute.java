package model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Meute{
    private List<LoupGarous> loupsGarous;
    private LoupGarous maleAlpha;
    private LoupGarous femelleAlpha;
    private String nomMeute;

    public Meute(String nomMeute) {
        this.loupsGarous = new ArrayList<>();
        this.femelleAlpha = null;
        this.maleAlpha = null;
        this.nomMeute = nomMeute;
        }

    public void ajouterLoup(LoupGarous loup) {
        loupsGarous.add(loup);
    }

    public void organiserHiérarchie() {
        // Vérifie qu'il y a des loups dans la meute
        if (loupsGarous.isEmpty()) {
            System.out.println("Aucun loup dans la meute !");
            return;
        }

        // Trier les loups par niveau décroissant
        loupsGarous.sort(Comparator.comparingDouble(LoupGarous::getNiveau).reversed());

        // Réinitialiser les Alphas
        maleAlpha = null;
        femelleAlpha = null;

        // Attribuer les rangs en commençant par les Alphas
        int index = 0;
        for (LoupGarous loup : loupsGarous) {
            // Déterminer les Alphas
            if (maleAlpha == null && loup.getSexe().equalsIgnoreCase("Mâle")) {
                maleAlpha = loup;
                loup.setRangGrec("α");
            } else if (femelleAlpha == null && loup.getSexe().equalsIgnoreCase("Femelle")) {
                femelleAlpha = loup;
                loup.setRangGrec("α");
            } else {
                // Pour les autres loups
                loup.setRangGrec(LoupGarous.rangGrec(index));
                index++;
            }
        }
        // Vérification si les Alphas ont été trouvés
        if (maleAlpha == null) {
            System.out.println("Aucun mâle alpha trouvé !");
        }
        if (femelleAlpha == null) {
            System.out.println("Aucune femelle alpha trouvée !");
        }
    }
	    public void afficherHiérarchie() {
	        System.out.println("\nHiérarchie de la meute :\n");
	        for (LoupGarous loup : loupsGarous) {
	            System.out.println(loup.getNom() + " - Rang : " + loup.getRangGrec() + " - Niveau : " + loup.getNiveau() + "\n");
	        }
	    }
	    public String getNomMeute() {
	        return nomMeute;
	    }
    }

