package src;

import Commun.src.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/* Le serveur ne possède pas la classe EspeceFille qui est
une sous classe de la classe Espece. 
On utilise donc le mécanisme de codebase en indiquant ou se situent
les classes utiles pour le serveur (et que le classpath n'arrive pas à trouver) */

public class Serveur {
    
    public Serveur(){}

    public static void main(String args[]){

        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());
        
        // Codebase
        System.setProperty("java.rmi.server.codebase", "file:Client/");

        try{
            /* Le serveur créer un cabinet */
            ICabinetVeterinaire cabinet = new CabinetVeterinaire("OuafOuafMedic");

            /* Le serveur créer un animal */
            IAnimal a1 = new Animal("Garfield", "Jon", new Espece("Jack Russell Terrier", 20), new Dossier("En forme !"));
            
            /* Le serveur ajoute cet animal au cabinet */
            cabinet.addAnimal(a1);
            
            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("RmiRegistry not found");
            }
            else{
                registry.bind("Cabinet", cabinet); // Le serveur envoi le cabinet au client
                System.out.println("Server ready");

                /* On fait une boucle infinie afin d'attendre que le client
                renvoie une EspeceFille (classe que le serveur ne connait pas) */
                while(true){
                    /* Le client va modifier l'espèce de cet animal et lui affecter à la place
                    une EspeceFille, comme le serveur ne connait pas cette classe, le mécanisme de codebase
                    va s'enclencher */
                    IEspece e1 = cabinet.getAnimal("Garfield").getEspece(); 
                    e1.printInfos();
                    Thread.sleep(1000);
                }
            }
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}