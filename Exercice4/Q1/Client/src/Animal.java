package src;

import Commun.src.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal{

    String nomAnimal;
    String nomMaitre;
    IEspece espece;
    IDossier dossierSuivi;
     
    public Animal(String nomA, String nomM, IEspece espece, IDossier dossier) throws RemoteException{
        this.nomAnimal = nomA;
        this.nomMaitre = nomM;
        this.espece = espece;
        this.dossierSuivi = dossier;
    }

    public String infos() throws RemoteException{ 
        return "Nom animal : " + nomAnimal + "\nNom maitre : " + nomMaitre + "\nInfos dossier : " + dossierSuivi.getSuivi() + "\nInfos espèce : " + espece.getInfos();
    }

    public void printInfos() throws RemoteException{
        System.out.println(this.infos());
        this.printSeparator();
    }

    public IDossier getDossier() throws RemoteException{
        return this.dossierSuivi;
    }

    public IEspece getEspece() throws RemoteException{
        return this.espece;
    }

    public void setEspece(IEspece espece) throws RemoteException{
        this.espece = espece;
    }

    public String getName() throws RemoteException{
        return this.nomAnimal;
    }

    public void printSeparator(){
        System.out.println("-------");
    }

}