
package clem_2048;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Jeu {
    Scanner scanner = new Scanner(System.in);
    char[] directions = new char[]{'d', 'g', 'h', 'b'};
    
    boolean commandes(Grid grille, char direction) {
        switch(direction) {
            case 'd':
                grille.deplacerDroite();
                break;
            case 'g':
                grille.deplacerGauche();
                break;
            case 'h':
                grille.deplacerHaut();
                break;
            case 'b':
                grille.deplacerBas();
                break;
            case 'q':
                return true;
        }
        return false;
    }
    
    Grid nouvelleGrille() {
        System.out.println("Entrez la taille de la grille:");
        int tailleGrille = scanner.nextInt();
        return  new Grid(tailleGrille, 5);
    }
    
    void loop() {
        Grid grille = null;
        if (new File("sauvegarde.txt").exists()) {
            try {
                System.out.println("Une sauvegarde existe. La charger? o/n:");
            char ouiNon = scanner.next().charAt(0);
            if (ouiNon == 'o') {
                grille = Sauvegarde.chargerSauvegarde();
            } else {
                grille = nouvelleGrille();
            }
            } catch (IOException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
             grille = nouvelleGrille();
        }
        System.out.println("Entrez le score max:");
        int scoreMax = scanner.nextInt();
        grille.afficherGrille();

        while (true) {
            System.out.println("Entrez la direction: d, g, h, b ou quitter q");
            char direction = scanner.next().charAt(0);
            grille.tuerCellulesExpirees();
            if (commandes(grille, direction)) {
                System.out.println("Jeu sauvegardé, a+");
                break;
            }
            grille.resetupGrille();
            grille.afficherGrille();
            grille.generationCellule();
            grille.afficherGrille();
            if (grille.grilleBloquee()) {
                System.out.println("Perdu. La grille est bloquée");
                System.out.printf("Score: %d\n", grille.highScore());
                break;
            }
            if (grille.checkSiTermine(scoreMax)) {
                System.out.println("Gagné!");
                break;
            }
            Sauvegarde.sauvegarderGrille(grille);
        }
    }
}
