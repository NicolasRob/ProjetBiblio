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
public class Suggestion {
    private int id, numroCompteId;
    private String titre,auteur,message;

    public Suggestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumroCompteId() {
        return numroCompteId;
    }

    public void setNumroCompteId(int numroCompteId) {
        this.numroCompteId = numroCompteId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Suggestion(int numroCompteId, String titre, String auteur, String message) {
        this.numroCompteId = numroCompteId;
        this.titre = titre;
        this.auteur = auteur;
        this.message = message;
    }
}