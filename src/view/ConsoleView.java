package view;

import java.util.Scanner;

/**
 * La classe ConsoleView fournit une interface utilisateur pour interagir via la console.
 * Elle permet d'afficher des messages, lire des entrées utilisateur, et afficher un menu.
 */
public class ConsoleView {

    /** Scanner utilisé pour lire les entrées de l'utilisateur. */
    private Scanner scanner;

    /**
     * Constructeur de la classe ConsoleView.
     * Initialise un scanner pour lire les entrées utilisateur.
     */
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
     * Affiche un menu avec plusieurs options et lit le choix de l'utilisateur.
     * 
     * @param options Les options à afficher dans le menu.
     * @return Le numéro correspondant au choix de l'utilisateur (index basé sur 1).
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
     * Lit une chaîne de caractères saisie par l'utilisateur après un message d'invite.
     * 
     * @param prompt Le message d'invite à afficher.
     * @return Le texte saisi par l'utilisateur.
     */
    public String lireTexte(String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); // Consomme la ligne précédente si nécessaire.
        return scanner.nextLine();
    }

    /**
     * Ferme le scanner utilisé pour lire les entrées utilisateur.
     * À appeler lorsque la classe ConsoleView n'est plus utilisée.
     */
    public void fermer() {
        scanner.close();
    }

    /**
     * Demande à l'utilisateur de faire un choix entre des valeurs minimales et maximales.
     * 
     * @param message Le message d'invite à afficher.
     * @param min     La valeur minimale acceptable.
     * @param max     La valeur maximale acceptable.
     * @return Le choix de l'utilisateur, compris entre min et max.
     */
    public int demanderChoix(String message, int min, int max) {
        int choix = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(message);
            try {
                choix = scanner.nextInt();
                // Vérifie que le choix est dans la plage spécifiée.
                if (choix >= min && choix <= max) {
                    valid = true;
                } else {
                    System.out.println("Erreur : Choix invalide. Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Consomme l'entrée non valide.
            }
        }

        return choix;
    }
}
