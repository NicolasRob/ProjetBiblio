
package com.robillard.bibliotheque.modele.classes;

public class Exemplaire {
    private int id;
    private String Emplacement;
    private Edition edition;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public void setEmplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    public Edition getedition() {
        return edition;
    }

    public void setedition(Edition edition) {
        this.edition = edition;
    }

    public Exemplaire(String Emplacement, Edition edition) {
        this.Emplacement = Emplacement;
        this.edition = edition;
    }

    public Exemplaire() {
    }
    
}
