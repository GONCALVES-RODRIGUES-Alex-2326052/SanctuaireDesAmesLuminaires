package controller;

import model.Medecin;
import model.Creature;
import model.ServiceMedical;
import view.ConsoleView;

import java.util.List;

/**
 * Le contrôleur de la classe Medecin gère les actions liées aux médecins,
 * y compris le soin des créatures et l'affichage des informations médicales.
 */
public class MedecinController {
    
    // Vue pour l'affichage des informations
    private final ConsoleView view;

    /**
     * Constructeur de MedecinController.
     * 
     * @param view La vue utilisée pour interagir avec l'utilisateur.
     */
    public MedecinController(ConsoleView view) {
        this.view = view;
    }

    /**
     * Permet de soigner une créature en choisissant un médecin et une créature
     * à soigner parmi la liste des patients du médecin sélectionné.
     * 
     * @param service Le service médical contenant les médecins disponibles.
     */
    public void soigner(ServiceMedical service) {
        // Récupération de la liste des médecins
        List<Medecin> medecins = service.getMedecins();
        
        // Demande à l'utilisateur de choisir un médecin
        int index = view.demanderChoix("Choisissez un médecin pour soigner la créature : ", 1, medecins.size()) - 1;
        Medecin medecin = medecins.get(index);

        // Récupération des patients du médecin
        List<Creature> patients = medecin.getPatients();
        
        // Vérification si le médecin a des patients à soigner
        if (patients.isEmpty()) {
            view.afficherMessage(medecin.getNom() + " n'a aucun patient à soigner.");
            return;
        }

        // Demande à l'utilisateur de choisir une créature parmi les patients
        int creatureIndex = view.demanderChoix("Choisissez une créature à soigner : ", 1, patients.size()) - 1;
        Creature creature = patients.get(creatureIndex);

        // Soigne la créature choisie par le médecin sélectionné
        medecin.soigner(creature);
    }

    /**
     * Affiche les informations détaillées d'un médecin ainsi que la liste de ses patients.
     * 
     * @param medecin Le médecin dont les informations doivent être affichées.
     */
    public void afficherInfoMedecin(Medecin medecin) {
        // Affichage des caractéristiques du médecin
        medecin.afficherCaracteristiques();
        
        // Affichage des créatures prises en charge par ce médecin
        medecin.afficherPatients();
    }
}
