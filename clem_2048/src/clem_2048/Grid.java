/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jc
 */
public class Grid {
    private final int size;
    private ArrayList<ArrayList<Cell>> grille = null;
    Random random = new Random();

    
    public Grid(int size, int cellSize) {
        this.size = size;
        setupGrille(size, cellSize);
    }
    
    private Cell getCellule(int y, int x) {
        return this.grille.get(y).get(x);
    }
    private ArrayList<int[]> getCellulesVides() {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Cell cellule = this.grille.get(i).get(j);
                if (cellule.estVide()) {
                    result.add(new int[] {cellule.getY(), cellule.getX()});
                }
            }
        }
        return result;
    }
    
    public void setupGrille(int size, int cellSize) {
        this.grille = new ArrayList<>(this.size); //on crée une nouvelle grille
        for (int i = 0; i < size * size; i++) {
            this.grille.add(new ArrayList<>(size));
            for (int j = 0; j < size * size; j++) {
               this.grille.get(i).add(new Cell(i, j, cellSize, 0)); //on la remplit de cellules vides
            }
        }
        int index1 = random.nextInt(size * size); //on prend une coordonnee en 1d (c'est plus simple)
        int index2 = random.nextInt(size * size);

        while (index1 == index2) { //on verifie si ces coordonnees sont differentes
            index2 = random.nextInt(size);
        }
        this.grille.get(index1 / size).get(index1 % size).setContenu(2); //on convertit la coordonnee 1d en 2d avec / et %
        this.grille.get(index2 / size).get(index2 % size).setContenu(2); //on convertit la coordonnee 1d en 2d avec / et %
    }
    
    public void generationCellule() {
        ArrayList<int[]> coorCellulesVides = getCellulesVides();
        int index1 = random.nextInt(coorCellulesVides.size());
        int [] coord = coorCellulesVides.get(index1);
        this.grille.get(coord[0]).get(coord[1]).setContenu(2);
    }
    
    public void BougerGrille(Regles.Direction direction) {
        
    }

    public void afficherGrille() {
        System.out.print("+");
        for (int i = 0; i < size * (size * 2 + 1); i++) {
            System.out.print("-");
        }
        System.out.println("+");

        for (int i = 0; i < size; i++) {
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                Cell cellule = grille.get(i).get(j);
                String contenu = cellule.estVide() ? " " : Integer.toString(cellule.getContenu());
                System.out.printf(" %-" + (size * 2 - 1) + "s|", contenu);
            }
            System.out.println();

            System.out.print("+");
            for (int j = 0; j < size; j++) {
                System.out.print("-");
                for (int k = 0; k < size * 2; k++) {
                    System.out.print(" ");
                }
                System.out.print("+");
            }
            System.out.println();
        }
    }
    

}
