package src;

//import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Espece extends UnicastRemoteObject implements IEspece{   // /!\ si on ne retire pas extends UnicastRemoteObject (et on implémente pas Serializable) l'objet est toujours renvoyé par référence
    
    String nomEspece;
    int dureeDeVieMoy;

    public Espece(String nomEspece, int dureeDeVieMoy) throws RemoteException{
        this.nomEspece = nomEspece;
        this.dureeDeVieMoy = dureeDeVieMoy;
    }

    public String getNomEspece() throws RemoteException{
        return nomEspece;
    }

    public void setNomEspece(String nom) throws RemoteException{
        this.nomEspece = nom;
    }

    public int getDureeDeVieMoy() throws RemoteException{
        return dureeDeVieMoy;
    }

    public void setDureeDeVieMoy(int duree) throws RemoteException{
        this.dureeDeVieMoy = duree;
    }

    public String getInfos() throws RemoteException{
        return nomEspece + " et " + dureeDeVieMoy;
    }

    public void printInfos() throws RemoteException{
        System.out.println(this.getInfos());
    }
}
