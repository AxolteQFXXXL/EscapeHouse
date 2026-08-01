package EscapeHouse.modelos;

import java.util.HashMap;

public class Equipo {
    private String nombre;
    private int puntajeNecesario;
    private int puntajeTotal;
    private int habitacionActual;
    private int puntajeActual;
    private HashMap<Integer, Desafio> resueltos = new HashMap<>();


    public Equipo(String nombre){
        this.nombre = nombre;
    }

    public Equipo(String nombre, int puntajeNecesario, int puntajeTotal, int habitacionActual, int puntajeActual){
        this.nombre = nombre;
        this.puntajeNecesario = puntajeNecesario;
        this.puntajeTotal = puntajeTotal;
        this.habitacionActual = habitacionActual;
        this.puntajeActual = puntajeActual;
    }

    public void agregarDesafioResuelto(int clave,Desafio unDes){
        resueltos.put(clave, unDes);
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getPuntajeNecesario(){
        return this.puntajeNecesario;
    }

    public int getPuntajeTotal(){
        return this.puntajeTotal;
    }

    public int getHabitacionActual(){
        return this.habitacionActual;
    }

    public int getPuntajeActual(){
        return this.puntajeActual;
    }

    public void setPuntajeNecesario(int puntajeNecesario){
        this.puntajeNecesario = puntajeNecesario;
    }

    public void setPuntajeTotal(int puntajeTotal){
        this.puntajeTotal = puntajeTotal;
    }

    public void setHabitacionActual(int habitacionActual){
        this.habitacionActual = habitacionActual;
    }

    public void setPuntajeActual(int puntajeActual){
        this.puntajeActual = puntajeActual;
    }

    public void sumarPuntActual(){
        this.puntajeTotal += this.puntajeActual;
        this.puntajeActual = 0;
    }

    public void agregarPuntajeActual(int puntos){
        this.puntajeActual+= puntos;
    }

    public void agregarDesafio(int clave, Desafio unD){
        resueltos.put(clave,unD);
    }

    public String misDatos(){
        String s = "Nombre: "+this.nombre+"| PuntajoNecesario:"+this.puntajeNecesario+"| PuntajeTotal:"+this.puntajeTotal+"| PuntajeActual:"+this.puntajeActual+"| HabitacionActual:"+this.habitacionActual+".";
        return s;
    }

    public String desafiosResueltos(){
        String ss="";
        for(Desafio des : resueltos.values()){
            ss += des.toString()+"\n";
        }
        return ss;
    }

    public boolean resolvioDesafio(int puntaje) {
        return resueltos.containsKey(puntaje);
    }
}
