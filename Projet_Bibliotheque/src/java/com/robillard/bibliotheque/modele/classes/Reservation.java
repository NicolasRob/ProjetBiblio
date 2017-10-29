/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robillard.bibliotheque.modele.classes;

/**
 *
 * @author c.blais
 */
public class Reservation {
    private int id;
    private String dateFin,dateDebut;
    private Compte numeroCompteId;
    private Exemplaire numeroExemplaireId;

    public Compte getNumeroCompteId() {
        return numeroCompteId;
    }

    public void setNumeroCompteId(Compte numeroCompteId) {
        this.numeroCompteId = numeroCompteId;
    }

    public Exemplaire getNumeroExemplaireId() {
        return numeroExemplaireId;
    }

    public void setNumeroExemplaireId(Exemplaire numeroExemplaireId) {
        this.numeroExemplaireId = numeroExemplaireId;
    }

    public Reservation(String dateFin, String dateDebut, Compte numeroCompteId, Exemplaire numeroExemplaireId) {
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.numeroCompteId = numeroCompteId;
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
