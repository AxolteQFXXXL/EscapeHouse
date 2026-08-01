package EscapeHouse.sistema;
import EscapeHouse.modelos.*;
import EscapeHouse.estructuras.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EscapeHouse {

    private Grafo casa;
    private AVL habitaciones;
    private HashMap<String, Equipo> equipos;

    public EscapeHouse(){
        this.casa= new Grafo();
        this.habitaciones = new AVL();
    }

    public void inicializar(AVL avl, Grafo graf, HashMap<String, Equipo> equipos){ //Constructor para el testeo de los metodos
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
    public String esPosibleLlegar(Object codigo1, Object codigo2, int puntos){
        String esPosible= "No es posible.";
        Lista habitaciones= casa.caminoMasLiviano(codigo1, codigo2);
        if(!habitaciones.esVacia()){
            int aux=(int) habitaciones.recuperar(habitaciones.longitud());
            int calculo=puntos- aux;
            if(aux>=0 && calculo>=0){
                esPosible="Es es posible.";
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

    public String mostrarDesafio(int cod1, int cod2){
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod2);
        return hab.obtenerDatoDesafio( cod1);
    }

    public String mostrarInfoEquipos(String unNombre){
        String datos = "no se ha encontrado el equipo";

        //busca el equipo en la tabla
        Equipo equipoB = equipos.get(unNombre);
        //si lo encuentra guarda sus datos
        if(equipoB != null) datos = equipoB.misDatos();
        return datos;
    }

    public String posiblesDesafios(String nombreEquipo, int codigoHab){
        String s;
        //busco el equipo
        if(equipos.containsKey(nombreEquipo)) {
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
            Lista desafiosPosibles = new Lista();
            Desafio unDes;
            int puntajeActual = unEquipo.getPuntajeActual();
            int puntajeTotal;
            //creo una lista y le inserto los desafios que cumplen la condicion 
            while(!desafios.esVacia()){
                unDes = (Desafio) desafios.recuperar(1);
                puntajeTotal = (int) unDes.getPuntaje() + puntajeActual;
                if((puntajeTotal >= puntajeNecesario)) desafiosPosibles.insertar(unDes, 1);
                desafios.eliminar(1);
            }
            if(!desafiosPosibles.esVacia()){
                s = "los desafios que bastan por si solos para llegar al puntaje son: " + "\n" + desafiosPosibles.toString();
            }else s = "No basta con hacer un desafio para llegar al puntaje";

        }else s = "No es posible acceder a la habitacion buscada desde la habitacion actual";
    }else s = "No existe el equipo";
        return s;

    }

    /**
     * segun
     * @Param: equipo destinado a pasar la habitacion
     * @Param: habitacion a pasar
     * @return: true or false
     *
     *  se verifica si codigoHab es adyacente desde la HabitacionActual de Equipo
     *  y tambien si Equipo cuenta con puntajeActual necesario para hacerlo
     *
     */
    public boolean pasarAHabitacion(String nombreEquipo, int codigoHab){
        boolean exito = false;
        Equipo eq = equipos.get(nombreEquipo);

        if(casa.existeArco(eq.getHabitacionActual(),codigoHab)){
            int a =casa.getEtiquetaArco(eq.getHabitacionActual(), codigoHab);
            System.out.println("la etiqueta: "+a);
            if(eq.getPuntajeActual()>= a){
                System.out.println("la etiqueta: "+a);
                exito = true;
                eq.setHabitacionActual(codigoHab);
                eq.sumarPuntActual();
            }
        }

        return exito;
    }

    /**
     * @Param: equipo que jugara el desafio
     * @Param: habitacion que posee el desafio
     * @Param: codigo del desafio (su puntaje)
     * @return el valor del desafio pasado, o 0 si el desafios no se encuentra en habitacion.
     *
     * realizamos las acciones necesarias para que el equipo lo juege
     * el desafio se guarda en el hash de desafios del equipo y sumamos su puntajeActual
     *
     */
    public int equipoJuega(String nombreEquipo, int codigoHab, int codigoDes){
        int a = 0;
        Equipo eq = equipos.get(nombreEquipo);
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(codigoHab);
        Desafio des = hab.getUnDesafio(codigoDes);
        if(des !=null){
            a = (int) des.getPuntaje();
            eq.agregarPuntajeActual(a);
            eq.agregarDesafio((int)des.getPuntaje(), des);
        }

        return a;
    }

    /**  verifica que
     * @Param un equipo pueda
    * escapar de una habitacion segun su puntajeTotal
    * @return true or false, dependiendo tambien si
    * su habitacion actual es salida o no
    */
    public boolean equipoEscapa(String nombreEquipo){
        boolean exito = false;
        Equipo eq = equipos.get(nombreEquipo);
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(eq.getHabitacionActual());

        if(hab.getSalida()){
            if(eq.getPuntajeTotal()>=eq.getPuntajeNecesario()){
                exito = true;
                equipos.remove(nombreEquipo);
                
            }
        }

        return exito;
    }

    public String desafiosDelEquipo(String nombreEquipo){
        Equipo eq = equipos.get(nombreEquipo);
        return eq.desafiosResueltos();
    }

    public boolean verificarDesafioResuelto(String nombreEquipo, int cod1, int cod2){
        Desafio des = null;
        Equipo eq = equipos.get(nombreEquipo);
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod1);
        if(hab!=null) des = hab.getUnDesafio(cod2);

        return (des!=null) ? eq.resolvioDesafio((Integer) des.getPuntaje()) : null;
    }

    public String desafiosDeTipo(String nombreDes, int cod1, int a, int b) {
        String ss="";
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod1);
        Lista lis = hab.mostrarDesafios();

        while(!lis.esVacia()){
            Desafio des = (Desafio) lis.recuperar(1);
            if(des.getTipo().equals(nombreDes) && (des.getPuntaje().compareTo(a)>=0 && des.getPuntaje().compareTo(b)<=0)) ss+= des.toString();
            lis.eliminar(1);
        }

        return ss;
    }

    public boolean agregarHabitacion(Habitacion unH) {
        return habitaciones.insertar(unH.getCodigo(), unH);}

    public boolean eliminarHabitacion(int a) {
        boolean estaLlena = false;
        Iterator<Map.Entry<String, Equipo>> eq = equipos.entrySet().iterator();
        while(!estaLlena && eq.hasNext()){
            Map.Entry<String, Equipo> val = eq.next();
            Equipo unEquipo = val.getValue();
            if(unEquipo.getHabitacionActual() == a) estaLlena = true;

        }
        if(!estaLlena) estaLlena = habitaciones.eliminar(a);

        return estaLlena;
    }

    public void cambiarNombreHabit(int cod, String nuevoNombre) {
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod);
        if(hab != null) hab.setNombre(nuevoNombre);
    }

    public boolean agregarEquipo(Equipo eq) {
        equipos.put(eq.getNombre(), eq);
        return true;
    }

    public boolean eliminarEquipo(String nombreEquipo) {
        equipos.remove(nombreEquipo);
        return true;
    }

    public void incrementarPuntajeNecesario(String nombreEquipo, int c) {
        Equipo eq = equipos.get(nombreEquipo);
        int sum = eq.getPuntajeNecesario();
        sum += c;
        eq.setPuntajeNecesario(sum);
    }

    public void disminuirPuntajeNecesario(String nombreEquipo, int c) {
        Equipo eq = equipos.get(nombreEquipo);
        int sum = eq.getPuntajeNecesario();
        sum -= c;
        eq.setPuntajeNecesario(sum);
    }

    public boolean agregarDesafio(Desafio des) {
        boolean bool = false;
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(des.getCodigoHabitacion());
        if(hab !=null) bool = hab.AgregarDesafio((int)des.getPuntaje(), des);

        return bool;
    }

    public boolean eliminarDesafio(int cod1, int codHab) {
        boolean bool = false;
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(codHab);
        if(hab!=null) bool = hab.eliminarDesafio(cod1);

        return bool;
    }

    public void cambiarTipoDesafio(int cod1, int cod2, String nuevoTipo) {
        Habitacion hab = (Habitacion) habitaciones.obtenerElemento(cod2);
        if(hab!=null) hab.cambiarTipoDe(cod1, nuevoTipo);

    }

    public boolean agregarPuerta(int origen, int destino, int puntaje) {
        return casa.insertarArco(origen,destino,puntaje);
    }

    public boolean eliminarPuerta(int origen, int destino) {
        return casa.eliminarArco(origen, destino);
    }
    
    public String mostrarSistema (){
        String sEH, sC, sE, sH;
        sE = "";

        for (Equipo unE : equipos.values()) {
          sE +=  unE.misDatos() + "\n";
        }

        sC = casa.toString() + "\n";
        sH = habitaciones.toString();

        sEH = "La casa: " + "\n" + sC + "\n" + "los equipos: " + "\n" + sE + "\n" + "las habitaciones: " + sH;
        return sEH;
        }
}


