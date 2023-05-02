package clem_2048;

import javax.swing.*;
import java.awt.*;
        
public class GrilleGraphique extends JFrame {
    
    private  final int TAILLE_GRILLE = 5;
    private  final int TAILLE_CELLULE = 50;
    private  final int LARGEUR_FENETRE = TAILLE_GRILLE * TAILLE_CELLULE + 50;
    private  final int HAUTEUR_FENETRE = TAILLE_GRILLE * TAILLE_CELLULE + 100;
    private enum MENUS {
        ACCUEIL,
        JEU,
        FIN
    }
    private JPanel masterPanel = new JPanel();
    private JLabel[][] gridLabels;
    private Grid grille; 
    
    private Grid nouvelleGrille() {
        return  new Grid(TAILLE_GRILLE, 5);
    }
    
    private JPanel[] panelJeu() {
        JPanel gridPanel;
        JPanel[] panels = new JPanel[5];
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(TAILLE_GRILLE, TAILLE_GRILLE));
        gridLabels = new JLabel[TAILLE_GRILLE][TAILLE_GRILLE];
        for (int i = 0; i < TAILLE_GRILLE; i++) {
            for (int j = 0; j < TAILLE_GRILLE; j++) {
                JLabel label = new JLabel(grille.getGrille()[i][j].getNomAtom(), SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setPreferredSize(new Dimension(TAILLE_CELLULE, TAILLE_CELLULE));
                gridLabels[i][j] = label;
                gridPanel.add(label);
            }
        }
        return panels;
    }
    public GrilleGraphique() {
        super("Grid Display");

        grille = nouvelleGrille();
        setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize grid panel
        
        //add(gridPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        JButton upButton = new JButton("Up");
        upButton.addActionListener(event -> moveUp());
        JButton downButton = new JButton("Down");
        downButton.addActionListener(event -> moveDown());
        JButton leftButton = new JButton("Left");
        leftButton.addActionListener(event -> moveLeft());
        JButton rightButton = new JButton("Right");
        rightButton.addActionListener(event -> moveRight());
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private void tickJeu() {
        grille.tuerCellulesExpirees();
        grille.generationCellule();
        updateGridLabels();

    }
    private void moveUp() {
        grille.deplacerHaut();
        tickJeu();
    }
    
    private void moveDown() {
        grille.deplacerBas();
        tickJeu();
    }
    
    private void moveLeft() {
        grille.deplacerGauche();
        tickJeu();
    }
    
    private void moveRight() {
       grille.deplacerDroite();
       tickJeu();
    }
    
    private void updateGridLabels() {
    for (int i = 0; i < TAILLE_GRILLE; i++) {
        for (int j = 0; j < TAILLE_GRILLE; j++) {
            gridLabels[i][j].setText(grille.getGrille()[i][j].getNomAtom());
        }
    }
    
}

    /*public static void main(String[] args) {
        
    }*/
}
