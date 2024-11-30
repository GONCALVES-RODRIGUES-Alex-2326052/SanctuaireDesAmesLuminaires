package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientVIP {
	private List<Creature> clientsPrio;
	Random random;
	
	// Contructeur
	public ClientVIP() {
		clientsPrio = new ArrayList<>();
	}
	
	// Methode pour ajouter des créature
	public void ajouterCreature(Creature creature) {
		clientsPrio.add(creature);
	}
	
	public void attendre(int dureeAttente) {
		int seuilAttente = 10;
		random = new Random();
		
		if (dureeAttente > seuilAttente) {
			for (Creature creature : clientsPrio) {
				System.out.println(creature.getNom() + " est affecter par une longue attente.");
				creature.diminuerMoral(random.nextInt(10, 21));
			}
		} else for (Creature creature : clientsPrio) System.out.println(creature.getNom() + " est satisfait de l'attente raisonnable"); 
	}
}
