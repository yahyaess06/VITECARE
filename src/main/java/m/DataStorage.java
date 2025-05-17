package m;

import java.util.*;
import java.util.stream.Collectors;

import bd.dao.Proprietaire.ProprietaireDao;
import bd.dao.Veterinaire.VeterinaireDao;
import bd.entities.Proprietaire;
import bd.entities.Veterinaire;
import models.*;

public class DataStorage {
    private static Map<String, Owner> owners = new HashMap<>();
    public static List<Veterinarian> veterinarians = new ArrayList<>();

    // Ajouter un nouveau propriétaire à la map
    public static boolean addOwner(Owner owner) {
        Proprietaire p = Converter.ownerToProprietaire(owner);
        return new ProprietaireDao().ajouterProprietaire(p);
    }

    // Supprimer un propriétaire de la map
    public static void removeOwner(String fullName) {
        owners.remove(fullName.toLowerCase());
    }

    public static List<Owner> searchOwnersByLastName(String lastName) {
        List<Proprietaire> proprietaires = new ProprietaireDao().rchercherEtudiantparnom(lastName);
        return proprietaires.stream()
                .map(Converter::proprietaireToOwner)
                .collect(Collectors.toList());
    }

    public static List<Owner> getOwners() {
        return new ArrayList<>(owners.values());
    }
    public static void initVeterinarians() {
        VeterinaireDao dao = new VeterinaireDao();
        List<Veterinaire> dbVeterinaires = dao.affichertsVeterinaires();
        for (Veterinaire v : dbVeterinaires) {
            veterinarians.add(
                    new Veterinarian(v.getPrenomvet(), v.getNomvet(), v.getSpecialite())
            );
        }
    }

}