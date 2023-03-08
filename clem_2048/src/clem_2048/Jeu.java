
package clem_2048;

import java.util.Scanner;

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
        System.out.println("Entrez la taille de la grille:");
        int tailleGrille = scanner.nextInt();
        System.out.println("Entrez le score max:");
        int scoreMax = scanner.nextInt();
        Grid grille = new Grid(tailleGrille, 5);
        grille.afficherGrille();

        while (true) {
            System.out.println("Entrez la direction: d, g, h, b");
            char direction = scanner.next().charAt(0);
            bougerGrille(grille, direction);
            grille.resetupGrille();
            grille.afficherGrille();
            grille.generationCellule();
            grille.afficherGrille();
            if (grille.checkSiTermine(scoreMax)) {
                System.out.println("Gagné!");
                break;
            }
        }
    }
}
