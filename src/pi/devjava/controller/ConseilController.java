/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.S;
import javafx.util.Callback;
import static jdk.nashorn.internal.runtime.Debug.id;
import pi.devjava.entities.info_sante;
import pi.devjava.services.info_santeservice;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ConseilController implements Initializable {

    @FXML
    private TextField tinfo;
    @FXML
    private DatePicker tvaccin;
    @FXML
    private TableView<info_sante> table;
    @FXML
    private TableColumn<info_sante, String> nomS;
    @FXML
    private TableColumn<info_sante, String> prenomS;
    @FXML
    private TableColumn<info_sante, String> specialiteS;
    @FXML
    private TableColumn<info_sante, String> infoS;
    @FXML
    private TableColumn<info_sante, String> id_medecin;
    
    
     Connection  myConnex;
       Statement ste; 
       
     
       List<info_sante> info_santes = new ArrayList<>() ;   
       info_santeservice ss= new info_santeservice();
        private final ObservableList<info_sante> data = FXCollections.observableArrayList() ;   
     private final ObservableList<String> datam = FXCollections.observableArrayList(ss.get_id_med()) ;  
    @FXML
    private ComboBox<String> com;
    @FXML
    private TableColumn<info_sante, String> dateS;
    @FXML
    private TextField rechercher;
    @FXML
    private TableColumn<info_sante, String> imageS;


        

        
        // private final ObservableList<String> dataid = FXCollections.observableArrayList(S.); 

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      com.setItems(datam); 
        try {
            Aff();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConseilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         RechercheAV();
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         
        
        String info = tinfo.getText() ;  
        DatePicker tmpdate = (DatePicker)tvaccin ; 
        String date=new String();
        if( !tmpdate.getEditor().getText().isEmpty())
        {date = (String) tmpdate.getValue().toString() ; 
        ComboBox tmpcom = (ComboBox) com ;
        if(info.isEmpty())
            {
             alert1("veuillez remplir tous les champs","Remplir tous les champs");
            }   
            
      else{
          info_sante s = new info_sante(info, date, ss.recherchem(tmpcom.getValue().toString())); 
        
       
        ss.ajouterinfo(s);
    //  SebMail.sendMail("nour.trabelsi@esprit.tn","Mise à jour de votre compte", "Votre conseil est bien ajouté, il est disponible dans notre application. Merci de mettre votre confiance en nous. ");             

        Aff();
      }
        
        }
        else{
        alert1("veuillez remplir tous les champs","Remplir tous les champs");
         
        }
        
        
               
      
       
               }
        
         
        
       private void alert1(String Message,String Accept) {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle("Alert");
        a1.setHeaderText(Accept);
        a1.setContentText(Message);
        a1.showAndWait();
    }  
        
        
        
        
    
    
    
    public void Aff() throws ClassNotFoundException {
          data.clear(); 
        info_santes =ss.afficherinfo() ;
        for (info_sante info_sante : info_santes) {
           data.add(info_sante);
        }
    
        
         nomS.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMed().getNom())) ;  
        prenomS.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMed().getPrenom())) ;  
       specialiteS.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMed().getSpecialite())) ;  
         infoS.setCellValueFactory(new PropertyValueFactory<>("info"));   
                  dateS.setCellValueFactory(new PropertyValueFactory<>("datevaccin"));  
       // id_medecin.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMed().getCin())) ; 
        // imageS.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMed().getNomfile())) ; 

        
         
        
        // idproduit.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getPro().getId()));  
        // Prix.setCellValueFactory(e-> new SimpleFloatProperty(e.getValue().getPro().getPrix()));

         table.setItems(data);  
         table.setEditable(true);
         
         
         
    

         infoS.setCellFactory(TextFieldTableCell.forTableColumn()); 
         dateS.setCellFactory(TextFieldTableCell.forTableColumn()); 

 

    } 

    @FXML
    private void Change_info(TableColumn.CellEditEvent bb ) { 
         
         info_sante tab_santeSelected =table.getSelectionModel().getSelectedItem();
     tab_santeSelected.setInfo(bb.getNewValue().toString());
        ss.modifierinfo(tab_santeSelected.getId(),tab_santeSelected.getDatevaccin(),tab_santeSelected.getInfo());
        
          
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Conseil Sante est modifié ");
                                     Optional<ButtonType> action = alert.showAndWait();
                             
                                     
                                     
                            
                                     
    }

    @FXML
    private void Change_date(TableColumn.CellEditEvent bb) { 
       info_sante tab_santeSelected =table.getSelectionModel().getSelectedItem();
     tab_santeSelected.setDatevaccin(bb.getNewValue().toString());
        ss.modifierinfo(tab_santeSelected.getId(),tab_santeSelected.getDatevaccin(),tab_santeSelected.getInfo());
    }

    @FXML
    private void Supprimer(ActionEvent event) throws ClassNotFoundException { 
         table.setItems(data);

             ObservableList<info_sante> allDemandes,SingleDemandes ;
             allDemandes=table.getItems();
             SingleDemandes=table.getSelectionModel().getSelectedItems();
             info_sante A = SingleDemandes.get(0);
             info_santeservice STP = new info_santeservice();
             
              int id = A.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette information");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (id != 0) {
                                                    STP.supprimerinfo(A.getId());
             SingleDemandes.forEach(allDemandes::remove); 
                                           //SEnt.supprimerformation(id);
                                                }
                                    }
                      
             
           //  STP.supprimerinfo(A.getId());
          //   SingleDemandes.forEach(allDemandes::remove); 
             Aff();
    } 
    
    
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<info_sante> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(info_sante -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                
                                 if (info_sante.getMed().getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                
				  if (info_sante.getInfo().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                   if (info_sante.getDatevaccin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
                                  
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<info_sante> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }
   
   
    
    
    
    
    
}

    
    
    
    
    

    

