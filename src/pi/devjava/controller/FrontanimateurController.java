/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import pi.devjava.controller.AnimateurController;

import java.util.ArrayList;
import pi.devjava.services.animateurservice;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import pi.devjava.entities.animateur;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontanimateurController implements Initializable {
    
    static ObservableList<animateur> obs;
public static int indice ;

    @FXML
    private ScrollPane scroll;
    @FXML
    private FlowPane fo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Afficher();
        // TODO
    }    
    

     public void Afficher()  {
        try {
         fo.getChildren().removeAll(fo.getChildren());
       animateurservice srv = new animateurservice();
        
        TestfrontanimateurController.b = 0;
         TestfrontanimateurController.i = 0;
        ArrayList<animateur> annonces = srv.afficherevent1();
        obs = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obs.size()];
       for (int i = 0; i < nodes.length; i++) {
           try {
                

            
                nodes[i] = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/testfrontanimateur.fxml"));
//                e=FrontEventController.obsl.get(i);
                fo.getChildren().add(nodes[i]);
            } catch (IOException e) {
            }

        }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TestfrontanimateurController.class.getName()).log(Level.SEVERE, null, ex);
    }
             
         }

    

    
    
    
}

