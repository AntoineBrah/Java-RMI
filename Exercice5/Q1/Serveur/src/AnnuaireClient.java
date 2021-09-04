package src;

import Commun.src.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AnnuaireClient extends UnicastRemoteObject implements IAnnuaireClient{
    
    ArrayList<IClient> annuaire;

    public AnnuaireClient() throws RemoteException{
        annuaire = new ArrayList<IClient>();
    }

    public void addClient(IClient c) throws RemoteException{
        annuaire.add(c);
    }

    public void removeClient(IClient c) throws RemoteException{
        annuaire.remove(c);
    }

    public void alertClient(IClient c, String msg) throws RemoteException{
        c.alert(msg);
    }

    public void alertAllClient(String msg) throws RemoteException{
        for(IClient c : annuaire){
            c.alert(msg);
        }
    }

}
