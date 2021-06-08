/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;


import pi.devjava.services.SebMail;
import pi.devjava.services.sms;
import pi.devjava.controller.Utilis.ConnexionDB;
import com.itextpdf.text.DocumentException;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.SystemTray;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;



import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
//import org.apache.commons.httpclient.HttpClient;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import org.apache.commons.lang3.RandomStringUtils;
import pi.devjava.entities.Medecin_Image;
import pi.devjava.entities.medecin;
import pi.devjava.services.medecinservice;





/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MedecinController implements Initializable {

    @FXML
    private TextField tfcin;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
   
    @FXML
    private TableView<Medecin_Image> tab;
    @FXML
    private TableColumn<Medecin_Image, String> cinT;
    @FXML
    private TableColumn<Medecin_Image, String> nomT;
    @FXML
    private TableColumn<Medecin_Image, String> prenonT;
    @FXML
    private TableColumn<Medecin_Image, String> specialiteT;
    @FXML
    private TableColumn<Medecin_Image, ImageView> imageT;

    
    
    
    
     Connection  myConnex;
       Statement ste;
       
       
       
        List<medecin> medecin1 = new ArrayList<>() ;   
        
        private final ObservableList<medecin> data = FXCollections.observableArrayList() ;  
         private final ObservableList<Medecin_Image> data1 = FXCollections.observableArrayList(); 
          private final ObservableList<String> datam = FXCollections.observableArrayList("Généraliste","Pédiatre","Psychiatre") ;  
    @FXML
    private Button supprimer;
    @FXML
    private ImageView btnimage2;
    @FXML
    private TextField rechercher;
    @FXML
    private Button pdf;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
                  combo.setItems(datam); 

            // TODO
            Aff();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RechercheAV();
        
 
    
       
    }    

    
    
     public boolean validateCINP() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tfcin.getText());
        if (m.find() && m.group().equals(tfcin.getText()) && tfcin.getText().length() == 8) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate CIN");
            alert.setHeaderText(null);
            alert.setContentText("veuillez Entrez Votre Valide CIN");
            alert.showAndWait();

            return false;
        }
    }
    
    
    
    
    
    
    @FXML
    private void Ajouter(ActionEvent event) throws ClassNotFoundException, SQLException {  
           medecinservice ms = new medecinservice(); 

        String cin = tfcin.getText() ; 
         String nom = tfnom.getText() ;
          String prenom = tfprenom.getText() ; 
        
           ComboBox tmpcom = (ComboBox) combo ;
   
          //  String nomfile = tfnomfile.getText() ; 
       /*     
            Image nomfile1=null;
        nomfile1 = btnimage2.getImage();
        String nomfile = null;
        System.out.println("gdfg"+nomfile1);
                    nomfile = saveToFileImageNormal(nomfile1);
            
          */  
            
             if(cin.equals("")&&  nom.equals("")  && prenom.equals("") )
            {
           //  alert1("DENIED APPLICATION","YOU ALREADY DENIED THIS APPLICATION");
                 Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Formulaire");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.showAndWait();
                
            }
            
            
             else if ( validateCINP()==true){
                   Image nomfile1=null;
        nomfile1 = btnimage2.getImage();
        String nomfile = null;
        System.out.println("gdfg"+nomfile1);
                    nomfile = saveToFileImageNormal(nomfile1);
                          

                 medecin m = new medecin(cin, nom, prenom, tmpcom.getValue().toString(), nomfile); 
         
               ms.ajoutermedecin(m);
      
                      TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage(" Medecin Ajouté ! ");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
                 
            
          //  Aff();
             } 
             
               
        Aff();
        RechercheAV();
            
          
        
    }
    
      
     public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
          
    }
    
    
    
    
    
    
    
    
    
    
     private void alert1(String Message,String Accept) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText(Accept);
        a1.setContentText(Message);
        a1.showAndWait();
    }
    
    
    
     public void Aff() throws ClassNotFoundException {
          medecinservice ms = new medecinservice();
         
           try {
                          
                       
                         
                         
                         myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();

           
            ste =  myConnex.createStatement();
                       data.clear();
                        data1.clear();
            ResultSet rs = ste.executeQuery("SELECT * FROM medecin ORDER BY nom ,cin ASC  ");
                            //System.out.println(rs);
                            ImageView image1;
            while(rs.next()){
                FileInputStream inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+rs.getString(5)); 
            Image img;
             image1=new ImageView();
            img =new Image(inputstream);
            image1=new ImageView(img);
            image1.setFitHeight(50);
            image1.setFitWidth(50);
                data1.add(new Medecin_Image(image1,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
                data.add(new medecin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));

            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
      
 
               
           cinT.setCellValueFactory(new PropertyValueFactory<>("cin"));
            nomT.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenonT.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            specialiteT.setCellValueFactory(new PropertyValueFactory<>("specialite"));
            imageT.setCellValueFactory(new PropertyValueFactory<>("img"));
            
 
            
            tab.setItems(data1);
            tab.setEditable(true);
            
            cinT.setCellFactory(TextFieldTableCell.forTableColumn());
            nomT.setCellFactory(TextFieldTableCell.forTableColumn());
              prenonT.setCellFactory(TextFieldTableCell.forTableColumn());
            //quanP.setCellFactory(TextFieldTableCell.forTableColumn());
               specialiteT.setCellFactory(TextFieldTableCell.forTableColumn());
//            imageT.setCellFactory(TextFieldTableCell.forTableColumn());
     
   medecin1 =ms.affichermedecin() ;
      

 

    }


    @FXML
    private void supprimer(ActionEvent event) throws ClassNotFoundException, IOException {
       
  
        
        tab.setItems(data1);

             ObservableList<medecin> allDemandes,SingleDemandes ;
             allDemandes=medecin.echange(tab.getItems());
                     
             SingleDemandes=medecin.echange(tab.getSelectionModel().getSelectedItems());
             medecin A = new medecin(tab.getSelectionModel().getSelectedItem());
            medecinservice STP = new medecinservice();
           
            
             
             
             //  String id = A.getCin();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer ce medecin");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                              
                   /* 
                              sms s =new sms();          
            try {
                s.sensSms();
            } catch (Exception ex) {
                Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
              */   
                       
                       STP.supprimermedecin(A.getCin());
                         
                    SingleDemandes.forEach(allDemandes::remove); 
                       
                                           
                                    }
            
        SebMail.sendMail("nour.trabelsi@esprit.tn","Annulation du contrat", "vous ne faites plus partie de notre jardin d'enfant.");             
                                    
                                    
         //    STP.supprimermedecin(A.getCin());
         //    SingleDemandes.forEach(allDemandes::remove); 
             try {
            // TODO
            Aff();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RechercheAV();
    
        
        
    }

    @FXML
    private void Change_Nom(TableColumn.CellEditEvent bb) throws ClassNotFoundException { 
         medecinservice ms = new medecinservice();
         
         medecin tab_MedecinSelected =new medecin(tab.getSelectionModel().getSelectedItem());
     tab_MedecinSelected.setNom(bb.getNewValue().toString());
     
        ms.modifierMedecin(tab_MedecinSelected.getCin(),tab_MedecinSelected.getNom(),tab_MedecinSelected.getPrenom(),tab_MedecinSelected.getSpecialite(),tab_MedecinSelected.getNomfile());
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Medecin modifier");
                                     Optional<ButtonType> action = alert.showAndWait();
         
        
           
             sms s =new sms();          
            try {
           s.sensSms();
            } catch (Exception ex) {
                Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        
    }

   @FXML
    private void Change_Prenom(TableColumn.CellEditEvent bb) throws ClassNotFoundException { 
         medecinservice ms = new medecinservice();
         
         medecin tab_MedecinSelected =new medecin(tab.getSelectionModel().getSelectedItem());
     tab_MedecinSelected.setPrenom(bb.getNewValue().toString());
        ms.modifierMedecin(tab_MedecinSelected.getCin(),tab_MedecinSelected.getNom(),tab_MedecinSelected.getPrenom(),tab_MedecinSelected.getSpecialite(),tab_MedecinSelected.getNomfile());
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Medecin modifié");
                                     Optional<ButtonType> action = alert.showAndWait();
                                     
                                     
    }

    @FXML
    private void Change_Specialite(TableColumn.CellEditEvent bb) throws ClassNotFoundException { 
         medecinservice ms = new medecinservice();
         
         medecin tab_MedecinSelected =new medecin(tab.getSelectionModel().getSelectedItem());
     tab_MedecinSelected.setSpecialite(bb.getNewValue().toString());
        ms.modifierMedecin(tab_MedecinSelected.getCin(),tab_MedecinSelected.getNom(),tab_MedecinSelected.getPrenom(),tab_MedecinSelected.getSpecialite(),tab_MedecinSelected.getNomfile());
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Medecin modifier");
                                     Optional<ButtonType> action = alert.showAndWait();
    }

    @FXML
    private void Change_Image(TableColumn.CellEditEvent bb) throws ClassNotFoundException { 
         medecinservice ms = new medecinservice();
         
         medecin tab_MedecinSelected =new medecin(tab.getSelectionModel().getSelectedItem());
     tab_MedecinSelected.setNomfile(bb.getNewValue().toString());
        ms.modifierMedecin(tab_MedecinSelected.getCin(),tab_MedecinSelected.getNom(),tab_MedecinSelected.getPrenom(),tab_MedecinSelected.getSpecialite(),tab_MedecinSelected.getNomfile());
    }

    @FXML
    private void addImage(MouseEvent event) {
        
          FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            btnimage2.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        
     }     
        
    }
    
    
    
   
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Medecin_Image> filteredData = new FilteredList<>(data1, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(medecin -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                
				  if (medecin.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
				if (medecin.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
                                if (medecin.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                  if (medecin.getSpecialite().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Medecin_Image> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tab.setItems(sortedData);
    }

  

    @FXML
    private void pdf(MouseEvent event) throws ClassNotFoundException, SQLException, DocumentException, IOException {
          medecinservice cc = new medecinservice();
          cc.FacturePdf();
        
    }
   
   
   
   
   

    
}   
    
    

