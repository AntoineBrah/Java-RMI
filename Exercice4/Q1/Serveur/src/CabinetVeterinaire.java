package src;

import Commun.src.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class CabinetVeterinaire extends UnicastRemoteObject implements ICabinetVeterinaire{
    
    private String nomCabinet;
    private ArrayList<IAnimal> cabinet;

    public CabinetVeterinaire(String nom) throws RemoteException{ 
        this.nomCabinet = nom;
        cabinet = new ArrayList<IAnimal>();
    }

    public void addAnimal(IAnimal a) throws RemoteException{ 
        cabinet.add(a);
    }

    public void createAnimal(String nomA, String nomM, String nomE, int d, String s) throws RemoteException{
        IEspece e = new Espece(nomE, d);
        IDossier suivi = new Dossier(s);
        IAnimal a = new Animal(nomA, nomM, e, suivi);

        cabinet.add(a);
    }

    public void removeAnimal(IAnimal a) throws RemoteException{ 
        cabinet.remove(this.cabinet.indexOf(a));
    }

    public IAnimal getAnimal(String nomAnimal) throws RemoteException{ 

        IAnimal a1 = null;
        
        for(IAnimal a2 : cabinet){       

            if(a2.getName().equals(nomAnimal)){
                a1 = a2;
                break;
            }
        }

        return a1;
    }

    public String getInfosPatients() throws RemoteException{

        String infos = "";

        for(IAnimal a : cabinet){
            infos += a.infos();
            infos += "\n-------------\n";
        }

        return infos;
    }

    public void printInfosPatients() throws RemoteException{
        System.out.println(this.getInfosPatients());
    }


}
