package src;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Serveur {
    
    public Serveur(){}

    public static void main(String args[]){
    
        /* Exo1-Q2 */
    	String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());

        try{
            IAnimal a = new Animal("Milou", "Tintin", "Fox-terrier à poil dur", new Dossier("En forme !"));

            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("RmiRegistry not found");
            }
            else{
                registry.bind("Animal", a); // Exo1-Q1
                registry.bind("Dossier", a.getDossier()); // Exo1-Q3

                System.out.println("Server ready");
            }
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
