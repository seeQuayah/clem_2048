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
    private Cell[][] grille = null;
    Random random = new Random();

    public boolean checkSiTermine(int scoreMax) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.getCellule(i, j).getContenu() >= scoreMax) {
                    return true;
                }
            }
        }
        return false;
    }
    public Grid(int size, int cellSize) {
        this.size = size;
        setupGrille(size, cellSize);
    }
    
    private Cell getCellule(int y, int x) {
        return this.grille[y][x];
    }
    
    private ArrayList<int[]> getCellulesVides() {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Cell cellule = this.grille[i][j];
                if (cellule.estVide()) {
                    result.add(new int[] {i, j});
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i)[0]);
             System.out.print("-");
            System.out.print(result.get(i)[1]);
            System.out.print(" ");
            System.out.println("");
        }
        return result;
    }
    
    public void setupGrille(int size, int cellSize) {
        this.grille = new Cell[this.size][this.size]; //on crée une nouvelle grille
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               this.grille[i][j] = new Cell(i, j, cellSize, 0); //on la remplit de cellules vides
            }
        }
        int index1 = random.nextInt(size * size); //on prend une coordonnee en 1d (c'est plus simple)
        int index2 = random.nextInt(size * size);

        while (index1 == index2) { //on verifie si ces coordonnees sont differentes
            index2 = random.nextInt(size);
        }
        this.grille[index1 / size][index1 % size].setContenu(2); //on convertit la coordonnee 1d en 2d avec / et %
        this.grille[index2 / size][index2 % size].setContenu(2); //on convertit la coordonnee 1d en 2d avec / et %
    }
    
    public void generationCellule() {
        ArrayList<int[]> coorCellulesVides = getCellulesVides();

        int index1 = random.nextInt(coorCellulesVides.size());
        int [] coord = coorCellulesVides.get(index1);
        this.grille[coord[0]][coord[1]].setContenu(2);
         System.out.print("I picked");
        System.out.print(coord[0]);
        System.out.print("-");
        System.out.print(coord[1]);
        System.out.print(" ");
        System.out.println("");
    }
    
    public void resetupGrille() {
        //on repasse toutes les cellules en contenuAChange a false apres avoir bougé la grille
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.grille[i][j].contenuAChange = false;
            }
        }
    }
  public void deplacerBas() {
    for (int j = 0; j < size; j++) {
        for (int i = size-2; i >= 0; i--) {
            if (!grille[i][j].estVide()) {
                int k = i;
                while (k < size-1 && grille[k+1][j].estVide()) {
                    grille[k+1][j].setContenu(grille[k][j].getContenu());
                    grille[k][j].setContenu(0);
                    k++;
                }
                if (k < size-1 && !grille[k+1][j].contenuAChange && grille[k+1][j].getContenu() == grille[k][j].getContenu()) {
                    grille[k+1][j].setContenu(grille[k+1][j].getContenu() * 2);
                    grille[k+1][j].contenuAChange = true;
                    grille[k][j].setContenu(0);
                    k++;
                }
            }
            }
        }
    }

    public void deplacerGauche() {
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (!grille[i][j].estVide()) {
                    int k = j;
                    while (k > 0 && grille[i][k-1].estVide()) {
                        grille[i][k-1].setContenu(grille[i][k].getContenu());
                        grille[i][k].setContenu(0);
                        k--;
                    }
                    if (k > 0 && !grille[i][k-1].contenuAChange && grille[i][k-1].getContenu() == grille[i][k].getContenu()) {
                        grille[i][k-1].setContenu(grille[i][k-1].getContenu() * 2);
                        grille[i][k-1].contenuAChange = true;
                        grille[i][k].setContenu(0);
                        k--;
                    }
                }
            }
        }
    }

    public void deplacerDroite() {
        for (int i = 0; i < size; i++) {
            for (int j = size-2; j >= 0; j--) {
                if (!grille[i][j].estVide()) {
                    int k = j;
                    while (k < size-1 && grille[i][k+1].estVide()) {
                        grille[i][k+1].setContenu(grille[i][k].getContenu());
                        grille[i][k].setContenu(0);
                        k++;
                    }
                    if (k < size-1 && !grille[i][k+1].contenuAChange && grille[i][k+1].getContenu() == grille[i][k].getContenu()) {
                        grille[i][k+1].setContenu(grille[i][k+1].getContenu() * 2);
                        grille[i][k+1].contenuAChange = true;
                        grille[i][k].setContenu(0);
                        k++;
                    }
                }
            }
        }
    }


    public void deplacerHaut() {
        for (int j = 0; j < size; j++) {
            for (int i = 1; i < size; i++) {
                if (!grille[i][j].estVide()) {
                    int k = i;
                    while (k > 0 && grille[k-1][j].estVide()) {
                        grille[k-1][j].setContenu(grille[k][j].getContenu());
                        grille[k][j].setContenu(0);
                        k--;
                    }
                    if (k > 0  && !grille[k-1][j].contenuAChange && grille[k-1][j].getContenu() == grille[k][j].getContenu()) {
                        grille[k-1][j].setContenu(grille[k-1][j].getContenu() * 2);
                        grille[k-1][j].contenuAChange = true;
                        grille[k][j].setContenu(0);
                        k--;
                    }
                }
            }
        }
    }


    public void BougerGrille(Regles.Direction direction) {
        
    }

    public void afficherGrille() {
    System.out.print("+");
    for (int i = 0; i < size * (size * 2) + 2; i++) {
        System.out.print("-");
    }
    System.out.println("+");

    for (int i = 0; i < size; i++) {
        System.out.print("|");
        for (int j = 0; j < size; j++) {
            Cell cellule = grille[i][j];
            String contenu = cellule.estVide() ? " " : Integer.toString(cellule.getContenu());
            System.out.printf(" %-" + (size * 2 - 1) + "s|", contenu);
        }
        System.out.println();

        System.out.print("+");
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size * 2; k++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }
}
    

}
