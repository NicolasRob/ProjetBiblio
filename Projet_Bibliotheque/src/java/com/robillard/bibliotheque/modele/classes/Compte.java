//Classe entité qui représente un compte de la bibliothèque
//Le numero du compte n'est pas auto-généré, c'est un code de 8 chiffres
//choisis lors de l'émission de la carte de membre.
//Un autre sytème est responsable de l'émission de la carte de membre, 
//la création de compte est donc en dehors de la portée de l'application
//Pour les besoins du TP, les comptes sont directement entrés dans la BD
//Un compte de type 1 est un membre tandis que le type 2 est un employé

package com.robillard.bibliotheque.modele.classes;

public class Compte
{

    private String numero, prenom, nom, mdp;
    private int type;

    public Compte()
    {
    }

    public Compte(String numero, String prenom, String nom, String mdp, int type)
    {
        this.numero = numero;
        this.prenom = prenom;
        this.nom = nom;
        this.mdp = mdp;
        this.type = type;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getMdp()
    {
        return mdp;
    }

    public void setMdp(String mdp)
    {
        this.mdp = mdp;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
