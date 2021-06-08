/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import static pi.devjava.PIDevJava.bdd;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.services.userService;

/**
 *
 * @author kamikaz
 */
public class Enfant {
    int id,parent;
    String nom,prenom,nomfile,garderie_id;
    Date dn;

    public String getGarderie_id() {
        return garderie_id;
    }

    public void setGarderie_id(String garderie_id) {
        this.garderie_id = garderie_id;
    }

  

    public Enfant(int id, int parent, String nom, String prenom, String nomfile, String garderie_id, Date dn) {
        this.id = id;
        this.parent = parent;
        this.nom = nom;
        this.prenom = prenom;
        this.nomfile = nomfile;
        this.garderie_id = garderie_id;
        this.dn = dn;
    }
    

    public Enfant() {
    }
    public Enfant(EnfantAffichage Ea) {
        this.id = Ea.id;
        this.garderie_id = Ea.garderie_id;
        this.parent = Ea.parent;
        this.nom = Ea.nom;
        this.prenom = Ea.prenom;
        this.nomfile = Ea.nomfile;
        this.dn =Ea.dn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomfile() {
        return nomfile;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    public Date getDn() {
        return dn;
    }

    public void setDn(Date dn) {
        this.dn = dn;
    }
    public static void ajoutEnfant(String nom,String prenom,String garderie,int parent,String nomfile,LocalDate dn){
     try {
            String reqC = "INSERT INTO enfant (nom,prenom,nomfile,parent,garderie_id,dn) VALUES(?,?,?,?,?,?)";
            PreparedStatement steC = DataBase.getInstance().getConnection().prepareStatement(reqC);
            steC.setString(1, nom);
            steC.setString(2, prenom);
            steC.setInt(4, parent);
            steC.setString(3, nomfile);
            steC.setString(5,garderie);
            steC.setDate(6,java.sql.Date.valueOf( dn ));
            steC.executeUpdate();//exécution
            System.out.println("enfant inserted");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static List findAllGaderie() {
        Garderie g = null;
        List ListG =new ArrayList();
        String req = "select * from Garderie";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
 g = new Garderie(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getString(6));
                if(g.telephone != null){
                   
                ListG.add(g.getNumGard());}
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListG;
    }
    public static String findGaderie(String id) {
        Garderie g = null;
        List ListG =new ArrayList();
        String req = "select * from Garderie where id=?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                g = new Garderie(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6));
                if(g.telephone != null){
                   
                ListG.add(g.getNumGard());}
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return g.getNom();
    }
    public static String findUser(int id) {
        Garderie g = null;
        List ListG =new ArrayList();
        String req = "select email from user where id=?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             return   resultSet.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return " ";
    }
    public static List findEnfant() {
        Enfant e = null;
        List ListE =new ArrayList();
        userService us = new userService();
        int ident;
        ident=us.getInfoUser(Utilisateur.uName).getId();
        String req = "select * from enfant where parent="+ident+"";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //(int id, int parent, String nom, String prenom, String nomfile, String garderie_id, Date dn)
              e = new Enfant(resultSet.getInt(1),resultSet.getInt(5),resultSet.getString(2),resultSet.getString(3),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(4));
                 
                ListE.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListE;
    }
    public static List findEnfantByGarderie() {
        Enfant e = null;
        List ListE =new ArrayList();
        userService us = new userService();
        int ident;
        ident=us.getInfoUser(Utilisateur.uName).getId();
        int idg;
        idg=bdd.getInt("SELECT numGard FROM garderie WHERE resp_id="+ident+";",1);
        String req = "select * from enfant where garderie_id="+idg+"";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //(int id, int parent, String nom, String prenom, String nomfile, String garderie_id, Date dn)
              e = new Enfant(resultSet.getInt(1),resultSet.getInt(5),resultSet.getString(2),resultSet.getString(3),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(4));
                 
                ListE.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListE;
    }
    public static List findEnfant(int id) {
        Enfant e = null;
        List ListE =new ArrayList();
        String req = "select * from enfant where parent = ?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                e = new Enfant(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(6),resultSet.getString(3),resultSet.getString(4),resultSet.getString(7),resultSet.getDate(5));
                   
                ListE.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListE;
    }
    
      public static List findEnfantByGard(int id) {
        Enfant e = null;
        List ListE =new ArrayList();
        String req = "select * from enfant where garderie_id = ?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                e = new Enfant(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(6),resultSet.getString(3),resultSet.getString(4),resultSet.getString(7),resultSet.getDate(5));
                   
                ListE.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListE;
    }
    public static void supprimerEnfant(int id){
        String req = "delete from enfant where id=?";
        PreparedStatement preparedStatement;
        
        try {
            PreparedStatement steC= DataBase.getInstance().getConnection().prepareStatement(req);
            
            steC.setInt(1, id);
            steC.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public static void ModifierEnfant(String nom,String prenom,String garderie,int parent,LocalDate dn, int id){
     try {
            String reqC = "update enfant set nom=?,prenom=?,garderie_id=?,dn=? where id=?";
            PreparedStatement steC = DataBase.getInstance().getConnection().prepareStatement(reqC);
            steC.setString(1, nom);
            steC.setString(2, prenom);
            steC.setInt(5,id);
            
            steC.setString(3,garderie);
            steC.setDate(4,java.sql.Date.valueOf( dn ));
            steC.executeUpdate();//exécution
            System.out.println("enfant modifier");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
     public static void saveToFile(Image image,String name) {
         File outputFile = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
         BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", garderie_id=" + garderie_id + ", parent=" + parent + ", nom=" + nom + ", prenom=" + prenom + ", nomfile=" + nomfile + ", dn=" + dn + '}';
    }
     
}
