package Commun.src;

public class Couleur {

    public static final String RESET(){
        return "\u001B[0m";
    } 

    public static final String YELLOW(){
        return "\033[1;33m";
    } 

    public static final String RED(){
        return "\033[1;31m";
    } 
}