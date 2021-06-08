/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

import java.io.File;

/**
 *
 * @author youssef
 */
public class Garderie {
    String 
numGard,
nom,
adresse,
telephone,

image;
    Integer resp_id;

    public Garderie(String numGard, String nom, String adresse, String telephone, String image, Integer resp_id) {
        this.numGard = numGard;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.image = image;
        this.resp_id = resp_id;
    }
     public Garderie(String numGard, String nom, String adresse, String telephone, Integer resp_id, String image) {
        this.numGard = numGard;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.image = image;
        this.resp_id = resp_id;
    }
    

  

    public Garderie(String numGard, String nom, String adresse, String telephone) {
        this.numGard = numGard;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Garderie(String numGard, String nom, String adresse, String telephone, String image) {
        this.numGard = numGard;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.image = image;
    }

    

    public String getNumGard() {
        return numGard;
    }

    public void setNumGard(String numGard) {
        this.numGard = numGard;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getResp_id() {
        return resp_id;
    }

    public void setResp_id(Integer resp_id) {
        this.resp_id = resp_id;
    }

   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Garderie{" + "numGard=" + numGard + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + ", resp_id=" + resp_id + ", image=" + image + '}';
    }
    
    
}
