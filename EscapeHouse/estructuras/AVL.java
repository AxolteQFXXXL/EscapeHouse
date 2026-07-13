package EscapeHouse.estructuras;

import EscapeHouse.estructuras.Lista;

public class AVL {
    public NodoAvl raiz;

    public AVL(){ this.raiz=null;}

    public boolean insertar(Object elem){

        if(this.esVacio()){
            this.raiz = new NodoAvl(elem);
        }else{
            this.raiz=insertarAux((Comparable) elem, this.raiz);
        }

        return true;
    }

    private NodoAvl insertarAux(Comparable elem, NodoAvl root){


        if(root!=null){
            if(elem.compareTo(root.getElem())!=0){
                if(elem.compareTo(root.getElem())<0){
                    root.setIzquierdo(insertarAux(elem, root.getIzquierdo()));
                }else if(elem.compareTo(root.getElem())>0){
                    root.setDerecho(insertarAux(elem, root.getDerecho()));
                }
            }
        }else root = new NodoAvl(elem);


        return rebalance(root);
    }

    private NodoAvl rebalance(NodoAvl root){
        actualizarAltura(root);
        int balance=getBalance(root);
        NodoAvl aux = null;

        if(balance>1){ //desbalance a la izquierda
            if(getBalance(root.getIzquierdo())<0) root.setIzquierdo(rotarIzquierda(root.getIzquierdo()));//rotacion izquierdo derecha doble/

            aux =rotarDerecho(root);

        }

        if(balance<-1){//desbalance a la derecha
            if(getBalance(root.getDerecho())>0) root.setDerecho(rotarDerecho(root.getDerecho()));//rotacion derecha izquierda doble/

            aux = rotarIzquierda(root);
        }

        return  aux != null? aux : root ;
    }

    public boolean esVacio(){ return this.raiz==null;}

    public Object minimoElem(){
        Object objetivo;
        NodoAvl root = this.raiz;
        while(root.getDerecho()!=null) root = root.getDerecho();
        objetivo = root.getElem();
        return objetivo;
    }

    public Object maximoElem(){
        Object objetivo;
        NodoAvl root = this.raiz;
        while(root.getIzquierdo()!=null) root = root.getIzquierdo();
        objetivo = root.getElem();
        return objetivo;
    }

    public Lista listar(){
        Lista lis = new Lista();
        int[] i = {1};
        if(!this.esVacio()) listarAux(this.raiz, lis, i);
        return lis;
    }

    void listarAux(NodoAvl root, Lista lis, int[] lon){
        if(root!=null){
            if(root.getIzquierdo()!=null) listarAux(root.getIzquierdo(), lis, lon);
            lis.insertar(root.getElem(), lon[0]); lon[0]+=1;
            if(root.getDerecho()!=null) listarAux(root.getDerecho(), lis, lon);
        }
    }

    public String toString(){
        String sa ="";

        if(this.raiz!=null) sa = toStringAux(this.raiz, sa);

        return sa;
    }

    String toStringAux(NodoAvl root, String sa){

        if(root!=null){
            sa = "Nodo:"+root.getElem() + ": HI:";

            if(root.getIzquierdo()!=null) sa+= root.getIzquierdo().getElem()+" ";
            else sa+="- ";

            sa+="HD:";
            if(root.getDerecho()!=null) sa+= root.getDerecho().getElem()+"\n";
            else sa+="-\n";

            if(root.getIzquierdo()!=null) sa += toStringAux(root.getIzquierdo(), sa);
            if(root.getDerecho()!=null) sa += toStringAux(root.getDerecho(), sa);
        }

        return sa;
    }

    void actualizarAltura(NodoAvl n) {
        if(n!=null) n.setAltura(1+Math.max(altura(n.getDerecho()), altura(n.getIzquierdo())));
    }

    int altura(NodoAvl n) {
        return n == null ? -1 : n.getAltura();
    }

    int getBalance(NodoAvl n) {
        return (n == null) ? 0 : altura(n.getIzquierdo()) - altura(n.getDerecho());
    }

    NodoAvl rotarDerecho(NodoAvl piv){

        NodoAvl h = piv.getIzquierdo();
        NodoAvl temp = h.getDerecho();
        h.setDerecho(piv);
        piv.setIzquierdo(temp);
        actualizarAltura(h);
        actualizarAltura(temp);

        return h;
    }

    NodoAvl rotarIzquierda(NodoAvl piv){

        NodoAvl h = piv.getDerecho();
        NodoAvl temp = h.getIzquierdo();
        h.setIzquierdo(piv);
        piv.setDerecho(temp);
        actualizarAltura(h);
        actualizarAltura(temp);

        return h;
    }

}