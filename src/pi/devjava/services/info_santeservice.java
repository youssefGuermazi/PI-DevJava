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
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pi.devjava.controller.Utilis.ConnexionDB;
import pi.devjava.entities.info_sante;

/**
 *
 * @author ASUS
 */
public class info_santeservice {

       Connection  myConnex;
       Statement ste;
       private ResultSet rs ;

    public info_santeservice()  {

              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();


    }

       public void ajouterinfo(info_sante s) {
                 try {
              String req =
                      "INSERT INTO info_sante"
                      + "(info,datevaccin,id_medecin) VALUES "
                      + "(?,?,?)";
                                      ste = myConnex.createStatement();
                    PreparedStatement ps = myConnex.prepareStatement(req);
             ps.setString(1,s.getInfo() );
             ps.setString(2,s.getDatevaccin() );
             ps.setString(3,s.getMed().getCin());
             ps.executeUpdate();


          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }


     public void modifierinfo(int id , String date , String info) {
        try {
            String req = "update info_sante set datevaccin='"+date+"',info='"+info+"' where id="+id;
                          ste = myConnex.createStatement();
            PreparedStatement ps = myConnex.prepareStatement(req);

            System.out.println("Sante Modifier");
               ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    public void supprimerinfo(int id) {
                  try {
            String req = "delete from info_sante where id =?";
                          ste = myConnex.createStatement();
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();

          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }
    }




      public ArrayList afficherinfo() {
        ArrayList<info_sante> psr = new ArrayList<>();
        medecin m ;
                  try {
              String req3 =
                      "select * from info_sante s INNER JOIN medecin m on s.id_medecin = m.cin ";
                            ste = myConnex.createStatement();

            PreparedStatement ps = myConnex.prepareStatement(req3);

            ResultSet res = ps.executeQuery();
            while (res.next()) {
                
                m = new medecin(res.getString("id_medecin"),res.getString("nom"),res.getString("prenom"),res.getString("specialite"),res.getString("nomfile"));
                psr.add(new info_sante(res.getInt("id"),res.getString("info"), res.getString("datevaccin"), m));


            }
          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }

            return psr;
    }

      public List<String> get_id_med()
      {
              List<String> psr = new ArrayList<>();
        medecin m ;
                  try {
              String req3 =
                      "select cin from medecin ";
                            ste = myConnex.createStatement();

            PreparedStatement ps = myConnex.prepareStatement(req3);

            ResultSet res = ps.executeQuery();
            while (res.next()) {
                psr.add(res.getString(1));

            }
          } catch (SQLException ex) {
              Logger.getLogger(info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
          }

            return psr;
      }
           public medecin recherchem (String id) throws SQLException
     {   String req = "SELECT * from medecin where cin= "+id ;
           medecin m = null ;
                 ste = myConnex.createStatement();
                 rs=ste.executeQuery(req);

                while(rs.next())
                {

                 m=new medecin(rs.getString(1));
                }
         return  m;
     }



 public info_sante getByInfo(String info) {
         info_sante  a = null;
         String requete = " select* from info_sante  where info='"+info+"'" ;
        try {
           
            ste = myConnex.createStatement();
            rs = ste.executeQuery(requete);
            if (rs.next())
            {
               a=new info_sante(rs.getInt(1),rs.getString(2),rs.getString(3));} 
        } catch (SQLException ex) {
            Logger.getLogger( info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }

public info_sante getDate(String datevaccin) {
         info_sante  a = null;
         String requete = " select* from info_sante  where datevaccin='"+datevaccin+"'" ;
        try {
           
            ste = myConnex.createStatement();
            rs = ste.executeQuery(requete);
            if (rs.next())
            {
               a=new info_sante(rs.getInt(1),rs.getString(2),rs.getString(3));} 
        } catch (SQLException ex) {
            Logger.getLogger( info_santeservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }



}
