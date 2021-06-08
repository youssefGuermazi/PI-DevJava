package pi.devjava.controller.Utilis;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Baha Dammak
 */
public class ConnexionDB {
    
    String url="jdbc:mysql://localhost:3306/learn and play?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ;
    String login="root";
    String pwd="";
   
    static Connection myConnex;
    static ConnexionDB myconnBD;
    
    private ConnexionDB() {    
        try {
          myConnex = DriverManager
                  .getConnection(url, login, pwd);

          System.out.println("connexion etablie");
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      }
    }
    
    public static ConnexionDB getInstance() {
        
        if(myconnBD == null)
         myconnBD =   new ConnexionDB();
           
        return myconnBD;
    }
    
    
    public  Connection getConnection(){
        
       
            return myConnex;
    }
    
    
}
