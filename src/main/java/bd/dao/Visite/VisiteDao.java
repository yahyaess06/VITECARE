package bd.dao.Visite;

import bd.entities.Visite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisiteDao implements IVisite{
    private String url="jdbc:mysql://localhost:3306/vitcare360";
    private String user="root";
    private String pwd="";
    @Override
    public boolean ajoutetvisite(Visite visite) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,user,pwd);
            PreparedStatement ps =con.prepareStatement("insert into  visite(date,description,ID_v,ID_a) values(?,?,?,?)");
            ps.setString(1,visite.getDate());
            ps.setString(2,visite.getDescription());
            ps.setInt(3,visite.getV());
            ps.setInt(4,visite.getA());
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
}
