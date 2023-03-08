/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jc
 */
public class Sauvegarde {
    public static void sauvegarderGrille(Grid grille) {
        try {
            FileWriter file = new FileWriter("sauvegarde.txt");
            file.write(String.valueOf(grille.getSize()));
            file.write("\n");
            file.write(grille.versSauvegarde());
            file.write("\n");
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Grid chargerSauvegarde() throws IOException {
        try {
            FileReader file = new FileReader("sauvegarde.txt");
            BufferedReader buffer = new BufferedReader(file);
            int tailleGrille = Integer.parseInt(buffer.readLine());
            String[] grilleSauvegarde = buffer.readLine().split(":");
            Grid grille = new Grid(tailleGrille, 5);
            int index = 0;
            for (int i = 0; i < grilleSauvegarde.length; i++) {
                //On set les cellules de la grille avec la sauvegarde
                grille.getGrille()[i / tailleGrille][i % tailleGrille].setContenu(Integer.parseInt(grilleSauvegarde[i]));
            }
            return grille;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    } 
    
}
