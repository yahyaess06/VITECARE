package m;

import bd.entities.Proprietaire;
import models.Owner;

public class Converter {

    public static Proprietaire ownerToProprietaire(Owner o) {
        Proprietaire p = new Proprietaire();
        p.setNom_proprietaire(o.getLastName());
        p.setPrenom_proprietaire(o.getFirstName());
        p.setAdresse_proprietaire(o.getAddress());
        p.setVille_proprietaire(o.getCity());
        p.setTele_proprietaire(o.getTelephone());
        return p;
    }

    public static Owner proprietaireToOwner(Proprietaire p) {
        return new Owner(
                p.getPrenom_proprietaire(),
                p.getNom_proprietaire(),
                p.getAdresse_proprietaire(),
                p.getVille_proprietaire(),
                p.getTele_proprietaire()
        );
    }
}
