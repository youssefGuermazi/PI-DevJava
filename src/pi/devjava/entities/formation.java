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
public class formation {
    private int id;
    private String dated ;
    private String datef ;
    private String titre ;
    private String description ;

    public formation() {
    }
    

    public formation(int id, String dated, String datef, String titre, String description) {
        this.id = id;
        this.dated = dated;
        this.datef = datef;
        this.titre = titre;
        this.description = description;
    }

    public formation(String dated, String datef, String titre, String description) {
        this.dated = dated;
        this.datef = datef;
        this.titre = titre;
        this.description = description;
    }

    

    public int getId() {
        return id;
    }

    public String getDated() {
        return dated;
    }

    public String getDatef() {
        return datef;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
   

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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
        final formation other = (formation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formation{" + "id=" + id + ", dated=" + dated + ", datef=" + datef + ", titre=" + titre + ", description=" + description + "}\n";
    }
  
   
    
}
