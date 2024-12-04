package model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Meute{
    private List<LoupGarous> loupsGarous;
    private String nomMeute;

    public Meute(String nomMeute) {
        this.loupsGarous = new ArrayList<>();
        this.nomMeute = nomMeute;
        }

    public void ajouterLoup(LoupGarous loup) {
        loupsGarous.add(loup);
    }

    public void organiserHiérarchie() {
        if (loupsGarous.isEmpty()) {
            System.out.println("Aucun loup dans la meute !");
            return;
        }

        loupsGarous.sort(Comparator.comparingDouble(LoupGarous::getNiveau).reversed());

    }
    
    public boolean creerMeuteValide(Meute meute) {
        LoupGarous maleAlpha = null;
        LoupGarous femelleAlpha = null;

        for (LoupGarous loup : loupsGarous) {
            if (loup.getSexe().equals("Mâle") && maleAlpha == null) {
                maleAlpha = loup;
            }
            if (loup.getSexe().equals("Femelle") && femelleAlpha == null) {
                femelleAlpha = loup;
            }
        }

        if (maleAlpha != null && femelleAlpha != null && loupsGarous.size() > 2) {
            for (LoupGarous loup : loupsGarous) {
                if (!loup.equals(maleAlpha) && !loup.equals(femelleAlpha)) {
                    return true;
                }
            }
        }
        return false;
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

