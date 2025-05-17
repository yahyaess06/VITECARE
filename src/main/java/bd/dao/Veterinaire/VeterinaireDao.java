package bd.dao.Veterinaire;

import bd.entities.Veterinaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinaireDao implements IVeterinaire{
    private String url="jdbc:mysql://localhost:3306/vitcare360";
    private String user="root";
    private String pwd="";

    @Override
    public List<Veterinaire> affichertsVeterinaires() {
        List<Veterinaire> veterinaires=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("select * from veterinaire ");
            ResultSet rs= ps.executeQuery();
            while (rs.next()) {
                Veterinaire veterinaire=new Veterinaire();
                veterinaire.setIdveterinaire(rs.getInt("ID_v"));
                veterinaire.setNomvet(rs.getString("nom_v"));
                veterinaire.setPrenomvet(rs.getString("prenom_v"));
                veterinaire.setSpecialite(rs.getString("Sspeciality"));
                veterinaires.add(veterinaire);
            }
            ps.close();
            con.close();
            return veterinaires;

        } catch (SQLException e) {
            System.out.println("sql erreur");
            throw new RuntimeException(e);

        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
