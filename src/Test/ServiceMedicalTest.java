package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ServiceMedical;
import model.Medecin;

class ServiceMedicalTest {
    private ServiceMedical service;
    private Medecin medecin;

    @BeforeEach
    void setUp() {
        service = new ServiceMedical("Service Test");
        medecin = new Medecin("Dr. Test", "Chirurgien", 2);
    }

    @Test
    void testServiceMedicalStringInt() {
        service = new ServiceMedical("Service Test", 5);
        assertEquals("Service Test", service.getNom());
        assertEquals(5, service.getLimitePatients());
    }

    @Test
    void testServiceMedicalString() {
        service = new ServiceMedical("Service Test");
        assertEquals("Service Test", service.getNom());
    }

    @Test
    void testAjouterCreature() {
        FakeCreature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        assertTrue(service.getCreatures().contains(creature), "La créature doit être ajoutée au service");
    }

    @Test
    void testAjouterCreatureSiPossible() {
        service.setLimitePatients(3);
        FakeCreature creature1 = new FakeCreature("Fake Test 1", "Femelle", 50.0, 1.7, 120, 80);
        FakeCreature creature2 = new FakeCreature("Fake Test 2", "Mâle", 60.0, 1.8, 150, 75);
        service.ajouterCreature(creature1);
        service.ajouterCreature(creature2);
        
        assertEquals(2, service.getCreatures().size(), "Deux créatures devraient être ajoutées");
    }

    @Test
    void testVerifierEtSupprimerCreatures() {
        FakeCreature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        assertTrue(service.getCreatures().contains(creature), "La créature doit être présente avant suppression");
    }

    @Test
    void testAfficherCreatures() {
        FakeCreature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        assertDoesNotThrow(() -> service.afficherCreatures(), "L'affichage des créatures ne doit pas lever d'exception");
    }

    @Test
    void testAjouterMedecin() {
        service.ajouterMedecin(medecin);
        assertTrue(service.getMedecins().contains(medecin), "Le médecin doit être ajouté au service");
    }

    @Test
    void testRetirerMedecin() {
        service.ajouterMedecin(medecin);
        service.retirerMedecin(medecin);
        assertFalse(service.getMedecins().contains(medecin), "Le médecin doit être retiré du service");
    }

    @Test
    void testAfficherMedecins() {
        service.ajouterMedecin(medecin);
        assertDoesNotThrow(() -> service.afficherMedecins(), "L'affichage des médecins ne doit pas lever d'exception");
    }

    @Test
    void testModifierEtatService() {
        service.modifierEtatService();
        assertNotNull(service, "Le service ne doit pas être nul après modification");    
    }

    @Test
    void testVerifierEtDeclencherCrise() {
        service.verifierEtDeclencherCrise();
    }

    @Test
    void testGetNombreDeCreatures() {
        FakeCreature creature1 = new FakeCreature("Fake Test 1", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature1);
        assertEquals(1, service.getNombreDeCreatures(), "Le nombre de créatures doit être 1");
    }

    @Test
    void testGetNom() {
        assertEquals("Service Test", service.getNom(), "Le nom du service doit être correct");
    }

    @Test
    void testGetMedecins() {
        service.ajouterMedecin(medecin);
        assertEquals(1, service.getMedecins().size(), "Il doit y avoir un médecin dans le service");
    }

    @Test
    void testGetCreatures() {
        FakeCreature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        assertEquals(1, service.getCreatures().size(), "Il doit y avoir une créature dans le service");
    }

    @Test
    void testGetLimitePatients() {
        service.setLimitePatients(5);
        assertEquals(5, service.getLimitePatients(), "La limite de patients doit être correcte");
    }

    @Test
    void testSetLimitePatients() {
        service.setLimitePatients(10);
        assertEquals(10, service.getLimitePatients(), "La limite de patients doit être mise à jour");
    }

    @Test
    void testAfficherService() {
        assertDoesNotThrow(() -> service.afficherService(), "L'affichage du service ne doit pas lever d'exception");
    }

    @Test
    void testAfficherEtTraiterPatientsParPriorite() {
        assertDoesNotThrow(() -> service.afficherEtTraiterPatientsParPriorite(), "Le traitement des patients par priorité ne doit pas lever d'exception");
    }

    @Test
    void testAssignerPatientsAuxMedecins() {
        FakeCreature creature = new FakeCreature("Fake Test", "Femelle", 50.0, 1.7, 120, 80);
        service.ajouterCreature(creature);
        service.ajouterMedecin(medecin);
        service.assignerPatientsAuxMedecins();
        assertTrue(medecin.getPatients().contains(creature), "La créature doit être assignée au médecin");
    }

    @Test
    void testAfficherRapport() {
        assertDoesNotThrow(() -> service.afficherRapport(), "L'affichage du rapport ne doit pas lever d'exception");
    }
}
