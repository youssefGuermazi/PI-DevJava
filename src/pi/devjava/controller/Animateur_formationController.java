/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;


import pi.devjava.services.animateur_formationservice;
import pi.devjava.services.formationservice;
import pi.devjava.services.mail;
import Technique.TrayIconDemo;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pi.devjava.entities.animateur_formation;
import pi.devjava.entities.formation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Animateur_formationController implements Initializable {

    ObservableList<formation> obs;
    formation Aem;
    @FXML
    private TableView<formation> fortable;
    @FXML
    private TableColumn<formation, Integer> id;
    @FXML
    private TableColumn<formation, String> datedebut;
    @FXML
    private TableColumn<formation, String> datefin;
    @FXML
    private TableColumn<formation, String> titre;
    @FXML
    private TableColumn<formation, String> description;
    @FXML

    private Button consulter;
    @FXML
    private Button affecter;
    @FXML
    private TextField yarab;
    @FXML
    private Button back;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
    }

    public void load() {
        formationservice ent;
        try {

            ent = new formationservice();
            ResultSet Enta = ent.afficherformation();
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new formation(Enta.getInt("id"), Enta.getString("dated"), Enta.getString("datef"), Enta.getString("titre"), Enta.getString("description")));

            }
            id.setCellValueFactory(new PropertyValueFactory<formation, Integer>("id"));
            datedebut.setCellValueFactory(new PropertyValueFactory<formation, String>("dated"));
            datefin.setCellValueFactory(new PropertyValueFactory<formation, String>("datef"));
            titre.setCellValueFactory(new PropertyValueFactory<formation, String>("titre"));
            description.setCellValueFactory(new PropertyValueFactory<formation, String>("description"));
            fortable.setItems(obs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TextField getYarab() {
        return yarab;
    }

    public void setYarab(int yarab) {
        this.yarab.setText(Integer.toString(yarab));
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void affecter(ActionEvent event) throws ClassNotFoundException, AWTException {
        Aem = fortable.getSelectionModel().getSelectedItem();
         mail f =new mail();
        String MSG = " vous etes affecter a une formation du musique du 23/04/2020 jusqu'a 29/04/2020" ;
        animateur_formationservice P = new animateur_formationservice();

        animateur_formation a = new animateur_formation(Integer.parseInt(yarab.getText()), Aem.getId());
           f.mail(MSG);

        if (P.RechercherVol(Aem.getId(), Integer.parseInt(yarab.getText()))) {
            if (P.RechercherVol1(Integer.parseInt(yarab.getText()))) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Ce formateur n'est pas disponible");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.CANCEL) {

                    P.ajouteranimateur(a);
                  
                    if (SystemTray.isSupported()) {
                        TrayIconDemo td = new TrayIconDemo();
                        td.displayTray();
                    } else {
                        System.err.println("System tray not supported!");
                    }
                    load();
                }

            } else {
                P.ajouteranimateur(a);
                if (SystemTray.isSupported()) {
                    TrayIconDemo td = new TrayIconDemo();
                    td.displayTray();
                } else {
                    System.err.println("System tray not supported!");
                }
                load();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("il est affecté déjà à  cette formation");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {

            }

        }

    }

    @FXML
    private void consulter(ActionEvent event) {

        animateur_formationservice ent;
        try {

            ent = new animateur_formationservice();
            ResultSet Enta = ent.listformation1(Integer.parseInt(yarab.getText()));
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new formation(Enta.getInt("id"), Enta.getString("dated"), Enta.getString("datef"), Enta.getString("titre"), Enta.getString("description")));

            }
            id.setCellValueFactory(new PropertyValueFactory<formation, Integer>("id"));
            datedebut.setCellValueFactory(new PropertyValueFactory<formation, String>("dated"));
            datefin.setCellValueFactory(new PropertyValueFactory<formation, String>("datef"));
            titre.setCellValueFactory(new PropertyValueFactory<formation, String>("titre"));
            description.setCellValueFactory(new PropertyValueFactory<formation, String>("description"));
            fortable.setItems(obs);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/animateur.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(Animateur_formationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refresh(ActionEvent event) {
        load();
    }

    @FXML
    private void supprimer(ActionEvent event) throws ClassNotFoundException {
              Aem = fortable.getSelectionModel().getSelectedItem();
        animateur_formationservice P = new animateur_formationservice();
        int id = Aem.getId();
        
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette formation");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (id != 0) {
                                           P.supprimeranim(id, Integer.parseInt(yarab.getText()));
                                           
                                           
                                         
                                                }
                                    }
        load();
    }
}
