/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author matias.miriuka
 */
public class Lista {
    private Nodo cabecera;

    public Lista(){
        cabecera = null;
    }

    public boolean insertar(Object elem, int pos){
        boolean funciona = true;

        if (pos < 1 || pos > this.longitud() + 1){
            funciona = false;
        } else {
            if (pos == 1){
                this.cabecera = new Nodo(elem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos -1){
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }

        return funciona;
    }

    public boolean eliminar(int pos){
        boolean funciona = false;
        Nodo aux = this.cabecera;

        if(cabecera != null){
            if (pos > 0 && pos <= this.longitud()){
                if(pos == 1 ){//caso especial si se quiere eliminar el primer nodo se cambia la cabecera al siguiente nodo
                    this.cabecera = this.cabecera.getEnlace();
                }else{
                    //lleva el enlace hasta el anterior del que queremos eliminar
                    for(int i = 1; i < pos-1; i++){
                        aux = aux.getEnlace();
                    }
                    //se saltea el enlace en pos para eliminar el nodo
                    if (aux.getEnlace() == null){
                        aux = null;
                    } else {
                        aux.setEnlace(aux.getEnlace().getEnlace());
                    }
                }
                funciona = true;
            }
        }

        return funciona;
    }

    public Object recuperar(int pos){
        Object elem = "la posicion esta fuera de rango";

        Nodo aux = this.cabecera;
        if (pos > 0 && pos <= this.longitud()){
            for(int i = 1; i < pos; i++){
                aux = aux.getEnlace();
            }
            elem = aux.getElem();
        }

        return elem;
    }

    public int localizar(Object elem){
        int pos = 0;
        boolean encontrado = false;
        Nodo aux = this.cabecera;

        while (aux != null && !encontrado){
            pos++;
            if (aux.getElem() == elem){
                encontrado = true;
            }
            aux = aux.getEnlace();
        }
        if (!encontrado){
            pos = -1;
        }
        return pos;
    }

    public int longitud(){
        int longi = 0;
        Nodo aux = this.cabecera;

        while (aux != null){
            longi++;
            aux = aux.getEnlace();
        }

        return longi;
    }

    public boolean esVacia(){
        boolean funciona = false;
        if(this.cabecera == null){
            funciona = true;
        }
        return funciona;
    }

    public void vaciar(){
        this.cabecera = null;
    }

    public Lista clone(){
        Lista copia= new Lista();
        int pos = 1;
        if(this.cabecera !=null){
            Nodo aux = this.cabecera;
            while (aux != null){
                copia.insertar(aux.getElem(), pos);
                pos++;
                aux = aux.getEnlace();
            }
        }

        return copia;
    }

    public String toString(){
        String s = "";
        if (this.cabecera == null){
            s = "[]";

            //s += " cabecera: null";
        } else {
            Nodo aux = this.cabecera;
            s = "[";
            while (aux != null){
                if (aux.getElem() == null){
                    s += "null";
                } else {
                    s += aux.getElem().toString();
                }
                aux = aux.getEnlace();
                if (aux != null)
                    s += ",";

            }
            s += "]";

            //s += " cabecera: " + this.cabecera.getElem().toString();
        }
        return s;
    }
    /*Agregar al TDA Lista la operación obtenerMultiplos(int num) que recibe un número y devuelve una lista nueva
que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA.
Ejemplo: si se invoca con la lista <A,B,C,D,E,F,G,H,I,J> y num=3, el método debe devolver la lista <C,F,I>*/

    public Lista obtenerMultiplos(int num){
        int contador = 1;
        int pos = 1;
        Lista resultado = new Lista();

        if (this.cabecera != null){
            Nodo aux = this.cabecera;

            while (aux != null){
                if (contador%num == 0){
                    if (pos == 1){
                        resultado.cabecera = new Nodo(aux.getElem(), resultado.cabecera);
                    } else {
                        Nodo aux2 = resultado.cabecera;
                        int i = 1;
                        while (i < pos -1){
                            aux2 = aux2.getEnlace();
                            i++;
                        }
                        Nodo nuevo = new Nodo(aux.getElem(), aux2.getEnlace());
                        aux2.setEnlace(nuevo);
                    }
                    pos++;
                }
                contador++;
                aux = aux.getEnlace();
            }

        }
        return resultado;
    }

    /*b) Agregar al TDA Lista la operación eliminarApariciones(TipoElemento x) que elimine todas las apariciones de
elementos iguales a x, haciendo un único recorrido de la estructura y sin usar otras operaciones del TDA.
*/
    public void eliminarApariciones(Object x){

        if (this.cabecera != null){
            Nodo aux = this.cabecera;

            if (aux.getEnlace() == null && aux.getElem() == x){
                this.cabecera = null;
            }

            while (aux.getEnlace() != null){

                if (aux.getElem() == x){
                    this.cabecera = aux.getEnlace();
                } else if (aux.getEnlace().getElem() == x){
                    aux.setEnlace(aux.getEnlace().getEnlace());
                }

                aux = aux.getEnlace();
            }
        }
    }

}