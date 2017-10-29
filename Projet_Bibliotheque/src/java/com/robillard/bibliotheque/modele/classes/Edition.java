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
    private int id, ouvrageId,nombrePage;
    private String isbn,datePublication,image;
    private Editeur numeroEditeurId;
    private Ouvrage numeroOuvrageId;

    public Edition() {
    }


    public Edition(int ouvrageId, int nombrePage, String isbn, String datePublication, String image, Editeur numeroEditeurId, Ouvrage numeroOuvrageId) {
        this.ouvrageId = ouvrageId;
        this.nombrePage = nombrePage;
        this.isbn = isbn;
        this.datePublication = datePublication;
        this.image = image;
        this.numeroEditeurId = numeroEditeurId;
        this.numeroOuvrageId = numeroOuvrageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
