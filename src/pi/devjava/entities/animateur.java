/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;



/**
 *
 * @author user
 */
public class animateur {
    private int cin;
    private String nom ;
    private String prenom ;
    private String activiter ;
    private String nomfile ;
    private double rate ;
    private int vote ;

    public animateur(int cin, String nom, String prenom, String activiter, String nomfile, double rate, int vote) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.activiter = activiter;
        this.nomfile = nomfile;
        this.rate = rate;
        this.vote = vote;
    }

    public animateur(int cin, String nom, String prenom, String activiter, String nomfile) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.activiter = activiter;
        this.nomfile = nomfile;
    }

    public animateur() {
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getActiviter() {
        return activiter;
    }

    public String getNomfile() {
        return nomfile;
    }

    public double getRate() {
        return rate;
    }

    public int getVote() {
        return vote;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setActiviter(String activiter) {
        this.activiter = activiter;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "animateur{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", activiter=" + activiter + ", nomfile=" + nomfile + "}\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.cin;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final animateur other = (animateur) obj;
        if (this.cin != other.cin) {
            return false;
        }
        return true;
    }
    
     
}
