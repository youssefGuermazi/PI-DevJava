/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.entities;

/**
 *
 * @author LENOVO
 */
 

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author LENOVO
 */
public class Evenement {
    private int eventId;
    private String eventName;
    private String club_evenement;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String nomfile;
    private int nbp;

    public Evenement() {
    }

    public Evenement( String eventName, String club_evenement ,  String description, Date dateDebut, Date dateFin,String nomfile) {
       
        this.eventName = eventName;
        this.club_evenement = club_evenement;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomfile=  nomfile;
        
    }

    public Evenement(int eventId, String eventName, String club_evenement, String description, Date dateDebut, Date dateFin, String nomfile, int nbp) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.club_evenement = club_evenement;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomfile = nomfile;
        this.nbp = nbp;
    }

     public Evenement(int eventId, String eventName, String club_evenement, String description, Date dateDebut, Date dateFin, String nomfile) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.club_evenement = club_evenement;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomfile = nomfile;
    
    }

    @Override
    public String toString() {
        return "Evenement{" + "eventId=" + eventId + ", eventName=" + eventName + ", club_evenement=" + club_evenement + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nomfile=" + nomfile + ", nbp=" + nbp + '}';
    }

    public String getClub_evenement() {
        return club_evenement;
    }

    public void setClub_evenement(String club_evenement) {
        this.club_evenement = club_evenement;
    }

    public String getNomfile() {
        return nomfile;
    }

    public void setNomfile(String nomfile) {
        this.nomfile = nomfile;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.eventId;
        hash = 23 * hash + Objects.hashCode(this.eventName);
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
        final Evenement other = (Evenement) obj;
        if (this.eventId != other.eventId) {
            return false;
        }
        if (!Objects.equals(this.eventName, other.eventName)) {
            return false;
        }
        return true;
    }

   
    
}
