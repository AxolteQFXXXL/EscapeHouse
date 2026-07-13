package EscapeHouse.estructuras;

public class AVL {
    public NodoAvl raiz;

    public AVL(){ this.raiz=null;}

    public boolean insertar(Comparable clave, Object elem){
        
        if(this.raiz==null){
            this.raiz = new NodoAvl(clave, elem);
        }else{
            this.raiz=insertarAux(clave, elem, this.raiz);
        }
        
        return true;
    }

    private NodoAvl insertarAux(Comparable clave, Object elem, NodoAvl root){


        if(root!=null){
            if(clave.compareTo(root.getClave())!=0){
                if(clave.compareTo(root.getClave())<0){
                    root.setIzquierdo(insertarAux(clave, elem, root.getIzquierdo()));
                }else if(clave.compareTo(root.getClave())>0){
                    root.setDerecho(insertarAux(clave, elem, root.getDerecho()));
                }
            }
        }else root = new NodoAvl(clave, elem);

        
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
            sa = "Clave:"+root.getClave()+": HI:";

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

    //Recibe un elem, lo busca por el AVL de habitaciones y si lo encuentra devuelve todos sus datos.
    public String obtenerDatos(Comparable clave){
        String datos=null;
        NodoAvl habitacion= null;

        if(this.raiz!=null){
            habitacion=buscarAux(this.raiz, clave);
            if(habitacion!=null){
                datos=habitacion.getElem().toString();
            }
        }


        return datos;
    }

    private NodoAvl buscarAux(NodoAvl n, Comparable clave){
        NodoAvl hab= null;

        if(clave.compareTo(n.getClave())== 0){
            hab=n;
        }else{
            if(clave.compareTo(n.getClave())<0){

                if(n.getIzquierdo()!= null){
                    hab= buscarAux(n.getIzquierdo(), clave);
                }
            }else{

                if(n.getDerecho()!= null){
                    hab= buscarAux(n.getDerecho(), clave);
                }
            }

        }

        return hab;
    }

}