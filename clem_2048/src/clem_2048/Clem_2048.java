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
        System.out.println("Init");
        grille.afficherGrille();//print
        grille.deplacerBas();
        System.out.println("bas");
        grille.afficherGrille();//print
        grille.generationCellule();
        System.out.println("gen cell");
        grille.afficherGrille();//print
        grille.deplacerBas();
        System.out.println("bas");
        grille.afficherGrille();//print
        grille.generationCellule();
        System.out.println("gen cell");
        grille.afficherGrille();//print
        grille.deplacerDroite();
        System.out.println("droite");
        grille.afficherGrille();//print


    }
    
}
