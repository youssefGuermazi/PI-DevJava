/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pi.devjava.connectionBD.DataBase;

/**
 *
 * @author kamikaz
 */
public class Suivi {
    int id,enfant_id,note_francais,note_anglais,note_info;
    String evaluation;
    Date date;

    public Suivi(int id, int enfant_id, int note_francais, int note_anglais, int note_info, String evaluation, Date date) {
        this.id = id;
        this.enfant_id = enfant_id;
        this.note_francais = note_francais;
        this.note_anglais = note_anglais;
        this.note_info = note_info;
        this.evaluation = evaluation;
        this.date = date;
    }

    public Suivi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnfant_id() {
        return enfant_id;
    }

    public void setEnfant_id(int enfant_id) {
        this.enfant_id = enfant_id;
    }

    public int getNote_francais() {
        return note_francais;
    }

    public void setNote_francais(int note_francais) {
        this.note_francais = note_francais;
    }

    public int getNote_anglais() {
        return note_anglais;
    }

    public void setNote_anglais(int note_anglais) {
        this.note_anglais = note_anglais;
    }

    public int getNote_info() {
        return note_info;
    }

    public void setNote_info(int note_info) {
        this.note_info = note_info;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
     public static List findSuivi( int id) {
        Suivi s = null;
        List ListS =new ArrayList();
        String req = "select * from suivi where enfant_id= ?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = DataBase.getInstance().getConnection().prepareStatement(req);
            preparedStatement.setInt(1, id);
            //preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                   System.out.println("pi.devjava.entities.Suivi.findSuivi()");
                s = new Suivi(resultSet.getInt("id"),resultSet.getInt("enfant_id"),resultSet.getInt("note_francais"),resultSet.getInt("note_anglais"),resultSet.getInt("note_info"),resultSet.getString("evaluation"),resultSet.getDate("date"));
                   
                ListS.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ListS;
    }
       public static void ajoutSuivi(int enfant_id,int note_francais, int note_anglais, int note_info, String evaluation ,LocalDate dn){
     try {
            String reqC = "INSERT INTO suivi (enfant_id,note_francais,note_anglais,note_info,evaluation,date) VALUES(?,?,?,?,?,?)";
            PreparedStatement steC = DataBase.getInstance().getConnection().prepareStatement(reqC);
            
            steC.setInt(1, enfant_id);
            steC.setInt(2, note_francais);
            steC.setInt(3, note_anglais);
            steC.setInt(4,note_info);
            steC.setString(5,evaluation);
            steC.setDate(6,java.sql.Date.valueOf( dn ));
            
            steC.executeUpdate();//exécution
            System.out.println("suivi inserted");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
       public static void supprimerSuivi(int id){
        String req = "delete from suivi where id=?";
        PreparedStatement preparedStatement;
        
        try {
            PreparedStatement steC= DataBase.getInstance().getConnection().prepareStatement(req);
            
            steC.setInt(1, id);
            steC.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       public static void ModifierSuivi(int id,int enfant_id,int note_francais, int note_anglais, int note_info, String evaluation ,LocalDate dn){
     try {
            String reqC = "update suivi set enfant_id=?,note_francais=?,note_anglais= ?,note_info=?,date=?,evaluation=? where id=?";
            PreparedStatement steC = DataBase.getInstance().getConnection().prepareStatement(reqC);
            steC.setInt(1, enfant_id);
            steC.setInt(2, note_francais);
            steC.setInt(3, note_anglais);
            steC.setInt(4,note_info);
            steC.setString(6,evaluation);
            steC.setDate(5,java.sql.Date.valueOf( dn ));
            steC.setInt(7, id);
            steC.executeUpdate();//exécution
            System.out.println("Suivi modifier");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
       
}
