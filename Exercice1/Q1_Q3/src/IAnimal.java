package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote{
    String infos() throws RemoteException;
    void printInfos() throws RemoteException;
    Dossier getDossier() throws RemoteException;
}
