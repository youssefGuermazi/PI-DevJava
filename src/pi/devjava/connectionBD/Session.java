/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.connectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.userService;


/**
 *
 * @author Nourelhouda
 */
public class Session {

    //private static Session instance ; 
    public static Utilisateur LoggedUser;
    public static userService iuserService = new userService();
    
    public Session() {
    }

    public Utilisateur getLoggedUser() {
        return LoggedUser;
    }

    public void setLoggedUser(Utilisateur LoggedUser) {
        this.LoggedUser = LoggedUser;
    }

    /**
     *
     * @param login
     * @return
     * @throws SQLException
     */
    public Utilisateur SetLoggedUser(String login) throws SQLException {
        Connection connection;
        connection = DataBase.getInstance().getConnexion();


        String sql = "SELECT * FROM user WHERE username=? ";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, login);

        ResultSet resultSet = pst.executeQuery();
        if (resultSet.next()) {
            LoggedUser = new Utilisateur();
            LoggedUser.setId(resultSet.getInt("id"));

            LoggedUser = iuserService.findById(LoggedUser.getId());
        }

        return LoggedUser;
    }

}
