/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import pi.devjava.controller.Animateur_formationController;

import pi.devjava.services.animateurservice;
import Technique.Browser;
import Technique.TrayIconDemo;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemTray;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import pi.devjava.entities.animateur;
import pi.devjava.entities.animateur_image;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AnimateurController implements Initializable {
    ObservableList<animateur_image> obs;
    animateur_image Aem;

    @FXML
    private TableView<animateur_image> anitable;
    @FXML
    private TableColumn<animateur_image, Integer> cin;
    @FXML
    private TableColumn<animateur_image, String> nom;
    @FXML
    private TableColumn<animateur_image, String> prenom;
    @FXML
    private TableColumn<animateur_image, String> activiter;
    @FXML
    private TableColumn<animateur_image, ImageView> image;
    @FXML
    private TextField prenomfield;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField nomfield;
    @FXML
    private TextField cinfield;
    @FXML
    private TextField activiterfield;
    @FXML
    private Button imagefield;
    @FXML
    private Button affecter;
    @FXML
    private AnchorPane rootPane;
    final FileChooser fileChooser = new FileChooser();
    private Image img;
    File file;
    @FXML
    private TextField search;
    @FXML
    private Button Google;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
         
            load();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AnimateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void load() throws FileNotFoundException {
animateurservice ent;
        try {
            
            ent = new animateurservice();
            ResultSet Enta = ent.afficheranimateur();
            obs = FXCollections.observableArrayList();
            FileInputStream inputstream;
            while (Enta.next()) {
             inputstream= new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+Enta.getString("nomfile")); 
            Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
            image1=new ImageView(img);
            image1.setFitHeight(50);
            image1.setFitWidth(50);
                obs.add(new animateur_image(image1,Enta.getInt("cin"), Enta.getString("nom"), Enta.getString("prenom"), Enta.getString("activiter"), Enta.getString("nomfile")));
            }
              cin.setCellValueFactory(new PropertyValueFactory<animateur_image, Integer>("cin"));
            nom.setCellValueFactory(new PropertyValueFactory<animateur_image, String>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<animateur_image, String>("prenom"));
            activiter.setCellValueFactory(new PropertyValueFactory<animateur_image, String>("activiter"));
            image.setCellValueFactory(new PropertyValueFactory<animateur_image, ImageView>("imo"));
            anitable.setItems(obs);
             cinfield.clear();
            nomfield.clear();
            prenomfield.clear();
            activiterfield.clear();
              chercher();
            //imagefield.clear();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnimateurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnimateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Aem = anitable.getSelectionModel().getSelectedItem();
            cinfield.setText(Integer.toString(Aem.getCin()));
            nomfield.setText(Aem.getNom());
            prenomfield.setText(Aem.getPrenom());
            activiterfield.setText(Aem.getActiviter());
            imagefield.setText(Aem.getNomfile());
    }

   
    @FXML
    private void add(ActionEvent event) throws ClassNotFoundException, AWTException, FileNotFoundException {
         animateurservice P = new animateurservice ();

        String m=cinfield.getText();
         String t = nomfield.getText();
         //mail f =new mail();
        //String MSG = " l'animateur" + nomfield.getText() ;
         if(file!=null){
         animateur a = new  animateur(Integer.parseInt(cinfield.getText()), nomfield.getText(), prenomfield.getText(),activiterfield.getText(),file.getName());
         if (!(t.isEmpty())&& (m.length()==8))
         {
        P.ajouteranimateur(a);
         // f.mail(MSG);
        imagefield.setText("Image");
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
        load();
      
         }
        }

    }


    @FXML
    private void edit(ActionEvent event) throws FileNotFoundException {
        try {
            
            Aem = anitable.getSelectionModel().getSelectedItem();
            
            animateurservice SEnt = new animateurservice();
              animateur a = new  animateur(Integer.parseInt(cinfield.getText()), nomfield.getText(), prenomfield.getText(),activiterfield.getText(),imagefield.getText());

            SEnt.modifieranimateur(a,Aem.getCin());
            imagefield.setText("Image");
            load();
          
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnimateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void delete(ActionEvent event) throws ClassNotFoundException, FileNotFoundException {
        Aem = anitable.getSelectionModel().getSelectedItem();
        animateurservice SEnt = new animateurservice();
        int cin = Aem.getCin();
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer ce animateur");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (cin != 0) {
                                           SEnt.supprimeranimateur(cin);
                                           imagefield.setText("Image");
                                           
                                                }
                                    }
        load();


    }

    @FXML
    private void affecter(ActionEvent event) {
         try {
            Aem = anitable.getSelectionModel().getSelectedItem();
            int id = Aem.getCin();
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pi/devjava/GUI/animateur_formation.fxml"));
           Parent root = loader.load();
           Animateur_formationController e = loader.getController();
           e.setYarab(id);
            rootPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(AnimateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void saveToFile(Image image,String name) {
         File outputFile = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
         BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
  }

    @FXML
    private void upload(ActionEvent event) {
        setExtFilters(fileChooser);
                    file = fileChooser.showOpenDialog(edit.getScene().getWindow());
                    if (file != null) {
                        saveToFile(new Image(file.toURI().toString()),file.getName());
                    }
     imagefield.setText(file.getName());
    }
   
private void setExtFilters(FileChooser chooser){
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

 public void chercher() {
        FilteredList<animateur_image> filteredData = new FilteredList<>(obs, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                if (aff.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
                if (aff.getActiviter().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } 
               
                else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<animateur_image> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(anitable.comparatorProperty());
        anitable.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.
    }

    @FXML
    private void Google(ActionEvent event) {
         Browser.load("http://localhost/projet/web/app_dev.php/front/AfficheAnimateur", new Dimension(1200,700), new Point(30, 20));
    }

  
    
}
