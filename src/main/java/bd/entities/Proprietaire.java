package bd.entities;

import java.util.ArrayList;

public class Proprietaire {
    private int id_proprietaire;
    private String nom_proprietaire;
    private String prenom_proprietaire;
    private String tele_proprietaire;
    private String adresse_proprietaire;
    private String ville_proprietaire;
    ArrayList<Animal> animaux;

    public Proprietaire(String nom_proprietaire, String prenom_proprietaire, String tele_proprietaire, String adresse_proprietaire, String ville_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
        this.prenom_proprietaire = prenom_proprietaire;
        this.tele_proprietaire = tele_proprietaire;
        this.adresse_proprietaire = adresse_proprietaire;
        this.ville_proprietaire = ville_proprietaire;

    }

    public Proprietaire() {
    }

    public void afficherProprietaireEtAnimaux(Proprietaire p) {


        System.out.println("=== Informations du propriétaire ===");
        System.out.println("Nom       : " + p.getNom_proprietaire());
        System.out.println("Prénom    : " + p.getPrenom_proprietaire());
        System.out.println("Téléphone : " + p.getTele_proprietaire());
        System.out.println("Adresse   : " + p.getAdresse_proprietaire());
        System.out.println("Ville     : " + p.getVille_proprietaire());

        System.out.println("\n=== Animaux du propriétaire ===");

        if (p.getAnimaux() == null || p.getAnimaux().isEmpty()) {
            System.out.println("Aucun animal enregistré.");
        } else {
            for (Animal a : p.getAnimaux()) {
                System.out.println("- Nom      : " + a.getNomea());
                System.out.println("  Type     : " + a.getType());
                System.out.println("  Naissance: " + a.getBirthday());
                System.out.println();
            }

        }
    }



    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public String getPrenom_proprietaire() {
        return prenom_proprietaire;
    }

    public void setPrenom_proprietaire(String prenom_proprietaire) {
        this.prenom_proprietaire = prenom_proprietaire;
    }

    public String getTele_proprietaire() {
        return tele_proprietaire;
    }

    public void setTele_proprietaire(String tele_proprietaire) {
        this.tele_proprietaire = tele_proprietaire;
    }

    public String getAdresse_proprietaire() {
        return adresse_proprietaire;
    }

    public void setAnimaux(ArrayList<Animal> animaux) {
        this.animaux = animaux;
    }

    public ArrayList<Animal> getAnimaux() {
        return animaux;
    }

    public void setAdresse_proprietaire(String adresse_proprietaire) {
        this.adresse_proprietaire = adresse_proprietaire;
    }

    public String getVille_proprietaire() {
        return ville_proprietaire;
    }

    public void setVille_proprietaire(String ville_proprietaire) {
        this.ville_proprietaire = ville_proprietaire;
    }
}
