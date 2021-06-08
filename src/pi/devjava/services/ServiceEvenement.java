/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Club;
import pi.devjava.entities.Evenement;
import pi.devjava.entities.EvenementUser;
import pi.devjava.entities.Utilisateur;

/**
 *
 * @author LENOVO
 */
public class ServiceEvenement {

    static Connection connexion;
    private Statement stmt = null;
    private static ServiceEvenement orderServiceInstance;

    public ServiceEvenement() {
        connexion = DataBase.getInstance().getConnexion();

    }

    public static ServiceEvenement getInstance() {   //Singleton Design Pattern
        if (orderServiceInstance == null) {
            orderServiceInstance = new ServiceEvenement();
        }
        return orderServiceInstance;
//        return new ShopService();
    }

    public static List<Evenement> getAllEvenement() {
        List<Evenement> Evenements = new ArrayList<>();
        try {

            String req = "SELECT * FROM `evenement`  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                Evenement e = new Evenement(result.getInt("id"), result.getString("club_evenement"), result.getString("nom"), result.getString("description"), result.getDate("date_debut"), result.getDate("date_fin"), result.getString("nomfile"), result.getInt("nbp"));
                Evenements.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Evenements);
        return Evenements;

    }
     public ArrayList afficherEvent() {
          ArrayList<Evenement> Evenements = new ArrayList<>();
        try {

            String req = "SELECT * FROM `evenement`  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                Evenement e = new Evenement(result.getInt("id"), result.getString("club_evenement"), result.getString("nom"), result.getString("description"), result.getDate("date_debut"), result.getDate("date_fin"), result.getString("nomfile"), result.getInt("nbp"));
                Evenements.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Evenements);
        return Evenements;
         
     }
    
        public static  Utilisateur getUserById(int id) {
        Utilisateur user = null;
        try {

            String req = "SELECT email,username,roles,password FROM `user` where id ="+id;
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
              user = new Utilisateur(result.getString(1),result.getString(2),result.getString(3),result.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user);
        return user;

    }

    public boolean addEvenement(Evenement e) throws SQLException {
        try {
            PreparedStatement statement = connexion.prepareStatement(
                    "INSERT INTO evenement (club_evenement,nom ,description ,date_debut,date_fin,nomfile,nbp ) values (?,?,?,?,?,?,? )");
            statement.setString(1, e.getClub_evenement());
            statement.setString(2, e.getEventName());
            statement.setString(3, e.getDescription());

            statement.setDate(4, e.getDateDebut());
            statement.setDate(5, e.getDateFin());
            statement.setString(6, e.getNomfile());
            statement.setInt(7, 20);

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public void delete(Evenement e) {
        try {
            PreparedStatement ps = connexion.prepareStatement("delete from evenement where  id=? ");
            ps.setInt(1, e.getEventId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModifierC(Evenement C) {

        try {
            PreparedStatement pt = connexion.prepareStatement("update evenement set club_evenement=?,nom=?,description=?,date_debut=?, date_fin=?,nomfile=?   where id= ?   ");

            pt.setInt(7, C.getEventId());
            pt.setString(1, C.getClub_evenement());
            pt.setString(2, C.getEventName());
            pt.setString(3, C.getDescription());
            pt.setDate(4, (Date) C.getDateDebut());
            pt.setDate(5, (Date) C.getDateFin());
            pt.setString(6, C.getNomfile());
                 
            pt.executeUpdate();
           

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static List findAllClubs() {
        Club g = null;
        List ListG =new ArrayList();
        String req = "select * from club";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnexion().prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                g = new Club(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3));
                if(g.nom != null){
                   
                ListG.add(g.nom);}
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListG;
    }
    public void ParticiperE(Evenement e, int i) {
       try {
            PreparedStatement ps = connexion.prepareStatement("update evenement set nbp=? where  id=? ");
            ps.setInt(1, e.getNbp()-1);
            ps.setInt(2 , e.getEventId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            PreparedStatement statement = connexion.prepareStatement(
                    "INSERT INTO evenement_user (evenement_id,user_id ) values (?,?)");
            statement.setInt(1, e.getEventId());
            statement.setInt(2, i);
            
         statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public void AnnulerE(Evenement e, int i) {
       try {
            PreparedStatement ps = connexion.prepareStatement("update evenement set nbp=? where  id=? ");
            ps.setInt(1, e.getNbp()+1);
            ps.setInt(2 , e.getEventId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {
            PreparedStatement statement = connexion.prepareStatement(
                    "delete from evenement_user where evenement_id=?");
            statement.setInt(1, e.getEventId());
            
            
         statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public static List<EvenementUser> getAllEvenementUser() {  //liste de l'entité evenement user
        List<EvenementUser> EvenementUsers = new ArrayList<>(); 
        try {

            String req = "SELECT * FROM `evenement_user`  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                EvenementUser eu = new EvenementUser(result.getInt("evenement_id"), result.getInt("user_id"));
                EvenementUsers.add(eu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(EvenementUsers);
        return EvenementUsers;

    }
     
     public static List<Utilisateur> getUsersForEvent(int id_event){
         List<Utilisateur> users = new ArrayList<>();
         try{
             PreparedStatement statement = connexion.prepareStatement(" select user_id from `evenement_user` where evenement_id = ?");
             statement.setInt(1, id_event);
             ResultSet listIdUserInEvent = statement.executeQuery();
             while (listIdUserInEvent.next()) {
                 users.add(getUserById(listIdUserInEvent.getInt(1)));
             }
             
             
         }catch(SQLException ex){
              Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
             
             
             
             
         }
         
         return users;
     }
     public static List findAllUsers() {
        Utilisateur g = null;
        List ListG =new ArrayList();
        String req = "SELECT email,username,roles,password FROM user";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnexion().prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                g = new Utilisateur(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
             
                
                if(g.getRole().indexOf("PA")>0){
                 
                ListG.add(g.getMail());
                
                }
                 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListG;
    }
}
