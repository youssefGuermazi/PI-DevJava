/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import com.jfoenix.controls.JFXTextField;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.swing.JOptionPane;
import static pi.devjava.PIDevJava.bdd;




/**
 *
 * @author youssef
 */
public class Utilisateur {

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    public Utilisateur(String username) {
        this.username = username;
    }




    private String mail,username,role,password;
    private int id;
        public static String uName;

    //public static DataBase bdd = new DataBase("localhost:3306", "portdepechesfax", "root", "");

    public Utilisateur(String mail, String username, String role, String password) {
        this.mail = mail;
        this.username = username;
        this.role = role;
        this.password = password;
        
       
    
    /*
  
        if(bdd.isInBdd("SELECT * FROM  user WHERE username ='"+username+"';"))
            {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("username existe déja");
           a1.show();
         }          
        
            else if (bdd.isInBdd("SELECT * FROM  user WHERE email ='"+mail+"';")){
            {  
           Alert a1=new Alert(Alert.AlertType.ERROR);
           a1.setTitle(null);
           a1.setHeaderText("mail existe déja");
        a1.show();
         }          
        }
               else{
            bdd.sendRequest("INSERT INTO user (email,email_canonical,username,username_canonical,roles,password,enabled) VALUES ('"+mail+"','"+mail+"', '"+username+"', '"+username+"','"+role+"','"+password+"','"+1+"');");
            {  
           Alert a1=new Alert(Alert.AlertType.INFORMATION);
           a1.setTitle(null);
           a1.setHeaderText("ajouté avec succès");
              
        a1.show();
         
         }
         }          
        */
        
    }
    
   /* public Utilisateur(String username){
          bdd.connection();
        this.id= bdd.getInt("SELECT * FROM user WHERE username ='"+username+"';",1);
        System.out.println(id);
        
    }*/

    public Utilisateur(String mail, String username, int id) {
        this.mail = mail;
        this.username = username;
        this.id = id;
    }

   

    public Utilisateur(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Utilisateur() {
            this.id= bdd.getInt("SELECT * FROM user WHERE username ='"+username+"';",1);
    }

    public Utilisateur(String mail, String username) {
        this.mail = mail;
        this.username = username;
    }



   

    

    public String getMail() {
        return mail;// = bdd.getString("SELECT * FROM user WHERE id="+id+";",4);
    }

    public void setMail(String mail) {
        this.mail = mail;
       // bdd.sendRequest("UPDATE user SET email='"+mail+"' WHERE id="+id+";");
    }

    public String getRole() {
    return role;// = bdd.getString("SELECT * FROM user WHERE id="+id+";",12);

    }

    public void setRole(String role) {
    this.role = role;
           // bdd.sendRequest("UPDATE user SET roles='"+role+"' WHERE id="+id+";");


    }

    public String getPassword() {
        return password;//= bdd.getString("SELECT * FROM user WHERE id="+id+";",8);
    }

    public void setPassword(String password) {
        this.password = password;
               // bdd.sendRequest("UPDATE user SET password='"+password+"' WHERE id="+id+";");

    }

    public String getUsername() {
        return username;// = bdd.getString("SELECT * FROM user WHERE id="+id+";",2);
    }

    public void setUsername(String username) {
        this.username = username;
               // bdd.sendRequest("UPDATE user SET username='"+username+"' WHERE id="+id+";");
               
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return "Utilisateur{" + "mail=" + mail + ", username=" + username + ", role=" + role + ", password=" + password + ", id=" + id + '}';
    }
    

    
}

    


