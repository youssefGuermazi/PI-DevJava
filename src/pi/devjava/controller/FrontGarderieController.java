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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import pi.devjava.entities.Garderie;
import pi.devjava.entities.info_sante;
import pi.devjava.services.GarderieService;
import pi.devjava.services.info_santeservice;

/**
 *
 * @author youssef
 */
public class FrontGarderieController implements Initializable{
    static ObservableList<Garderie> obs;

public static int indice ;
    @FXML
    private ScrollPane scroll;
    @FXML
    private FlowPane fo;

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Afficher();
        System.out.println("aaajuuababaayou");
    }    
    
     public void Afficher()  {
         
         fo.getChildren().removeAll(fo.getChildren());
         GarderieService srv = new GarderieService();
         TestfrontGarderieController.b = 0;
         TestfrontGarderieController.i = 0;
         ArrayList<Garderie> annonces = srv.afficherGarderie();
         obs = FXCollections.observableArrayList(annonces);
         //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
         //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
         indice = 0;
         Node[] nodes = new Node[obs.size()];
         System.out.println("aaa");
         for (int i = 0; i < nodes.length; i++) {
             try {
                 
                 System.out.println("aaaaa");
                 
                 nodes[i] = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/testfrontGarderie.fxml"));
//                e=FrontEventController.obsl.get(i);
fo.getChildren().add(nodes[i]);
             } catch (IOException e) {
             }
             
         }
             
         }

    
    

    
}
