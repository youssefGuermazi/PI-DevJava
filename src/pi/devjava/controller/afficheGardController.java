/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static pi.devjava.controller.LoginController.loadWindow;
import pi.devjava.entities.Garderie;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.GarderieService;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class afficheGardController implements Initializable {

    @FXML
    private AnchorPane frame;
    @FXML
    private Label inscrirLabel;
    @FXML
    private ImageView close;
    @FXML
    private JFXButton Delete;
    @FXML
    private JFXButton Update;
    @FXML
    private TableView<Garderie> table;
    @FXML
    private TableColumn<Garderie,String> numero;
    @FXML
    private TableColumn<Garderie,String> nom;
    @FXML
    private TableColumn<Garderie,String> telephone;
    @FXML
    private TableColumn<Garderie,String>adresse;
    @FXML
    private TableColumn<Garderie,Image> image;
        ObservableList<Garderie> orderData = FXCollections.observableArrayList();
        public static Garderie GardMod;
        


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  Delete.setVisible(false);
       // Update.setVisible(false);
       close.setVisible(false);
        
      table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<Garderie> gardList = new ArrayList<>();
        GarderieService gs = GarderieService.getInstance();
        gardList=gs.getAllGards();
        orderData.clear();
        orderData.addAll(gardList);

        if (!gardList.isEmpty()) {
                   
        FileInputStream inputstream; 
        
       
          try {
              inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\amal.jpg");
          
             Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
           

               numero.setCellValueFactory(new PropertyValueFactory<Garderie,String>("numGard"));
               nom.setCellValueFactory(new PropertyValueFactory<Garderie,String>("nom"));
               adresse.setCellValueFactory(new PropertyValueFactory<Garderie,String>("adresse"));
               telephone.setCellValueFactory(new PropertyValueFactory<Garderie,String>("telephone"));
              // image.setCellValueFactory(new PropertyValueFactory<Garderie,Image>("image"));
               table.setItems(orderData);
               System.out.println(img);
                } catch (FileNotFoundException ex) {
              Logger.getLogger(afficheGardController.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
            System.out.println("no");
        }
    
        
    
    }

    @FXML
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
    private void Delete(MouseEvent event) {
             try{
        
     
        Garderie g = table.getSelectionModel().getSelectedItem();
        GarderieService.getInstance().delete(g);
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez supprimer la garderie");
        alert.setHeaderText("Vous allez supprimer la garderie");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        

    table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<Garderie> gardList = new ArrayList<>();
        GarderieService gs = GarderieService.getInstance();
        gardList=gs.getAllGards();
        orderData.clear();
        orderData.addAll(gardList);

        if (!gardList.isEmpty()) {

               numero.setCellValueFactory(new PropertyValueFactory<Garderie,String>("numGard"));
               nom.setCellValueFactory(new PropertyValueFactory<Garderie,String>("nom"));
               adresse.setCellValueFactory(new PropertyValueFactory<Garderie,String>("adresse"));
               telephone.setCellValueFactory(new PropertyValueFactory<Garderie,String>("telephone"));
              // image.setCellValueFactory(new PropertyValueFactory<Garderie,Image>("image"));
               table.setItems(orderData);
        } else {
            System.out.println("no");
        }
    
        
            
        } else {
            alert.close();
            
}
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Garderie supprimé");
        alert1.setHeaderText("Garderie supprimé");
        alert1.show();

    }
 catch(Exception e){

          Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("sélectionner une ligne SVP!!");
        alert1.setHeaderText("sélectionner une ligne SVP!!");
        alert1.show();
        }
    }

    @FXML
    private void Update(MouseEvent event) {
       // String numgard = numero.getText();
        GardMod=table.getSelectionModel().getSelectedItem();
        {
                        loadWindow(getClass().getResource("/pi/devjava/GUI/updateGard.fxml"), "Dashboard", null);
/*Stage stage = (Stage) table.getScene().getWindow();
         stage.close();*/
                        
                        }
       
    }
    
}
