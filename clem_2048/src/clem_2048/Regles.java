/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clem_2048;

import java.util.HashMap;


public class Regles {
    public static HashMap<Integer, Integer> MapTempsDeVie = new HashMap<>() {{
         put(8, 5);
         put(32, 5);
    }};
    
    public static int RecupererTempsDeVie(int numeroCellule) {
        if (MapTempsDeVie.containsKey(numeroCellule)) {
            return MapTempsDeVie.get(numeroCellule);
        }
        return -1;
    }
    
}
