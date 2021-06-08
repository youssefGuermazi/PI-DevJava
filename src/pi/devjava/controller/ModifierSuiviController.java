/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import pi.devjava.entities.Suivi;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class ModifierSuiviController implements Initializable {

    @FXML
    private JFXTextField fr;
    @FXML
    private JFXTextField ang;
    @FXML
    private JFXTextField info;
    @FXML
    private Button mod;
    @FXML
    private DatePicker date;
    int noteFr,noteAng,noteInfo;
    Alert alert;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fr.setText(Integer.toString(AfficherSuiviController.SuiviMod.getNote_francais()));
        ang.setText(Integer.toString(AfficherSuiviController.SuiviMod.getNote_anglais()));
        info.setText(Integer.toString(AfficherSuiviController.SuiviMod.getNote_info()));
        date.setValue(AfficherSuiviController.SuiviMod.getDate().toLocalDate());
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
    }    

    @FXML
    private void ModifierSuivi(ActionEvent event) {
        if(!fr.getText().isEmpty()&&!ang.getText().isEmpty()&&!info.getText().isEmpty()){
        noteFr=Integer.parseInt(fr.getText());
        noteAng=Integer.parseInt(ang.getText());
        noteInfo=Integer.parseInt(info.getText());
        if((noteFr+noteAng+noteInfo)/3<10)
        Suivi.ModifierSuivi(AfficherSuiviController.SuiviMod.getId(),AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "faible", date.getValue());
        else
            if((noteFr+noteAng+noteInfo)/3>=10&&(noteFr+noteAng+noteInfo)/3<=15)
        Suivi.ModifierSuivi(AfficherSuiviController.SuiviMod.getId(),AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "moyenne", date.getValue());
   else
        Suivi.ModifierSuivi(AfficherSuiviController.SuiviMod.getId(),AfficherEnfantsController.EnfantMod.getId(),Integer.parseInt(fr.getText()) ,Integer.parseInt(ang.getText()), Integer.parseInt(info.getText()), "excellent", date.getValue());
        loadWindow(getClass().getResource("/pi/devjava/GUI/AfficherSuivi.fxml"), "Afficher",null);
   Stage stage = (Stage) fr.getScene().getWindow();
         stage.close();
    }
         else
        {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Note vide");
                    alert.setContentText("un note est vide");
                    alert.showAndWait();
        }
    }
    
}
