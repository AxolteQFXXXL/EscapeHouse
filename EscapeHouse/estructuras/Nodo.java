package EscapeHouse.estructuras;

public class Nodo {
    private Object elem;
    private Nodo enlace;

    public Nodo(Object elemento, Nodo unEnlace){
        this.elem = elemento;
        this.enlace = unEnlace;
    }

    public Object getElem(){
        return this.elem;
    }

    public Nodo getEnlace(){
        return this.enlace;
    }
    public void setElem(Object elemento){
        this.elem = elemento;
    }

    public void setEnlace(Nodo unEnlace){
        this.enlace = unEnlace;
    }

}