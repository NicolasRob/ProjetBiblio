
package com.robillard.bibliotheque.modele.classes;

public class Emprunt {
    private int id;
    private Compte compte;
    private Exemplaire Exemplaire;
    private String dateDebut,dateFin, status;

    public Emprunt() {
    }
    
    public Emprunt(int id, Compte compte, Exemplaire Exemplaire, String dateDebut, String dateFin, String status) {
        this.id = id;
        this.compte = compte;
        this.Exemplaire = Exemplaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }
    
    public Emprunt(Compte compte, Exemplaire Exemplaire, String dateDebut, String dateFin, String status) {
        this.compte = compte;
        this.Exemplaire = Exemplaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Exemplaire getExemplaire() {
        return Exemplaire;
    }

    public void setExemplaire(Exemplaire Exemplaire) {
        this.Exemplaire = Exemplaire;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    
}
