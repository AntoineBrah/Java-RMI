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

            ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("Cabinet"); // Le client récupère le cabinet

            System.out.println(cabinet.getInfosPatients()); // Le client affiche tous les patients du cabinet

            IAnimal animalClient = cabinet.getAnimal("Idefix"); // Le client récupère un animal nommé Idefix dans le cabinet

            System.out.println("On récupère l'animal : \n\n" + animalClient.infos()); // Le client affiche des infos sur cet animal
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
