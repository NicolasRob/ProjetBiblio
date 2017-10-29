
package com.robillard.bibliotheque.modele.classes;

public class Reservation {
    private int id;
    private String dateFin,dateDebut;
    private Compte compte;
    private Exemplaire numeroExemplaireId;

    public Reservation() {
    }
    
    public Compte getcompte() {
        return compte;
    }

    public void setcompte(Compte compte) {
        this.compte = compte;
    }

    public Exemplaire getNumeroExemplaireId() {
        return numeroExemplaireId;
    }

    public void setNumeroExemplaireId(Exemplaire numeroExemplaireId) {
        this.numeroExemplaireId = numeroExemplaireId;
    }

    public Reservation(String dateFin, String dateDebut, Compte compte, Exemplaire numeroExemplaireId) {
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.compte = compte;
        this.numeroExemplaireId = numeroExemplaireId;
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
