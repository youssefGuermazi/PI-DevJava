/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class Medecin_Image extends medecin {
    ImageView img;

    public Medecin_Image(ImageView img, String cin, String nom, String prenom, String specialite, String nomfile) {
        super(cin, nom, prenom, specialite, nomfile);
        this.img = img;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
    
    
}
