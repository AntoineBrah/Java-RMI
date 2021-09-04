package src;

import Commun.src.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


public class Serveur {
    
    public Serveur(){}

    public static void main(String args[]){

        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());
        
        System.setProperty("java.rmi.server.codebase", "file:Client/");


        try{
            ICabinetVeterinaire cabinet = new CabinetVeterinaire("OuafOuafMedic");
            
            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("RmiRegistry not found");
            }
            else{
                registry.bind("Cabinet", cabinet);
                System.out.println("Server ready");

                /*
                while(true){
                    Thread.sleep(1000);
                    System.out.println(cabinet.getNombrePatients());
                }
                */
            }


        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }


    }
}