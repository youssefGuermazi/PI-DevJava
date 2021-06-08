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
public class AfficherClubController implements Initializable {

    public static Club Clubmod;
    ServiceClub se;
    Connection connexion = DataBase.getInstance().getConnexion();
    @FXML
    private AnchorPane contentPane;

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
    private ImageView close;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button ajouter;
    @FXML
    private Button Evenement;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement.setVisible(false);

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
    private void closeApplication(MouseEvent event) {
           

             close.getScene().getWindow().hide();
       
    }

    @FXML
    public void delete() {
        Club t = ClubTable.getSelectionModel().getSelectedItem();

        ServiceClub.getInstance().delete(t);

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reclamation Envoyer");
        alert.setHeaderText(null);
        alert.setContentText("évenement supprimé ");

        alert.showAndWait();

    }

    @FXML
    public void changeScreenButtonPushed2(ActionEvent event) throws IOException {
        Clubmod = ClubTable.getSelectionModel().getSelectedItem();

        FXMLLoader f = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/ModificationClub.fxml"));
        Parent root = (Parent) f.load();
        ModificationClubController mc = f.<ModificationClubController>getController();
        mc.setE(ClubTable.getSelectionModel().getSelectedItem());
        Scene ModificationScene = new Scene(root);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(ModificationScene);
        window.show();
    }

    @FXML
    public void changeScreenButtonPushed1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/ajoutClub.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
 @FXML
    public void changeScreenButtonPushed3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenement.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
}
