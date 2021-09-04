package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEspece extends Remote{

    String getNomEspece() throws RemoteException;
    void setNomEspece(String nom) throws RemoteException;
    int getDureeDeVieMoy() throws RemoteException;
    void setDureeDeVieMoy(int duree) throws RemoteException;
    String getInfos() throws RemoteException;
    void printInfos() throws RemoteException;
}
