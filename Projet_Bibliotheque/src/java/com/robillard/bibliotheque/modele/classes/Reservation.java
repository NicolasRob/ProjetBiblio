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
    private int id,numeroCompteId,numeroExemplaireId;
    private String dateFin,dateDebut;

    public Reservation(String dateFin, String dateDebut) {
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroCompteId() {
        return numeroCompteId;
    }

    public void setNumeroCompteId(int numeroCompteId) {
        this.numeroCompteId = numeroCompteId;
    }

    public int getNumeroExemplaireId() {
        return numeroExemplaireId;
    }

    public void setNumeroExemplaireId(int numeroExemplaireId) {
        this.numeroExemplaireId = numeroExemplaireId;
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
