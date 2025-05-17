package bd.dao.Proprietaire;

import bd.entities.Proprietaire;

import java.util.List;

public interface IProprietaire {
    public boolean ajouterProprietaire(Proprietaire proprietaire);
    public List<Proprietaire> rchercherEtudiantparnom(String nom);
    public Proprietaire afficherProprietaire(int id);

}
