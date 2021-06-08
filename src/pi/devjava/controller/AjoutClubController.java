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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import pi.devjava.entities.Club;
import pi.devjava.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutClubController implements Initializable {

    @FXML
    private JFXTextField desc;
    @FXML
    private JFXButton AddButton;

    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField clubName;
    @FXML
    private JFXButton affich;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
@FXML
    private void closeApplication(MouseEvent event) {
           

             date.getScene().getWindow().hide();
       
    }

    @FXML
    private void AddButton(MouseEvent event) {

        if (event.getSource() == AddButton) {
            // here

            if (AddClub()) {
               
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add club");
                alert.setHeaderText("Results:");
                alert.setContentText("ERROR!");
                alert.showAndWait();

            }

        }
    }

    public boolean AddClub() {

        boolean res = true;

        try {
            Date gettedDatePickerDate = Date.valueOf(date.getValue());
            Club c = new Club(clubName.getText(), desc.getText(), gettedDatePickerDate);
             if (date.getValue().isBefore(LocalDate.now())){
            ServiceClub Clubs = new ServiceClub();
            res = Clubs.addClub(c);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add club");
                alert.setHeaderText("Results:");
                alert.setContentText("Added successfully!");
                alert.showAndWait();}
             else {
                
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add club");
                alert.setHeaderText("Results:");
                alert.setContentText("ERROR! , verifier le date");
                alert.showAndWait();}

        } catch (SQLException ex) {

        }

        return res;

    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherClub.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

}
