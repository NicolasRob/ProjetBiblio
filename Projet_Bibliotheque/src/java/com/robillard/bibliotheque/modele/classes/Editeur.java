
package com.robillard.bibliotheque.modele.classes;

public class Editeur {
    private int id;
    private String nom; 

    public Editeur() {
    }

    public Editeur(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
