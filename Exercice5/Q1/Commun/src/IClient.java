package Commun.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote{
    void alert(String msg) throws RemoteException;
    long getNumClient() throws RemoteException;
}
