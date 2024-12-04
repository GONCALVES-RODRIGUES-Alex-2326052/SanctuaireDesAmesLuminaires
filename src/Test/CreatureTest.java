package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import model.Creature;
import model.Maladie;

class CreatureTest {
	
	private Creature creature;
    private List<Maladie> maladies;
    
    @BeforeEach
    void setUp() {
        maladies = new ArrayList<>();
        creature = new FakeCreature("TestCreature", "Mâle", 70.0, 1.75, 25, 80);
        creature.setEtat(70); // Initial state
    }
	
    @Test
    void testAmeliorerEtat() {
        creature.ameliorerEtat(20);
        assertEquals(90, creature.getEtat(), "L'état aurait dû être amélioré à 90.");

        creature.ameliorerEtat(20);
        assertEquals(100, creature.getEtat(), "L'état ne doit pas dépasser 100.");
    }

    @Test
    void testDiminuerEtat() {
        creature.diminuerEtat(10);
        assertEquals(60, creature.getEtat(), "L'état aurait dû être diminué à 60.");

        creature.diminuerEtat(100);
        assertEquals(0, creature.getEtat(), "L'état ne doit pas descendre en dessous de 0.");
    }

    @Test
    void testDiminuerMoral() {
        creature.diminuerMoral(30);
        assertEquals(50, creature.getMoral(), "Le moral aurait dû être diminué à 50.");

        creature.diminuerMoral(100);
        assertEquals(0, creature.getMoral(), "Le moral ne doit pas descendre en dessous de 0.");
    }

    @Test
    void testAmeliorerMoral() {
        creature.ameliorerMoral(10);
        assertEquals(90, creature.getMoral(), "Le moral aurait dû être amélioré à 90.");

        creature.ameliorerMoral(20);
        assertEquals(100, creature.getMoral(), "Le moral ne doit pas dépasser 100.");
    }

    @Test
    void testAttendre() {
        String resultat = creature.attendre();
        assertTrue(resultat.contains("attendre"), "Le message devrait indiquer que la créature attend.");
        assertTrue(creature.getMoral() < 80, "Le moral aurait dû diminuer.");
    }

    @Test
    void testHurler() {
        creature.diminuerMoral(80); // Met le moral à 0
        String resultat = creature.hurler();
        assertTrue(resultat.contains("hurle"), "Le message devrait indiquer que la créature hurle.");
    }

    @Test
    void testTomberMalade() {
        Maladie maladie = new Maladie("Grippe");
        String resultat = creature.tomberMalade(maladie);
        assertTrue(resultat.contains("Grippe"), "Le message devrait indiquer que la créature a attrapé la grippe.");
    }

    @Test
    void testTrepasser() {
        String resultat = creature.trepasser();
        assertTrue(resultat.contains("mort"), "Le message devrait indiquer que la créature est morte.");
    }

    @Test
    void testEtreSoignee() {
        int soin = 15;
        String resultat = creature.etreSoignee(soin);
        assertTrue(resultat.contains("soignée"), "Le message devrait indiquer que la créature est soignée.");
        assertTrue(creature.getEtat() > 70, "L'état aurait dû être amélioré.");
    }

    @Test
    void testGetNom() {
        assertEquals("TestCreature", creature.getNom(), "Le nom de la créature devrait être TestCreature.");
    }

    @Test
    void testGetSexe() {
        assertEquals("Mâle", creature.getSexe(), "Le sexe de la créature devrait être Mâle.");
    }

    @Test
    void testGetPoids() {
        assertEquals(70.0, creature.getPoids(), 0.01, "Le poids devrait être de 70.0.");
    }

    @Test
    void testGetTaille() {
        assertEquals(1.75, creature.getTaille(), 0.01, "La taille devrait être de 1.75.");
    }

    @Test
    void testGetAge() {
        assertEquals(25, creature.getAge(), "L'âge devrait être de 25.");
    }

    @Test
    void testGetMoral() {
        assertEquals(80, creature.getMoral(), "Le moral initial devrait être de 80.");
    }

    @Test
    void testSetEtat() {
        creature.setEtat(90);
        assertEquals(90, creature.getEtat(), "L'état devrait être mis à jour à 90.");
    }

    @Test
    void testGetEtat() {
        assertEquals(70, creature.getEtat(), "L'état initial devrait être de 70.");
    }

}
