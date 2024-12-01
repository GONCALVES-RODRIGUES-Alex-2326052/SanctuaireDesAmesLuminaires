
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Triage {
	private List<Creature> habitantTriage;
	private Random random;
	
	//Constructeur
	public Triage(Orque orque, HommeBete hommeBete, Zombie zombie) {
		habitantTriage = new ArrayList<>();
	}
	
	// Methode pour ajouter une créature au Triage
	public void ajouterCreature(Creature creature) {
		habitantTriage.add(creature);
		System.out.println(creature.getNom() + " a été ajouter au triage.");
	}
	
	// Methode pour verifier la patience des habitant
	public void attendre(List<Creature> creatures) {
		Map<String, List<Creature>> creatureParEspece = new HashMap<>();
		String espece;
		// Compter les créatures par especes
		for (Creature creature: habitantTriage) {
			espece = creature.getEspece();
			creatureParEspece.putIfAbsent(espece, new ArrayList<>());
			creatureParEspece.get(espece).add(creature);
		}
		
		for (Map.Entry<String, List<Creature>> entry : creatureParEspece.entrySet()) {
			List<Creature> groupe = entry.getValue();
			System.out.println("Espèce : " + entry.getKey() + ", Nombre : " + groupe.size());
			
			if(groupe.size() > 1) {
				System.out.println("Les " + entry.getKey() + " sont plus patient car ils sont en groupe !");
				for(Creature creature : groupe) creature.diminuerMoral(random.nextInt(5));
			} else {
				System.out.println("Seul " + groupe.get(0).getNom() + " de l'espèce " + entry.getKey() + " attend seul.");
			}
		}
	}
}
