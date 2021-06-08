/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static pi.devjava.controller.AfficherEnfantsController.EnfantMod;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import pi.devjava.entities.Enfant;
import pi.devjava.entities.EnfantAffichage;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class AfficherEnfantsParentController implements Initializable {

   @FXML
    private AnchorPane pane;
    @FXML
    private TableColumn<EnfantAffichage,?> id;
    @FXML
    private TableColumn<EnfantAffichage,?> nom;
    @FXML
    private TableColumn<EnfantAffichage,?> prenom;
    @FXML
    private TableColumn<EnfantAffichage,?> dn;
    @FXML
    private TableColumn<EnfantAffichage,?> gard_id;
    @FXML
    private TableColumn<EnfantAffichage,?> parent;
    @FXML
    private TableColumn<EnfantAffichage,?> image;
@FXML
    private TableView<EnfantAffichage>  table=new TableView<>();
    @FXML
    private Button suivi;
    @FXML
    private Button mod;
    @FXML
    private Button supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mod.setVisible(false);
        supp.setVisible(false);
        try {
            setTable();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherEnfantsParentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void setTable() throws FileNotFoundException{
         List<Enfant> ListE=Enfant.findEnfantByGarderie();
   
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
 gard_id.setCellValueFactory(new PropertyValueFactory<>("garderie_id"));
 parent.setCellValueFactory(new PropertyValueFactory<>("parent"));
 prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
 dn.setCellValueFactory(new PropertyValueFactory<>("dn"));
 nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
 image.setCellValueFactory(new PropertyValueFactory<>("image"));
        ObservableList<Enfant> OLE= FXCollections.observableArrayList(ListE);
       System.out.println(OLE.get(0).getId());
       System.out.println(ListE.get(0).getId());
        List<EnfantAffichage> ListE1=new ArrayList<>();
        for(int i=0; i<ListE.size(); i++)
        {System.out.println(ListE.get(i).getNomfile());
            FileInputStream inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+ListE.get(i).getNomfile()); 
            System.out.println(ListE.get(i).getNomfile());
            Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
            image1=new ImageView(img);
            image1.setFitHeight(50);
            image1.setFitWidth(50);
        EnfantAffichage EA= new EnfantAffichage(image1, ListE.get(i).getId(), ListE.get(i).getParent(), ListE.get(i).getNom(), ListE.get(i).getPrenom(), ListE.get(i).getNomfile(), ListE.get(i).getGarderie_id(), ListE.get(i).getDn());
        ListE1.add(EA);
        
        }
        ObservableList<EnfantAffichage> OLE1= FXCollections.observableArrayList(ListE1);
         System.out.println(OLE1.get(0).getId());
         
        table.setItems(OLE1);
       
        
     }
    @FXML
    private void suivi(ActionEvent event) {
        EnfantMod=new Enfant(table.getSelectionModel().getSelectedItem());
        System.out.println(EnfantMod.toString());
        loadWindow(getClass().getResource("/pi/devjava/GUI/AfficherSuivi.fxml"), "Affichage",null);
         Stage stage = (Stage) table.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void modiifer(ActionEvent event) {
       /* EnfantMod=new Enfant(table.getSelectionModel().getSelectedItem());
        loadWindow(getClass().getResource("/pi/devjava/GUI/ModifierEnfant.fxml"), "modifier",null);
        
         Stage stage = (Stage) table.getScene().getWindow();
         stage.close();*/
    }

    @FXML
    private void suppression(ActionEvent event) throws FileNotFoundException {
        /* Enfant.supprimerEnfant(table.getSelectionModel().getSelectedItem().getId());
        setTable();*/
    }
    
}
