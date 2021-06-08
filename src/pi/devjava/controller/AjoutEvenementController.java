/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import org.apache.commons.lang3.RandomStringUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pi.devjava.entities.Evenement;
import pi.devjava.entities.Mailing;

import pi.devjava.services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXDatePicker date_debut;
    @FXML
    private JFXButton AddButton;
    @FXML
    private JFXButton affich;
    @FXML
    private JFXComboBox<String> club_evenement=new JFXComboBox<>();
    @FXML
    private JFXDatePicker date_fin;
    private JFXTextField nomfile;
    @FXML
    private ImageView imgButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List g;
        g = ServiceEvenement.findAllClubs();
        ObservableList<String> listg = FXCollections.observableArrayList(g);
        club_evenement.setItems(listg);
    }    
      @FXML
    private void closeApplication(MouseEvent event) {
           

             nom.getScene().getWindow().hide();
       
    }

    @FXML
    private void AddButton(MouseEvent event) {
        if (event.getSource() == AddButton) {
            // here

            if (AddEvenement()) {
                System.out.println("hazem");
                List<String> userMail;
                userMail=ServiceEvenement.findAllUsers();
                for(String i:userMail){
                    System.out.println(i);
                Mailing.send(i,nom.getText() ,"un nouvel evenement est crée , vous pouvez participer maintenant." +
                        description.getText() , "tesprit2020@gmail.com", "Test2020");
                }
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Event");
                alert.setHeaderText("Results:");
                alert.setContentText("ERROR!");
                alert.showAndWait();

            }

        }
    }
public boolean AddEvenement() {

        

        try {
               Image image1=null;
             image1 = imgButton.getImage();
            String photo = null;
             photo = saveToFileImageNormal(image1);
            Date gettedDatePickerDate = Date.valueOf(date_debut.getValue());
             Date gettedDatePickerDate1 = Date.valueOf(date_fin.getValue());
            Evenement c = new Evenement(nom.getText(),club_evenement.getValue(),  description.getText(), gettedDatePickerDate,gettedDatePickerDate1,photo);
            if((date_debut.getValue().isAfter(LocalDate.now()))&& (date_debut.getValue().isBefore(date_fin.getValue()))){
            ServiceEvenement Evenements = new ServiceEvenement();
            Evenements.addEvenement(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Event");
                alert.setHeaderText("Results:");
                alert.setContentText("Added successfully!");
                alert.showAndWait();
                return true;
}
            else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Event");
                alert.setHeaderText("Results:");
                alert.setContentText("ERROR! fix date debut et date fin ");
                alert.showAndWait();
                return false;
            }
        } catch (SQLException ex) {
        return false;
        }

        

    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/afficherEvenement.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void addImage(MouseEvent event)throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgButton.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
 public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads");
        String name = String.format("%s.%s",RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
