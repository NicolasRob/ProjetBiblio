
package com.robillard.bibliotheque.modele.classes;

public class Emprunt {
    private int id;
    private Compte compte;
    private Exemplaire Exemplaire;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    private String dateDebut,dateFin;

    public Emprunt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Emprunt(Compte compte, Exemplaire Exemplaire, String dateDebut, String dateFin) {
        this.compte = compte;
        this.Exemplaire = Exemplaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
}
