package sistema;
import modelos.*;
import estructuras.*;

import java.util.HashMap;

// aca implementamos cada metodo del menu

public class EscapeHouse {

    private Grafo casa; //plano de la casa mediante un grafo
    private AVL habitaciones;
    private HashMap<Integer, Equipo> equipos;

    public EscapeHouse(){
        this.casa= new Grafo();
        this.habitaciones = new AVL();
    }

    public void inicializar(AVL avl, Grafo graf, HashMap<Integer, Equipo> equipos){ //Constructor para el testeo de los metodos
        this.casa= graf;
        this.habitaciones = avl;
        this.equipos=equipos;
    }

    public String mostrarHabitacion(int codigo){
        String datos=habitaciones.obtenerDatos(codigo);
        
        if(datos==null){
            datos="No se encontró la habitación con el código "+codigo;
        }
        
        return datos;
    }
    
    public String habitacionesContiguas(Object codigo){
        String datos=casa.mostrarAdyacentes(codigo);

        if(datos=="") {
            datos = "No se encontró la habitación con el código " + codigo;
        }

        return datos;
    }

    //metodo que calcula la cantidad de puntos minima y la resta con la requerida, devolviendo true or false si llega al destino.
    public boolean esPosibleLlegar(Object codigo1, Object codigo2, int puntos){
        boolean esPosible=false;
        Lista habitaciones= casa.caminoMasLiviano(codigo1, codigo2);
        if(!habitaciones.esVacia()){
            int aux=(int) habitaciones.recuperar(habitaciones.longitud());
            int calculo=puntos- aux;
            if(aux>=0 && calculo>=0){
                esPosible=true;
            }
        }

        return esPosible;
    }

    public String minimoPuntaje(Object codigo1, Object codigo2){
        String resultado="";
        Lista camino= casa.caminoMasLiviano(codigo1, codigo2);
        int ultPos= camino.longitud();
        Object puntaje=camino.recuperar(ultPos);
        camino.eliminar(ultPos);

        resultado="Puntaje minimo necesario: "+puntaje+"\n"+"Camino a realizar: "+camino.toString();

        return resultado;
    }

    public String sinPasarPor(Object origen, Object destino, Object prohibido, int puntMax){
        String res=null;
        Lista caminos= casa.caminosConRestricciones(origen, destino, prohibido, puntMax);
        if(caminos.esVacia()){
            res="Los datos ingresados son incorrectos.";
        }else{
            res="Caminos posibles: "+caminos.toString();
        }

        return res;
    }

    public String mostrarDesafio(Short cod1, Short cod2){
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod2);
        return hab.obtenerDatoDesafio(cod1);
    }

    public String mostrarInfoEquipos(String unNombre){
        String datos = "no se ha encontrado el equipo";

        //busca el equipo en la tabla
        Equipo equipoB = equipos.get(unNombre);
        //si lo encuentra guarda sus datos
        if(equipoB != null) datos = equipoB.misDatos();
        return datos;
    }

    public String posiblesDesafios(String nombreEquipo, short codigoHab){
        String s;
        //busco el equipo
        Equipo unEquipo = equipos.get(nombreEquipo);

       //verifico que sean adyacentes
        if(casa.existeArco(unEquipo.getHabitacionActual(), codigoHab)){
            //si existe el arco tengo q buscar q tiene en la etiqueta
            Lista camino = casa.caminoMasLiviano(unEquipo.getHabitacionActual(), codigoHab);
            int ultPos = camino.longitud();
            int puntajeNecesario =  (int) camino.recuperar(ultPos);
            puntajeNecesario = unEquipo.getPuntajeActual();
            //busco los desafios de la  habitacion actual
            Habitacion habActual = (Habitacion) habitaciones.obtenerElemento(unEquipo.getHabitacionActual());
            Lista desafios = habActual.mostrarDesafios();
            int pos = desafios.longitud();
            Lista desafiosPosibles = new Lista();
            //creo una lista y le inserto los desafios que cumplen la condicion 
            while(pos > 1 && puntajeNecesario <= (int) desafios.recuperar(pos)){
                desafiosPosibles.insertar(habActual.obtenerDatoDesafio((short) desafios.recuperar(pos)), 1);
            }
            if(!desafiosPosibles.esVacia()){
                s = "los desafios que bastan por si solos para llegar al puntaje son: " + "\n" + desafiosPosibles.toString();
            }else s = "No basta con hacer un desafio para llegar al puntaje";

        }else s = "No es posible acceder a la habitacion buscada desde la habitacion actual";

        return s;

    }
    //...

}
