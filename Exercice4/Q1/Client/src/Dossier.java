package src;

import Commun.src.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Dossier extends UnicastRemoteObject implements IDossier{

    String suivi;
    
    public Dossier() throws RemoteException{
        suivi = "null";
    }

    public Dossier(String suivi) throws RemoteException{
        this.suivi = suivi;
    }

    public String getSuivi() throws RemoteException{
        return suivi;
    }

    public void setSuivi(String suivi) throws RemoteException{
        this.suivi = suivi;
    }
}
