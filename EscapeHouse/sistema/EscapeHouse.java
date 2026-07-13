package EscapeHouse.sistema;
import EscapeHouse.modelos.*;
import EscapeHouse.estructuras.AVL;
import EscapeHouse.estructuras.Grafo;
import EscapeHouse.estructuras.*;

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

    public String mostrarHabitación(int codigo){
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

    public String mostrarHabitacion(Short cod){
        String aMostrar = habitaciones.obtenerDatos(cod);

        return  aMostrar;
    }

    public String mostrarDesafio(Short cod1, Short cod2){
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod2);
        return hab.obtenerDatoDesafio(cod1);
    }
    //...

}
