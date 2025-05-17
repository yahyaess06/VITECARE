import bd.dao.Proprietaire.ProprietaireDao;
import bd.dao.Visite.VisiteDao;
import bd.entities.Proprietaire;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        boolean condition =true;
        Scanner scanner = new Scanner(System.in);
//        System.out.println("----mrhba dans notre vitcare projet ----");
//        while(condition){
//            System.out.println("1- consultation veterinaires ");
//            System.out.println("2- proprietaire");
//            System.out.print("choisir un option: ");
//            int option = scanner.nextInt();
//            switch(option){
//                case 1:{
//            VeterinaireDao dao = new VeterinaireDao();
//            List<Veterinaire> listeveterinaire=dao.affichertsVeterinaires();
//            System.out.println("-------liste des veterinaires-------");
//            for(Veterinaire v :listeveterinaire){
//                System.out.println(v.toString());
//            }
//            System.out.println("-----------fin----------");
//
//                break;}
//                case 2:{
                    VisiteDao dao1 = new VisiteDao();
//                    System.out.println("--------gestion proprietaire-------");
//                    System.out.println("1-ajouter un proprietaire");
//                    System.out.println("2-afficher un proprietaire ");
//                    System.out.println("---------");
//                    System.out.print("choisir un option: ");
//                    int option2 = scanner.nextInt();
//                    switch(option2){
//                        case 1:{
//                    System.out.println(" ajouter date ");
//                    String nom = scanner.next();
//                    System.out.println(" ajouter description");
//                    String birth = scanner.next();
//                    System.out.println(" id v");
//                    int idv = scanner.nextInt();
//                    System.out.println("id a");
//                    int ida = scanner.nextInt();
//                    Visite p=new Visite(nom,birth);
//                            Animal a=new Animal();
//        a.setIdanimal(ida); // très important
//        p.setA(a);
//                            Veterinaire v=new Veterinaire();
//                            v.setIdveterinaire(idv); // très important
//                            p.setV(v);
//
//                    if(dao1.ajoutetvisite(p)){
//                        System.out.println("animal ajoutee ");
//                    }else {
//                        System.out.println("reeur");
//                    }
//                   }break;
//                        case 2:{
////                            System.out.println("saisisser le nom d'proprietaire a lister :");
////                            String noms =scanner.next();
////                            List<Proprietaire> proprietairesList=dao1.(noms);
////                            for(Proprietaire p:proprietairesList){
////                                System.out.println(p.toString());
////                            }
//                        }
//                    }
//                    break;
//
//                } case 3:{
//                    condition = false;
//                    break;
//                }
//            }}
        ProprietaireDao proprietaireDao = new ProprietaireDao();
        Proprietaire p = proprietaireDao.afficherProprietaire(1);  // Afficher le propriétaire avec ID 1
        p.afficherProprietaireEtAnimaux(p);

    }
}
