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
import pi.devjava.entities.Utilisateur;
import sample.Main;

/**
 *
 * @author youssef
 */
public class userService {
       static Connection connexion;
    private Statement stmt = null;
  private static userService orderServiceInstance;

    public userService() {
                connexion = DataBase.getInstance().getConnexion();

    }
     public static userService getInstance() {   //Singleton Design Pattern
        if (orderServiceInstance==null)
        {
            orderServiceInstance = new userService();
        }
        return orderServiceInstance ;  
//        return new ShopService();
    }

    public static List<Utilisateur> getAllUsers()  {
       List<Utilisateur> user = new ArrayList<>();
        try {

            String req = "SELECT * FROM user  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                String rl;
                if (result.getString("roles").equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}"))
               rl = "Parent"; 
            else 
            if (result.getString("roles").equals("a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}"))
               rl = "Responsable";
            
            else rl = "Admin";
                Utilisateur u = new Utilisateur(result.getString("email"),result.getString("username"),rl,result.getString("password"));
                user.add(u);
            }
             /*   Club t = new Club( result.getString("nom"), result.getString("description"),result.getDate("date_creation"));
                Clubs.add(t);
            }*/
        } catch (SQLException ex) {
            Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(user);
        return user;
        


    }
    
     public Utilisateur findById(Integer r) {
        Utilisateur user = null;
        String req = "select * from user where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setInt(1, r);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Utilisateur(
                        
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("role"),
                        resultSet.getString("password"));
                     
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
     
        public Utilisateur findByname(String s) {
        Utilisateur user = null;
        String req = "select * from user where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Utilisateur(
                        
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("role"),
                        resultSet.getString("password"));
                     
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
         public boolean searchUserByEmail(String mail)
     {
         
         try {
        Statement ste = connexion.createStatement();

         String req = "select * from user where email='"+mail+"'";
         ResultSet rs =ste.executeQuery(req);
           while (rs.next()) {
              return true;
           }
            } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return  false;
     }
    


    public boolean addUser(Utilisateur u ) throws SQLException {
try {
    
            PreparedStatement statement = connexion.prepareStatement("INSERT INTO user (email,email_canonical,username,username_canonical,roles,password,enabled) VALUES (?,?,?,?,?,?,'"+1+"');");
            statement.setString(1, u.getMail());
            statement.setString(2, u.getMail());
            statement.setString(3, u.getUsername());
            statement.setString(4, u.getUsername());
            statement.setString(5, u.getRole());
            statement.setString(6, u.getPassword());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Utilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    
    }

public void delete(Utilisateur u){
            try {
                PreparedStatement ps = connexion.prepareStatement("delete from user where  username=? " );
                ps.setString(1,u.getUsername());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(userService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

 public void changepassword(String s1, String m) {
        try {
            String requete = "update user set password=? where email=?";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setString(1, s1);
            pst.setString(2, m);
            pst.executeUpdate();
            System.out.println("Modification effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
 
 public Boolean checkpw(String pword, String uname) {
        String s1 = "";
        String req = "Select password from user where username= ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString(1);
               // BCrypt.checkpw(mdp, o.getPassword());
                
                if (BCrypt.checkpw(s1,pword)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }
 
  public boolean findByLogin(String s) {
        Utilisateur user = null;
        String req = "select * from user where username =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Utilisateur(
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("roles"),
                        resultSet.getString("password")
                        );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
  
     public String Gettype(String s) {
        String s1 = "";
        String req = "select roles from user where username =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString("roles");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s1;
    }
  
 public Utilisateur getInfoUser(String cli)
     {
         Utilisateur c = new Utilisateur();
         try {
        Statement ste = connexion.createStatement();
         String req = "select  username , email, id from user where username='"+cli+"'";
         ResultSet rs =ste.executeQuery(req);
           while (rs.next()) {
               String username = rs.getString(1);
               String email = rs.getString(2);
               Integer id = rs.getInt(3);
               
                c = new Utilisateur(email,username,id);
              
           }
            } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
       
       
     }
 
 public void changenom(String s1, int i) {
        try {
            String requete = "update user set username=?,username_canonical=? where id=?";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setString(1, s1);
            pst.setString(2, s1);
            pst.setInt(3, i);
            pst.executeUpdate();
            System.out.println("Modification nom effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
  public void changemail(String s2, int i) {
        try {
            String requete = "update user set email=?,email_canonical=? where id=?";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setString(1, s2);
            pst.setString(2, s2);
            pst.setInt(3, i);
            pst.executeUpdate();
            System.out.println("Modification mail effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}