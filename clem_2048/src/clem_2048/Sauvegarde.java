/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;

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
    public void sauvegarderGrille(Grid grille) {
        try {
            FileWriter file = new FileWriter("sauvegarde.txt");
            file.write(grille.getSize());
            file.write(grille.versSauvegarde());
        } catch (IOException ex) {
            Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Grid chargerSauvegarde() {
        try {
            FileReader file = new FileReader("sauvegarde.txt");
            int 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sauvegarde.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
}
