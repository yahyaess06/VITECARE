package bd.entities;

import java.util.ArrayList;

public class Veterinaire {
    private int idveterinaire;
    private String nomvet;
    private String prenomvet;
    private String specialite;
    ArrayList<Animal> animaux;

    public Veterinaire(String nomvet, String prenomvet, String specialite) {
        this.nomvet = nomvet;
        this.prenomvet = prenomvet;
        this.specialite = specialite;
    }
public Veterinaire() {

}

    public int getIdveterinaire() {
        return idveterinaire;
    }

    public void setIdveterinaire(int idveterinaire) {
        this.idveterinaire = idveterinaire;
    }

    public String getNomvet() {
        return nomvet;
    }

    public void setNomvet(String nomvet) {
        this.nomvet = nomvet;
    }

    public String getPrenomvet() {
        return prenomvet;
    }

    public void setPrenomvet(String prenomvet) {
        this.prenomvet = prenomvet;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "----------------------------------\n" +
                " Vétérinaire ID : " + idveterinaire + "\n" +
                " Nom            : " + nomvet + "\n" +
                " Prénom         : " + prenomvet + "\n" +
                " Spécialité     : " + specialite + "\n" +
                "----------------------------------";
    }
}
