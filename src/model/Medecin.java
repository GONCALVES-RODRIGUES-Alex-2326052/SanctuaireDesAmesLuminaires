package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * La classe Medecin représente un médecin capable de soigner des créatures.
 * Elle contient des informations sur le nom, la spécialité, l'expérience et 
 * la liste des créatures prises en charge par le médecin.
 */
public class Medecin {
    private String nom; // Nom du médecin
    private String specialite; // Spécialité du médecin (ex. : "Chirurgie", "Virologie", etc.)
    private int experience; // Nombre d'années d'expérience
    private List<Creature> patients; // Liste des créatures prises en charge

    /**
     * Constructeur de la classe Medecin.
     *
     * @param nom        Le nom du médecin.
     * @param specialite La spécialité médicale du médecin.
     * @param experience Le nombre d'années d'expérience du médecin.
     */
    public Medecin(String nom, String specialite, int experience) {
        this.nom = nom;
        this.specialite = specialite;
        this.experience = experience;
        this.patients = new ArrayList<>();
    }

    /**
     * Récupère la liste des créatures prises en charge par le médecin.
     *
     * @return Une liste de créatures représentant les patients du médecin.
     */
    public List<Creature> getPatients() {
        return patients;
    }

    /**
     * Soigne une créature en fonction de l'expérience du médecin.
     * La qualité du soin est déterminée de manière aléatoire et dépend 
     * de l'expérience du médecin.
     *
     * @param creature La créature à soigner.
     */
    public void soigner(Creature creature) {
        Random random = new Random();
        System.out.println(nom + " soigne la créature " + creature.getNom() + "...");
        creature.etreSoignee(random.nextInt(11) * experience);
    }

    /**
     * Vérifie si le médecin peut prendre en charge un nouveau patient.
     *
     * @param limitePatients Le nombre maximal de patients qu'un médecin peut gérer.
     * @return true si le médecin a moins de patients que la limite, sinon false.
     */
    public boolean estDisponible(int limitePatients) {
        return patients.size() < limitePatients;
    }

    /**
     * Affiche les caractéristiques principales du médecin, notamment 
     * son nom, sa spécialité, son expérience et le nombre de patients.
     */
    public void afficherCaracteristiques() {
        System.out.println("Médecin : " + nom);
        System.out.println("Spécialité : " + specialite);
        System.out.println("Expérience : " + experience + " ans");
        System.out.println("Nombre de patients : " + patients.size());
    }

    /**
     * Affiche la liste des patients actuellement pris en charge par le médecin.
     * Pour chaque patient, affiche son nom et son état actuel.
     */
    public void afficherPatients() {
        System.out.println("Patients pris en charge par " + nom + " :");
        for (Creature patient : patients) {
            System.out.println("- " + patient.getNom() + " (État : " + patient.getEtat() + ")");
        }
    }

    /**
     * Récupère le nom du médecin.
     *
     * @return Le nom du médecin.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du médecin.
     *
     * @param nom Le nouveau nom du médecin.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la spécialité du médecin.
     *
     * @return La spécialité du médecin.
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * Modifie la spécialité du médecin.
     *
     * @param specialite La nouvelle spécialité du médecin.
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * Récupère le nombre d'années d'expérience du médecin.
     *
     * @return L'expérience du médecin en années.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Modifie l'expérience du médecin.
     *
     * @param experience Le nouveau nombre d'années d'expérience.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
