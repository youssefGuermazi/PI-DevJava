/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class medecin {
    
     private String cin;
    private String nom ;
    private String prenom ;
    private String specialite ;
    private String nomfile ; 
   
    public medecin() {
    }
 public medecin(String cin) { 
     this.cin = cin ; 
    }
    public medecin(String cin, String nom, String prenom, String specialite, String nomfile) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.nomfile = nomfile;
    } 
     public medecin(Medecin_Image MI) {
        this.cin = MI.getCin();
        this.nom = MI.getNom();
        this.prenom = MI.getPrenom();
        this.specialite = MI.getSpecialite();
        this.nomfile = MI.getNomfile();
    } 
   

   
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNomfile() {
        return nomfile;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    @Override
    public String toString() {
        return "medecin{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", specialite=" + specialite + ", nomfile=" + nomfile + "}\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.cin);
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
        final medecin other = (medecin) obj;
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        return true;
    }

  public static ObservableList<medecin> echange(ObservableList<Medecin_Image> ImgO)
  {
    ObservableList<medecin> obM=FXCollections.observableArrayList();
      System.out.println(ImgO.size()); 
         for(int i=0;i<ImgO.size();i++)
         {
             System.out.println(i);
             obM.add(new medecin(ImgO.get(i))) ;
         }
         return obM;
  }
    
    
    
}
