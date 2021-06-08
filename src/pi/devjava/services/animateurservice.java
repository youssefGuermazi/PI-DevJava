/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pi.devjava.services;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import pi.devjava.controller.Utilis.ConnexionDB;
import pi.devjava.entities.animateur;
/**
 *
 * @author user
 */
public class animateurservice {
    
        Connection  myConnex;
       Statement ste;
    
    public animateurservice() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public void ajouteranimateur(animateur p) {
                 try {
              String req =
                      "INSERT INTO animateur"
                      + "(cin,nom,prenom,activiter,nomfile,rate,vote) VALUES "
                      + "(?,?,?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
             ps.setInt(1, p.getCin());
             ps.setString(2, p.getNom());
             ps.setString(3, p.getPrenom());
             ps.setString(4, p.getActiviter());
             ps.setString(5, p.getNomfile());
             ps.setDouble(6, p.getRate());
             ps.setInt(7, p.getVote());
             
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public void modifieranimateur(animateur p,int id) {
        try {
            String req = "update animateur set cin=?,nom=?,prenom=?,activiter=?,nomfile=? where cin=?";
            PreparedStatement ps = myConnex.prepareStatement(req);
             ps.setInt(6,id);
            ps.setString(2, p.getNom());
             ps.setString(3, p.getPrenom());
             ps.setString(4, p.getActiviter());
             ps.setString(5, p.getNomfile());
              ps.setInt(1, p.getCin());
             
               ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
     public void supprimeranimateur(int cin) {
                  try {
                      String req1 = "delete from animateur_formation where idAnimateur =?";
            PreparedStatement ps1 = myConnex.prepareStatement(req1);
            ps1.setInt(1, cin);
            ps1.executeUpdate();
            
            String req = "delete from animateur where cin =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, cin);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     public ResultSet afficheranimateur() {
                  try {
              String req3 =
                      "select * from animateur";
            PreparedStatement ps = myConnex.prepareStatement(req3);
          
            ResultSet res = ps.executeQuery();
                        return res; 

        
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
          
            return null; 
    }
         public ArrayList<animateur> afficherevent1() {
          ArrayList<animateur> psr = new ArrayList<>();
                  try {
              String req3;
                      req3 = "select * from animateur ";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                    
                      ResultSet res =   ps.executeQuery();
              
              while (res.next()) {
                                 animateur p = new animateur();
                 p.setCin(res.getInt("cin"));
                p.setNom(res.getString("nom"));
                p.setPrenom(res.getString("prenom"));
                p.setActiviter(res.getString("activiter"));
                p.setNomfile(res.getString("nomfile"));
                
               
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
            return psr;
    }
}

