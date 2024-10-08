package JeuClasses;


public class Carte {
    private String couleur;
    private String forme;
    
    public Carte(){}
   
    public Carte(String couleur, String forme){
       
        this.couleur = couleur;
        this.forme = forme;
    }
    
    public String getCouleur(){
        return this.couleur;
    }
    public void setCouleur(String couleur){
        this.couleur = couleur;
    }
    
    public String getForme(){
        return this.forme;
    }
    
    public void setForme(String forme){
        this.forme = forme;
    }
    
    @Override
    public String toString(){
        return this.forme + "/" + this.couleur;
    }
    
}
