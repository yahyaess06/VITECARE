package bd.entities;

public class Visite {
    private int id_V;
    private String date;
    private String description;
    private Veterinaire v;
    private Animal a;

    public Visite(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public int getV() {
        return v.getIdveterinaire();
    }

    public int getA() {
        return a.getIdanimal();
    }

    public Visite() {
    }

    public void setV(Veterinaire v) {
        this.v = v;
    }

    public void setA(Animal a) {
        this.a = a;
    }

    public int getId_V() {
        return id_V;
    }

    public void setId_V(int id_V) {
        this.id_V = id_V;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "id_V=" + id_V +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", veterinaire=" + v.getNomvet() +v.getPrenomvet() +
                ", animal=" + a.getNomea() +
                '}';
    }
}
