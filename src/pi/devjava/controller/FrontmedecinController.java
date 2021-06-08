/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import pi.devjava.entities.info_sante;
import pi.devjava.services.info_santeservice;
import sample.Controller;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FrontmedecinController implements Initializable {
static ObservableList<info_sante> obs;

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
        // TODO
        Afficher();
    }    
    
     public void Afficher()  {
         fo.getChildren().removeAll(fo.getChildren());
           MedecinController s = new MedecinController();
         // medecinservice srv = new medecinservice();
         info_santeservice ss = new info_santeservice();
         TestFrontmedecinController.b = 0;
         TestFrontmedecinController.i = 0;
         ArrayList<info_sante> medecin= ss.afficherinfo();
        
         obs = FXCollections.observableArrayList(medecin);
        
         //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
         //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
         indice = 0;
         Node[] nodes = new Node[obs.size()];
         for (int i = 0; i < nodes.length; i++) {
             try {
                 
                 
                 
                 nodes[i] = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/TestFrontmedecin.fxml"));
//               e=FrontEventController.obsl.get(i);
fo.getChildren().add(nodes[i]);
             } catch (IOException e) {
             }
             
         }

    }

    
    
    
}
