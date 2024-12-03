import controller.SanctuaireDesAmesLuminairesController;
import controller.CreationCreature;
import controller.MaladieController;
import model.*;
import view.ConsoleView;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SanctuaireDesAmesLuminaires {

    public static void main(String[] args) {
        Hopital hopital = new Hopital("Sanctuaire des Âmes Luminaires", 10);
        ConsoleView view = new ConsoleView();
        
        ServiceMedical serviceMedical = new ServiceMedical("Service General");
        hopital.ajouterService(serviceMedical);
        hopital.genererCreaturesParDefaut();  
        hopital.genererMedecinsParDefaut();
      
        SanctuaireDesAmesLuminairesController controller = new SanctuaireDesAmesLuminairesController(hopital, view);
        
        controller.demarrer();
    }
}