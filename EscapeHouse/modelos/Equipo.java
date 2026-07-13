package EscapeHouse.modelos;

public class Equipo {
    private String nombre;
    private int puntajeNecesario;
    private int puntajeTotal;
    private int habitacionActual;
    private int puntajeActual;

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
}
