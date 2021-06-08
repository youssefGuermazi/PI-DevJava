/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static pi.devjava.controller.LoginController.loadWindow;
import static pi.devjava.controller.addGardController.saveToFile;
import pi.devjava.entities.Garderie;
import pi.devjava.services.GarderieService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class updateGardController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private ImageView view1;
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXTextField numg;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton Modifier;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField adresse;
    @FXML
    private ImageView view2;
    @FXML
    private Label labelImage;

    /**
     * Initializes the controller class.
     */
    
        GarderieService se1;

    private Garderie g;
    public String URL ;
        boolean verificationImage=false;
        FileInputStream fis = null;
    File f=null;
    List<String> lstFile;
   

    public updateGardController() throws SQLException {
        this.se1 = new GarderieService();
    }

    public Garderie getG() {
        return g;
    }

    public void setG(Garderie g) {
        this.g = g;
    }

    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
   // @SuppressWarnings("empty-statement")
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numg.setText(afficheGardController.GardMod.getNumGard());
        nom.setText(afficheGardController.GardMod.getNom());
        adresse.setText(afficheGardController.GardMod.getAdresse());
        tel.setText(afficheGardController.GardMod.getTelephone());
        //labelImage.setText(afficheGardController.GardMod.getImage());
        //String name=afficheGardController.GardMod.getImage();
        //Class<?> clazz = this.getClass();
        //String ur=("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
        //final URL imageURL = getClass().getResource(ur); 
       // final Image image = new Image(imageURL.toExternalForm()); 
        
            //localUrl = f.toURI().toURL().toString();
           // Image image = new Image(input);
            //view2.setImage(image);
       // System.out.println(name);
       
        //view2.setVisible(true);
            //view1.setVisible(false);
            
        lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.jpg");
        String name=afficheGardController.GardMod.getImage();
        System.out.println(name);
        FileInputStream inputstream; 
        try {
            inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
             Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
            view2.setImage(img);
            view2.setFitHeight(250);
            view2.setFitWidth(250);
           view2.setVisible(true);
            view1.setVisible(false);
            labelImage.setText("Cliquez sur l'image pour la changer!");
           
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(updateGardController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        

    }

 
    @FXML
     private void ChargeImage(MouseEvent event) throws MalformedURLException, FileNotFoundException{
         
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("word file", lstFile));
        f = fc.showOpenDialog(null);
        
        if (f!=null)
        {
            //lab.setText(f.getAbsolutePath());
            Image image = new Image(f.toURI().toString());
            view2.setImage(image) ;
            view2.setVisible(true);
            view1.setVisible(false);
            labelImage.setText("Cliquez sur l'image pour la changer!");
            URL=f.getPath();
            if(URL!="")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }
    public static void saveToFile(Image image,String name) {
        
         File outputFile = new File("C:\\Users\\youssef\\Documents\\NetBeansProjects\\PI-DevJava\\src\\pi\\devjava\\images\\"+name);
                  File outputw = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);

         BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
        ImageIO.write(bImage, "png", outputw);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

    @FXML
    private void Modifier(MouseEvent event) {
       
//saveToFile( new Image(f.toURI().toString()),f.getName());

        String num = numg.getText();
        String nomg = nom.getText();
        String telephone=tel.getText();
        String adr=adresse.getText();
       // String img=f.getName();
        //view1.getImage() ;
        
       Garderie g =new Garderie(num,nomg,adr,telephone);

        GarderieService.getInstance().ModifierC(g);
        

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification");
        alert.setHeaderText(null);
        alert.setContentText("Garderie Modifié");

        alert.showAndWait();
        
        {
            /*
                        loadWindow(getClass().getResource("/pi/devjava/GUI/afficheGard.fxml"), "Dashboard", null);
Stage stage = (Stage) nom.getScene().getWindow();
         stage.close();*/
                        
                        }

    }



        
    

    @FXML
    private void closeApplication(MouseEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'inerface garderie");
        alert.setHeaderText("Vous allez quitter l'inerface garderie");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             inscrirLabel.getScene().getWindow().hide();
        } else {
            alert.close();
}
 
    }

    @FXML
       private void ChangeImage(MouseEvent event) throws MalformedURLException, FileNotFoundException{
        
           FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("word file", lstFile));
       f = fc.showOpenDialog(null);
        
        if (f!=null)
        {
            //lab.setText(f.getAbsolutePath());
            Image image = new Image(f.toURI().toString());
            view2.setImage(image) ;
            view2.setVisible(true);
            view1.setVisible(false);
            labelImage.setText("Cliquez sur l'image pour la changer!");
            URL=f.getPath();
            System.out.println(URL.toString());
            System.out.println(fc);
            if (URL != "")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }
    
}
