/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import Technique.Browser;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import static pi.devjava.controller.interfaceParentController.loadWindow;
import pi.devjava.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FrontController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button home;
    @FXML
    private TextField web;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void medecin(MouseEvent event)  {
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/Frontmedecin.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }
     @FXML
    private void garderie(MouseEvent event)  {
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/FrontGarderie.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }

 @FXML
    private void event(MouseEvent event)  {
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/FrontEvent.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }

    @FXML
    private void home(MouseEvent event) {
    loadWindow(getClass().getResource("/pi/devjava/GUI/Login.fxml"), "responsable", null);
                                              
        Utilisateur.uName="";
       // Scene scene = new Scene(root);
       // Stage stage  = new Stage();
       // stage.initStyle(StageStyle.UNDECORATED);
        //stage.setScene(scene);
        bp.getScene().getWindow().hide();
        //stage.show();
    }
    @FXML
    private void Login(MouseEvent event) {
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/interfaceParent.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }
    
    
    @FXML
    private void animateur(MouseEvent event) {
        
   Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/Frontanimateur.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }

    @FXML
    private void test(ActionEvent event) {
                Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("Frontanimateur.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }

    @FXML
    private void web(ActionEvent event) {
        Browser.load("https://www.google.com/search?q="+web.getText()+"", new Dimension(1200,700), new Point(30, 20));
    }
    
    
   
    
} 
