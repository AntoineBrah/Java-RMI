package Commun.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnnuaireClient extends Remote{
    void addClient(IClient c) throws RemoteException;
    void removeClient(IClient c) throws RemoteException;
    void alertClient(IClient c, String msg) throws RemoteException;
    void alertAllClient(String msg) throws RemoteException;
}
