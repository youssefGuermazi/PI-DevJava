/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Club;
import pi.devjava.services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherClubFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
        public static Club Clubmod;
    ServiceClub se;
    Connection connexion = DataBase.getInstance().getConnexion();

    @FXML
    private TableView<Club> ClubTable;

    ObservableList<Club> orderData = FXCollections.observableArrayList();
    ObservableList<Club> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Club, String> clubName;
    @FXML
    private TableColumn<Club, String> desc;
    @FXML
    private TableColumn<Club, Date> date;
    @FXML
    private Button Evenement;
    @FXML
    private Button Club;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClubTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<Club> clubList = new ArrayList<>();
        ServiceClub serviceClub = ServiceClub.getInstance();
        clubList = ServiceClub.getAllClubs();
        orderData.clear();
        orderData.addAll(clubList);

        if (!clubList.isEmpty()) {

            clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
            desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            date.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

            ClubTable.setItems(orderData);
        } else {
            System.out.println("no");
        }
    } 
     
     @FXML
    public void changeScreenButtonPushed3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenementFront.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void changeScreenButtonPushed4(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherClubFront.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
}
