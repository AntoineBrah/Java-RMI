package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote{
    String infos() throws RemoteException;
    void printInfos() throws RemoteException;
    IDossier getDossier() throws RemoteException;
    IEspece getEspece() throws RemoteException;
    void printSeparator() throws RemoteException;
}
