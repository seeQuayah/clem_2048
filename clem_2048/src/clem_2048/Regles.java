/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;

import java.util.HashMap;

/**
 *
 * @author jc
 */
public class Regles {
    public static HashMap<Integer, Integer> MapTempsDeVie = new HashMap<>();
    
    public static int RecupererTempsDeVie(int numeroCellule) {
        if (MapTempsDeVie.containsKey(numeroCellule)) {
            return MapTempsDeVie.get(numeroCellule);
        }
        return -1;
    }
    public static enum Direction {
        DROITE,
        GAUCHE,
        HAUT,
        BAS,
        STATIQUE,
    }
    
}
