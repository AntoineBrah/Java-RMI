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
            /* Le serveur créer des animaux */
            IAnimal a1 = new Animal("Milou", "Tintin", new Espece("Fox-terrier à poil dur", 10), new Dossier("En forme !"));
            IAnimal a2 = new Animal("Flipper", "Inconnu", new Espece("Grand dauphin", 13), new Dossier("En forme !"));
            IAnimal a3 = new Animal("Idefix", "Asterix", new Espece("Schnauzer blanc", 20), new Dossier("En forme !"));
            IAnimal a4 = new Animal("BipBip", "Inconnu", new Espece("Grand Géocoucou", 8), new Dossier("En forme !"));

            /* Le serveur créer un cabinet */
            ICabinetVeterinaire cabinet = new CabinetVeterinaire("OuafOuafMedic");

            /* Le serveur ajoute les animaux au cabinet */
            cabinet.addAnimal(a1);
            cabinet.addAnimal(a2);
            cabinet.addAnimal(a3);
            cabinet.addAnimal(a4);

            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("RmiRegistry not found");
            }
            else{
                registry.bind("Cabinet", cabinet); // Le serveur envoi un proxy du cabinet au client
                System.out.println("Server ready");
            }
        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
