/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import pi.devjava.entities.Enfant;
import pi.devjava.entities.Suivi;
import pi.devjava.services.SebMail;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class AjoutSuiviController implements Initializable {

    @FXML
    private JFXTextField fr;
    @FXML
    private JFXTextField ang;
    @FXML
    private JFXTextField info;
    @FXML
    private Button ajout;
    @FXML
    private DatePicker date;
int noteFr,noteAng,noteInfo;
Alert alert;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fr.textProperty().
                addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            fr.setText(oldValue);
        }
        else if(Integer.parseInt(fr.getText())>20 ||Integer.parseInt(fr.getText())<0)
           {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Note");
                    
                    alert.setContentText("note doit etre entre 0 et 20");
                    alert.showAndWait();
                    fr.setText("");
                    }
    }
});
        ang.textProperty().
                addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            ang.setText(oldValue);
        }
        else if(Integer.parseInt(ang.getText())>20 ||Integer.parseInt(ang.getText())<0)
           {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Note");
                    
                    alert.setContentText("note doit etre entre 0 et 20");
                    alert.showAndWait();
                    ang.setText("");
                    }
    }
});
        info.textProperty().
                addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            info.setText(oldValue);
        }
        else if(Integer.parseInt(info.getText())>20 ||Integer.parseInt(info.getText())<0)
           {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Note");
                    
                    alert.setContentText("note doit etre entre 0 et 20");
                    alert.showAndWait();
                    info.setText("");
                    }
    }
});
        date.setValue(java.time.LocalDate.now());
    }    

    @FXML
    private void AjoutSuivi(ActionEvent event) {
        if(!fr.getText().isEmpty()&&!ang.getText().isEmpty()&&!info.getText().isEmpty()){
      noteFr=Integer.parseInt(fr.getText());
        noteAng=Integer.parseInt(ang.getText());
        noteInfo=Integer.parseInt(info.getText());
        if((noteFr+noteAng+noteInfo)/3<10)
        Suivi.ajoutSuivi(AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "faible", date.getValue());
        else
            if((noteFr+noteAng+noteInfo)/3>=10&&(noteFr+noteAng+noteInfo)/3<=15)
        Suivi.ajoutSuivi(AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "moyenne", date.getValue());
   else
        Suivi.ajoutSuivi(AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "excellent", date.getValue());
            System.out.println(Enfant.findUser(AfficherEnfantsController.EnfantMod.getParent()));
        SebMail.sendMail(Enfant.findUser(AfficherEnfantsController.EnfantMod.getParent()),"Suivi", "un suivi et ajouter a ton enfant");
            
            loadWindow(getClass().getResource("/pi/devjava/GUI/AfficherSuivi.fxml"), "Afficher",null);
   Stage stage = (Stage) fr.getScene().getWindow();
         stage.close();}
        else
        {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Note vide");
                    alert.setContentText("un note est vide");
                    alert.showAndWait();
        }
    }

    @FXML
    private void frChange(KeyEvent event) {
    }

    @FXML
    private void angChange(KeyEvent event) {
    }

    @FXML
    private void infoChange(KeyEvent event) {
    }
    
    
    
    
    
}
