/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pi.devjava.entities.Evenement;
import pi.devjava.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModificationEvenementController implements Initializable {
    ServiceEvenement se1;

    private Evenement e;
       public ModificationEvenementController() throws SQLException {
        this.se1 = new ServiceEvenement();
    }

    public Evenement getE() {
        return e;
    }

    public void setE(Evenement e) {
        this.e = e;
    }
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXDatePicker date_debut;
    @FXML
    private JFXButton Modifier;
    @FXML
    private JFXButton affich;
    @FXML
    private JFXComboBox<String> club_evenement=new JFXComboBox<>();;
    @FXML
    private JFXDatePicker date_fin;
    @FXML
    private JFXTextField nomfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List g;
        g = ServiceEvenement.findAllClubs();
        ObservableList<String> listg = FXCollections.observableArrayList(g);
        club_evenement.setItems(listg);
        
        club_evenement.setValue(AfficherEvenementController.Clubmod.getEventName());
        nom.setText(AfficherEvenementController.Clubmod.getClub_evenement());
        date_debut.setValue(AfficherEvenementController.Clubmod.getDateDebut().toLocalDate());
           date_fin.setValue(AfficherEvenementController.Clubmod.getDateFin().toLocalDate());
        nomfile.setText(AfficherEvenementController.Clubmod.getNomfile());
        description.setText(AfficherEvenementController.Clubmod.getDescription());
     
    }    

    @FXML
    private void modifier(MouseEvent event) {
        String cl = club_evenement.getValue();
        String name = nom.getText();
        String d = description.getText();
        Date dad = java.sql.Date.valueOf(date_debut.getValue());
        Date daf = java.sql.Date.valueOf(date_fin.getValue());
        String namef = nomfile.getText();

        Evenement e1 = new Evenement(AfficherEvenementController.Clubmod.getEventId(),name, cl, d, dad,daf,namef);
        ServiceEvenement.getInstance().ModifierC(e1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification");
        alert.setHeaderText(null);
        alert.setContentText("évenement Modifié");

        alert.showAndWait();
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenement.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    
    }
    
}
