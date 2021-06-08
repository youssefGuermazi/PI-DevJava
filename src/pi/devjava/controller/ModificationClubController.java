/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pi.devjava.entities.Club;
import pi.devjava.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModificationClubController implements Initializable {

    ServiceClub se1;

    private Club e;
    @FXML
    private JFXButton Modifier;
    @FXML
    private Button retour;

    public ModificationClubController() throws SQLException {
        this.se1 = new ServiceClub();
    }

    public Club getE() {
        return e;
    }

    public void setE(Club e) {
        this.e = e;
    }

    @FXML
    private JFXTextField clubName;
    @FXML
    private JFXTextField desc;
    @FXML
    private JFXDatePicker date;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clubName.setText(AfficherClubController.Clubmod.getClubName());
        desc.setText(AfficherClubController.Clubmod.getDescription());
        date.setValue(AfficherClubController.Clubmod.getCreationDate().toLocalDate());

    }

    @FXML
    private void modifier(MouseEvent event) {

        String nom = clubName.getText();
        String d = desc.getText();
        Date da = java.sql.Date.valueOf(date.getValue());

        Club e1 = new Club(nom, d, da);

        ServiceClub.getInstance().ModifierC(e1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modification");
        alert.setHeaderText(null);
        alert.setContentText("évenement Modifié");

        alert.showAndWait();

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherClub.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

}
