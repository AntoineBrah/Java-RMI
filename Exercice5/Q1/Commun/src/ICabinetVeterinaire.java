package Commun.src;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;

public interface ICabinetVeterinaire extends Remote{
    void addAnimal(IAnimal a) throws RemoteException;
    void createAnimal(String nomA, String nomM, String nomE, int d, String s, long id) throws RemoteException;
    void removeAnimal(IAnimal a) throws RemoteException;
    void removeAllAnimalFromAClient(long idClient) throws RemoteException;
    IAnimal getAnimal(String nomAnimal) throws RemoteException;
    String getInfosPatients() throws RemoteException;
    void printInfosPatients() throws RemoteException;
    int getNombrePatients() throws RemoteException;
    void loginClient(IClient c) throws RemoteException;
    void logoutClient(IClient c) throws RemoteException;
    void animNumber(long idClient) throws RemoteException;
}
