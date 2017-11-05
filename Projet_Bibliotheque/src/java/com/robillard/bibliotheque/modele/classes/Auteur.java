//Classe entité qui représente un auteur d'un ouvrage
//Contrairement à la majorité des classes, l'identifiant n'est pas auto-généré
//L'identifiant doit être spécifié lorqu'un employé 
//ajoute un ouvrage dans la gestion du catalogue

package com.robillard.bibliotheque.modele.classes;

public class Auteur
{

    private String id, nom, prenom;

    public Auteur()
    {
    }

    public Auteur(String id, String prenom, String nom)
    {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
}
