/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.devjava.entities.medecin;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;
import pi.devjava.controller.Utilis.ConnexionDB;

        
//import java.util.ArrayList;
//import java.sql.PreparedStatement;

/**
 *
 * @author ASUS
 */
public class medecinservice {
    
        Connection  myConnex;
       Statement ste;
        private ResultSet res ;
    
    public medecinservice() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
      
          public void ajoutermedecin(medecin m) {
                 try {
              String req =
                      "INSERT INTO medecin"
                      + "(cin,nom,prenom,specialite,nomfile) VALUES "
                      + "(?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
             ps.setString(1,m.getCin() );
             ps.setString(2,m.getNom() );
             ps.setString(3,m.getPrenom() );
             ps.setString(4,m.getSpecialite() );
             ps.setString(5,m.getNomfile() );
             ps.executeUpdate();
               
           
          } catch (SQLException ex) {
              Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
     
          
          
     public void modifierMedecin(String id , String nom , String prenom , String specialite , String image) {
        try {
            String req = "update medecin set nom='"+nom+"',prenom='"+prenom+"',specialite='"+specialite+"',nomfile='"+image+"' where cin="+id;
            PreparedStatement ps = myConnex.prepareStatement(req);
            
            System.out.println("medecin Modifier");
               ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
          
          
          
    public void supprimermedecin(String cin) {
                  try {
            String req = "delete from medecin where cin =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, cin);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }       
          
     public ArrayList affichermedecin() {
        ArrayList<medecin> psr = new ArrayList<>();
                  try {
              String req3 =
                      "SELECT * FROM medecin ORDER BY nom, cin ASC";
            PreparedStatement ps = myConnex.prepareStatement(req3);
          
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                       medecin m = new medecin();
                       
                m.setCin(res.getString("cin"));
                m.setNom(res.getString("nom"));
                m.setPrenom(res.getString("prenom"));
                m.setSpecialite(res.getString("specialite"));
                m.setNomfile(res.getString("nomfile"));
                psr.add(m);
            }
          } catch (SQLException ex) {
              Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
          }
          
            return psr; 
    }      
          
     
    
     
      
       public List<medecin> trier() throws SQLException{
           List<medecin> arr=new ArrayList<>();
        ste=myConnex.createStatement();
    String query = " select * from medecin order by cin desc";
    PreparedStatement prepare = myConnex.prepareStatement(query);
     ResultSet rs=prepare.executeQuery();
            // System.out.println("idUtlisateur\t\tidEvenement\t\tNumPaticipant");  

     while (rs.next()){
            String cin=rs.getString(1);
               String nom=rs.getString(2);
                String prenom=rs.getString(3);
                 String specialite=rs.getString(4);
                  String nomfile=rs.getString(5);
               
             
               medecin m=new medecin(cin, nom, prenom, specialite, nomfile);
     arr.add(m);
        
} 
     return arr;
    }
      
      
  
     
    public medecin getById(String cin) {
          medecin a = null;
         String requete = " select* from medecin  where cin='"+cin+"'" ;
        try {
           
            ste = myConnex.createStatement();
            res = ste.executeQuery(requete);
            if (res.next())
            {
               a=new medecin(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));} 
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
     public medecin getByName(String nom) {
          medecin a = null;
         String requete = " select * from medecin where (nom like '"+nom+"%')" ;
        try {
           
            ste = myConnex.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new medecin(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  
   public medecin getByPrenom(String prenom) {
          medecin a = null;
         String requete = " select * from medecin where (prenom like '"+prenom+"%')" ;
        try {
           
            ste = myConnex.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new medecin(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    } 
     
   public medecin getBySpecialite(String specialite) {
          medecin a = null;
         String requete = " select * from medecin where (specialite like '"+specialite+"%')" ;
        try {
           
            ste = myConnex.createStatement();
            res=ste.executeQuery(requete);
            if (res.next()){
             a=new medecin(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(medecinservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }    
  
   
    public void FacturePdf() throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        ste=myConnex.createStatement();
        ResultSet rs=ste.executeQuery("select * from medecin ORDER BY nom");
        PdfWriter.getInstance(doc, new FileOutputStream("c:/pdf/Resultat.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Liste Des M??decins :  ",FontFactory.getFont("Comic Sans MS",26)));
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" Le But De Nos M??decins:  ",FontFactory.getFont("Comic Sans MS",16)));
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph(" Nos m??decins et autres professionnels du monde m??dical, vous offre des conseils pour garder vos enfants en bonne sant??."
                + " Nos experts vous guide ?? bien prendre soins de vos enfants. Sortir le carnet de vaccination du placard et s???assurer que tous les vaccins des enfants sont ?? jour sinon nos medecins seront ?? votre disposition pour les rappeller , on vous facilte la tache.  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
          cell = new PdfPCell(new Phrase("Cin",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Nom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Prenom",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Specialite",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        
      /*  cell = new PdfPCell(new Phrase("image",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);*/
    
        
     while (rs.next()) {                
         
               String cin=rs.getString("cin");
               String nom=rs.getString("nom");
               String prenom=rs.getString("prenom");
               String specialite=rs.getString("specialite");
               String nomfile=rs.getString("nomfile");
               
      
               //Conversion to String
              /*
               String nom  = nom.toString();
               String prenom  = prenom.toString();
*/
               //DateFormat df = new SimpleDateFormat("hh:mm:ss");
               //String rec = df.format(record);
               // String rank = Integer.toString(ranking);
         
               
                 cell = new PdfPCell(new Phrase(cin,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               
               cell = new PdfPCell(new Phrase(nom,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(prenom,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(specialite,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
        
        
            /*   cell = new PdfPCell(new Phrase(nomfile,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);*/
        
                        }
            doc.add(table);
            
            
   doc.add(new Paragraph("   "));             
   doc.add(new Paragraph(" Conseil G??n??ral:  ",FontFactory.getFont("Comic Sans MS",16)));
   doc.add(new Paragraph("   "));         
   doc.add(new Paragraph(" La vigilance parentale est de mise pour des enfants en s??curit?? ?? la maison."
           + " Il ne faut jamais laisser un b??b?? sans surveillance lorsqu???il est dans le bain, sur sa chaise-haute ou sur la table ?? langer. De plus, il faut toujours s???assurer de ramasser le mat??riel dangereux apr??s utilisation, de tourner les poignets des casseroles vers la cuisini??re, de ne pas laisser de liquides chauds ?? la port??e des enfants et de faire tremper les casseroles chaudes dans l???eau froide apr??s-usage. "));
   doc.add(new Paragraph("   "));         
            
            
            
            
            
            
            
            doc.close();
            Desktop.getDesktop().open(new File ("c:/pdf/Resultat.pdf"));
            }  
   
   
 
   
   
   
   
   
   
   
   
   
   
   
   
    
}
   
   
   
   
   
   
   
   
    

