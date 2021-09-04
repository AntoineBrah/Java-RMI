package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinetVeterinaire extends Remote{
    void addAnimal(IAnimal a) throws RemoteException;
    void removeAnimal(IAnimal a) throws RemoteException;
    IAnimal getAnimal(String nomAnimal) throws RemoteException;
    String getInfosPatients() throws RemoteException;
}
