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

            /* Le client créer à son tour des animaux */
            cabinet.createAnimal("Mickey", "Inconnu", "Souris à pattes blanches", 10, "En forme !");
            cabinet.createAnimal("Snoopie", "Charlie Brown", "Beagle", 13, "En forme !");
            cabinet.createAnimal("Garfield", "Jon", "Jack Russell Terrier", 20, "En forme !");
            cabinet.createAnimal("Rantanplan", "Lucky luke", "Lévrier", 17, "En forme !");

            /* On affiche désormais tous les patients (les anciens + les nouveaux) */
            System.out.println("\nLes patients sont désormais :\n\n");
            System.out.println(cabinet.getInfosPatients());
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
