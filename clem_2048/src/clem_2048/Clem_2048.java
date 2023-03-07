/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clem_2048;

/**
 *
 * @author jc
 */
public class Clem_2048 {

    public static void main(String[] args) {
        Grid grille = new Grid(3, 5);
        grille.afficherGrille();
        grille.BougerGrille(Grid.Direction.DROITE);
        grille.generationCellule();
        grille.afficherGrille();
        grille.BougerGrille(Grid.Direction.BAS);
        grille.afficherGrille();
        grille.generationCellule();
        grille.BougerGrille(Grid.Direction.DROITE);


    }
    
}
