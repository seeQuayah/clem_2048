
package clem_2048;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


public class Cell {

    
    private Instant debutTimer;
    private final int size;
    private int tempsDeVie;
    private int x;
    private int y;
    private int contenu;
    public boolean contenuAChange;
    
    public Cell(int x, int y, int size, int contenu) {
        this.debutTimer = Instant.now();
        this.x = x;
        this.y = y;
        this.size = size;
        this.contenu = contenu;
        this.tempsDeVie = Regles.RecupererTempsDeVie(contenu);
    }
    
    public boolean estVide() {
        return contenu == 0;
    }

    public int getContenu() {
        return contenu;
    }

    public void setContenu(int contenu) {
        this.contenu = contenu;
        this.tempsDeVie = Regles.RecupererTempsDeVie(contenu);
       
        if (tempsDeVie != -1) {
            this.debutTimer = Instant.now();
        }
    }
    
    public void setContenu(Cell cell) {
        this.contenu = cell.getContenu();
        this.debutTimer = cell.getTimer();
        this.tempsDeVie = cell.tempsDeVie;
    }
    public void setTimer(Instant timer) {
        this.debutTimer = timer;
    }
    
    
    public Instant getTimer() {
        return debutTimer;
    }
    
    public void tuerCellule() {
        this.contenu = 0;
        this.tempsDeVie = -1;
    }
    
    public boolean estVivante() {
        if (tempsDeVie == -1)
            return true;
        Instant now = Instant.now();
        long elapsedTimeSeconds = ChronoUnit.SECONDS.between(debutTimer, now);
        return elapsedTimeSeconds < tempsDeVie;
    }

    int getY() {
        return y;
    }

    int getX() {
        return x;
    }
    
    @Override
    public String toString() {
        return String.format("%d", this.getContenu());
    }
}
