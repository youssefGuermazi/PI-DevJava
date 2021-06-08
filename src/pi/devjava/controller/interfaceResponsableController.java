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
import java.net.MalformedURLException;
import java.util.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import static pi.devjava.PIDevJava.bdd;
import static pi.devjava.controller.updateGardController.saveToFile;
import pi.devjava.entities.Garderie;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.GarderieService;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class interfaceResponsableController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private ImageView view1;
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXTextField nomt;
    @FXML
    private JFXButton Modifier;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField telt;
    @FXML
    private JFXTextField adresset;
    @FXML
    private ImageView view2;
    @FXML
    private Label labelImage;
    @FXML
    private Label nom;
    @FXML
    private Label mail;
    @FXML
    private Label telephone;
    @FXML
    private Label adres;
     @FXML
    private Label nom1;
    @FXML
    private Label mail1;
    @FXML
    private Label telephone1;
    @FXML
    private Label adres1;
    @FXML
    private JFXTextField mailt;
    @FXML
    private ImageView defimage;
    @FXML
    private JFXButton profButton;
    @FXML
    private JFXButton gardButton;
    @FXML
    private JFXButton clubButton;
    @FXML
    private JFXButton eventButton;
    @FXML
    private JFXButton animButton;
    @FXML
    private JFXButton formtionButton;
    @FXML
    private JFXButton decButton;
    @FXML
    private JFXButton affprof;
    userService us = new userService();
    static Integer ident;
    File f=null;
    List<String> lstFile;
      public String URL ;
        boolean verificationImage=false;

  
    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.jpg");
        
       close.setVisible(false);
       formtionButton.setVisible(false);
       //animButton.setVisible(false);
      
      //adres.setVisible(false);
      adresset.setVisible(false);
      Modifier.setVisible(false);
      defimage.setVisible(true);
    labelImage.setVisible(false);
      mailt.setVisible(false);
      nom.setVisible(true);
      nomt.setVisible(false);
      //telephone.setVisible(false);
      telt.setVisible(false);
      view1.setVisible(false);
      view2.setVisible(false);
      
      nom.setText(us.getInfoUser(Utilisateur.uName).getUsername());
      mail.setText(us.getInfoUser(Utilisateur.uName).getMail());
        System.out.println("aaa");
        ident=us.getInfoUser(Utilisateur.uName).getId();
        if (bdd.getInt("SELECT * FROM info WHERE id_user ="+ident+";",1)==-1)
        {
         bdd.sendRequest("insert into info  (id_user) values("+ident+") ;");
         telephone.setVisible(true);
         adres.setVisible(true);
        
        }
        adres.setText(bdd.getString("SELECT adresse FROM info WHERE id_user ="+ident+";",1));
         telephone.setText(bdd.getString("SELECT tel FROM info WHERE id_user ="+ident+";",1));
        //bdd.getString("SELECT * FROM user WHERE id="+ident+";",8);
      //interfaceResponsable
             

     //nom.setText(servClient.getInfoClient(User.uName).getFirstname()
     
     
     //image
           
          
        String name=(bdd.getString("SELECT image FROM info WHERE id_user ="+ident+";",1));
        System.out.println(name);
        if (name=="")
        {
        defimage.setVisible(true);
        }
        else{
               
        FileInputStream inputstream; 
        try {
            inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
             Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
            
            defimage.setImage(img);
            defimage.setFitHeight(150);
            defimage.setFitWidth(150);
           //view2.setVisible(true);
            //view1.setVisible(false);
            //labelImage.setText("Cliquez sur l'image pour la changer!");
           
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(interfaceResponsableController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }  }
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
            String img=f.getName();
                   bdd.sendRequest("update info set image='"+img+"' WHERE id_user ="+ident+";");
                   defimage.setImage(image);
                           saveToFile( new Image(f.toURI().toString()),f.getName());


            if(URL!="")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }

    @FXML
    private void Modifier(MouseEvent event) {    
    }

    @FXML
    private void save(ActionEvent event) {
  
       // String name=(bdd.getString("SELECT image FROM info WHERE id_user ="+ident+";",1));
        //saveToFile( new Image(f.toURI().toString()),f.getName());

        String email = mailt.getText();
        String nomu = nomt.getText();
        String teleph=telt.getText();
        String adrs=adresset.getText();
        //String img=f.getName();
        //view1.getImage() ;
        
       //Utilisateur u =new Utilisateur(email, nomu);
       
       us.changenom(nomu, ident);
       us.changemail(email,ident);

        //userService.getInstance().ModifierUser(u);
        System.out.println(nomu);
       bdd.sendRequest("update info set tel='"+teleph+"' WHERE id_user ="+ident+";");
       bdd.sendRequest("update info set adresse='"+adrs+"' WHERE id_user ="+ident+";");
//       bdd.sendRequest("update info set image='"+img+"' WHERE id_user ="+ident+";");
        //sendRequest("INSERT INTO user (email,email_canonical,username,username_canonical,roles,password,enabled) VALUES ('"+mail+"','"+mail+"', '"+username+"', '"+username+"','"+role+"','"+password+"','"+1+"');");
            mail.setText(mailt.getText());
      nom.setText(nomt.getText());
      telephone.setText(telt.getText());
      adres.setText(adresset.getText());
      //view2.setImage(defimage.getImage());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification");
        alert.setHeaderText(null);
        alert.setContentText("profil Modifié");
        alert.showAndWait();
    }

    @FXML
    private void closeApplication(MouseEvent event) {
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
            String img=f.getName();
                   bdd.sendRequest("update info set image='"+img+"' WHERE id_user ="+ident+";");
                   defimage.setImage(image);
                           saveToFile( new Image(f.toURI().toString()),f.getName());


            if(URL!="")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }

    @FXML
    private void updateprof(ActionEvent event) {
         adres.setVisible(false);
         adres1.setVisible(false);
      adresset.setVisible(true);
      Modifier.setVisible(true);
      defimage.setVisible(false);
    mail.setVisible(false);
        mail1.setVisible(false);

      mailt.setVisible(true);
      nom.setVisible(false);
            nom1.setVisible(false);

      nomt.setVisible(true);
      telephone.setVisible(false);
            telephone1.setVisible(false);
            labelImage.setVisible(true);

      telt.setVisible(true);
      view1.setVisible(false);
      view2.setVisible(true);
      mailt.setText(mail.getText());
      nomt.setText(nom.getText());
      telt.setText(telephone.getText());
      adresset.setText(adres.getText());
      view2.setImage(defimage.getImage());
            //view1.setImage(defimage.getImage());
            labelImage.setText("Cliquez sur l'image pour la changer!");

        
    }
    @FXML
    private void affprof (ActionEvent event) {
           adres.setVisible(true);
         adres1.setVisible(true);
      adresset.setVisible(false);
      Modifier.setVisible(false);
      defimage.setVisible(true);
    mail.setVisible(true);
        mail1.setVisible(true);

      mailt.setVisible(false);
      nom.setVisible(true);
            nom1.setVisible(true);

      nomt.setVisible(false);
      telephone.setVisible(true);
            telephone1.setVisible(true);

      telt.setVisible(false);
      view1.setVisible(false);
      view2.setVisible(false);
      labelImage.setVisible(false);
    }

    @FXML
    private void affgard(ActionEvent event) {
        
        
        
        loadWindow(getClass().getResource("/pi/devjava/GUI/addGard.fxml"), "responsable", null);
        
        //adres.getScene().getWindow().hide();
  
        
    }

    @FXML
    private void affClub(ActionEvent event) {
        
 loadWindow(getClass().getResource("/pi/devjava/GUI/ajoutClub.fxml"), "responsable", null);    }

    @FXML
    private void affEven(ActionEvent event) {
         loadWindow(getClass().getResource("/pi/devjava/GUI/ajoutEvenement.fxml"), "responsable", null);
    }

    @FXML
    private void affsui(ActionEvent event) {
         loadWindow(getClass().getResource("/pi/devjava/GUI/AfficherEnfantsParent.fxml"), "responsable", null);
    }

    @FXML
    private void affForm(ActionEvent event) {
    }
     public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.UNDECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void deconect(ActionEvent event) throws IOException {
        // Parent root = null;
                       

                            loadWindow(getClass().getResource("/pi/devjava/GUI/Login.fxml"), "responsable", null);
                                              
        Utilisateur.uName="";
       // Scene scene = new Scene(root);
       // Stage stage  = new Stage();
       // stage.initStyle(StageStyle.UNDECORATED);
        //stage.setScene(scene);
        adres.getScene().getWindow().hide();
        //stage.show();
    }
    
}
