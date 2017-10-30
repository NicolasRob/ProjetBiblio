
package com.robillard.bibliotheque.modele.classes;

public class Reservation {
    private int id;
    private String dateFin,dateDebut;
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

    public Reservation(String dateFin, String dateDebut, Compte compte, Exemplaire numeroExemplaireId) {
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.compte = compte;
        this.exemplaire = numeroExemplaireId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
}
