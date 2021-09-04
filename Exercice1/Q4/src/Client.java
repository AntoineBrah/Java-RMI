package src;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {
    
    private Client(){}

    public static void main(String[] args){

        String host = (args.length < 1)? null : args[0];

        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());

        try{
            Registry registry = LocateRegistry.getRegistry(host);

            IAnimal a = (IAnimal) registry.lookup("Animal");
            IDossier d = (IDossier) registry.lookup("Dossier");
            IEspece e = (IEspece) registry.lookup("Espece"); // On récupère une copie de l'espèce 

            System.out.println(a.infos()); // On affiche sur le client
            a.printInfos(); // On affiche sur le serveur

            e.setDureeDeVieMoy(1000); // On modifie la durée de vie moyenne de l'espèce
            e.setNomEspece("UFO"); // On modifie le nom de l'espèce

            Thread.sleep(1000);
            System.out.println("----");

            System.out.println(e.getInfos()); // On affiche les infos de l'espèce après modification par le client pour constater le changement (en local)
            
            /* On affiche les infos de l'espèce de l'animal pour constater que le client a bien modifié une COPIE de l'espèce de l'Animal */
            System.out.println(a.getEspece().getInfos());

            /* On affiche les infos de l'animal sur le serveur et on constate qu'il n'y a eu aucune modification de l'espèce de l'animal */
            a.printInfos();
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
