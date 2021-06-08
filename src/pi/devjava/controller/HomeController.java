/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static pi.devjava.controller.interfaceResponsableController.loadWindow;
import pi.devjava.entities.Utilisateur;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button conseil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void medecin(MouseEvent event) { 
        Loadp("medecin");
    } 
      private void Loadp(String p) 
     {  
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/"+p+".fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
     }



    @FXML
    private void conseil(MouseEvent event) { 
          Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/conseil.FXML.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }
    
     @FXML
    private void animateur(MouseEvent event) { 
        Loadp1("animateur");
    } 
      private void Loadp1(String p) 
     {  
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/"+p+".fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
     }



    @FXML
    private void formation(MouseEvent event) { 
          Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/Formation.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
    }

     @FXML
    private void user(MouseEvent event) { 
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/ut.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
        
    }
    
      @FXML
    private void deconnection(MouseEvent event) { 
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
    private void gard(MouseEvent event) { 
         Parent root = null ; 
        try { 
           root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/afficheGard.fxml")) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         bp.setCenter(root);
        
    }
    
    
   
  
}
