package EscapeHouse.modelos;
import EscapeHouse.estructuras.AVL;
import EscapeHouse.estructuras.Lista;

public class Habitacion {
    private int codigo;
    private String nombre;
    private int planta;
    private int metros;
    private boolean salida;
    private AVL desafios;

    public Habitacion(int cod, int pla, int met){
        this.codigo = cod;
        this.nombre = "";
        this.planta = pla;
        this.metros = met;
        this.salida = false;
        this.desafios = new AVL();
    }

    public Habitacion(int cod, String nom, int pla, int met, boolean esSalida){
        this.codigo = cod;
        this.nombre = nom;
        this.planta = pla;
        this.metros = met;
        this.salida=esSalida;
        this.desafios = new AVL();
    }

    public Habitacion(int cod){
        this.codigo = cod;
        this.nombre = "";
        this.desafios = new AVL();
    }


    public int getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getPlanta(){
        return this.planta;
    }

    public int getMetros(){
        return this.metros;
    }

    public boolean getSalida(){
        return this.salida;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setSalida(boolean salida){
        this.salida = salida;
    }

    public String obtenerDatoDesafio(Short cod){
    return desafios.obtenerDatos(cod);
    }

    public String toString(){
        String s = "Nombre: "+this.nombre+"| Planta: "+this.planta+"| Metros a 2: "+this.metros+"| esSalida: "+this.salida+".";
        return s;
    }

    public Lista mostrarDesafios(){
        Lista listaDesafios = desafios.listar();
        return listaDesafios;
    }
}
