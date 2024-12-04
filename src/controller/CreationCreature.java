package controller;

import model.ClientVIP;
import model.Creature;
import model.Elfe;
import model.HommeBete;
import model.LoupGarous;
import model.Maladie;
import model.Meute;
import model.Nain;
import model.Orque;
import model.Reptilien;
import model.Triage;
import model.Triage;
import model.Vampire;
import model.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreationCreature {
    private static final Random rand = new Random();
    private static Meute meute;

    public static Creature creerCreature(String type){
    	ClientVIP clientVIP = new ClientVIP();
    	Triage triage = new Triage();
        switch (type.toLowerCase()) {
            case "elfe":
                return creerElfe(clientVIP);
            case "nain":
                return creerNain(clientVIP);
            case "orque":
                return creerOrque(triage);
            case "vampire":
                return creerVampire(clientVIP);
            case "zombie":
                return creerZombie(triage);
            case "hommebete":
                return creerHommeBete(triage);
            case "lycanthrope":
                return creerLycanthrope();
            case "reptilien":
                return creerReptilien(clientVIP);
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
    
    
    private static String genererCategorieAge(int age) {
	    if (age < 50) return "jeune";
	    else if (age < 150) return "adulte";
	    else return "vieux";
    }
    
    public static String genererNomMeute() {
        String[] meutes = {"Lune Sanglante", "Griffe d'Argent", "Ombres Sauvages"};
        return meutes[rand.nextInt(meutes.length)];
    }

    private static Elfe creerElfe(ClientVIP clientVIP){
        String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(45, 65);
        double taille = genererValeurAleatoire(1.5, 1.9);
        int age = genererValeurAleatoire(100, 750);
        int moral = genererValeurAleatoire(50, 100);
        List<Maladie> maladies = new ArrayList<>();  
        Elfe elfe = new Elfe(nom, sexe, poids, taille, age, moral, maladies);
        if(clientVIP!=null) clientVIP.ajouterCreature(elfe);
        return elfe;
    }

    private static Nain creerNain(ClientVIP clienVIP) {
        String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        Nain nain =  new Nain(nom,sexe,poids,taille,age,moral, maladies);
        if(clienVIP!=null) clienVIP.ajouterCreature(nain);
        return nain;
    }

    private static HommeBete creerHommeBete(Triage triage) {
    	String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        HommeBete hommeBete = new HommeBete(nom,sexe,poids,taille,age,moral, maladies);
        if(triage!=null)
        	triage.ajouterCreature(hommeBete);
        return hommeBete;
    }
    
    private static Orque creerOrque(Triage triage) {
    	String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        Orque orque = new Orque(nom,sexe,poids,taille,age,moral, maladies);
        if(triage!=null) triage.ajouterCreature(orque);
        return orque;
    }
    
    private static Vampire creerVampire(ClientVIP clientVIP) {
    	String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        Vampire vampire = new Vampire(nom,sexe,poids,taille,age,moral, maladies);
        if(clientVIP!=null) clientVIP.ajouterCreature(vampire);
        return vampire;
    }
    
    private static Zombie creerZombie(Triage triage) {
    	String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        Zombie zombie = new Zombie(nom,sexe,poids,taille,age,moral, maladies);
        if(triage!=null) triage.ajouterCreature(zombie);
        return zombie;
    }
    
    private static Reptilien creerReptilien(ClientVIP clientVIP) {
    	String nom = genererNom();
        String sexe = genererSexe();
        double poids = genererValeurAleatoire(60,200);
        double taille = genererValeurAleatoire(1.3,1.6);
        int age = genererValeurAleatoire(70,350);
        int moral = genererValeurAleatoire(60,100);
        List<Maladie> maladies = new ArrayList<>();
        Reptilien reptilien = new Reptilien(nom,sexe,poids,taille,age,moral, maladies);
        if(clientVIP!=null) clientVIP.ajouterCreature(reptilien);
        return reptilien;
    }
    
    public static LoupGarous creerLycanthrope() {
        String nom = genererNom();
        String sexe = genererSexe();
        double poids = rand.nextDouble(70,100);
        double taille = genererValeurAleatoire(1.6,1.9);
        int age = rand.nextInt(1,300);;
        int moral = rand.nextInt(1,100);
        String categorieAge = genererCategorieAge(age);
        int force = rand.nextInt(0,moral);
        int facteurDomination = rand.nextInt(1,240);
        int rang = (int)(facteurDomination / 10);
        double niveau = 0;
        double facteurImpetuosite = rand.nextInt(rang,facteurDomination);
        String nomMeute = rand.nextBoolean() ? genererNomMeute() : "Solitaire";
        boolean humain = moral <= 50;
        List<Maladie> maladies = new ArrayList<>();
        LoupGarous loup = new LoupGarous(nom, sexe, poids, taille, age, moral, maladies, categorieAge, force, facteurDomination, rang, niveau, facteurImpetuosite, nomMeute, humain);
        if (meute != null) {
            meute.ajouterLoup(loup);
        }
        return loup;
    }
}