package bd.entities;

import java.util.ArrayList;

public class Animal {
    private int idanimal;
    private String nomea;
    private String birthday;
    private String type;
    Proprietaire p;
    ArrayList<Visite> visites;
    ArrayList<Veterinaire> veterinaire;
    public Animal() {

    }

    public void setVisites(ArrayList<Visite> visites) {
        this.visites = visites;
    }

    public Animal(String nomea, String birthday, String type) {
        this.nomea = nomea;
        this.birthday = birthday;
        this.type = type;
        this.p = new Proprietaire();
    }

    public int getP() {
        return p.getId_proprietaire();
    }

    public void setP(Proprietaire p) {
        this.p = p;
    }

    public int getIdanimal() {
        return idanimal;
    }

    public void setIdanimal(int idanimal) {
        this.idanimal = idanimal;
    }

    public String getNomea() {
        return nomea;
    }

    public void setNomea(String nomea) {
        this.nomea = nomea;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void afficherVisites() {
        Veterinaire vet = new Veterinaire();
        if (visites == null || visites.isEmpty()) {
            System.out.println("Aucune visite pour l'animal : " + nomea);
        } else {
            System.out.println("Visites de l'animal : " + nomea);
            for (Visite v : visites) {
                System.out.println("  - Date        : " + v.getDate());
                System.out.println("    Description : " + v.getDescription());
                    System.out.println("    Vétérinaire : " + vet.getNomvet() + " " + vet.getPrenomvet());
            }
        }
    }

}
