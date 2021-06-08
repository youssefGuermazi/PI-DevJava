/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author kamikaz
 */
public class EnfantAffichage extends Enfant{
    ImageView image;
    String Garderie;

    public EnfantAffichage(ImageView image, int id, int parent, String nom, String prenom, String nomfile, String garderie_id, Date dn) {
        super(id, parent, nom, prenom, nomfile, garderie_id, dn);
        this.image = image;
    }

    public EnfantAffichage(ImageView image, String Garderie, int id, int parent, String nom, String prenom, String nomfile, String garderie_id, Date dn) {
        super(id, parent, nom, prenom, nomfile, garderie_id, dn);
        this.image = image;
        this.Garderie = Garderie;
    }
  
    

   
    

    public String getGarderie() {
        return Garderie;
    }

    public void setGarderie(String Garderie) {
        this.Garderie = Garderie;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "EnfantAffichage{" + "image=" + image + ", Garderie=" + Garderie + '}';
    }
    
}
