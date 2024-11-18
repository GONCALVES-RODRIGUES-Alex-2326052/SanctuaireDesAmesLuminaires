package controller;

import model.Creature;
import model.Elfe;
import model.Maladie;
import model.Nain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreationCreature {
    private static final Random rand = new Random();

    public static Creature creerCreature(String type){
        switch (type.toLowerCase()) {
            case "elfe":
                return creerElfe();
            case "nain":
                return creerNain();
            /*case "orque":
                return creerOrque();
            case "vampire":
                return creerVampire();
            case "zombie":
                return creerZombie();
            case "hommebete":
                return creerHommeBete();
            case "lycanthrope":
                return creerLycanthrope();
            case "reptilien":
                return creerReptilien();*/
            default:
                throw new IllegalArgumentException("Type de créature inconnu : " + type);
        }
    }

    public static String genererNom() {
        String[] noms = {"Aldarion", "Thorin", "Gruk", "Lestat", "Akasha","Crabouille","Tartenpion",
        "Marmouflon", "Choklard", "L'Éternuement Divin", "Groumpf", "Pince-Fantome", "L'Averse Sacrée",
        "Soupir Cosmogonique","Astre Détraqué", "Rond-point", "Boudha Culotté", "Ni Vu Ni Vonnu", "Mutuelle",
        "Le Sage des Trous","Arbracadabra","Piquerax", "Claquetus", "Atchoumitius", "Ctrl-S", "Poil-de-Chèvre", 
        "Chloé Pâtre", "Plan-B", "Sir Coup’Lèche", "Chevalier Clé-de-Bras", "Croque-Savate", "Zigomarabracadabra",
        "Lave-Baguette", "Patate Solaire", "Saucisse Éclipse", "Tracteur Licorne", "Sardine Céleste", "Casserole Cosmique",
        "Ketchup Écarlate", "Brouette Cosmique", "Papillon en Béton", "Nevot-pas-trop", "Gluptor Mouchi", 
        "Brolix Torpouille", "Zigglon Tifflax", "Fliquerbois Deplof", "Brobilou Manfou", "Vennox Scrognet",
        "Mogdan Croulek", "Zibbleron Jixou", "Barkion Fivelle", "Pligert Noshkin", "Zaltrik Poforo", "Juxol Parflot",
        "Mervyn Trasker", "Flakdoze Vixoul", "Trolixz Vickrinn", "Scurrit Wopho", "Praxmor Zaffok", "Chifto Zolgram",
        "Gormik Levglot", "Frubix Wobsal", "Zaltrixol", "Bromatex", "Viroxen", "Dextropil", "Cefalum", "Loratimax",
        "Protonil", "Tisoflex", "Amorvex", "Floradene", "Bobby Pamplemousse", "Choco-Magouille", "Fluffy Crouton",
        "Tartine Boudinette", "Gloubi-Boulga", "Pierre Pot-au-Feu", "Dora L\'Escalope", "Zébulon Brindille",
        "Bertie Hurluberlu", "Mimi Patapouf", "Malénia Blade of Miquella", "Hoara Loux", "Elden Beast",
        "Kratos", "Messmer", "Iamcursed", "Gintoki", "Maomao", "Sosuke Aizen", "Luffy", "Garp", "2b", "Ripley"};
        String nom = noms[rand.nextInt(noms.length)];
        return nom;
    }

    private static String genererSexe() {
        return rand.nextBoolean() ? "Mâle" : "Femelle";
    }

    private static double genererValeurAleatoire(double min, double max) {
        return min + (max - min) * rand.nextDouble();
    }

    private static int genererValeurAleatoire(int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }

    private static Elfe creerElfe(){
        String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(45, 65);
        double taille = genererValeurAleatoire(1.5, 1.9);
        int age = genererValeurAleatoire(100, 750);
        int moral = genererValeurAleatoire(50, 100);
        List<Maladie> maladies = new ArrayList<>();  // Liste vide de maladies (tu peux y ajouter des objets Maladie si nécessaire)

        return new Elfe(nom, sexe, poids, taille, age, moral, maladies);
    }

    private static Nain creerNain() {
        String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        return new Nain(nom,sexe,poids,taille,age,moral, maladies);
    }

    
}