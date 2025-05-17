package bd.dao.Animal;

import bd.entities.Animal;
import bd.entities.Proprietaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AnimalDao implements IAnimal{
    private String url="jdbc:mysql://localhost:3306/vitcare360";
    private String user="root";
    private String pwd="";

    @Override
    public boolean ajouterAnimal(Animal animal) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("insert into  animal(nom_a,birthday,type,ID_P) values(?,?,?,?)");
            ps.setString(1,animal.getNomea());
            ps.setString(2,animal.getBirthday());
            ps.setString(3,animal.getType());
            ps.setInt(4,animal.getP());
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
    public List<Animal> affichertsanimal(int idproprietaire) {
        List<Animal> animaux= new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("select * from Animal where ID_P=?");
            ps.setInt(1,idproprietaire);
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setNomea(rs.getString("nom_a"));
                animal.setBirthday(rs.getString("birthday"));
                animal.setType(rs.getString("type"));
              Proprietaire proprietaire = new Proprietaire();
              proprietaire.setId_proprietaire(idproprietaire); // très important
              animal.setP(proprietaire);
              animaux.add(animal);
            }
            ps.close();
            con.close();
            return animaux;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
