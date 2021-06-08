/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pi.devjava.controller.Utilis.ConnexionDB;
import pi.devjava.entities.animateur;
import pi.devjava.entities.animateur_formation;


/**
 *
 * @author user
 */
public class animateur_formationservice {
            Connection  myConnex;
       Statement ste;
    
    public  animateur_formationservice() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(animateurservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
    public void ajouteranimateur(animateur_formation p) {
                 try {
              String req =
                      "INSERT INTO animateur_formation"
                      + "(id,idformateur,idAnimateur) VALUES "
                      + "(?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.setInt(3, p.getIdAnimateur());
              ps.setInt(2,p.getIdFormateur());
            
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     public ResultSet listformation1(int id1) {
         
                  try {
              String req3;
                      req3 = "select animateur_formation.idformateur,formation.* from animateur_formation INNER JOIN formation ON animateur_formation.idformateur=formation.id where animateur_formation.idAnimateur = ?";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                    ps.setInt(1, id1);
                      ResultSet res =   ps.executeQuery();
              
             
              return res ;
             
          } catch (SQLException ex) {
              Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }
     
      public ArrayList<animateur> listanimateur(int id1) {
          ArrayList<animateur> psr = new ArrayList<>();
                  try {
              String req3;
                      req3 = "select animateur_formation.idAnimateur,animateur.* from animateur_formation INNER JOIN animateur ON animateur_formation.idAnimateur=animateur.cin where animateur_formation.idformateur = ?";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                    ps.setInt(1, id1);
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
              Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
            return psr;
    }
      
       public boolean RechercherVol(int id1 , int id2)
{
        try {
            String requete="select * from animateur_formation where animateur_formation.idformateur=? and animateur_formation.idAnimateur=?";
              PreparedStatement ps = myConnex.prepareStatement(requete);
            ps.setInt(1, id1);
            ps.setInt(2, id2);
         
           
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return false;
           
        } catch (SQLException ex) {
            Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
}
       
              public boolean RechercherVol1(int id1)
{
        try {
            String requete="select * from animateur_formation where animateur_formation.idAnimateur=? ";
              PreparedStatement ps = myConnex.prepareStatement(requete);
            ps.setInt(1, id1);
          
         
           
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
}
       
     public boolean supprimeranim(int id1 , int id2)
{
        try {
            String requete="delete from animateur_formation where idformateur=? and idAnimateur=?";
              PreparedStatement ps = myConnex.prepareStatement(requete);
            ps.setInt(1, id1);
            ps.setInt(2, id2);
           ps.executeUpdate();
          
           
          
           
        } catch (SQLException ex) {
            Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
}
     public String listformation2(int id1) {
          String b="" ;
                  try {
              String req3;
                      req3 = "select animateur_formation.idformateur,formation.* from animateur_formation INNER JOIN formation ON animateur_formation.idformateur=formation.id where animateur_formation.idAnimateur = ?";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                    ps.setInt(1, id1);
                      ResultSet res =   ps.executeQuery();
              
             
              while (res.next()) {
               
                b=res.getString("titre");
              }
             
          } catch (SQLException ex) {
              Logger.getLogger(animateur_formationservice.class.getName()).log(Level.SEVERE, null, ex);
          }
            return b;
    }
      
      
      
      
   
}
