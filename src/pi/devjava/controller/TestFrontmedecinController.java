/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;


import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import Technique.Browser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;



/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TestFrontmedecinController implements Initializable {
static int b ;
static int i ;
public int t;
public String aa ;
    
     Connection  myConnex;
       Statement ste;
    private Label descriptionfield;
    @FXML
    private Label date;
    @FXML
    private Label lieu;
    @FXML
    private ImageView star1;
    @FXML
    private Label nomtf;
    @FXML
    private Label prenomf1;
    @FXML
    private Label sp;
    private TextArea con;
    @FXML
    private Label nomfield;
    @FXML
    private Button google;
    @FXML
    private ImageView rectangle;
       
       

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // tester(); 
        // TODO
        //  lignecommandeService = new LigneCommandeService();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMMMMMM");
      //  t=FrontController.obs.get(i).getId_event();
        aa=FrontmedecinController.obs.get(i).getMed().getNom();
        date.setText(FrontmedecinController.obs.get(i).getDatevaccin());
        nomtf.setText(FrontmedecinController.obs.get(i).getMed().getNom()+" "+FrontmedecinController.obs.get(i).getMed().getPrenom());
       // prenomf1.setText(FrontmedecinController.obs.get(i).getMed().getPrenom());
        sp.setText(FrontmedecinController.obs.get(i).getMed().getSpecialite());
        
     //   InputStream input = this.getClass().getResourceAsStream("C:\\Users\\lenovo\\Desktop\\PI\\"+this.getClass().getResource(FrontmedecinController.obs.get(i).getMed().getNomfile()));
      //  Image img = new Image(input);
      //  rectangle.setImage(img);
        ImageView rectangle1;
      FileInputStream inputstream = null; 
    try {
        inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontmedecinController.obs.get(i).getMed().getNomfile());
     
      Image img;
      System.out.println("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+FrontmedecinController.obs.get(i).getMed().getNomfile());
      img =new Image(inputstream);
      rectangle1 =new ImageView(img);
      rectangle.setImage(img);}
      catch (FileNotFoundException ex) {
        Logger.getLogger(TestFrontmedecinController.class.getName()).log(Level.SEVERE, null, ex);
    }
           
            
      
        sp.setWrapText(true);
       // con.setText(FrontmedecinController.obs.get(i).getInfo());
        nomfield.setText(FrontmedecinController.obs.get(i).getInfo());
       // lieu.setText(FrontmedecinController.obs.get(i).getMed().getPrenom());
        //nomfield.setWrapText(true);
        //descriptionfield.setText(FrontmedecinController.obs.get(i).getDatevaccin());
       // descriptionfield.setWrapText(true);
       
    
       
        i++;
        
        
        
         google.setOnAction((event) -> {
           
     Browser.load("https://www.google.com/search?q="+aa+"", new Dimension(1200,700), new Point(30, 20));
           //  Browser.load("https://www.google.com/search?q=youssef", new Dimension(1200,700), new Point(30, 20));  //
            
        });
        
        
        
        
    }  

    @FXML
    private void google(ActionEvent event) {
     // Browser.load("https://www.google.com/search?q="+aa+"", new Dimension(1200,700), new Point(30, 20));

    }
    
    
    
    
    
    
    
    
    
    
    
    
}
