/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clem_2048;

/*Jeu jeu = new Jeu();
        jeu.loop();*/
public class Clem_2048 {

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSwitcher().setVisible(true);
            }
        });

    }
    
}
