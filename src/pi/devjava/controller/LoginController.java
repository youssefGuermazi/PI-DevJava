/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import static pi.devjava.PIDevJava.bdd;
import pi.devjava.connectionBD.Session;
import pi.devjava.entities.Mailing;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.BCrypt;
import pi.devjava.services.ServiceRandomMailConfirmation;
import pi.devjava.services.userService;
import tray.animations.AnimationType;
import static tray.notification.NotificationType.ERROR;
import static tray.notification.NotificationType.SUCCESS;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private Label inscrirLabel;
     @FXML
    private Label inscrirLabel1;
    @FXML
    private JFXTextField username;
     @FXML
    private JFXTextField mail;
     @FXML
    private JFXTextField incode;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Hyperlink mdoLabel;
    @FXML
    private JFXButton Login;
    @FXML
    private JFXButton code;
     @FXML
    private JFXButton vcode;
    @FXML
    private ImageView close;
    @FXML
    private TableView<?> table;
      @FXML
    private JFXPasswordField nvmpTF;
    @FXML
    private JFXPasswordField rnvmpTF;
    @FXML
    private JFXButton terminer;
    boolean verificationEmail = false;
    userService us = new userService();
    ServiceRandomMailConfirmation serviceMail = new ServiceRandomMailConfirmation();
    //static String uname;
    public static Utilisateur o;
    
String codem =  serviceMail.generateRandomString();
             

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mail.setVisible(false);
        code.setVisible(false);
        vcode.setVisible(false);
        incode.setVisible(false);
         inscrirLabel1.setVisible(false);
            
            terminer.setVisible(false);
            nvmpTF.setVisible(false);
            rnvmpTF.setVisible(false);
        // TODO
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
    private void Login(MouseEvent event) {
         String user = username.getText();
        String mdp = password.getText();
        //bdd.connection();
        //Integer id= bdd.getInt("SELECT * FROM user WHERE username ='"+user+"';",1);
        
        String pssd=bdd.getString("SELECT * FROM user WHERE username='"+user+"';",8);
        
       
            try{
           //userService us = new userService();
           
                //if (us.findByLogin(user) && us.checkpw(mdp, user)){
                    o = new Utilisateur(user);
            if(BCrypt.checkpw(mdp,pssd)){
                String proff ;
                //Accés
                if (us.Gettype(user).equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}"))
               proff= "Parent"; 
                else 
                if (us.Gettype(user).equals("a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}"))
               proff = "Responsable";
            
                else proff = "Admin";
                switch (proff){
                        case "Parent":
                        {
                              /*{
                     Session.LoggedUser = (Session.iuserService.findByname(user));
                        Utilisateur.uName=user;
                                        System.out.println(Utilisateur.uName);

                                                 username.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenementFront.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
                }*/
                             Session.LoggedUser = (Session.iuserService.findByname(user));
                        Utilisateur.uName=user;
                                        System.out.println(Utilisateur.uName);

                                                 username.getScene().getWindow().hide();

                        loadWindow(getClass().getResource("/pi/devjava/GUI/Front.fxml"), "responsable", null);
                            
                        }
                        break;
                      
                        case "Responsable":{
                       //JOptionPane.showMessageDialog(null, "resp");
                       Session.LoggedUser = (Session.iuserService.findByname(user));
                        Utilisateur.uName=user;
                                        System.out.println(Utilisateur.uName);

                                                 username.getScene().getWindow().hide();

                         loadWindow(getClass().getResource("/pi/devjava/GUI/interfaceResponsable.fxml"), "responsable", null);
                        }
                        break;
                        case "Admin":
                        {
                        Session.LoggedUser = (Session.iuserService.findByname(user));
                        Utilisateur.uName=user;
                                        System.out.println(Utilisateur.uName);

                                                 username.getScene().getWindow().hide();

                         loadWindow(getClass().getResource("/pi/devjava/GUI/Home.fxml"), "responsable", null);
                /*Notifications n = Notifications.create()
                        .title("Bienvenue")
                        .text("Vous étes connecté en tant que Administrateur!")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(5));
                n.showInformation();*/
                        }
                          
                        break; 
            }}
            else{
                JOptionPane.showMessageDialog(null, "Vérifier NOM D'UTILISATEUR / MOT DE PASSE !!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Vérifier NOM D'UTILISATEUR / MOT DE PASSE !!");
        }
    }
    @FXML
    private void switchMdo(ActionEvent event) {
        mdoLabel.setVisible(false);
        inscrirLabel.setVisible(false);
        Login.setVisible(false);
        username.setVisible(false);
        password.setVisible(false);
        mail.setVisible(true);
        code.setVisible(true);
        vcode.setVisible(false);
        incode.setVisible(false);
     
    }
    @FXML
    
    private void changerpassword(ActionEvent event) {
          if (us.searchUserByEmail(mail.getText())==true)
             
        
           // verificationEmail=true;
            {
              
              
              
              System.out.println(codem);
              String to = mail.getText();
                String subject = "Changement de mot de passe";
                String message =  "Bienvenu dans notre application voici votre code de confirmation "+ codem + "  Veillez saisir votre code pour changer votre mot de passe" ;
                String usermail = "tesprit2020@gmail.com";
                String passmail = "Test2020";
                 Mailing.send(to,subject, message, usermail, passmail);                       
                 JOptionPane.showMessageDialog(null, "code envoyer avec succcés");
                 mail.setVisible(false);
                 this.code.setVisible(false);
                 vcode.setVisible(true);
                 incode.setVisible(true);
                
        }
        
        else{
   
        // verificationEmail=false;
                         JOptionPane.showMessageDialog(null, "Vérifier Email!!");
 
       
    }}
     @FXML
    private void Valider(ActionEvent event) {
         System.out.println(codem);
        if (incode.getText().equals(codem)) {
            nvmpTF.setVisible(true);
            rnvmpTF.setVisible(true);
            vcode.setVisible(false);
            terminer.setVisible(true);
            incode.setStyle("-fx-background-color:#3cbc53");
            incode.setVisible(false);
        } else {
            incode.setStyle("-fx-background-color:#ff0000");
        }
        
    }
     @FXML
    private void switchlogin(ActionEvent event) {
        String s1 = nvmpTF.getText();
        String s2 = rnvmpTF.getText();
        String m = mail.getText();
        userService us = new userService();
        
        if (s1.equals(s2) && s1.isEmpty() == false && s2.isEmpty() == false) {
             
             String hashed = BCrypt.hashpw(s1, BCrypt.gensalt(13));
            us.changepassword(hashed,m);
            TrayNotification tray = new TrayNotification("Succés", "Modification terminé", SUCCESS);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.ONE);
            mdoLabel.setVisible(true);
            inscrirLabel1.setVisible(false);
            inscrirLabel.setVisible(true);
            terminer.setVisible(false);
            nvmpTF.setVisible(false);
            rnvmpTF.setVisible(false);
            username.setVisible(true);
            password.setVisible(true);     
            Login.setVisible(true);
        
            
            System.out.println("Opération terminé");
        } else {
            TrayNotification tray = new TrayNotification("Oups", "Vérififiez vos paramétre", ERROR);
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.ONE);
        }
    }

    @FXML
    private void closeApplication(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'application");
        alert.setHeaderText("Vous allez quitter l'application");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alert.close();
}
    }
    
}
