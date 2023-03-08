
package clem_2048;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc
 */
public class Jeu {
    Scanner scanner = new Scanner(System.in);
    char[] directions = new char[]{'d', 'g', 'h', 'b'};
    
    void bougerGrille(Grid grille, char direction) {
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
        }
    }
    void loop() {
        Grid grille = null;
        if (new File("sauvegarde.txt").exists()) {
            try {
                grille = Sauvegarde.chargerSauvegarde();
            } catch (IOException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
             System.out.println("Entrez la taille de la grille:");
            int tailleGrille = scanner.nextInt();
            
            grille = new Grid(tailleGrille, 5);
        }
        System.out.println("Entrez le score max:");
        int scoreMax = scanner.nextInt();
        grille.afficherGrille();

        while (true) {
            System.out.println("Entrez la direction: d, g, h, b");
            char direction = scanner.next().charAt(0);
            grille.tuerCellulesExpirees();
            bougerGrille(grille, direction);
            grille.resetupGrille();
            grille.afficherGrille();
            grille.generationCellule();
            grille.afficherGrille();
            if (grille.grilleBloquee()) {
                System.out.println("Perdu. La grille est bloquée");
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
