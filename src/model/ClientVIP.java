package model;

import java.util.ArrayList;
import java.util.List;

public class ClientVIP {
	private List<Creature> clientsPrio;
	
	/**
	 * Constructeur de la classe ClientVIP
	 */
	public ClientVIP() {
		clientsPrio = new ArrayList<>();
	}
	
	/**
	 * Methode pour ajouter des créature
	 * 
	 * @param creature
	 */
	public void ajouterCreature(Creature creature) {
		clientsPrio.add(creature);
	}
	
	/**
	 * Methode d'attente
	 * 
	 * @param dureeAttente
	 */
	public void attendre(int dureeAttente) {
		int seuilAttente = 10;
		
		if (dureeAttente > seuilAttente) {
			for (Creature creature : clientsPrio) {
				System.out.println(creature.getNom() + " est affecter par une longue attente.");
				creature.diminuerMoral(50);
			}
		} else for (Creature creature : clientsPrio) System.out.println(creature.getNom() + " est satisfait de l'attente raisonnable"); 
	}
}