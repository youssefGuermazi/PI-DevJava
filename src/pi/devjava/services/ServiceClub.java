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
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Club;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class ServiceClub {

    static Connection connexion;
    private Statement stmt = null;
    private static ServiceClub orderServiceInstance;

    public ServiceClub() {
        connexion = DataBase.getInstance().getConnexion();

    }

    public static ServiceClub getInstance() {   //Singleton Design Pattern
        if (orderServiceInstance == null) {
            orderServiceInstance = new ServiceClub();
        }
        return orderServiceInstance;
//        return new ShopService();
    }

    public static List<Club> getAllClubs() {
        List<Club> Clubs = new ArrayList<>();
        try {

            String req = "SELECT * FROM `club`  ";
            Statement stm = connexion.createStatement();
            ResultSet result = stm.executeQuery(req);

            while (result.next()) {
                Club t = new Club(result.getString("nom"), result.getString("description"), result.getDate("date_creation"));
                Clubs.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Clubs);
        return Clubs;

    }

    public boolean addClub(Club t) throws SQLException {
        try {
            PreparedStatement statement = connexion.prepareStatement(
                    "INSERT INTO club (nom ,description ,date_creation ) values (?,?,?)");
            statement.setString(1, t.getClubName());
            statement.setString(2, t.getDescription());
            statement.setDate(3, t.getCreationDate());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void delete(Club t) {
        try {
            PreparedStatement ps = connexion.prepareStatement("delete from club where  nom=? ");
            ps.setString(1, t.getClubName());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModifierC(Club C) {

        try {
            PreparedStatement pt = connexion.prepareStatement("update club set nom=?,description=?,date_creation=? where nom= ?   ");

            pt.setString(1, C.getClubName());
            pt.setString(2, C.getDescription());
            pt.setDate(3, (Date) C.getCreationDate());
            pt.setString(4, C.getClubName());

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
