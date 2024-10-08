/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JeuClasses;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author apenne
 */

public class Paquet {
    private ArrayList<Carte> cartes = new ArrayList<>();
    private ArrayList<Carte> defausse = new ArrayList<>();
    private String[] couleurs = {"blanc","bleu","cyan","jaune","rose","rouge","vert"};
    private String[] formes = {"ballon","bloc","cone","escalier","tube","voiture"};
    
    public Paquet(int nbFormes, int nbCouleurs){
       
        for(int i = 0; i < nbFormes; i++){
           
            for (int y = 0; y < nbCouleurs; y++){
                
                Carte carte = new Carte(couleurs[y],formes[i]);
                this.cartes.add(carte);
            }
        }
        
        Collections.shuffle(this.cartes);
    
        
        
    }
    
    public Paquet(ArrayList<Carte> cartes){
        this.cartes = cartes;
    }
    
    public ArrayList<Carte> getCartes(){
        return this.cartes;
    }
    
    public void setCartes(ArrayList<Carte> cartes){
        this.cartes = cartes;
    }
    
    public void retireCarte(){  
        this.defausse.add(this.cartes.get(0));
        this.cartes.remove(0);
        
        if (this.cartes.isEmpty()){ //utilise la defausse
            this.cartes = this.defausse;
            Collections.shuffle(this.cartes);
            this.defausse = new ArrayList<>();
            
        }
    }
    
    public Carte getCarteDessus(){
        return this.cartes.get(0);
    }
    
    
    
}
