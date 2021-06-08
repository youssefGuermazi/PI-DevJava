/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static pi.devjava.PIDevJava.bdd;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AfficheUserController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXButton Login;
    @FXML
    private ImageView close;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur,String> mail;
    @FXML
    private TableColumn<Utilisateur,String>role;
    //ObservableList<Utilisateur> usersList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    ObservableList<Utilisateur> orderData = FXCollections.observableArrayList();
    //ObservableList<Utilisateur> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   //  ObservableList<Utilisateur> user = FXCollections.observableArrayList();   
   table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<Utilisateur> userList = new ArrayList<>();
        userService us = userService.getInstance();
        userList=us.getAllUsers();
        orderData.clear();
        orderData.addAll(userList);

        if (!userList.isEmpty()) {

               nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("username"));
               mail.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("mail"));
               role.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("role"));

            table.setItems(orderData);
        } else {
            System.out.println("no");
        }
        
    }
 

    @FXML
    private void Delete(MouseEvent event) {
        try{
        
     
        Utilisateur u = table.getSelectionModel().getSelectedItem();
        userService.getInstance().delete(u);
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez supprimer l'utilisateur");
        alert.setHeaderText("Vous allez supprimer l'utilisateur");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

       
        List<Utilisateur> userList = new ArrayList<>();
        userService us = userService.getInstance();
        userList=us.getAllUsers();
        orderData.clear();
        orderData.addAll(userList);

        if (!userList.isEmpty()) {

               nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("username"));
               mail.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("mail"));
               role.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("role"));

            table.setItems(orderData);
        } else {
            System.out.println("no");
        }
        
            
        } else {
            alert.close();
            
}
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Utilisateur supprimé");
        alert1.setHeaderText("Utilisateur supprimé");
        alert1.show();

    }
 catch(Exception e){

          Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("sélectionner une ligne SVP!!");
        alert1.setHeaderText("sélectionner une ligne SVP!!");
        alert1.show();
        }}
        
    

    @FXML
    private void closeApplication(MouseEvent event) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'inerface garderie");
        alert.setHeaderText("Vous allez quitter l'inerface garderie");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             inscrirLabel.getScene().getWindow().hide();
        } else {
            alert.close();
}
    }
    
    
}
