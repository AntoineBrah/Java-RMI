package Commun.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinetVeterinaire extends Remote{
    void addAnimal(IAnimal a) throws RemoteException;
    void createAnimal(String nomA, String nomM, String nomE, int d, String s) throws RemoteException;
    void removeAnimal(IAnimal a) throws RemoteException;
    IAnimal getAnimal(String nomAnimal) throws RemoteException;
    String getInfosPatients() throws RemoteException;
    void printInfosPatients() throws RemoteException;
}
