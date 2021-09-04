package src;

import Commun.src.*;

public class EspeceFille extends Espece{

    String msg;

    public EspeceFille(){
        this.msg = "";
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public void printInfos(){
        System.out.println(this.msg);
    }

}