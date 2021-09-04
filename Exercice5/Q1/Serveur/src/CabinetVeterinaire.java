package src;

import Commun.src.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;


public class CabinetVeterinaire extends UnicastRemoteObject implements ICabinetVeterinaire{
    
    private String nomCabinet;
    private ArrayList<IAnimal> cabinet;
    private IAnnuaireClient annuaire;

    public static boolean palier1 = false; // 100
    public static boolean palier2 = false; // 500
    public static boolean palier3 = false; // 1000

    public CabinetVeterinaire(String nom) throws RemoteException{ 
        this.nomCabinet = nom;
        cabinet = new ArrayList<IAnimal>();
        annuaire = new AnnuaireClient();
    }

    public void addAnimal(IAnimal a) throws RemoteException{ 
        cabinet.add(a);

        if(this.getNombrePatients() >= 100 && this.getNombrePatients() < 500 && !palier1){
            palier1 = true;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet possède au moins " + Couleur.YELLOW() + "100 " + Couleur.RESET() + "patients !");
        }
        else if(this.getNombrePatients() >= 500 && this.getNombrePatients() < 1000 && !palier2){
            palier2 = true;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet possède au moins " + Couleur.YELLOW() + "500 " + Couleur.RESET() + "patients !");
        }
        else if(this.getNombrePatients() >= 1000 && !palier3){
            palier3 = true;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet possède au moins " + Couleur.YELLOW() + "1000 " + Couleur.RESET() + "patients !");
        }
    }

    public void createAnimal(String nomA, String nomM, String nomE, int d, String s, long id) throws RemoteException{
        IEspece e = new Espece(nomE, d);
        IDossier suivi = new Dossier(s);
        IAnimal a = new Animal(nomA, nomM, e, suivi, id);

        this.addAnimal(a);
    }

    public void removeAnimal(IAnimal a) throws RemoteException{ 
        
        cabinet.remove(a);

        if(this.getNombrePatients() < 100 && palier1){
            palier1 = false;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "100 " + Couleur.RESET() + "patients !");
        }
        else if(this.getNombrePatients() < 500 && palier2){
            palier2 = false;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "500 " + Couleur.RESET() + "patients !");
        }
        else if(this.getNombrePatients() < 1000 && palier3){
            palier3 = false;
            annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "1000 " + Couleur.RESET() + "patients !");
        }
    }

    public void removeAllAnimalFromAClient(long idClient) throws RemoteException{

        Iterator<IAnimal> iterateur = cabinet.iterator();

        while (iterateur.hasNext()) {
            IAnimal a = iterateur.next();
            if (a.getAddByClientId()==idClient){
                iterateur.remove();

                if(this.getNombrePatients() < 100 && palier1){
                    palier1 = false;
                    annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "100 " + Couleur.RESET() + "patients !");
                }
                else if(this.getNombrePatients() < 500 && palier2){
                    palier2 = false;
                    annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "500 " + Couleur.RESET() + "patients !");
                }
                else if(this.getNombrePatients() < 1000 && palier3){
                    palier3 = false;
                    annuaire.alertAllClient(Couleur.RED() + "[!] " + Couleur.RESET() + "Le cabinet vient de passer en dessous de " + Couleur.YELLOW() + "1000 " + Couleur.RESET() + "patients !");
                }
            
            }
          }
    }

    public void animNumber(long idClient) throws RemoteException{
        int i =0;

        for(IAnimal a : cabinet){

            if(a.getAddByClientId() == idClient){
                i++;
            }
        }

        System.out.println("Le client n° " + idClient + " possède : " + i + " animaux");
    }

    public IAnimal getAnimal(String nomAnimal) throws RemoteException{ 

        IAnimal a1 = null;
        
        for(IAnimal a2 : cabinet){       

            if(a2.getName().equals(nomAnimal)){
                a1 = a2;
                break;
            }
        }

        return a1;
    }

    public String getInfosPatients() throws RemoteException{

        String infos = "";

        for(IAnimal a : cabinet){
            infos += a.infos();
            infos += "\n-------------\n";
        }

        return infos;
    }

    public void printInfosPatients() throws RemoteException{
        System.out.println(this.getInfosPatients());
    }

    public int getNombrePatients() throws RemoteException{
        return this.cabinet.size();
    }

    public void loginClient(IClient c) throws RemoteException{
        annuaire.addClient(c);
        annuaire.alertAllClient("[i] Le Client n°" + Couleur.YELLOW() + c.getNumClient() + Couleur.RESET() + " vient de se logger.");
    }

    public void logoutClient(IClient c) throws RemoteException{
        annuaire.removeClient(c);
        annuaire.alertAllClient("[i] Le Client n°" + Couleur.YELLOW() + c.getNumClient() + Couleur.RESET() + " vient de se déconnecter.");
    }

}
