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
public class Emprunt {
    private int id;
    private Compte numeroCompteId;
    private Exemplaire numeroExemplaireId;
    private String dateDebut,dateFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Emprunt(Compte numeroCompteId, Exemplaire numeroExemplaireId, String dateDebut, String dateFin) {
        this.numeroCompteId = numeroCompteId;
        this.numeroExemplaireId = numeroExemplaireId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
}
