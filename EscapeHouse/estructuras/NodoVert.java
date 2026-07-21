package EscapeHouse.estructuras;

public class NodoVert {
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object elemento){
        this.elem = elemento;
        this.sigVertice = null;
        this.primerAdy = null;
    }

    public NodoVert(Object elemento, NodoVert v){
        this.elem = elemento;
        this.sigVertice = v;
        this.primerAdy = null;
    }

    public Object getElem(){
        return this.elem;
    }

    public void setElem(Object elemento){
        this.elem = elemento;
    }

    public NodoVert getSigVertice(){
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert v){
        this.sigVertice = v;
    }

    public NodoAdy getPrimerAdy(){
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy a){
        this.primerAdy = a;
    }
}
