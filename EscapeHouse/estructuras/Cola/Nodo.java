package lineales.dinamicas;

public class Nodo{
    
    private Object elemento;
    private Nodo enlace;
    
    //Constructores
    public Nodo(Object elemento){
        this.elemento=elemento;
        this.enlace=null;
    }
    public Nodo(Object elemento, Nodo enlace){
        this.elemento=elemento;
        this.enlace=enlace;
    }
    
    //Modificadoras
    public void setElemento(Object elemento){
        this.elemento=elemento;
    }
    public void setEnlace(Nodo enlace){
        this.enlace=enlace;
    }
    
    //Observadoras
    public Object getElem(){
        return this.elemento;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }
    
}