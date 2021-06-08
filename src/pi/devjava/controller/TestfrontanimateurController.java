/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import pi.devjava.services.animateur_formationservice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pi.devjava.entities.animateur;

/**
 * FXML Controller class
 *
 * @author user
 */
public class TestfrontanimateurController implements Initializable {

    static int b ;
static int i ;
public int t;
public String aa ;
public static animateur e ;

    @FXML
    private ImageView rectangle;
    @FXML
    private Label nomfield;
    @FXML
    private Label prenomfield;
    @FXML
    private Label activiterfield;
    @FXML
    private Label formationfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        t=FrontanimateurController.obs.get(i).getCin();
        nomfield.setText(FrontanimateurController.obs.get(i).getNom());
        prenomfield.setText(FrontanimateurController.obs.get(i).getPrenom());
        prenomfield.setWrapText(true);
        activiterfield.setText(FrontanimateurController.obs.get(i).getActiviter());
        activiterfield.setWrapText(true);
        try {
            animateur_formationservice m = new animateur_formationservice();
           String titre = m.listformation2(t);
             formationfield.setText(titre);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestfrontanimateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageView rectangle1;
        
      FileInputStream inputstream = null; 
    try {
       
        inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontanimateurController.obs.get(i).getNomfile()); 
         Image img;
         System.out.println("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontanimateurController.obs.get(i).getNomfile());
       
            img =new Image(inputstream);
            rectangle1 =new ImageView(img);
            rectangle.setImage(img);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(TestfrontanimateurController.class.getName()).log(Level.SEVERE, null, ex);
    }
        i++;
        
        // TODO
    }    
    
}
