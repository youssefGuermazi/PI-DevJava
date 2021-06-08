/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import static pi.devjava.controller.TestfrontGarderieController.num;
import pi.devjava.entities.Enfant;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class ModifierEnfantController implements Initializable {

    @FXML
    private JFXTextField id;
   
    private JFXTextField image;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField gardd;
    @FXML
    private JFXTextField prenom;
   // @FXML
   // private JFXTextField dn;
    @FXML
    private JFXDatePicker dn;
    
    @FXML
    private Button modifier;
Alert alert;
     boolean ajoutb=true;
    @FXML
    private ImageView iimg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(AfficherEnfantsController.EnfantMod.getId());
        id.setText(Integer.toString(AfficherEnfantsController.EnfantMod.getId()));
        List g;
        g = Enfant.findAllGaderie();
        System.out.println(g.get(0));
        ObservableList<String> listg = FXCollections.observableArrayList(g);
//        gard.setItems(listg);
        //image.setText(AfficherEnfantsController.EnfantMod.getNomfile());
       nom.setText(AfficherEnfantsController.EnfantMod.getNom());
        prenom.setText(AfficherEnfantsController.EnfantMod.getPrenom());
        dn.setValue(AfficherEnfantsController.EnfantMod.getDn().toLocalDate());
        gardd.setText(AfficherEnfantsController.EnfantMod.getGarderie_id());
        
        
        FileInputStream inputstream; 
        try {
            inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+AfficherEnfantsController.EnfantMod.getNomfile());
            Image img;
            img =new Image(inputstream);
            iimg.setImage(img);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModifierEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
        
      dn.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) {
                if (!newV) { // focus lost
                    if(dn.getValue().isAfter(java.time.LocalDate.now()))
                    {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Date");
                    
                    alert.setContentText("le date n'est pas correct");
                    alert.showAndWait();
                    dn.getEditor().setText("");
                    }
                    
                }}
        });
        
    }    

    @FXML
    private void modifier(ActionEvent event) {
        if(!nom.getText().isEmpty()&&!prenom.getText().isEmpty()&&!dn.getEditor().getText().isEmpty())
        {
            userService us = new userService();
        int identi;
         identi=us.getInfoUser(Utilisateur.uName).getId();
            Enfant.ModifierEnfant(nom.getText(), prenom.getText(), gardd.getText(),identi , dn.getValue(), Integer.parseInt(id.getText()));
   // loadWindow(getClass().getResource("/pi/devjava/GUI/AfficherEnfants.fxml"), "Afficher",null);
  {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Modification");
                    
                    alert.setContentText("modification avec succée");
                    alert.showAndWait();
                    ajoutb=false;
                    }
   Stage stage = (Stage) nom.getScene().getWindow();
         stage.close();}
         else
        {
         {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Empty");
                    
                    alert.setContentText("un champ est empty");
                    alert.showAndWait();
                    ajoutb=false;
                    }
        }
        
    }

    @FXML
    private void nomChange(KeyEvent event) {
        if(event.getText().matches(".*[^a-z].*"))
        {
        if(!nom.getText().trim().isEmpty())
        {
    
        nom.setText(nom.getText().replace(event.getText().charAt(0), Character.MIN_VALUE));
        nom.positionCaret(nom.getText().length());
        }
        
        }
    }

    @FXML
    private void prenomChange(KeyEvent event) {
          if(event.getText().matches(".*[^a-z].*"))
        {
        if(!prenom.getText().trim().isEmpty())
        {
    
        prenom.setText(prenom.getText().replace(event.getText().charAt(0), Character.MIN_VALUE));
        prenom.positionCaret(prenom.getText().length());
        }
        
        }
    }
    
    
}
