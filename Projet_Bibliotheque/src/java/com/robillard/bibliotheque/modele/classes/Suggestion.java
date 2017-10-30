
package com.robillard.bibliotheque.modele.classes;

public class Suggestion {
    private int id;
    private String titre,auteur,message;
    private Compte compte;

    public Suggestion() {
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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

    public Suggestion(String titre, String auteur, String message, Compte compte) {
        this.titre = titre;
        this.auteur = auteur;
        this.message = message;
        this.compte = compte;
    }
    
}