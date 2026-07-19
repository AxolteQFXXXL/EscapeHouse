package estructuras;

//este grafo es etiquetado pero aun no usamos las etiquetas

public class Grafo {
    private NodoVert inicio;

    public Grafo(){
        this.inicio = null;
    }

    //grafo no etiquetado
    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object elem){
        boolean exito = false;
        NodoVert auxE = null;

        if (this.inicio.getElem().equals(elem)){
            auxE = this.inicio;
            NodoAdy auxA = auxE.getPrimerAdy();
            while (auxA != null){
                eliminarArcoAux(auxA.getVertice(), elem);
                auxA = auxA.getSigAdyacente();
            }
            this.inicio = this.inicio.getSigVertice();
            exito = true;
        } else {
            NodoVert aux = this.inicio;
            NodoVert previo = null;
            while (auxE == null && aux.getSigVertice() != null){
                if (aux.getSigVertice().getElem().equals(elem)) {
                    previo = aux;
                    auxE = aux.getSigVertice();
                }
                aux = aux.getSigVertice();
            }
            if (auxE != null){
                NodoAdy auxA = auxE.getPrimerAdy();
                while (auxA != null){
                    eliminarArcoAux(auxA.getVertice(), elem);
                    auxA = auxA.getSigAdyacente();
                }
                previo.setSigVertice(auxE.getSigVertice());
                exito = true;
            }
        }

        return exito;
    }

    public boolean existeVertice(Object elem){
        NodoVert aux = this.inicio;
        boolean existe = false;

        while (!existe && aux != null){
            if (aux.getElem().equals(elem)){
                existe = true;
            }
            aux = aux.getSigVertice();
        }

        return existe;
    }

    public boolean insertarArco(Object elem1, Object elem2, int etiqueta){
        NodoVert inicio = ubicarVertice(elem1);
        NodoVert fin = ubicarVertice(elem2);
        boolean funciona = false;
        if (inicio != null && fin != null){
            if (inicio.getPrimerAdy() == null){
                inicio.setPrimerAdy(new NodoAdy(etiqueta, fin, null));
            } else {
                NodoAdy adyInicio = inicio.getPrimerAdy();
                while (adyInicio.getSigAdyacente() != null){
                    adyInicio = adyInicio.getSigAdyacente();
                }
                adyInicio.setSigAdyacente(new NodoAdy(etiqueta, fin, null));
            }
            if (fin.getPrimerAdy() == null){
                fin.setPrimerAdy(new NodoAdy(etiqueta, inicio, null));
            } else {
                NodoAdy adyFin = fin.getPrimerAdy();
                while (adyFin.getSigAdyacente() != null){
                    adyFin = adyFin.getSigAdyacente();
                }
                adyFin.setSigAdyacente(new NodoAdy(etiqueta, inicio, null));
            }
            funciona = true;
        }
        return funciona;
    }

    public boolean eliminarArco(Object origen, Object destino){
        boolean exito = false;
        // verifica si ambos vertices existen
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null){
            if (aux.getElem().equals(origen)) auxO=aux;
            if (aux.getElem().equals(destino)) auxD=aux;
            aux = aux.getSigVertice();
        }

        if (auxO != null && auxD != null) {
            eliminarArcoAux(auxO, destino);
            exito = eliminarArcoAux(auxD, origen);
        }
        return exito;
    }

    private boolean eliminarArcoAux (NodoVert n, Object objetivo){
        boolean realizado = false;
        if (n.getPrimerAdy() != null){
            if (n.getPrimerAdy().getVertice().getElem().equals(objetivo)){
                n.setPrimerAdy(n.getPrimerAdy().getSigAdyacente());
                realizado = true;
            } else{
                NodoAdy aux = n.getPrimerAdy();
                while (aux.getSigAdyacente() != null && !aux.getSigAdyacente().getVertice().getElem().equals(objetivo)){
                    aux = aux.getSigAdyacente();
                }
                if (aux.getSigAdyacente() != null){
                    aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                    realizado = true;
                }
            }
        }

        return realizado;
    }

    public boolean existeArco(Object origen, Object destino){
        NodoVert verticeAux = this.inicio;
        boolean existe = false;

        while (!existe && verticeAux != null){
            if (verticeAux.getElem().equals(origen)){
                NodoAdy adyAux = verticeAux.getPrimerAdy();
                while (!existe && adyAux != null){
                    if (adyAux.getVertice().getElem().equals(destino)){
                        existe = true;
                    }
                    adyAux = adyAux.getSigAdyacente();
                }
            }
            verticeAux = verticeAux.getSigVertice();
        }

        return existe;
    }

    //grafo no etiquetado
    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        // verifica si ambos vertices existen
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null){
            if (aux.getElem().equals(origen)) auxO=aux;
            if (aux.getElem().equals(destino)) auxD=aux;
            aux = aux.getSigVertice();
        }

        if (auxO != null && auxD != null) {
            // si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            // si vertice n es el destino: HAY CAMINO!
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                // si no es el destino verifica si hay camino entre n y destino
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino){
        Lista camino = new Lista();
        NodoVert vOrigen = ubicarVertice(origen);
        NodoVert vDestino = ubicarVertice(destino);


        if (vOrigen != null && vDestino != null) {
            Lista nodosVisitados = new Lista();
            Lista padres = new Lista();
            Cola q = new Cola();

            boolean encontrado = false;
            q.poner(vOrigen);
            nodosVisitados.insertar( vOrigen.getElem(), nodosVisitados.longitud() + 1);
            padres.insertar(null, padres.longitud() + 1); // El origen no tiene padre

            while (!q.esVacia() && !encontrado) {
                NodoVert u = (NodoVert) q.obtenerFrente();
                q.sacar();
                if (u.equals(vDestino)) {
                    encontrado = true;
                } else {
                    NodoAdy v = u.getPrimerAdy();
                    while (v != null) {
                        NodoVert vertDest = v.getVertice();
                        if (nodosVisitados.localizar(vertDest.getElem()) < 0) { //lista debe devolver -1 si no lo encuentra
                            nodosVisitados.insertar(vertDest.getElem(), nodosVisitados.longitud() + 1);
                            padres.insertar(u, padres.longitud() + 1);
                            q.poner(vertDest);
                        }
                        v = v.getSigAdyacente();
                    }
                }
            }
            if (encontrado) {
                // Reconstruir el camino desde el destino al origen
                NodoVert actual = vDestino;
                while (actual != null) {
                    camino.insertar(actual.getElem(), 1); // Insertar al inicio para que quede ordenado
                    int pos = nodosVisitados.localizar(actual.getElem());
                    actual = (NodoVert) padres.recuperar(pos);
                }
            }
        }
        return camino;
    }

    public Lista caminoMasLiviano(Object origen, Object destino) {
        Lista camino = new Lista();
        NodoVert vOrigen = ubicarVertice(origen);
        NodoVert vDestino = ubicarVertice(destino);

        if (vOrigen != null && vDestino != null) {
            Lista nodos=new Lista();//Lista de nodos vistos
            Lista padres=new Lista();
            Lista valorAcum=new Lista();//Lista de las distancias que guarda cada nodo
            Lista pendientes=new Lista();//Lista de nodos candidatos a revisar
            //Lista yaPisados= new Lista();

            //agrego al nodo inicial sin padre y con distancia 0
            nodos.insertar(vOrigen.getElem(),nodos.longitud()+1);
            padres.insertar(null, padres.longitud()+1);
            valorAcum.insertar(0,valorAcum.longitud()+1);
            //yaPisados.insertar(true, 1);
            pendientes.insertar(vOrigen,pendientes.longitud()+1);

            boolean encontrado = false;

            while(!pendientes.esVacia() && !encontrado){//Verifico cual es el nodo candidato a pisar y lo saco de pendientes
                int posMenor= 1;
                NodoVert u=(NodoVert) pendientes.recuperar(1);
                int distMinima=(int)valorAcum.recuperar(nodos.localizar(u.getElem()));//buscar distancia del nodo actual

                for (int i=2;i<=pendientes.longitud(); i++) {
                    NodoVert candidato=(NodoVert) pendientes.recuperar(i);
                    int posCandidato=nodos.localizar(candidato.getElem());
                    int distCandidato=(int) valorAcum.recuperar(posCandidato);

                    if(distCandidato<distMinima){
                        distMinima = distCandidato;
                        u = candidato;
                        posMenor = i;
                    }
                }

                pendientes.eliminar(posMenor);

                //Si el nodo a pisar es el destino terminamos y sino buscamos el siguiente a pisar entre los adyacentes
                if(u.equals(vDestino)){
                    encontrado = true;
                }else{
                    NodoAdy v=u.getPrimerAdy();
                    while(v!=null){
                        NodoVert vertDest= v.getVertice();
                        int nuevoPeso=distMinima+v.getEtiqueta();
                        int posDest= nodos.localizar(vertDest.getElem());

                        //Si no fue descubierto aún lo cargamos
                        if(posDest<0){
                            nodos.insertar(vertDest.getElem(), nodos.longitud()+1);
                            padres.insertar(u, padres.longitud()+1);
                            valorAcum.insertar(nuevoPeso, valorAcum.longitud()+1);
                            pendientes.insertar(vertDest, pendientes.longitud()+1);

                            //Si fue descubierto busco un mejor camino y lo actualizo en las listas
                        }else{
                            int distAnterior= (int)valorAcum.recuperar(posDest);
                            if(nuevoPeso<distAnterior){
                                padres.eliminar(posDest);
                                padres.insertar(u,posDest);

                                valorAcum.eliminar(posDest);
                                valorAcum.insertar(nuevoPeso, posDest);

                                // Si ya se había sacado de pendientes por otra ruta, se podría reevaluar
                                if(pendientes.localizar(vertDest)<0){
                                    pendientes.insertar(vertDest,pendientes.longitud()+1);
                                }
                            }
                        }
                        v= v.getSigAdyacente();
                    }
                }
            }

            if(encontrado){
                NodoVert actual=vDestino;
                while(actual!=null){
                    camino.insertar(actual.getElem(), 1);
                    int pos= nodos.localizar(actual.getElem());
                    if(pos>=1){
                        actual =(NodoVert) padres.recuperar(pos);
                    }else{
                        actual= null;
                    }
                }
                int posValorFinal= nodos.localizar(vDestino.getElem());
                int valorCamino=(int)valorAcum.recuperar(posValorFinal);
                camino.insertar(valorCamino, camino.longitud()+1);//almacena al final de la lista el valor minimo necesario para llegar al destino
            }
        }
        return camino;
    }

    public Lista caminoMasLargo(Object origen, Object destino){//---------------------------------------------------------------------------
        return null;
    }



    //grafo no etiquetado
    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        // define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                // si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {
            // marca al vertice n como visitado
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                // visita en profundidad los adyacentes de n aun no visitados
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean esVacio(){
        return this.inicio == null;
    }

    public void vaciar(){
        this.inicio = null;
    }

    public Grafo clone(){//---------------------------------------------------------------------------
        Grafo clon = new Grafo();
        NodoVert nodoAux = new NodoVert(this.inicio.getElem());
        clon.inicio=nodoAux;
        if (this.inicio != null){
            NodoAdy auxAdy = this.inicio.getPrimerAdy();
            if (auxAdy != null){
                nodoAux.setPrimerAdy(new NodoAdy(auxAdy.getEtiqueta(), auxAdy.getVertice(), null));
                auxAdy = auxAdy.getSigAdyacente();
            }
            NodoAdy clonAux = nodoAux.getPrimerAdy();
            while (auxAdy != null){
                clonAux.setSigAdyacente(new NodoAdy (auxAdy.getEtiqueta(), auxAdy.getVertice(), null));
                auxAdy = auxAdy.getSigAdyacente();
            }
            cloneAux(this.inicio.getSigVertice(), nodoAux);
        }
        return clon;
    }

    private void cloneAux(NodoVert n, NodoVert aux){
        if (n != null){
            aux.setSigVertice(new NodoVert (n.getElem()));
            NodoAdy auxAdy = n.getPrimerAdy();
            if (auxAdy != null){
                aux.getSigVertice().setPrimerAdy(new NodoAdy(auxAdy.getEtiqueta(), auxAdy.getVertice(), null));
                auxAdy = auxAdy.getSigAdyacente();
            }
            NodoAdy clonAux = aux.getSigVertice().getPrimerAdy();
            while (auxAdy != null){
                clonAux.setSigAdyacente(new NodoAdy (auxAdy.getEtiqueta(), auxAdy.getVertice(), null));
                clonAux = clonAux.getSigAdyacente();
                auxAdy = auxAdy.getSigAdyacente();
            }
            cloneAux(n.getSigVertice(), aux.getSigVertice());
        }
    }

    public String toString(){
        String grafo = "";
        // define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        NodoAdy auxAdy = null;
        while (aux != null) {
            grafo += aux.getElem() + ":";
            auxAdy = aux.getPrimerAdy();
            while (auxAdy != null){
                grafo += "-" + auxAdy.getEtiqueta() + "->" + auxAdy.getVertice().getElem() + " ";
                auxAdy = auxAdy.getSigAdyacente();
            }
            aux = aux.getSigVertice();
            if (aux != null){
                grafo += "\n";
            }
        }
        return grafo;
    }

    public String mostrarAdyacentes(Object elem){
        String datos="";

        NodoVert objetivo= ubicarVertice(elem);

        if(objetivo!=null){
            NodoAdy ady= objetivo.getPrimerAdy();
            while(ady!=null){
                datos+="Habitacion "+ ady.getVertice().getElem()+ " Puntaje "+ ady.getEtiqueta()+ "\n";
                ady=ady.getSigAdyacente();
            }
        }

        return datos;
    }

    public Lista caminosConRestricciones(Object origen, Object destino, Object prohibido, int puntMax){
        NodoVert vOrigen = ubicarVertice(origen);
        NodoVert vDestino = ubicarVertice(destino);
        Lista visitados= new Lista();
        Lista caminos= new Lista();
        int[] punt= new int[1];
        punt[0]=0;
        
        if(vOrigen != null && vDestino != null){
            recorreAux(visitados, vOrigen, vDestino, caminos, prohibido, punt, puntMax);
        }

        return caminos;
    }

    private void recorreAux(Lista visitados, NodoVert n, NodoVert dest, Lista caminos, Object pro, int[] punt, int puntMax){
        
        if(n!=null){
            visitados.insertar(n.getElem(), visitados.longitud()+1);
            if(n!=dest){

                NodoAdy v= n.getPrimerAdy();
                while(v!=null){
                    NodoVert u=v.getVertice();
                    if(visitados.localizar(u.getElem())<0 && u.getElem()!=pro){
                        punt[0]+=v.getEtiqueta();
                        recorreAux(visitados, u, dest, caminos, pro, punt, puntMax);
                        punt[0]-=v.getEtiqueta();
                    }
                    
                    v=v.getSigAdyacente();
                }
            }else{
                if(punt[0]<=puntMax){
                    caminos.insertar((visitados.toString()), caminos.longitud()+1);
                }
                
            }   
            
            visitados.eliminar(visitados.longitud());
        }
    }

}