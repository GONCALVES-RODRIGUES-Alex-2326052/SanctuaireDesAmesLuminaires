package view;

import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public int afficherMenuEtLireChoix(String[] options) {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    public String lireTexte(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void fermer() {
        scanner.close();
    }

    public int demanderChoix(String message, int min, int max) {
        int choix = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(message); // Affiche le message (prompt)
            try {
                choix = scanner.nextInt();
                // Vérifier que le choix est dans la plage spécifiée
                if (choix >= min && choix <= max) {
                    valid = true;
                } else {
                    System.out.println("Erreur : Choix invalide. Veuillez entrer un nombre entre " + min + " et " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }

        return choix;
    }
}