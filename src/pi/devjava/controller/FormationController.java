/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;


import pi.devjava.services.formationservice;
import Technique.TrayIconDemo;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pi.devjava.entities.formation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FormationController implements Initializable {
        ObservableList<formation> obs;
    formation Aem;
    @FXML
    private TableView<formation> fortable;
    @FXML
    private TableColumn<formation,Integer> id;
    @FXML
    private TableColumn<formation,String> datedebut;
    @FXML
    private TableColumn<formation, String> datefin;
    @FXML
    private TableColumn<formation, String> titre;
    @FXML
    private TableColumn<formation,String> description;
    @FXML
    private TextArea Description;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField titrefield;
    @FXML
    private DatePicker datefinfield;
    @FXML
    private DatePicker datedfield;
    @FXML
    private TextField search1;
  

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
             datedfield.getEditor().clear();
            datefinfield.getEditor().clear();;
            titrefield.clear();
            Description.clear();
             chercher();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add(ActionEvent event) throws ClassNotFoundException, AWTException {
         formationservice P = new formationservice();

        String m=Description.getText();
         String t = titrefield.getText();
         formation a = new  formation(datedfield.getValue().toString(), datefinfield.getValue().toString(), titrefield.getText(), Description.getText());
         if (!(t.isEmpty())&& !(m.isEmpty())&& !(datedfield.getValue().isBefore(LocalDate.now()))&&!(datefinfield.getValue().isBefore(datedfield.getValue())))
         {
        P.ajouterformation(a);
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
       load();
         }
         else
         {
             System.out.println("laaaaaaaaaaaa");
         }

    }

    @FXML
    private void edit(ActionEvent event) {
        try {
            Aem = fortable.getSelectionModel().getSelectedItem();
            formationservice SEnt = new formationservice();
           formation a = new  formation(Aem.getId(),datedfield.getValue().toString(), datefinfield.getValue().toString(), titrefield.getText(), Description.getText());
       
            SEnt.modifierformation(a);
            load();
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) throws ClassNotFoundException {
      Aem = fortable.getSelectionModel().getSelectedItem();
        formationservice SEnt = new formationservice();
        int id = Aem.getId();
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette formation");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (id != 0) {
                                           SEnt.supprimerformation(id);
                                           
                                                }
                                    }
        load();


    }

    @FXML
    private void getSelected(MouseEvent event) {
        Aem = fortable.getSelectionModel().getSelectedItem();
            datedfield.setValue(LocalDate.parse(Aem.getDated()));
            datefinfield.setValue(LocalDate.parse(Aem.getDatef()));
             titrefield.setText(Aem.getTitre());
            Description.setText(Aem.getDescription());
             
           
        
        
    }
    public void chercher() {
        FilteredList<formation> filteredData = new FilteredList<>(obs, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (aff.getDated().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                if (aff.getDatef().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                if (aff.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                
                if (aff.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
               
                else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<formation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.s
        sortedData.comparatorProperty().bind(fortable.comparatorProperty());
        fortable.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.
    }
   
}