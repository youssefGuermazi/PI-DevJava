/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Garderie;
//import pi.devjava.entities.Utilisateur;

/**
 *
 * @author youssef
 */
public class GarderieService {
    static Connection connexion;
    private Statement stmt = null;
  private static GarderieService orderServiceInstance;

    public GarderieService() {
                connexion = DataBase.getInstance().getConnexion();

    }
     public static GarderieService getInstance() {   //Singleton Design Pattern
        if (orderServiceInstance==null)
        {
            orderServiceInstance = new GarderieService();
        }
        return orderServiceInstance ;  
//        return new ShopService();
    }

    public static List<Garderie> getAllGards()  {
       List<Garderie> gard= new ArrayList<>();
        try {

            String req = "SELECT * FROM garderie  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
               
                Garderie g = new Garderie(result.getString("numGard"),result.getString("nom"),result.getString("adresse"),result.getString("telephone"),result.getString("image"));
                gard.add(g);
            }
             /*   Club t = new Club( result.getString("nom"), result.getString("description"),result.getDate("date_creation"));
                Clubs.add(t);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(GarderieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("a");
        System.out.println(gard);
        System.out.println("b");
        return gard;
        


    }
      public ArrayList afficherGarderie() {
                 ArrayList<Garderie> gard= new ArrayList<>();
        try {

            String req = "SELECT * FROM garderie  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
               
                Garderie g = new Garderie(result.getString("numGard"),result.getString("nom"),result.getString("adresse"),result.getString("telephone"),result.getString("image"));
                gard.add(g);
            }
             /*   Club t = new Club( result.getString("nom"), result.getString("description"),result.getDate("date_creation"));
                Clubs.add(t);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(GarderieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("a");
        System.out.println(gard);
        System.out.println("b");
        return gard;
        


    }
    


    public boolean addGarderie(Garderie g ) throws SQLException {
try {
    
            PreparedStatement statement = connexion.prepareStatement("INSERT INTO garderie(numGard,nom,adresse,telephone,resp_id,image) VALUES (?,?,?,?,?,?);");
            statement.setString(1, g.getNumGard());
            statement.setString(2, g.getNom());
            statement.setString(3, g.getAdresse());
            statement.setString(4, g.getTelephone());
            statement.setInt(5, g.getResp_id());
           statement.setString(6, g.getImage());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Garderie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    
    }

public void delete(Garderie g){
            try {
                PreparedStatement ps = connexion.prepareStatement("delete from garderie where  numGard=? " );
                ps.setString(1,g.getNumGard());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(GarderieService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
public void ModifierC(Garderie g){
        
      
        
        try {
            PreparedStatement statement = connexion.prepareStatement("update garderie set numGard=?,nom=?,adresse=?,telephone=? where numGard=? ");
            
       
            statement.setString(1, g.getNumGard());
            statement.setString(2, g.getNom());
            statement.setString(3, g.getAdresse());
            statement.setString(4, g.getTelephone());
            //statement.setString(5, g.getResp_id());
          // statement.setString(5, g.getImage());
           statement.setString(5, g.getNumGard());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GarderieService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
    

