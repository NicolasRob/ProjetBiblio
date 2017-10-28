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
public class Edition {
    private int id, ouvrageId,nombrePage,numeroEditeurId,numeorOuvrageId;
    private String isbn,datePublication;

    public Edition() {
    }

    public Edition(int nombrePage, String isbn, String datePublication) {
        this.nombrePage = nombrePage;
        this.isbn = isbn;
        this.datePublication = datePublication;
    }

    public int getId() {
        return id;
    }

    public int getOuvrageId() {
        return ouvrageId;
    }

    public int getNombrePage() {
        return nombrePage;
    }

    public void setNombrePage(int nombrePage) {
        this.nombrePage = nombrePage;
    }

    public int getNumeroEditeurId() {
        return numeroEditeurId;
    }

    public void setNumeroEditeurId(int numeroEditeurId) {
        this.numeroEditeurId = numeroEditeurId;
    }

    public int getNumeorOuvrageId() {
        return numeorOuvrageId;
    }

    public void setNumeorOuvrageId(int numeorOuvrageId) {
        this.numeorOuvrageId = numeorOuvrageId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
}
