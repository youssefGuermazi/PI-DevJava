/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

/**
 *
 * @author user
 */
public class animateur_formation {
    private int id ;
    private int idAnimateur;
     private int idFormateur;

    public animateur_formation(int id, int idAnimateur, int idFormateur) {
        this.id = id;
        this.idAnimateur = idAnimateur;
        this.idFormateur = idFormateur;
    }

    public animateur_formation(int idAnimateur, int idFormateur) {
        this.idAnimateur = idAnimateur;
        this.idFormateur = idFormateur;
    }

    public animateur_formation() {
    }

    public int getId() {
        return id;
    }

    public int getIdAnimateur() {
        return idAnimateur;
    }

    public int getIdFormateur() {
        return idFormateur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAnimateur(int idAnimateur) {
        this.idAnimateur = idAnimateur;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
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
        final animateur_formation other = (animateur_formation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "animateur_formation{" + "id=" + id + ", idAnimateur=" + idAnimateur + ", idFormateur=" + idFormateur + '}';
    }
     
     
    
    
}
