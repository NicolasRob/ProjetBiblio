//Classe entité qui représente un emprunt ou une réservation
//La variable status désigne si l'emprunt est une réservation ou un emprunt
//Bien qu'il soit possible de consulter les emprunts, l'ajouts de nouveaux emprunts
//est hors de la portée de l'application, cette dernière ne permet de faire que des réservations
//Tous les "Emprunt" créés lors de l'utilisation de l'application seront donc de type "RESERVATION"
//Des "Emprunt" de type "EMPRUNT" on été ajouté à la BD pour être utilisés lors de la consultation cependant


package com.robillard.bibliotheque.modele.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Emprunt
{

    private int id;
    private Compte compte;
    private Exemplaire exemplaire;
    private String dateDebut, dateFin, status;
    //Durée d'une réservation ou d'un emprunt
    private static final int duree = 14;

    public Emprunt()
    {
    }

    public Emprunt(int id, Compte compte, Exemplaire Exemplaire, String dateDebut, String dateFin, String status)
    {
        this.id = id;
        this.compte = compte;
        this.exemplaire = Exemplaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }

    public Emprunt(Compte compte, Exemplaire exemplaire, String dateDebut, String dateFin, String status)
    {
        this.compte = compte;
        this.exemplaire = exemplaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }

    public Emprunt(Compte compte, Exemplaire exemplaire, Date dateDebut, String status)
    {
        Date dt1;
        Date dt2;
        Calendar c = Calendar.getInstance();
        c.setTime(dateDebut);
        c.add(Calendar.DATE, 1);
        dt1 = c.getTime();
        c.add(Calendar.DATE, duree);
        dt2 = c.getTime();
        this.compte = compte;
        this.exemplaire = exemplaire;
        this.dateDebut = (new SimpleDateFormat("yyyy-MM-dd").format(dt1));
        this.dateFin = (new SimpleDateFormat("yyyy-MM-dd").format(dt2));
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Compte getCompte()
    {
        return compte;
    }

    public void setCompte(Compte compte)
    {
        this.compte = compte;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Exemplaire getExemplaire()
    {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire Exemplaire)
    {
        this.exemplaire = Exemplaire;
    }

    public String getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public String getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(String dateFin)
    {
        this.dateFin = dateFin;
    }

}
