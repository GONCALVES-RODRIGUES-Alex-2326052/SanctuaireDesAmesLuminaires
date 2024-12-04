package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Hopital;
import model.Medecin;
import model.ServiceMedical;
import model.Creature;

class HopitalTest {
    private Hopital hopital;
    private ServiceMedical service;
    private Medecin medecin;

    @BeforeEach
    void setUp() {
        hopital = new Hopital("Hôpital Test", 3); // Nom de l'hôpital et max services = 3
        service = new ServiceMedical("Service Test", 15, Creature.class);
        medecin = new Medecin("Dr. Test", "Chirurgien", 2);
    }

    @Test
    void testAjouterService() {
        assertTrue(hopital.ajouterService(service) != null, "Le service doit être ajouté avec succès");
        assertEquals(1, hopital.getServices().size(), "L'hôpital doit contenir un service");
    }

    @Test
    void testAjouterServiceDejaAjoute() {
        hopital.ajouterService(service);
        assertNull(hopital.ajouterService(service), "Un service déjà ajouté ne peut pas être ré-ajouté");
    }

    @Test
    void testAjouterServiceNom() {
        ServiceMedical nouveauService = hopital.ajouterService("Service Nouveau");
        assertNotNull(nouveauService, "Le service doit être créé et ajouté avec succès");
        assertEquals("Service Nouveau", nouveauService.getNom());
    }

    @Test
    void testSupprimerService() {
        hopital.ajouterService(service);
        assertTrue(hopital.supprimerService(service), "Le service doit être supprimé avec succès");
        assertEquals(0, hopital.getServices().size(), "Il ne doit plus y avoir de service dans l'hôpital");
    }

    @Test
    void testAjouterMedecin() {
        hopital.ajouterMedecin(medecin);
        assertTrue(hopital.getMedecins().contains(medecin), "Le médecin doit être ajouté");
    }

    @Test
    void testSupprimerMedecin() {
        hopital.ajouterMedecin(medecin);
        assertTrue(hopital.supprimerMedecin(medecin), "Le médecin doit être supprimé");
        assertFalse(hopital.getMedecins().contains(medecin), "Le médecin ne doit plus être dans la liste");
    }

    @Test
    void testGenererMedecinsParDefaut() {
        hopital.ajouterService(service);
        Creature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        hopital.genererMedecinsParDefaut();
        assertEquals(2, service.getMedecins().size(), "Deux médecins par défaut doivent être ajoutés au service");
    }

    @Test
    void testGenererCreaturesAleatoires() {
        hopital.ajouterService(service);
        hopital.genererCreaturesAleatoires(2);
        assertEquals(2, service.getCreatures().size(), "2 créatures doivent être générées et ajoutées");
    }

    @Test
    void testAfficherEtat() {
        hopital.ajouterService(service);
        assertDoesNotThrow(() -> hopital.afficherEtat(), "Afficher l'état ne doit pas lever d'exception");
    }

    @Test
    void testEvaluerHopital() {
        hopital.ajouterService(service);
        Creature creature1 = new FakeCreature("Fake 1", "Femelle", 50.0, 1.7, 120, 100);
        Creature creature2 = new FakeCreature("Fake 2", "Mâle", 60.0, 1.8, 150, 50);
        service.ajouterCreature(creature1);
        service.ajouterCreature(creature2);

        hopital.evaluerHopital();
        assertTrue(hopital.getServices().size() > 0, "L'hôpital doit contenir des services après évaluation");
    }

    @Test
    void testAssignerPatientsMedecins() {
        hopital.ajouterService(service);
        Creature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        service.ajouterMedecin(medecin);

        hopital.assignerPatientsMedecins();
        assertTrue(medecin.getPatients().contains(creature), "La créature doit être assignée au médecin");
    }

    @Test
    void testIsPartiePerdue() {
        hopital.setPartiePerdue(true);
        assertTrue(hopital.isPartiePerdue(), "La partie doit être marquée comme perdue");
    }
}