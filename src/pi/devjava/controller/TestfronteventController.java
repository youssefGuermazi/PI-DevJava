/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXTextArea;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static pi.devjava.PIDevJava.bdd;
import static pi.devjava.controller.TestfrontGarderieController.i;
import pi.devjava.entities.Evenement;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.ServiceEvenement;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class TestfronteventController implements Initializable {

    @FXML
    private ImageView rectangle;
    @FXML
    private Label nomfield;
    @FXML
    private Label prenomfield;
    @FXML
    private Label activiterfield;
    @FXML
    private Label part;
    @FXML
    private JFXTextArea activiterfield1;
    @FXML
    private Label dd;
    @FXML
    private Label df;
    @FXML
    private Button butPart;
     @FXML
    private Button butPart1;
     @FXML
    private Label l1;
     @FXML
    private Label l2;
     @FXML
    private Label l3;
 static int b ;
static int i ;
public int t;
String nb;
int nbpp;
int  idd;
int idu;
int test;
 static Connection connexion;
    userService us = new userService();

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
           //System.out.println(FrontGarderieController.obs.get(i).getNumGard());  
           System.out.println(FrontEventController.obs.get(i));
           //a=FrontEventController.obs.get(i).getNbp();
          // nb=toString(a);
        prenomfield.setText(FrontEventController.obs.get(i).getEventName());
        nomfield.setText(FrontEventController.obs.get(i).getClub_evenement());
        activiterfield1.setText(FrontEventController.obs.get(i).getDescription());
        dd.setText(FrontEventController.obs.get(i).getDateDebut().toString());
        df.setText(FrontEventController.obs.get(i).getDateFin().toString());
        part.setText(Integer.toString(FrontEventController.obs.get(i).getNbp()));
         l1.setText("a");
         l2.setText(Integer.toString(FrontEventController.obs.get(i).getEventId()));
         test=bdd.getInt("select user_id from evenement_user WHERE evenement_id ="+l2.getText()+";",1);
        System.out.println(test);
        if (test==us.getInfoUser(Utilisateur.uName).getId())
        {
             butPart1.setVisible(true);
              butPart.setVisible(false);
        }
        else {
            butPart.setVisible(true);
              butPart1.setVisible(false);
        }
        l1.setVisible(false);
                l2.setVisible(false);
                l3.setVisible(false);
                part.setVisible(false);
         
        
        //formationfield.setText(FrontGarderieController.obs.get(i).getAdresse());
       ImageView rectangle1;
      FileInputStream inputstream = null; 
    try {
        inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontEventController.obs.get(i).getNomfile());
     
      Image img;
      System.out.println("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontEventController.obs.get(i).getNomfile());
      img =new Image(inputstream);
      rectangle1 =new ImageView(img);
      rectangle.setImage(img);}
      catch (FileNotFoundException ex) {
        Logger.getLogger(TestfronteventController.class.getName()).log(Level.SEVERE, null, ex);
    }
    i++;
        
    }  
        @FXML

      private void Participer(MouseEvent event) {
            
          
        
         //=(FrontEventController.obs.get(i).getEventId());
          
          
         nbpp=bdd.getInt("select nbp from evenement where id="+l2.getText()+";",1);
         idd=nbpp-1;
         idu=us.getInfoUser(Utilisateur.uName).getId();
                  bdd.sendRequest("UPDATE evenement SET nbp="+idd+" WHERE id="+l2.getText()+";");
                  bdd.sendRequest("INSERT INTO evenement_user (evenement_id,user_id ) values ("+l2.getText()+","+idu+");");
                   
      butPart1.setVisible(true);
       butPart.setVisible(false);
      }
         @FXML

      private void Annuler(MouseEvent event) {
            
          
        
         //=(FrontEventController.obs.get(i).getEventId());
          
          
         nbpp=bdd.getInt("select nbp from evenement where id="+l2.getText()+";",1);
         idd=nbpp+1;
         idu=us.getInfoUser(Utilisateur.uName).getId();
                  bdd.sendRequest("UPDATE evenement SET nbp="+idd+" WHERE id="+l2.getText()+";");
                  bdd.sendRequest("delete from evenement_user WHERE evenement_id="+l2.getText()+";");
                   butPart1.setVisible(false);
                   butPart.setVisible(true);
                   
      
      }
    



    
    
}
