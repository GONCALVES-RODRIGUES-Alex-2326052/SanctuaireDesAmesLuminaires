package controller;

import model.Medecin;
import model.Creature;
import model.ServiceMedical;
import view.ConsoleView;

import java.util.List;

public class MedecinController {
    private final ConsoleView view;

    // Constructeur
    public MedecinController(ConsoleView view) {
        this.view = view;
    }

    // Méthode pour soigner une créature
    public void soigner(ServiceMedical service) {
        List<Medecin> medecins = service.getMedecins();
        int index = view.demanderChoix("Choisissez un médecin pour soigner la créature : ", 1, medecins.size()) - 1;
        Medecin medecin = medecins.get(index);

        // Affichage de la liste des créatures prises en charge par ce médecin
        List<Creature> patients = medecin.getPatients();
        if (patients.isEmpty()) {
            view.afficherMessage(medecin.getNom() + " n'a aucun patient à soigner.");
            return;
        }

        // Affichage des créatures à soigner
        int creatureIndex = view.demanderChoix("Choisissez une créature à soigner : ", 1, patients.size()) - 1;
        Creature creature = patients.get(creatureIndex);

        // Soigner la créature
        medecin.soigner(creature);
    }

    // Méthode pour afficher les informations d'un médecin
    public void afficherInfoMedecin(Medecin medecin) {
        medecin.afficherCaracteristiques();
        medecin.afficherPatients();
    }
}