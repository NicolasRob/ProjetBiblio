//Classe entité qui représente un exemplaire d'une édition
//La String emplacement représente la chaine qui permet de trouver
//l'exemplaire dans la bibliothèque
package com.robillard.bibliotheque.modele.classes;

public class Exemplaire
{

    private int id;
    private String emplacement;
    private Edition edition;

    public Exemplaire()
    {
    }

    public Exemplaire(int id, String emplacement, Edition edition)
    {
        this.id = id;
        this.emplacement = emplacement;
        this.edition = edition;
    }

    public Exemplaire(String emplacement, Edition edition)
    {
        this.emplacement = emplacement;
        this.edition = edition;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmplacement()
    {
        return emplacement;
    }

    public void setEmplacement(String Emplacement)
    {
        this.emplacement = Emplacement;
    }

    public Edition getEdition()
    {
        return edition;
    }

    public void setEdition(Edition edition)
    {
        this.edition = edition;
    }
}
