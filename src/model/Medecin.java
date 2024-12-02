package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Medecin {
    private String nom;
    private String specialite; // Spécialité du médecin (ex. : "Chirurgie", "Virologie", etc.)
    private int experience; // Nombre d'années d'expérience
    private List<Creature> patients; // Liste des créatures prises en charge
    private Random random;
    
    // --- Constructeur ---
    public Medecin(String nom, String specialite, int experience) {
        this.nom = nom;
        this.specialite = specialite;
        this.experience = experience;
        this.patients = new ArrayList<>();
    }

    /**
     * Récupère la liste des créatures prises en charge.
     * 
     * @return La liste des patients.
     */
    public List<Creature> getPatients() {
        return patients;
    }

    // --- Méthode de soin ---

    /**
     * Soigne une créature en fonction de l'expérience du médecin.
     * 
     * @param creature La créature à soigner.
     */
    public void soigner(Creature creature) {
        if (!patients.contains(creature)) {
            System.out.println("Cette créature n'est pas sous la responsabilité de ce médecin.");
            return;
        }

        int soin = random.nextInt(11) + (experience * 5);
        System.out.println("Soins apportés : " + soin);
        creature.ameliorerEtat(soin);
    }

    // --- Méthodes d'affichage ---

    /**
     * Affiche les caractéristiques du médecin.
     */
    public void afficherCaracteristiques() {
        System.out.println("Médecin : " + nom);
        System.out.println("Spécialité : " + specialite);
        System.out.println("Expérience : " + experience + " ans");
        System.out.println("Nombre de patients : " + patients.size());
    }

    /**
     * Affiche la liste des patients pris en charge.
     */
    public void ajouterPatient(Creature creature) {
        if (patients.size() < 5) {
            patients.add(creature);
            System.out.println(creature.getNom() + " a été ajouté aux patients de " + nom + ".");
        } else {
            System.out.println("Le médecin " + nom + " ne peut plus accepter de patients.");
        }
    }

    // --- Getters et Setters ---

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}