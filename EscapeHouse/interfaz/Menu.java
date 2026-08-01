package EscapeHouse.interfaz;

import EscapeHouse.modelos.Desafio;
import EscapeHouse.modelos.Equipo;
import EscapeHouse.modelos.Habitacion;
import EscapeHouse.sistema.EscapeHouse;
import EscapeHouse.sistema.GestorArchivos;

import java.util.Scanner;


public class Menu {
    Scanner scanner = new Scanner(System.in);
    EscapeHouse house;
    public void mostrarMenuPrincipal(EscapeHouse house) {
        int opcion;
        this.house=house;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                //faltaria opcion de la carga inicial del sistema y las opciones de ABM (añadir en, quitar en y modificar el sistema) ---------------------------------------------------------------
                case 1:
                    //carga del sistema
                    GestorArchivos gestorArchivos = new GestorArchivos();
                    gestorArchivos.cargarSistema(house);
                    break;
                case 2:
                    menuABM();//cambiar nombre a algo mejor ------------------------------------------------------------------------------------------------------------------------------
                    break;
                case 3:
                    menuConsultasHabitaciones();
                    break;
                case 4:
                    menuConsultasDesafios();
                    break;
                case 5:
                    menuConsultasEquipos();
                    break;
                case 6:
                    mostrarSistema();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE JUEGO DE HABITACIONES ===");
        System.out.println("1. Cargar sistema");
        System.out.println("2. Modificar Sistema");// cambiar por algo que tenga sentido ------------------------------------------------------------------------------------------------------------------------------
        System.out.println("3. Consultas sobre habitaciones");
        System.out.println("4. Consultas sobre desafíos");
        System.out.println("5. Consultas sobre equipos participantes");
        System.out.println("6. Mostrar sistema");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void menuABM() {// cambiar nombre de metodo ------------------------------------------------------------------------------------------------------------------------------
        int opcion;
        do {
        /*ABM: inserter, eliminar y modificar datos de los TDA Habitaciones, Desafios, Equipos.
        se puede empezar con todo vacio e ir cargando todo mediante una opcion de carga en el menú.
        se puede modificar nombre de habitaciones, metros cuadrados podría ser pero es a elección nuestra, solo se pueden añadir habitaciones que NO sean salida y la habitación de entrada podemos elegir
        que sea 1 o muchas, a criterio del alumno (pero hace falta especificar porque es donde empieza cada equipo).
        se puede modificar puntajes de puertas.
        se puede modificar nombre y tipo de desafíos.*/
            System.out.println("\n--- ALTAS, BAJAS Y MODIFICACIONES ---");
            //añadir una habitacion (con codigo, nombre, metros cuadrados y planta)
            //añadir un desafio (con puntaje, codigo de la habitacion, nombre y tipo)
            //añadir un equipo (con nombre, puntaje necesario para ganar, puntaje total acumulado, habitacion actual (empiezan en una que sea habitacion inicial), puntaje actual de la habitacion) (si solo tenemos 1 habitacion de entrada, cuando se crean colocarlos ahi con puntaje actual 0)
            //añadir una puerta (habitacion nro1, habitacion nro2, puntaje)
            //modificar nombre de una habitacion
            //modificar nombre de un desafio
            //modificar tipo de un desafio
            //modificar puntaje de una puerta
            //
            System.out.println("1. Modificacion de Habitaciones");
            System.out.println("2. Modificacion de Equipos");
            System.out.println("3. Modificacion de Desafios");
            System.out.println("4. Modificacion de Puertas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {// aca añadir cara modificacion
                case 1:
                    // modificar habitacion
                    menuModificarHabitaciones();
                    break;
                case 2:
                    // modificar Equipos
                    menuModificarEquipos();
                    break;
                case 3:
                    // medificar desasfios
                    menuModificarDesafios();
                    break;
                case 4:
                    // modificar Puertas
                    menuModificarPuertas();
                    break;
                case 5:
                    // sinPasarPor
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void menuModificarPuertas() {
        int opcion, a, b, c;
        do{
            System.out.println("\n--- MODIFICACION DE HABITACIONES ---");
            System.out.println("1. crear un nueva Puerta");
            System.out.println("2. eliminar un Desafio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    //crear puerta
                    System.out.println("Ingrese codigo habitacion origen: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese codigo habitacion destino: ");
                    b = scanner.nextInt();
                    System.out.println("Ingrese puntaje necesario para pasar: ");
                    c = scanner.nextInt();

                    if(house.agregarPuerta(a, b, c)) System.out.println("Se ha agregado correctamente!");
                    break;
                case 2:
                    // eliminar una puerta
                    System.out.println("Ingrese codigo habitacion origen: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese codigo habitacion destino: ");
                    b = scanner.nextInt();

                    if(house.eliminarPuerta(a, b)) System.out.println("Se ha eliminado Correctamente!");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }while(opcion!=0);
    }

    private void menuModificarDesafios() {
        int opcion, a, b, c, d;
        String st1, st2;
        do{
            System.out.println("\n--- MODIFICACION DE HABITACIONES ---");
            System.out.println("1. crear un nuevo Desafio");
            System.out.println("2. eliminar un Desafio");
            System.out.println("3. modificar tipo de un Desafio");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    //crear habitacion
                    System.out.println("Ingrese el puntaje que dara el desafio: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese su habitacion correspondiente: ");
                    b = scanner.nextInt();
                    System.out.println("Ingrese el nombre y el tipo de desafio: ");
                    st1 = scanner.nextLine();
                    st2 = scanner.nextLine();

                    Desafio des = new Desafio(a, b, st1, st2);
                    if(house.agregarDesafio(des)) System.out.println("Se ha agregado correctamente!");

                    break;
                case 2:
                    // eliminar un desafio
                    System.out.println("Ingrese el puntaje del desafio: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese el codigo de su habitacion: ");
                    b = scanner.nextInt();

                    if(house.eliminarDesafio(a, b)) System.out.println("Se ha eliminado Correctamente!");

                    break;
                case 3:
                    // modificar tipo desafio
                    System.out.println("Ingrese el puntaje del desafio: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese el codigo de su habitacion: ");
                    c = scanner.nextInt();
                    System.out.println("Ingrese el nueva tipo para el desafio: ");
                    st2 = scanner.nextLine();

                    house.cambiarTipoDesafio(a, c, st2);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }while(opcion!=0);
    }


    private void menuModificarHabitaciones() {
        int opcion, a, b, c, d;
        String st1;
        do{
            System.out.println("\n--- MODIFICACION DE HABITACIONES ---");
            System.out.println("1. crear una habitacion");
            System.out.println("2. eliminar una habitacion");
            System.out.println("3. modificar nombre de una habitacion");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    //crear habitacion
                    System.out.println("Ingrese un codigo mayor a 24: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese nombre de la habitacion:");
                    st1 = scanner.nextLine();
                    System.out.println("Ingrese en que planta estara y sus metros Cuadrados: ");
                    b = scanner.nextInt();
                    c = scanner.nextInt();

                    Habitacion unH = new Habitacion(a, st1, b, c, false);
                    if(house.agregarHabitacion(unH)) System.out.println("Se ha agregado correctamente!");;

                    break;
                case 2:
                    // eliminar una habitacion
                    System.out.println("Ingrese el codigo de la habitacion que desea eliminar: ");
                    a = scanner.nextInt();
                    if(house.eliminarHabitacion(a)) System.out.println("Se ha eliminado Correctamente!");

                    break;
                case 3:
                    // modificar nombre habitacion
                    System.out.println("Ingrese el codigo de la habitacion: ");
                    c = scanner.nextInt();
                    System.out.println("Que nombre desea colocarle: ");
                    st1 = scanner.nextLine();

                    house.cambiarNombreHabit(c, st1);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }while(opcion!=0);
    }

    private void menuModificarEquipos() {
        int opcion, a, b, c, d;
        String st1;
        do{
            System.out.println("\n--- MODIFICACION DE HABITACIONES ---");
            System.out.println("1. crear un nueva equipo");
            System.out.println("2. eliminar un equipo");
            System.out.println("3. incrementar puntaje necesario");
            System.out.println("4. disminuar puntaje necesario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    //crear equipo nuevo
                    System.out.println("Ingrese un nombre para su equipo: ");
                    st1 = scanner.nextLine();
                    System.out.println("Ingrese puntaje necesario para escapar:");
                    a = scanner.nextInt();
                    System.out.println("Ingrese su puntaje total, actual y luego en que habitacion comienza: ");
                    b = scanner.nextInt();
                    c = scanner.nextInt();
                    d = scanner.nextInt();
                    Equipo eq = new Equipo(st1, a, b,d,c);

                    if(house.agregarEquipo(eq)) System.out.println("Se ha agregado correctamente!");

                    break;
                case 2:
                    // eliminar un equipo
                    System.out.println("Ingrese el nombre del equipo que desea eliminar: ");
                    st1 = scanner.nextLine();
                    if(house.eliminarEquipo(st1)) System.out.println("Se ha eliminado correctamente!");;

                    break;
                case 3:
                    // incrementar puntaje necesario
                    System.out.println("Ingrese el nombre del equipo: ");
                    st1 = scanner.nextLine();
                    System.out.println("Cuanto quiere incrementarle(un valor): ");
                    c = scanner.nextInt();

                    house.incrementarPuntajeNecesario(st1, c);
                    break;
                case 4:
                    //disminuir puntaje necesario
                    System.out.println("Ingrese el nombre del equipo: ");
                    st1 = scanner.nextLine();
                    System.out.println("Cuanto quiere disminuirle(un valor): ");
                    c = scanner.nextInt();

                    house.disminuirPuntajeNecesario(st1, c);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }while(opcion!=0);
    }


    public void menuConsultasHabitaciones() {
        int opcion, a, b, c, d;
        do {
            System.out.println("\n--- CONSULTAS SOBRE HABITACIONES ---");
            System.out.println("1. mostrarHabitación");
            System.out.println("2. habitacionesContiguas");
            System.out.println("3. esPosibleLlegar");
            System.out.println("4. minimoPuntaje");
            System.out.println("5. sinPasarPor");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // mostrarHabitación
                    System.out.println("Que habitacion desea Conocer: ");
                    a = scanner.nextInt();
                    System.out.println(house.mostrarHabitacion(a));
                    break;
                case 2:
                    // habitacionesContiguas
                    System.out.println("De cual habitacion quiere conocer sus adyacentes: ");
                    a = scanner.nextInt();
                    System.out.println(house.habitacionesContiguas(a));
                    break;
                case 3:
                    // esPosibleLlegar
                    String resp;
                    System.out.println("Ingrese el codigo de la primer habitacion: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese el codigo de la segunda habitacion: ");
                    b = scanner.nextInt();
                    System.out.println("Ingrese la puntuacion: ");
                    c = scanner.nextInt();
                    if(house.esPosibleLlegar(a, b, c)){//¿Quieren que devuelva de esta forma la respuesta o un true/false simplemente?
                        resp= "Es posible llegar.";
                    }else{
                        resp="No es posible llegar con la puntuacion ingresada.";
                    }
                    System.out.println(resp);
                    break;
                case 4:
                    // minimoPuntaje
                    System.out.println("Ingrese el codigo de la primer habitacion: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese el codigo de la segunda habitacion: ");
                    b = scanner.nextInt();
                    System.out.println(house.minimoPuntaje(a, b));
                    break;
                case 5:
                    // sinPasarPor
                    System.out.println("Ingrese el codigo de la primer habitacion: ");
                    a = scanner.nextInt();
                    System.out.println("Ingrese el codigo de la segunda habitacion: ");
                    b = scanner.nextInt();
                    System.out.println("Ingrese el codigo de la habitacion por la que no se puede pasar: ");
                    c = scanner.nextInt();
                    System.out.println("Ingrese la puntuacion: ");
                    d = scanner.nextInt();
                    System.out.println(house.sinPasarPor(a, b, c, d));
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    public void menuConsultasDesafios() {
        int opcion;
        do {
            System.out.println("\n--- CONSULTAS SOBRE DESAFÍOS ---");
            System.out.println("1. mostrarDesafío");
            System.out.println("2. mostrarDesafíosResueltos");
            System.out.println("3. verificarDesafíoResuelto");
            System.out.println("4. mostrarDesafíosTipo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese codigo desafio: ");
                    int a = scanner.nextInt();
                    System.out.println("Y su habitacion correspondiente: ");
                    int b = scanner.nextInt();

                    System.out.println(house.mostrarDesafio(a, b));
                    // mostrarDesafío
                    break;
                case 2:
                    // mostrarDesafíosResueltos
                    break;
                case 3:
                    // verificarDesafíoResuelto
                    break;
                case 4:
                    // mostrarDesafíosTipo
                    System.out.println("Ingrese codigo de la habitacion: ");
                    a = scanner.nextInt();
                    System.out.println("Desafio de tipo?: ");
                    tt1 = scanner.nextLine();
                    System.out.println("Desde un rango: ");
                    b = scanner.nextInt();
                    System.out.println("Hasta: ");
                    c = scanner.nextInt();

                    System.out.println(house.desafiosDeTipo(tt1, a, b, c));

                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    public void menuConsultasEquipos() {
        int opcion;
        do {
            System.out.println("\n--- CONSULTAS SOBRE EQUIPOS ---");
            System.out.println("1. mostrarInfoEquipo");
            System.out.println("2. posiblesDesafios");
            System.out.println("3. jugarDesafío");
            System.out.println("4. pasarAHabitacion");
            System.out.println("5. puedeSalir");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // mostrarInfoEquipo
                    System.out.println("ingrese el nombre del equipo");
                    String nombreEquipo;
                    nombreEquipo = scanner.nextLine();
                    System.out.println(house.mostrarInfoEquipos(nombreEquipo));
                    break;
                case 2:
                    // posiblesDesafios
                    System.out.println("ingrese el nombre del equipo");
                    String unNombre = scanner.nextLine();
                    System.out.println("ingrese el codigo de habitacion que desea buscar");
                    short unCodigo = scanner.nextShort();
                    System.out.println(house.posiblesDesafios(unNombre, unCodigo));
                    break;
                case 3:
                    // jugarDesafío

                    System.out.println("Ingrese nombre de equipo: ");
                    String tt1 = scanner.nextLine();
                    System.out.println("Ingrese codigo habitacion: ");
                    short hh1 = scanner.nextShort();
                    System.out.println("Ingrese codigo desafio: ");
                    short cc1 = scanner.nextShort();
                    int pp1 = house.equipoJuega(tt1,hh1,cc1);

                    if(pp1>0) System.out.println("Equipo: "+tt1+" ganó:"+ pp1+" puntos.");
                    else System.out.println("Tal desafio no se encuentra en esa habitacion.");

                    break;
                case 4:
                    // pasarAHabitacion

                    System.out.println("ingrese el nombre del equipo");
                    String tt2 = scanner.nextLine();
                    System.out.println("ingrese el codigo de habitacion que desea buscar");
                    short cc2 = scanner.nextShort();
                    boolean exito = house.pasarAHabitacion(tt2, cc2);
                    if(exito) System.out.println("Equipo: "+tt2+" paso a habitacion:"+ cc2);
                    else System.out.println("Equipo: "+tt2+" no puede pasar a habitacion");

                    break;
                case 5:
                    // puedeSalir

                    System.out.println("Ingrese nombre del equipo: ");
                    String tt3 = scanner.nextLine();
                    boolean hanSalido = house.equipoEscapa(tt3);

                    if(hanSalido) System.out.println("ENHORABUENA! El equipo: "+tt3+" HA ESCAPADO!");
                    else System.out.println("Le queda un largo camino por recorrer...");

                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    public void mostrarSistema() {
        System.out.println("\n=== MOSTRANDO TODAS LAS ESTRUCTURAS DEL SISTEMA ===");
        // Implementación de mostrarSistema
    }
}

