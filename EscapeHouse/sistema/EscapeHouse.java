package EscapeHouse.sistema;

// aca implementamos cada metodo del menu

import EscapeHouse.estructuras.AVL;
import EscapeHouse.estructuras.Grafo;
import EscapeHouse.modelos.Equipo;

import java.util.HashMap;

public class EscapeHouse {
    AVL habitaciones;
    HashMap<Integer, Equipo> equipos;
    Grafo esquema;

    public void inicializar(AVL habits,HashMap<Integer, Equipo> equipos,AVL desafios){
        this.habitaciones = habits;
        this.equipos=equipos;
    }
    public void mostrarHabitación(){
        System.out.println("Hola");
    }

    public void habitacionesContiguas(){

    }

    //...

}
