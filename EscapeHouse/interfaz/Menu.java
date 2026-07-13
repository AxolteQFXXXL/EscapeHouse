package EscapeHouse.interfaz;
import java.util.Scanner;

import EscapeHouse.sistema.EscapeHouse;
import EscapeHouse.sistema.GestorArchivos;


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
        System.out.println("2. ABM");// cambiar por algo que tenga sentido ------------------------------------------------------------------------------------------------------------------------------
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
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {// aca añadir cara modificacion
                case 1:
                    // mostrarHabitación
                    break;
                case 2:
                    // habitacionesContiguas
                    break;
                case 3:
                    // esPosibleLlegar
                    break;
                case 4:
                    // minimoPuntaje
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

    public void menuConsultasHabitaciones() {
        int opcion;
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
                    Short codigo = scanner.nextShort();
                    System.out.println(house.mostrarHabitacion(codigo));
                    break;
                case 2:
                    // habitacionesContiguas
                    break;
                case 3:
                    // esPosibleLlegar
                    break;
                case 4:
                    // minimoPuntaje
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
                    Short a = scanner.nextShort();
                    System.out.println("Y su habitacion correspondiente: ");
                    Short b = scanner.nextShort();

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
                    break;
                case 2:
                    // posiblesDesafios
                    break;
                case 3:
                    // jugarDesafío
                    break;
                case 4:
                    // pasarAHabitacion
                    break;
                case 5:
                    // puedeSalir
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

