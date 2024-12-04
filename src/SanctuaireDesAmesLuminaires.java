import controller.SanctuaireDesAmesLuminairesController;
import model.*;
import view.ConsoleView;


public class SanctuaireDesAmesLuminaires {

    public static void main(String[] args) {
        Hopital hopital = new Hopital("Sanctuaire des Âmes Luminaires", 10);
        ConsoleView view = new ConsoleView();
        
        ServiceMedical serviceMedical = new ServiceMedical("Service General", 15);
        hopital.ajouterService(serviceMedical);
        hopital.genererCreaturesAleatoires(2);  
        hopital.genererMedecinsParDefaut();
      
        SanctuaireDesAmesLuminairesController controller = new SanctuaireDesAmesLuminairesController(hopital, view);
        
        controller.demarrer();
    }
}