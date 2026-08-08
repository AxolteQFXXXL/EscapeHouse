package EscapeHouse.estructuras.grafo;

public class NodoAdy {
    private int etiqueta;
    private NodoVert vertice;
    private NodoAdy sigAdyacente;

    public NodoAdy(int e){
        this.etiqueta = e;
        this.vertice = null;
        this.sigAdyacente = null;
    }

    public NodoAdy(int e, NodoVert v, NodoAdy a){
        this.etiqueta = e;
        this.vertice = v;
        this.sigAdyacente = a;
    }

    public int getEtiqueta(){
        return this.etiqueta;
    }

    public void setEtiqueta(int e){
        this.etiqueta = e;
    }

    public NodoVert getVertice(){
        return this.vertice;
    }

    public void setVertice(NodoVert v){
        this.vertice = v;
    }

    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy a){
        this.sigAdyacente = a;
    }
}