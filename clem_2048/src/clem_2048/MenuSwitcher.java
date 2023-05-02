/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;
    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author jc
 */

public class MenuSwitcher extends JFrame {
    JPanel contentPane;

    public JPanel getContentPane() {
        return contentPane;
    }
    
     
     public MenuSwitcher() {
        this.setMinimumSize(new Dimension(500, 500));
        DataClass data = new DataClass();
        data.setTaille(5);
        data.setGrille(new Grid(data.getTaille(), 5));
        EcranAccueil ecranAccueil = new EcranAccueil(data, this);
        EcranGrille ecranGrille = new EcranGrille(data, this);
        EcranFin ecranFin = new EcranFin(data);


    contentPane = new JPanel();
    contentPane.setLayout(new CardLayout());
    
    contentPane.add(ecranAccueil, "accueil");
    contentPane.add(ecranGrille, "grille");
    contentPane.add(ecranFin, "fin");
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(contentPane);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    addWindowListener(new WindowAdapter() {
  public void windowClosing(WindowEvent we) {
    Sauvegarde.sauvegarderGrille(data.getGrille());
  }
});
    
}

class EcranAccueil extends JPanel {
    private JTextField textField;
    private JTextField scoreMaxField;
    private JButton button;
    private MenuSwitcher menu;
    private int taille;
    
    // La classe contenant les données
    private DataClass data;
    
    public EcranAccueil(DataClass data, MenuSwitcher menu) {
        this.data = data;
        this.menu = menu;
        
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Entrez une taille pour la grille :");
        textField = new JTextField(5);
        JLabel label2 = new JLabel("Entrez le score max");
        scoreMaxField = new JTextField(5);
        button = new JButton("Valider");
        button.addActionListener(e -> NouvelleGrille());
        JButton buttonCharger = new JButton("Charger partie");
        buttonCharger.addActionListener(e -> ChargerPartie());
        // Ajout des composants au JPanel
        add(label);
        add(textField);
        add(label2);
        add(scoreMaxField);
        add(button);
        add(buttonCharger);
        
    }
    
    public void ChargerPartie() {
        try {
            Grid aa = Sauvegarde.chargerSauvegarde();
            if (aa != null) {
                data.setGrille(aa);
                data.setTaille(data.getGrille().getSize());
                ((EcranGrille)menu.getContentPane().getComponent(1)).updateData(data);
                CardLayout cl = (CardLayout)getParent().getLayout();
                cl.next(getParent());
            }
        } catch (IOException e) {
            System.out.println("Aucune partie sauvegardée.");
        }
    }
    public void NouvelleGrille() {
        try {
            taille = Integer.parseInt(textField.getText());
            int scoreMax = Integer.parseInt(scoreMaxField.getText());
            data.setTaille(taille);
            data.setGrille(new Grid(taille, 5));
            data.setScoreMax(scoreMax);
            // Affichage de l'écran suivant
            ((EcranGrille)menu.getContentPane().getComponent(1)).updateData(data);
            
            CardLayout cl = (CardLayout)getParent().getLayout();
            cl.next(getParent());
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
}

 class EcranGrille extends JPanel {
    private int taille_grille;
    private int TAILLE_CELLULE = 50;
    DataClass data;
    JLabel[][] gridLabels;
    MenuSwitcher menu;
    
    public void updateData(DataClass data) {
        this.removeAll();
        this.data = data;
        taille_grille = data.getTaille();
        gridLabels = new JLabel[taille_grille][taille_grille];
        for (int i = 0; i < taille_grille; i++) {
            for (int j = 0; j < taille_grille; j++) {
                JLabel label = new JLabel(data.getGrille().getGrille()[i][j].getNomAtom(), SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setPreferredSize(new Dimension(TAILLE_CELLULE, TAILLE_CELLULE));
                gridLabels[i][j] = label;
                this.add(label);
            }
        }
        JButton buttonUp = new JButton("Haut");
        JButton buttonDown = new JButton("Bas");
        JButton buttonLeft = new JButton("Gauche");
        JButton buttonRight = new JButton("Droite");
        JButton buttonQuitter = new JButton("Quitter");
        
        // Associer les boutons aux fonctions correspondantes
        buttonUp.addActionListener(e -> up());
        buttonDown.addActionListener(e -> down());
        buttonLeft.addActionListener(e -> left());
        buttonRight.addActionListener(e -> right());
        buttonQuitter.addActionListener(e -> quitter());
        
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(buttonUp);
        buttonPanel.add(buttonDown);
        buttonPanel.add(buttonLeft);
        buttonPanel.add(buttonRight);
        buttonPanel.add(buttonQuitter);
        
        setLayout(new GridLayout(taille_grille + 1, taille_grille));
        add(buttonPanel, BorderLayout.SOUTH);

    }
    public EcranGrille(DataClass data, MenuSwitcher menu) {
        this.menu = menu;
        updateData(data);
        
    }
    public void quitter() {
        Sauvegarde.sauvegarderGrille(data.getGrille());
        System.exit(0);
    }
    public void up() {
        data.getGrille().deplacerHaut();
        majGrille();
    }

    public void down() {
        data.getGrille().deplacerBas();
        majGrille();
    }

    public void left() {
        data.getGrille().deplacerGauche();
        majGrille();
    }

    public void right() {
        data.getGrille().deplacerDroite();
        majGrille();
    }

    private void majGrille() {
        for (int i = 0; i < taille_grille; i++) {
            for (int j = 0; j < taille_grille; j++) {
                gridLabels[i][j].setText(data.getGrille().getGrille()[i][j].getNomAtom());
            }
        }
        data.getGrille().tuerCellulesExpirees();
        data.getGrille().generationCellule();
        for (int i = 0; i < taille_grille; i++) {
            for (int j = 0; j < taille_grille; j++) {
                gridLabels[i][j].setText(data.getGrille().getGrille()[i][j].getNomAtom());
            }
        }
        if (data.getGrille().grilleBloquee()) {
            CardLayout cl = (CardLayout)getParent().getLayout();
            data.setMessageFin("Perdu!");
            ((EcranFin)menu.getContentPane().getComponent(2)).updateData(data);
            cl.next(getParent());
            }
        if (data.getGrille().checkSiTermine(data.getScoreMax())) {
            CardLayout cl = (CardLayout)getParent().getLayout();
            data.setMessageFin(String.format("Gagné! Score max: %d", data.getGrille().highScore()));
            ((EcranFin)menu.getContentPane().getComponent(2)).updateData(data);
            cl.next(getParent());
        }
    }
}



 class EcranFin extends JPanel {
     private DataClass data;
     public void updateData(DataClass data) {
         this.data = data;
         setLayout(new BorderLayout());
            JLabel label = new JLabel(data.getMessageFin());
            add(label, BorderLayout.CENTER);
     }
        public EcranFin(DataClass data) {
            updateData(data);
        }
}

 class DataClass {
    private int taille;
    private Grid grille;
    private int scoreMax;
    private String messageFin;

    public String getMessageFin() {
        return messageFin;
    }

    public void setMessageFin(String messageFin) {
        this.messageFin = messageFin;
    }
    
    public int getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public Grid getGrille() {
        return grille;
    }

    public void setGrille(Grid grille) {
        this.grille = grille;
    }
    
    public int getTaille() {
        return taille;
    }
    
    public void setTaille(int taille) {
        this.taille = taille;
    }
}

    
