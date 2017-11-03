
package com.robillard.bibliotheque.modele.classes;

public class Reservation {
    private int id;
    private String date;
    private Compte compte;
    private Exemplaire exemplaire;

    public Reservation() {
    }
    
    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire numeroExemplaireId) {
        this.exemplaire = numeroExemplaireId;
    }

    public Reservation(String date, Compte compte, Exemplaire numeroExemplaireId) {
        this.date = date;
        this.compte = compte;
        this.exemplaire = numeroExemplaireId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
