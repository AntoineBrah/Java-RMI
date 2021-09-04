package src;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Serveur {
    
    public Serveur(){}

    public static void main(String args[]){

        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());

        try{
            IAnimal a = new Animal("Milou", "Tintin", new Espece("Fox-terrier à poil dur", 10), new Dossier("En forme !"));

            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("RmiRegistry not found");
            }
            else{
                registry.bind("Animal", a);
                registry.bind("Dossier", a.getDossier());
                registry.bind("Espece", a.getEspece());

                System.out.println("Server ready");
            }

        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
