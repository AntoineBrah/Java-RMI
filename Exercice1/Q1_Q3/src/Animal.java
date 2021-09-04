package src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal{

    String nomAnimal;
    String nomMaitre;
    String espece;
    Dossier dossierSuivi;

    public Animal(String nomA, String nomM, String espece, Dossier dossier) throws RemoteException{
        this.nomAnimal = nomA;
        this.nomMaitre = nomM;
        this.espece = espece;
        this.dossierSuivi = dossier;
    }

    public String infos() throws RemoteException{ 
        return "Nom animal : " + nomAnimal + "\nNom maitre : " + nomMaitre + "\nEspece : " + espece + "\nInfos dossier : " + dossierSuivi.getSuivi();
    }

    public void printInfos() throws RemoteException{
        System.out.println(this.infos());
    }

    public Dossier getDossier() throws RemoteException{
        return this.dossierSuivi;
    }

}