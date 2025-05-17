package bd.dao.Animal;

import bd.entities.Animal;

import java.util.List;

public interface IAnimal {
    public boolean ajouterAnimal(Animal animal);
    public List<Animal> affichertsanimal(int idproprietaire);

}
