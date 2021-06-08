/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static pi.devjava.PIDevJava.bdd;
import pi.devjava.entities.Mailing;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.BCrypt;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class addUserController implements Initializable {
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Choisir role",
        "Parent",
        "Responsable",
        "Admin"
    );

    @FXML
    private AnchorPane frame;
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXTextField username1;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signupButton;
    @FXML
    private ImageView close;
    @FXML
    private JFXPasswordField password1;
    @FXML
    private JFXComboBox role;
    @FXML
    private JFXButton Login;

    
    public AnchorPane getFrame() {
        return frame;
        // TODO
    }    

    public void setFrame(AnchorPane frame) {
        this.frame = frame;
    }

    public Label getInscrirLabel() {
        return inscrirLabel;
    }

    public void setInscrirLabel(Label inscrirLabel) {
        this.inscrirLabel = inscrirLabel;
    }

    public JFXTextField getUsername() {
        return username;
    }

    public void setUsername(JFXTextField username) {
        this.username = username;
    }

    public JFXPasswordField getPassword() {
        return password;
    }

    public void setPassword(JFXPasswordField password) {
        this.password = password;
    }

    public JFXButton getSignupButton() {
        return signupButton;
    }

    public void setSignupButton(JFXButton signupButton) {
        this.signupButton = signupButton;
    }

    public ImageView getClose() {
        return close;
    }

    public void setClose(ImageView close) {
        this.close = close;
    }

    public JFXTextField getUsername1() {
        return username1;
    }

    public void setUsername1(JFXTextField username1) {
        this.username1 = username1;
    }

    public JFXPasswordField getPassword1() {
        return password1;
    }

    /**
     * Initializes the controller class.
     */
    public void setPassword1(JFXPasswordField password1) {    
        this.password1 = password1;
    }
    String a;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.setValue("Choisir role");
        role.setItems(options);
        // TODO
    }    

    @FXML
    private void Signup(MouseEvent event) throws SQLException {
       /* String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(username1.getText());
        //
        /*if (username1.equals("")) {
           JOptionPane.showMessageDialog(null, "mail obligatoire");
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("mail invalide");
        } else 
     if (!controler.matches()) {
           {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("mail invalide");
            
            a1.show();
         }            
           
        } else 
         if(username.getText().equals("")){
           {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("username not null");
           a1.show();
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Warning");
                            alert.setHeaderText("Warning");
                            alert.setContentText("Veillez remplir tous les champs");
                            alert.show();
         
         }            
        }else 
         if  (password.getText().trim().equals(""))
         {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("password not null");
            
            a1.show();
         }             
         else 
        if(password.getText().trim().equals(password1.getText().trim()))
        {
      String pwd = password.getText().trim();
                            String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
                           
            String rl;
             if(role.getSelectionModel().getSelectedItem().toString().equals("Choisir role"))
                     {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("veuillez choisir role SVP");
            
           a1.show();
        } 
             else{ 
                 if(role.getSelectionModel().getSelectedItem().toString().equals("Parent"))
                    rl="a:1:{i:0;s:11:\"ROLE_PARENT\";}";  
            else if(role.getSelectionModel().getSelectedItem().toString().equals("Responsable"))
                    rl="a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}";
            else rl="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";*/
                          
                //Utilisateur u = new Utilisateur(jTextField1.getText(),jTextField2.getText(),jComboBox1.getSelectedItem().toString(),hashed); 
        //Utilisateur u = new Utilisateur(username1.getText(),username.getText(),rl,hashed);
        /*
          @FXML
    private void AddButton(MouseEvent event) {
*/
       // if (event.getSource() == AddButton) {
            // here

       
        
    String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
    Pattern pattern = Pattern.compile(masque);
    Matcher controler = pattern.matcher(username1.getText());
    //
    if (!controler.matches()) {
        {
            Alert a1=new Alert(Alert.AlertType.ERROR);
            a1.setTitle(null);
            a1.setHeaderText("mail invalide");
            
            a1.show();
        }
        
    } else
        if(username.getText().equals("")){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("username not null");
                a1.show();
                
                
            }
        }else
            if  (password.getText().trim().equals(""))
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("password not null");
                
                a1.show();
            }
                      else if(bdd.isInBdd("SELECT * FROM  user WHERE username ='"+username.getText()+"';"))
            {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("username existe déja");
           a1.show();
         }          
        
            else if (bdd.isInBdd("SELECT * FROM  user WHERE email ='"+username1.getText()+"';"))
            {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("mail existe déja");
        a1.show();
         }
            
            else
                if(password.getText().trim().equals(password1.getText().trim()))
                {
                    String pwd = password.getText().trim();
                    String hashed = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
                    
                    String rl;
                    if(role.getSelectionModel().getSelectedItem().toString().equals("Choisir role"))
                    {
                        Alert a1=new Alert(Alert.AlertType.ERROR);
                        a1.setTitle(null);
                        a1.setHeaderText("veuillez choisir role SVP");
                        
                        a1.show();
                    }
                    
        
                    
                    
                    else{
                        if(role.getSelectionModel().getSelectedItem().toString().equals("Parent"))
                            rl="a:1:{i:0;s:11:\"ROLE_PARENT\";}";
                        else if(role.getSelectionModel().getSelectedItem().toString().equals("Responsable"))
                            rl="a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}";
                        else rl="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
                    
                    
                         {  
                        
                        Utilisateur u = new Utilisateur(username1.getText(),username.getText(),rl,hashed);
                        
                        userService user = new userService();
                        user.addUser(u);
                        
                        {  
           Alert a1=new Alert(Alert.AlertType.INFORMATION);
           a1.setTitle(null);
           a1.setHeaderText("ajouté avec succès");
              
        a1.show();
         
         }
                        String to = username1.getText();
                        String nom=username.getText();
                        String mdp = password.getText();
                        String ro=role.getSelectionModel().getSelectedItem().toString();
        
                String subject = "ajout avec sucées";
                String message =  "Bienvenu dans notre application \n "
                        +"vous êtes ajouté a notre application comme  "+ro
                        +"\n Votre username est "+nom
                        +"\n Votre mot de passe est "+mdp ;
                String usermail = "tesprit2020@gmail.com";
                String passmail = "Test2020";
                 Mailing.send(to,subject, message, usermail, passmail);                       
                
                 
                        
                    }}

        

        
    }else 
                    {  
           Alert a1=new Alert(Alert.AlertType.INFORMATION);
           a1.setTitle(null);
           a1.setHeaderText("vérifier mot de passe");
              
        a1.show();
         
         }
                    
       }
       

    @FXML
    private void closeApplication(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'inerface garderie");
        alert.setHeaderText("Vous allez quitter l'inerface garderie");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             username.getScene().getWindow().hide();
        } else {
            alert.close();
}
}}
  
    
    
    
    

