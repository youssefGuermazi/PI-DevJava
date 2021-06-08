/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import pi.devjava.entities.Enfant;
import sun.plugin2.jvm.RemoteJVMLauncher;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class AfficherEnfantController implements Initializable {

    @FXML
    private TreeTableColumn<Enfant, String> id;
    @FXML
    private TreeTableColumn<Enfant, String> nom;
    @FXML
    private TreeTableColumn<Enfant, String> image;
    @FXML
    private TreeTableColumn<Enfant, String> prenom;
    @FXML
    private TreeTableColumn<Enfant, String> dn;
    @FXML
    private TreeTableColumn<Enfant, String> gard_id;
    @FXML
    private TreeTableColumn<Enfant, String> parent;
    @FXML
    private Button ajout;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
     @FXML
    private TreeTableView table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Enfant> ListE=Enfant.findEnfant();
    
id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
 gard_id.setCellValueFactory(new TreeItemPropertyValueFactory<>("garderie_id"));
 parent.setCellValueFactory(new TreeItemPropertyValueFactory<>("parent"));
 prenom.setCellValueFactory(new TreeItemPropertyValueFactory<>("prenom"));
 dn.setCellValueFactory(new TreeItemPropertyValueFactory<>("dn"));
 nom.setCellValueFactory(new TreeItemPropertyValueFactory<>("nom"));
 image.setCellValueFactory(new TreeItemPropertyValueFactory<>("nomfile"));
        ObservableList<Enfant> OLE= FXCollections.observableArrayList(ListE);
        
    }   
    
}
