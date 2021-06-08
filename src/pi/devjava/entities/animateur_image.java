/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class animateur_image extends animateur{
private ImageView imo;

    public animateur_image(ImageView imo, int cin, String nom, String prenom, String activiter, String nomfile) {
        super(cin, nom, prenom, activiter, nomfile);
        this.imo = imo;
    }

    public ImageView getImo() {
        return imo;
    }

    public void setImo(ImageView imo) {
        this.imo = imo;
    }

   
}
