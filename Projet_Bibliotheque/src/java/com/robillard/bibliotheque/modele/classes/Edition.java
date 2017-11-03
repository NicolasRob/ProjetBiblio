
package com.robillard.bibliotheque.modele.classes;

public class Edition {
    private int id,nombrePage;
    private String isbn,datePublication,image;
    private Editeur editeur;
    private Ouvrage ouvrage;

    public Edition() {
    }


    public Edition(int id,int nombrePage, String isbn, String datePublication, String image, Editeur editeur, Ouvrage ouvrage) {
        this.nombrePage = nombrePage;
        this.id=id;
        this.isbn = isbn;
        this.datePublication = datePublication;
        this.image = image;
        this.editeur = editeur;
        this.ouvrage = ouvrage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;
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
