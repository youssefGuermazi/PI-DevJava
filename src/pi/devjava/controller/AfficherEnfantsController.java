/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pi.devjava.entities.Enfant;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kairos.components.FlexTable;
import pi.devjava.entities.EnfantAffichage;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class AfficherEnfantsController implements Initializable {
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
    private Button modifier;
    public static Enfant EnfantMod;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficherSuivi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    try {
        setTable();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(AfficherEnfantsController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }    

    @FXML
    private void modifier(ActionEvent event) {
        
        EnfantMod=new Enfant(table.getSelectionModel().getSelectedItem());
        loadWindow(getClass().getResource("/pi/devjava/GUI/ModifierEnfant.fxml"), "modifier",null);
        
         Stage stage = (Stage) table.getScene().getWindow();
         //stage.close();
    }
    
    public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void supprimer(ActionEvent event) throws FileNotFoundException {
        
        Enfant.supprimerEnfant(table.getSelectionModel().getSelectedItem().getId());
        setTable();
    }
     public void setTable() throws FileNotFoundException{
         List<Enfant> ListE=Enfant.findEnfant();
   
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
    private void AfficherSuivi(ActionEvent event) {
         
        EnfantMod=new Enfant(table.getSelectionModel().getSelectedItem());
        System.out.println(EnfantMod.toString());
        loadWindow(getClass().getResource("/pi/devjava/GUI/SuiviParent.fxml"), "Affichage",null);
         Stage stage = (Stage) table.getScene().getWindow();
         //stage.close();
    }
    
}
