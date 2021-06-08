/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import java.sql.Date;

import java.util.Objects;
import javafx.scene.control.Alert;
import static pi.devjava.PIDevJava.bdd;

/**
 *
 * @author LENOVO
 */
public class Club {

    public String nom;
    private String description;
    private Date creationDate;
    private int id;

    public Club() {
    }

    public Club(String nom, String description, Date creationDate) {

        this.nom = nom;
        this.description = description;
        this.creationDate = creationDate;

    }

    public Club(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getClubName() {
        return nom = nom;

    }

    public String getDescription() {
        return description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setClubName(String clubName) {
        this.nom = clubName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Club{" + "nom=" + nom + ", description=" + description + ", creationDate=" + creationDate + '}';
    }

}
