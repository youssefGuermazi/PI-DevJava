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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static pi.devjava.PIDevJava.bdd;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import static pi.devjava.controller.TestFrontmedecinController.i;
import pi.devjava.entities.Garderie;
import pi.devjava.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class TestfrontGarderieController implements Initializable {
    static int b ;
static int i ;
public int t;
public String aa ;
public static Garderie e ;
    @FXML
    private ImageView rectangle;
    @FXML
    private Label nomfield;
    @FXML
    private Label prenomfield;
    @FXML
    private JFXTextArea activiterfield1;
    @FXML
    private Label formationfield;
    static String num;
    static String nomm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(FrontGarderieController.obs.get(i).getNumGard());   
        nomfield.setText(FrontGarderieController.obs.get(i).getNom());
        prenomfield.setText(FrontGarderieController.obs.get(i).getTelephone());
        activiterfield1.setText(FrontGarderieController.obs.get(i).getAdresse());
        formationfield.setText(FrontGarderieController.obs.get(i).getNumGard());
        formationfield.setVisible(false);
        //formationfield.setText(FrontGarderieController.obs.get(i).getAdresse());
       ImageView rectangle1;
      FileInputStream inputstream = null; 
    try {
        inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontGarderieController.obs.get(i).getImage());
     
      Image img;
      System.out.println("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontGarderieController.obs.get(i).getImage());
      img =new Image(inputstream);
      rectangle1 =new ImageView(img);
      rectangle.setImage(img);}
      catch (FileNotFoundException ex) {
        Logger.getLogger(TestfrontGarderieController.class.getName()).log(Level.SEVERE, null, ex);
    }
    i++;
        
        
        
        
    }    
    @FXML
      private void ajout(MouseEvent event) {
           nomm=nomfield.getText();
          num=formationfield.getText();
          loadWindow(getClass().getResource("/pi/devjava/GUI/AjoutEnfant.fxml"), "ajouter",null);
        
         //Stage stage = (Stage) activiterfield1.getScene().getWindow();
         //stage.close();
    }
     
      }
    

