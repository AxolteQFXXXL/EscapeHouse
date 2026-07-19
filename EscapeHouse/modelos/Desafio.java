package modelos;

public class Desafio {
    private Comparable puntaje;
    private int codigoHabitacion;
    private String nombre;
    private String tipo;

    public Desafio(Comparable punt){
        this.puntaje = punt;
        this.codigoHabitacion = 0;
        this.nombre = "";
        this.tipo = "";
    }

    public Desafio(Comparable puntaje, int codHab, String nombre, String tipo){
        this.puntaje = puntaje;
        this.codigoHabitacion = codHab;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Comparable getPuntaje(){
        return this.puntaje;
    }

    public int getCodigoHabitacion(){
        return this.codigoHabitacion;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setCodigoHabitacion(int codHab){
        this.codigoHabitacion = codHab;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String s = "PuntajeOtorgado:" + this.puntaje + "| Nombre: " + this.nombre + "| tipo: " + this.tipo + "| en Habitacion:" + this.codigoHabitacion+".";
        return s;
    }
}
