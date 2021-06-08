/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JFileChooser;
import pi.devjava.entities.Suivi;

/**
 * FXML Controller class
 *
 * @author kamikaz
 */
public class SuiviParentController implements Initializable {

    @FXML
    private TableView<Suivi> tab;
    @FXML
    private TableColumn<Suivi, ?> id;
    @FXML
    private TableColumn<Suivi, ?> francais;
    @FXML
    private TableColumn<Suivi, ?> anglais;
    @FXML
    private TableColumn<Suivi, ?> info;
    @FXML
    private TableColumn<Suivi, ?> evaluation;
    @FXML
    private TableColumn<Suivi, ?> dn;
    @FXML
    private Button pdf;
List<Suivi> Lists;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setTable();
    }    
 public void setTable() {
        
          Lists=Suivi.findSuivi(AfficherEnfantsController.EnfantMod.getId());
  for(int i=0;i<Lists.size();i++)
      if(Lists.get(i).getEnfant_id()!=AfficherEnfantsController.EnfantMod.getId())
          Lists.remove(i);
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
 francais.setCellValueFactory(new PropertyValueFactory<>("note_francais"));
 anglais.setCellValueFactory(new PropertyValueFactory<>("note_anglais"));
 info.setCellValueFactory(new PropertyValueFactory<>("note_info"));
 evaluation.setCellValueFactory(new PropertyValueFactory<>("evaluation"));
 dn.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<Suivi> OLE= FXCollections.observableArrayList(Lists);
       
      
         
        tab.setItems(OLE);
        
        
     }
    @FXML
    private void pdfGenarate(ActionEvent event) throws FileNotFoundException, DocumentException {
         String path="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x=j.showSaveDialog(j);
        
        if (x==JFileChooser.APPROVE_OPTION)
        {
            path=j.getSelectedFile().getPath();
        }
        
        Document doc = new Document();
        
        PdfWriter.getInstance(doc, new FileOutputStream(path+"Suivi.pdf"));
        
        doc.open();
        doc.add(new Paragraph("Suivi de "+AfficherEnfantsController.EnfantMod.getNom(),FontFactory.getFont("Comic Sans MS",26)));
        doc.add(new Paragraph(" "));
        PdfPTable tb1 = new PdfPTable(5);
        
        //Adding headers

        tb1.addCell("francais");
        tb1.addCell("anglais");
        tb1.addCell("informatique");
        tb1.addCell("evaluation");
        tb1.addCell("date");
        
        
        
           
for(int c=0;c<Lists.size();c++){
             String f = Integer.toString(Lists.get(c).getNote_francais()) ;
            String ang =  Integer.toString(Lists.get(c).getNote_anglais()) ;
          String info =  Integer.toString(Lists.get(c).getNote_info()) ;
          String evaluation =  Lists.get(c).getEvaluation() ;
          String date =  Lists.get(c).getDate().toString() ;
             tb1.addCell(f);
            tb1.addCell(ang);
            tb1.addCell(info);
            tb1.addCell(evaluation);
            tb1.addCell(date);
          
}
                    doc.add(tb1);
/*
            Paragraph para = new Paragraph("Test !");
            doc.add(para);
            */
                    doc.close();
    }
    
}
