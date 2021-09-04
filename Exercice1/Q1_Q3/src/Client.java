package src;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Client {
    
    private Client(){}

    public static void main(String[] args){

        String host = (args.length < 1)? null : args[0];

        /* Exo1-Q2 */
        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());

        try{
            Registry registry = LocateRegistry.getRegistry(host);

            IAnimal a = (IAnimal) registry.lookup("Animal"); // Exo1-Q1
            IDossier d = (IDossier) registry.lookup("Dossier"); // Exo1-Q3

            System.out.println(a.infos()); // On affiche sur le client

            d.setSuivi("Malade..."); // Le client modifie le dossier de l'animal

            Thread.sleep(1000); // Pour ralentir l'affichage
            System.out.println("----------");

            System.out.println(a.infos()); // On affiche l'animal après la modification du dossier par le client
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
