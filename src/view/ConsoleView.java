package view;

import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Affiche un message à l'utilisateur.
     * 
     * @param message Le message à afficher.
     */
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    /**
     * Affiche un menu et retourne le choix de l'utilisateur.
     * 
     * @param options Les options du menu.
     * @return Le choix de l'utilisateur (index basé sur 1).
     */
    public int afficherMenuEtLireChoix(String[] options) {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    /**
     * Lit une chaîne saisie par l'utilisateur.
     * 
     * @param prompt Le message à afficher avant la saisie.
     * @return La chaîne saisie.
     */
    public String lireTexte(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Ferme le scanner (bonnes pratiques pour libérer les ressources).
     */
    public void fermer() {
        scanner.close();
    }

    public int demanderChoix(String message, int min, int max) {
        int choix = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("\n" + message); // Affiche le message (prompt)
            try {
                choix = scanner.nextInt(); // Lire l'entrée de l'utilisateur

                // Vérifier que le choix est dans la plage spécifiée
                if (choix >= min && choix <= max) {
                    valid = true; // Choix valide, sortir de la boucle
                } else {
                    System.out.println("Erreur : Choix invalide. Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Consommer le reste de la ligne pour éviter une boucle infinie
            }
        }

        return choix; // Retourner le choix validé
    }
}