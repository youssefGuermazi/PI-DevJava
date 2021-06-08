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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static pi.devjava.PIDevJava.bdd;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Evenement;
import pi.devjava.services.ServiceEvenement;
import pi.devjava.entities.EvenementUser;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.ServiceClub;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherEvenementFrontController implements Initializable {

    public static Evenement Clubmod;
    ServiceEvenement se;
    Connection connexion = DataBase.getInstance().getConnexion();
 userService us = new userService();
    ObservableList<Evenement> orderData = FXCollections.observableArrayList();
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
    ObservableList<EvenementUser> orderData1 = FXCollections.observableArrayList();
    ObservableList<EvenementUser> oblist1 = FXCollections.observableArrayList();
    @FXML
    private TableView<Evenement> ClubTable;
    @FXML
    private TableColumn<Evenement, String> clubName;
    @FXML
    private TableColumn<Evenement, String> nom;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Date> dateD;
    @FXML
    private TableColumn<Evenement, Date> dateF;
    @FXML
    private TableColumn<Evenement, String> nomfile;
    @FXML
    private Button Participer;
    @FXML
    private Button Annuler;
    @FXML
    private Label Complet;
    @FXML
    private Label Evenement_terminé;
    @FXML
    private Button Club;
    @FXML
    private Button Evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Participer.setVisible(false);
        Annuler.setVisible(false);
        Complet.setVisible(false);
        Evenement_terminé.setVisible(false);
        ClubTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<Evenement> evenementList = new ArrayList<>();
        ServiceEvenement serviceEvenement = ServiceEvenement.getInstance();
        evenementList = ServiceEvenement.getAllEvenement();
        orderData.clear();
        orderData.addAll(evenementList);

        if (!evenementList.isEmpty()) {

            clubName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
            nom.setCellValueFactory(new PropertyValueFactory<>("club_evenement"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateD.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            dateF.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            nomfile.setCellValueFactory(new PropertyValueFactory<>("nomfile"));

            ClubTable.setItems(orderData);
            ClubTable.setEditable(true);
        } else {
            System.out.println("no");
        }

    }

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

    @FXML
    private void Participerr(ActionEvent event) {
       
         Evenement t = ClubTable.getSelectionModel().getSelectedItem();
         //System.out.println(t);
      ServiceEvenement.getInstance().ParticiperE(t,us.getInfoUser(Utilisateur.uName).getId());
        System.out.println("");
        System.out.println(LoginController.o.getId());
                Participer.setVisible(false);
                Annuler.setVisible(true);
                Complet.setVisible(false);
                Evenement_terminé.setVisible(false);


    }

    public boolean test(List<EvenementUser> l, int idE, int idU) {
        System.out.println(idU);
        System.out.println(idE);
        boolean b = false;
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i).getEvenement_id());
            System.out.println(l.get(i).getUser_id());

            if (l.get(i).getEvenement_id() == idE && l.get(i).getUser_id() == idU) {
                b = true;
                break;
            }
        }
        System.out.println(b);
        return b;
    }

    @FXML
    private void select(MouseEvent event) {

      //  int id1 = bdd.getInt("SELECT * FROM user WHERE username ='" + Utilisateur.username + "';", 1); //id user connecté
        int id1 =us.getInfoUser(Utilisateur.uName).getId();
        Evenement t = ClubTable.getSelectionModel().getSelectedItem();

        int x = t.getNbp();
        int i = t.getEventId(); //id of selected event 
       

        List<EvenementUser> evenementUList = new ArrayList<>(); //list de lentité evenement_user
        ServiceEvenement serviceEvenement = ServiceEvenement.getInstance();
        evenementUList = ServiceEvenement.getAllEvenementUser();
        orderData1.clear();
        orderData1.addAll(evenementUList);
        // if id user connecté dans la liste de levenement selecteé
        System.out.println(t.getDateFin().toLocalDate().isAfter( LocalDate.now()));
         if(!(t.getDateFin().toLocalDate().isAfter( LocalDate.now()))){
                Participer.setVisible(false);
                Annuler.setVisible(false);
                Complet.setVisible(false);
                Evenement_terminé.setVisible(true);
         }
         else{

        if (test(evenementUList, i, id1) == false) {
            if (x > 0 && x <= 20) {
                Participer.setVisible(true);
                Annuler.setVisible(false);
                Complet.setVisible(false);
               Evenement_terminé.setVisible(false);

            } else {
                Participer.setVisible(false);
                Annuler.setVisible(false);
                Complet.setVisible(true);
                Evenement_terminé.setVisible(false);

            }
        } 
        else {
            Participer.setVisible(false);
            Annuler.setVisible(true);
            Complet.setVisible(false);
            Evenement_terminé.setVisible(false);

        }

    }
    }
    @FXML
    public void changeScreenButtonPushed3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherClubFront.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
     @FXML
    public void changeScreenButtonPushed4(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenementFront.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void anuler(ActionEvent event) {
        Evenement t = ClubTable.getSelectionModel().getSelectedItem();
      ServiceEvenement.getInstance().AnnulerE(t,us.getInfoUser(Utilisateur.uName).getId());
                Participer.setVisible(true);
                Annuler.setVisible(false);
                Complet.setVisible(false);
                Evenement_terminé.setVisible(false);
    }
}
