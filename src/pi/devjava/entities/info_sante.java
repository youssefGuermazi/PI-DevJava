/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

/**
 *
 * @author ASUS
 */
public class info_sante {
    private int id;
    private String info;
    private String datevaccin; 
    medecin med ; 

    public info_sante() {
    }

    public info_sante(int id, String info, String datevaccin) {
        this.id = id;
        this.info = info;
        this.datevaccin = datevaccin;
    }
    public info_sante(int id, String info, String datevaccin , medecin m ) {
        this.id = id;
        this.info = info;
        this.datevaccin = datevaccin;
         this.med= m ; 
    } 
     public info_sante( String info, String datevaccin , medecin m ) {
        
        this.info = info;
        this.datevaccin = datevaccin;
         this.med= m ; 
    }

    public medecin getMed() {
        return med;
    }

    public void setMed(medecin med) {
        this.med = med;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDatevaccin() {
        return datevaccin;
    }

    public void setDatevaccin(String datevaccin) {
        this.datevaccin = datevaccin;
    }

    @Override
    public String toString() {
        return "info_sante{" + "id=" + id + ", info=" + info + ", datevaccin=" + datevaccin + ", med=" + med + '}';
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final info_sante other = (info_sante) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public Object getPro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
     
     
    
    
}
