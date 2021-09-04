package src;

import Commun.src.*;
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
            
            EspeceFille e1 = new EspeceFille(); // Le client créer une EspeceFille (classe inconnue du Serveur)
            e1.setMsg("JE SUIS UNE ESPÈCE FILLE !");

            cabinet.getAnimal("Garfield").setEspece(e1); // Le client modifie l'espèce de l'animal présent dans le cabinet envoyé par le serveur
            
            /* Le serveur ne connaissant pas la classe EspeceFille
            le mécanisme de codebase est déclenché est donc aucune 
            erreur n'a lieu */ 
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
