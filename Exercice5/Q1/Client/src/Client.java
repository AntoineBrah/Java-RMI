package src;

import Commun.src.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements IClient{

    long numClient;

    private static Client INSTANCE = null;
    
    public Client() throws RemoteException{
        numClient = ProcessHandle.current().pid();
    }

    public long getNumClient() throws RemoteException{
        return numClient;
    }

    public void alert(String msg) throws RemoteException{
        System.out.println(msg);
    }

    // Pattern singleton pour avoir un seul client par terminaux
    public synchronized static Client getInstance() throws RemoteException{
        if(INSTANCE == null)
            INSTANCE = new Client(); 

        return INSTANCE;
    }

    public static void main(String[] args) throws RemoteException{

        IClient c1 = Client.getInstance();

        String host = (args.length < 1)? null : args[0];

        String policy = "../../security.policy";
        System.setProperty("java.security.policy", policy);
        System.setSecurityManager(new SecurityManager());

        try{
            Registry registry = LocateRegistry.getRegistry(host);
            
            ICabinetVeterinaire cabinet = (ICabinetVeterinaire) registry.lookup("Cabinet");
            cabinet.loginClient(c1);

            while(true){

                System.out.println("\n==================================");
                System.out.println("Je souhaite : ");
                System.out.println("   1. Ajouter des Animaux au cabinet");
                System.out.println("   2. Me déconnecter");
                System.out.println("==================================\n");

                System.out.print("> ");

                Scanner input = new Scanner(System.in);
                String choix_str = input.nextLine();

                Integer choix_int = 0;

                try{
                    choix_int = Integer.parseInt(choix_str);
                }
                catch(Exception e){
                    System.out.println("La saisie entrée est invalide, veuillez recommencer.");
                }

                if(choix_int == 1){
                        System.out.println("Combien d'animaux souhaitez-vous ajouter au cabinet ?");
                        System.out.print("> ");

                        String choix_str2 = input.nextLine();
                        Integer choix_int2 = 0;

                        try{
                            choix_int2 = Integer.parseInt(choix_str2);
                        }
                        catch(Exception e){
                            System.out.println("La saisie entrée est invalide, retour au menu.");
                        }

                        if(choix_int2 > 0){
                            for(int i=0; i<choix_int2; i++){
                                cabinet.createAnimal("Animal", "Race", "Espece_Animal", 10, "Dossier_Animal", c1.getNumClient());
                            }
                            System.out.println("done.");
                            cabinet.animNumber(c1.getNumClient());
                        }
                }
                else if(choix_int == 2){
                    System.out.println("Aurevoir !");

                    cabinet.removeAllAnimalFromAClient(c1.getNumClient());
                    cabinet.logoutClient(c1);
                    break;
                }
            }

            System.exit(0);
        }
        catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
