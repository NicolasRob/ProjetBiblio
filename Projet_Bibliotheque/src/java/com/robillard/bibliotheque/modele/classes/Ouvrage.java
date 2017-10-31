package com.robillard.bibliotheque.modele.classes;

public class Ouvrage {
    
    private int id;
    private String titre, type;
    private Auteur auteur;

    public Ouvrage() {}
    
    public Ouvrage(String titre, String type, Auteur auteur) {
        this.titre = titre;
        this.type = type;
        this.auteur = auteur;
    }
    
    public Ouvrage(int id,String titre, String type, Auteur auteur) {
        this.id=id;
        this.titre = titre;
        this.type = type;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    
}
