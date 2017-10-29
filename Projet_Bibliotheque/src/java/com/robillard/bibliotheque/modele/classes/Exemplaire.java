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
public class Exemplaire {
    private int id,numeroEditionId;
    private String Emplacement;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroEditionId() {
        return numeroEditionId;
    }

    public void setNumeroEditionId(int numeroEditionId) {
        this.numeroEditionId = numeroEditionId;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public void setEmplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    public Exemplaire(int numeroEditionId, String Emplacement) {
        this.numeroEditionId = numeroEditionId;
        this.Emplacement = Emplacement;
    }
    
}
