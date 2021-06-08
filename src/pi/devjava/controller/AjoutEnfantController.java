/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXComboBox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static pi.devjava.controller.AfficherEnfantsController.loadWindow;
import static pi.devjava.controller.TestfrontGarderieController.nomm;
import static pi.devjava.controller.TestfrontGarderieController.num;
import static pi.devjava.controller.interfaceResponsableController.ident;
import pi.devjava.entities.Enfant;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.userService;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class AjoutEnfantController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField gard1;
    @FXML
    private DatePicker date;
    @FXML
    private JFXComboBox<String> garderie=new JFXComboBox<>();
    @FXML
    private ImageView close;
    @FXML
    private Button ImagecChooser;
    final FileChooser fileChooser = new FileChooser();
    private Image image;
    File file;
     Alert alert;
     boolean ajoutb=true;
     userService us = new userService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List g;
        g = Enfant.findAllGaderie();
        System.out.println(g.get(0));
        
        ObservableList<String> listg = FXCollections.observableArrayList(g);
        garderie.setItems(listg);
        date.getEditor().focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldV, Boolean newV) {
                if (!newV) { // focus lost
                    if(date.getValue().isAfter(java.time.LocalDate.now()))
                    {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Date");
                    
                    alert.setContentText("le date n'est pas correct");
                    alert.showAndWait();
                    date.getEditor().setText("");
                    }
                    
                }}
        });
        garderie.setVisible(false);
        garderie.setValue(num);
        gard1.setText(nomm);
    }    

    @FXML
    private void ajout(ActionEvent event) {
        System.out.println(garderie.getValue());
        System.out.println(ident);
        int identi;
         identi=us.getInfoUser(Utilisateur.uName).getId();
        if(!nom.getText().isEmpty()&&!prenom.getText().isEmpty()&&!garderie.getSelectionModel().isEmpty()&&!date.getEditor().getText().isEmpty()&&!ImagecChooser.getText().equals("chose Image"))
        {Enfant.ajoutEnfant(nom.getText(), prenom.getText(), garderie.getValue(), identi, file.getName(), date.getValue());
        
        {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("succée");
                    
                    alert.setContentText("ajouer avec sucées");
                    alert.showAndWait();
                    ajoutb=false;
                    }
        Stage stage = (Stage) nom.getScene().getWindow();
         stage.close();}
        else
        {
         {alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Empty");
                    
                    alert.setContentText("un champ est empty");
                    alert.showAndWait();
                    ajoutb=false;
                    }
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
    private void choseImage(ActionEvent event) {
    setExtFilters(fileChooser);
                    file = fileChooser.showOpenDialog(nom.getScene().getWindow());
                    if (file != null) {
                        Enfant.saveToFile(new Image(file.toURI().toString()),file.getName());
                        ImagecChooser.setText(file.getName());
                    }
    }
  
     
    private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void nomEnter(KeyEvent event) {
        
  
        
    }

    @FXML
    private void prenomEnter(KeyEvent event) {
        if(event.getText().matches(".*[^a-z].*"))
        {
        if(!prenom.getText().trim().isEmpty())
        {
    
        prenom.setText(prenom.getText().replace(event.getText().charAt(0), Character.MIN_VALUE));
        prenom.positionCaret(prenom.getText().length());
        }
        
        }
    }
@FXML
    private void checkDate(MouseEvent event) {
       
      
    }

    @FXML
    private void nomchange(KeyEvent event) {
              
        
                System.out.println(event.getText());
        
    }

    @FXML
    private void nomchange1(KeyEvent event) {
    if(event.getText().matches(".*[^a-z].*"))
        {
        if(!nom.getText().trim().isEmpty())
        {
    
        nom.setText(nom.getText().replace(event.getText().charAt(0), Character.MIN_VALUE));
        nom.positionCaret(nom.getText().length());
        }
        
        }
    }

    private void checkDate(TouchEvent event) {
         
    }

    @FXML
    private void checkDate(InputMethodEvent event) {
       
        
    }
             
    
}
