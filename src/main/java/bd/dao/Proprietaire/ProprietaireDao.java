package bd.dao.Proprietaire;

import bd.entities.Animal;
import bd.entities.Proprietaire;
import bd.entities.Visite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietaireDao implements IProprietaire{
    private String url="jdbc:mysql://localhost:3306/vitcare360";
    private String user="root";
    private String pwd="";
    @Override
    public boolean ajouterProprietaire(Proprietaire proprietaire) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("insert into  proprietaire(nom_p,prenom_p,telephone,adresse,ville) values(?,?,?,?,?)");
            ps.setString(1,proprietaire.getNom_proprietaire());
            ps.setString(2,proprietaire.getPrenom_proprietaire());
            ps.setString(3,proprietaire.getTele_proprietaire());
            ps.setString(4,proprietaire.getAdresse_proprietaire());
            ps.setString(5,proprietaire.getVille_proprietaire());
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Proprietaire> rchercherEtudiantparnom(String nom) {
        List<Proprietaire> proprietaires=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("select * from proprietaire where nom_p like ?");
            ps.setString(1,"%"+nom+"%");
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                Proprietaire proprietaire=new Proprietaire();
                proprietaire.setId_proprietaire(rs.getInt("ID_P"));
                proprietaire.setNom_proprietaire(rs.getString("nom_p"));
                proprietaire.setPrenom_proprietaire(rs.getString("prenom_p"));
                proprietaire.setTele_proprietaire(rs.getString("telephone"));
                proprietaire.setAdresse_proprietaire(rs.getString("adresse"));
                proprietaire.setVille_proprietaire(rs.getString("ville"));
                proprietaires.add(proprietaire);
            }
            ps.close();
            con.close();
            return proprietaires;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Proprietaire afficherProprietaire(int id) {
        Proprietaire p = new Proprietaire();
        ArrayList<Animal> animaux = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);
            PreparedStatement ps = con.prepareStatement(
                    "SELECT p.ID_P, p.nom_p, p.prenom_p, p.telephone, p.adresse, p.ville, " +
                            "a.ID_a, a.nom_a, a.birthday, a.type, " +
                            "v.ID_visite, v.description, v.ID_v, v.ID_a " +
                            "FROM proprietaire p, animal a, visite v " +
                            "WHERE p.ID_P = a.ID_P AND v.ID_a = a.ID_a AND p.ID_P = ?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Initialiser les infos du propriétaire une seule fois
                if (p.getId_proprietaire() == 0) {
                    p.setId_proprietaire(rs.getInt("ID_P"));
                    p.setNom_proprietaire(rs.getString("nom_p"));
                    p.setPrenom_proprietaire(rs.getString("prenom_p"));
                    p.setTele_proprietaire(rs.getString("telephone"));
                    p.setAdresse_proprietaire(rs.getString("adresse"));
                    p.setVille_proprietaire(rs.getString("ville"));
                }

                // Créer un objet Animal
                Animal animal = new Animal();
                animal.setIdanimal(rs.getInt("ID_a"));
                animal.setNomea(rs.getString("nom_a"));
                animal.setBirthday(rs.getString("birthday"));
                animal.setType(rs.getString("type"));

                // Créer la visite
                Visite visite = new Visite();
                visite.setId_V(rs.getInt("ID_visite"));
                visite.setDescription(rs.getString("description"));

                // Ajouter la visite à une liste dans l'animal (si tu as cette structure)
                ArrayList<Visite> visites = new ArrayList<>();
                visites.add(visite);
                animal.setVisites(visites);  // suppose que `animal` a un attribut `List<Visite> visites;`

                animal.setP(p); // Lier au propriétaire
                animaux.add(animal);
            }

            p.setAnimaux(animaux); // Ajouter tous les animaux au propriétaire
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }


}
