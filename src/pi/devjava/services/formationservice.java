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
import pi.devjava.entities.formation;
/**
 *
 * @author user
 */
public class formationservice {
    Connection  myConnex;
       Statement ste;
    
    public formationservice() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public void ajouterformation(formation f) {
                 try {
              String req =
                      "INSERT INTO formation"
                      + "(id,dated,datef,titre,description) VALUES "
                      + "(?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, f.getId());
            ps.setString(2,f.getDated());
             ps.setString(3, f.getDatef());
             ps.setString(4, f.getTitre());
             ps.setString(5, f.getDescription());
            
             
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    public void modifierformation(formation f) {
        try {
            String req = "update formation set dated=?,datef=?,titre=?,description=? where id=?";
            PreparedStatement ps = myConnex.prepareStatement(req);
             
            ps.setString(1,f.getDated());
             ps.setString(2, f.getDatef());
             ps.setString(3, f.getTitre());
             ps.setString(4, f.getDescription());
            
              ps.setInt(5, f.getId());
             
               ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
     public void supprimerformation(int id) {
                  try {
            String req = "delete from formation where id =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     public ResultSet afficherformation() {
       
                  try {
              String req3 =
                      "select * from formation";
            PreparedStatement ps = myConnex.prepareStatement(req3);
          
            ResultSet res = ps.executeQuery();
return res; 
          } catch (SQLException ex) {
              Logger.getLogger(formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
          
            return null; 
    }
}
