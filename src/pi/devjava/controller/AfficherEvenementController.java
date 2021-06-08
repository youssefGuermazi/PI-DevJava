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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Evenement;
import pi.devjava.entities.Mailing;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AfficherEvenementController implements Initializable {
 public static Evenement Clubmod;
    ServiceEvenement se;
    Connection connexion = DataBase.getInstance().getConnexion();
    @FXML
    private AnchorPane contentPane;
    
     ObservableList<Evenement> orderData = FXCollections.observableArrayList();
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
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
    private TableColumn<Evenement, ?> nbp;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button Club;
    @FXML
    private ImageView close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Club.setVisible(false);
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
            nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
            
            ClubTable.setItems(orderData);
            ClubTable.setEditable(true);
        } else {
            System.out.println("no");
        }

    }
     @FXML
    private void closeApplication(MouseEvent event) {
           

             close.getScene().getWindow().hide();
       
    }
    @FXML
    private void changeScreenButtonPushed1(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/ajoutEvenement.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    

    @FXML
    private void changeScreenButtonPushed2(ActionEvent event) throws IOException {
         Clubmod = ClubTable.getSelectionModel().getSelectedItem();

        FXMLLoader f = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/ModificationEvenement.fxml"));
        Parent root = (Parent) f.load();
        ModificationEvenementController mc = f.<ModificationEvenementController>getController();
        mc.setE(ClubTable.getSelectionModel().getSelectedItem());
        Scene ModificationScene = new Scene(root);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(ModificationScene);
        window.show();
    }

    @FXML
    private void delete(ActionEvent event) {
        TextInputDialog td = new TextInputDialog("enter any text"); 
        td.setHeaderText("why you want to delete"); 
        
        
        
        Optional<String> res=td.showAndWait();
        
        if (res.isPresent()) {
        Evenement t = ClubTable.getSelectionModel().getSelectedItem();

//          
        List<Utilisateur> listUsers =  ServiceEvenement.getUsersForEvent(t.getEventId());
        for (Utilisateur user : listUsers) {
            String to =user.getMail();
                String subject = "Annulation d'un evennement";
                String message =  td.getResult() ;
                String usermail = "tesprit2020@gmail.com";
                String passmail = "Test2020";
                 Mailing.send(to,subject, message, usermail, passmail);
            
            
            System.out.println(user.getMail());
        }
        ServiceEvenement.getInstance().delete(t);
        
        ClubTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<Evenement> evenementList = new ArrayList<>();
        ServiceEvenement serviceEvenement = ServiceEvenement.getInstance();
        evenementList = ServiceEvenement.getAllEvenement();
        orderData.clear();
        orderData.addAll(evenementList);

        if (!evenementList.isEmpty()) {

             clubName.setCellValueFactory(new PropertyValueFactory<>("club_evenement"));
            nom.setCellValueFactory(new PropertyValueFactory<>("eventName"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            dateD.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            dateF.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            nomfile.setCellValueFactory(new PropertyValueFactory<>("nomfile"));
            nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
            
            ClubTable.setItems(orderData);

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
    }

     @FXML
    public void changeScreenButtonPushed3(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherClub.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
    
}
