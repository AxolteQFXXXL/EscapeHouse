package sistema;
import modelos.*;
import estructuras.AVL;
import estructuras.Grafo;
import estructuras.*;

// aca implementamos cada metodo del menu

public class EscapeHouse {

    private Grafo casa; //plano de la casa mediante un grafo
    private AVL habitaciones;

    public EscapeHouse(){
        this.casa= new Grafo();
        this.habitaciones = new AVL();
    }

    public EscapeHouse(AVL avl, Grafo graf){ //Constructor para el testeo de los metodos
        this.casa= graf;
        this.habitaciones = avl;
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

        if(datos==""){
            datos="No se encontró la habitación con el código "+codigo;
        }

        return datos;
    }
    //...

}
