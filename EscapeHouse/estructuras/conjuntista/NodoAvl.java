package EscapeHouse.estructuras.conjuntista;

public class NodoAvl{
    private Comparable clave;
    private Object Elem;
    private int altura;
    private NodoAvl izquierdo;
    private NodoAvl derecho;

    public NodoAvl(Comparable clave, Object elem){
        this.clave= clave;
        this.Elem=elem;
        this.altura=0;
        this.izquierdo=null;
        this.derecho=null;
    }

    public Comparable getClave(){return this.clave;}
    public Object getElem(){ return this.Elem;}
    public int getAltura(){ return this.altura;}
    public NodoAvl getIzquierdo(){ return this.izquierdo;}
    public NodoAvl getDerecho(){ return this.derecho;}

    public void setElem(Object novoE){ this.Elem=novoE;}
    public void setAltura(int num){ this.altura=num;}
    public void setIzquierdo(NodoAvl novoN){ this.izquierdo=novoN;}
    public void setDerecho(NodoAvl novoN){ this.derecho=novoN;}
}