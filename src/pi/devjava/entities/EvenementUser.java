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
public class EvenementUser {
   public int  evenement_id;
   public int user_id;

    public EvenementUser() {
    }

    public EvenementUser(int evenement_id, int user_id) {
        this.evenement_id = evenement_id;
        this.user_id = user_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "EvenementUser{" + "evenement_id=" + evenement_id + ", user_id=" + user_id + '}';
    }
   
}
