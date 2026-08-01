package EscapeHouse.sistema;

import EscapeHouse.estructuras.AVL;
import EscapeHouse.estructuras.Grafo;
import EscapeHouse.modelos.Desafio;
import EscapeHouse.modelos.Equipo;
import EscapeHouse.modelos.Habitacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

public class GestorArchivos {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void cargarSistema(EscapeHouse house) {
            AVL habitaciones = new AVL();
            AVL desafios = new AVL();
            HashMap<String, Equipo> equipos = new HashMap<String, Equipo>(20);
            Grafo esquema = new Grafo();
        try (FileReader fr = new FileReader("C:\\Users\\yazmi\\OneDrive\\Desktop\\Facultad Prog\\trabajos EDAT\\TPO\\EscapeHouse\\EscapeHouse\\sistema\\test.txt");
             BufferedReader br = new BufferedReader(fr)) {
            int[] nros = {0, 0, 0, 0};
            String line;
            reiniciarLog();
            log("=== INICIO DE CARGA DEL SISTEMA ===");
            // Read line-by-line until the end of the file (null)
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");

                switch (st.nextToken()) {
                    case "H":
                        cargarHabitacion(nros, st, habitaciones, esquema);
                        break;
                    case "E":
                        cargarEquipo(nros, st, equipos);
                        break;
                    case "D":
                        cargarDesafio(nros, st, desafios, habitaciones);
                        break;
                    case "P":
                        cargarPuerta(nros, st, esquema);
                        break;
                    default:
                        System.out.println();
                }

            }
            log("=== CARGA FINALIZADA ===");
            log("Estadísticas: " +
                    "Habitaciones=" + nros[0] +
                    ", Equipos=" + nros[1] +
                    ", Desafíos=" + nros[2] +
                    ", Puertas=" + nros[3]);

        } catch (IOException e) {
            String errorMsg = "Error al leer el archivo: " + e.getMessage();
            System.err.println(errorMsg);
            log("ERROR: " + errorMsg);
        }
        house.inicializar(habitaciones, esquema, equipos);

        log("=== FIN DE CARGA DEL SISTEMA ===");


    }

    private void cargarHabitacion(int[] nros, StringTokenizer st, AVL habits, Grafo scheme){
        //aca hacer la carga de las habitaciones en el AVL y en el GRAFO como vertices
        nros[0]++;
        Object [] cajon = new Object[5];
        short i=0;
        log("Habitación #" + nros[0] + " cargada");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            log("  - Habitación dato: " + token);
            cajon[i] = token;
            i++;
        }

        cajon[0] = Integer.parseInt((String) cajon[0]);
        cajon[2] = Integer.parseInt((String) cajon[2]);
        cajon[3] = Integer.parseInt((String) cajon[3]);
        cajon[4] = Boolean.parseBoolean((String) cajon[4]);

        Habitacion hab = new Habitacion((int) cajon[0], (String) cajon[1], (int) cajon[2], (int) cajon[3], (boolean)cajon[4]);
        habits.insertar(hab.getCodigo(), hab);
        scheme.insertarVertice(hab.getCodigo());
    }

    private void cargarEquipo(int[] nros, StringTokenizer st, HashMap<String, Equipo> equipos){
        //aca hacer la carga de los equipos en el Hash
        nros[1]++;
        Object [] cajon = new Object[5];
        short i = 0;
        log("Equipo #" + nros[1] + " cargada");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            log("  - Equipo dato: " + token);
            cajon[i]=token;
            i++;
        }

        cajon[1] = Integer.parseInt((String) cajon[1]);
        cajon[2] = Integer.parseInt((String) cajon[2]);
        cajon[3] = Integer.parseInt((String) cajon[3]);
        cajon[4] = Integer.parseInt((String) cajon[4]);

        Equipo eq = new Equipo((String)cajon[0], (int) cajon[1], (int)cajon[2], (int)cajon[3], (int)cajon[4]);
        equipos.put( eq.getNombre(), eq);
    }

    private void cargarDesafio(int[] nros, StringTokenizer st, AVL defs, AVL habits){
        //aca hacer la carga de los desafios en el AVL dentro de las habitaciones, tener en cuenta que cada desafio tiene como 3er token del String a la habitacion que corresponde
        nros[2]++;
        Object [] cajon = new Object[4];
        short i = 0;
        log("Desafío #" + nros[2] + " cargada");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            log("  - Desafío dato: " + token);
            cajon[i]=token;
            i++;
        }
        cajon[0]= Integer.parseInt((String) cajon[0]);
        cajon[1]= Integer.parseInt((String) cajon[1]);

        Desafio des = new Desafio((int) cajon[0], (int) cajon[1],(String) cajon[2],(String) cajon[3]);
        Habitacion hab = (Habitacion) habits.obtenerElemento((int) cajon[1]);
        if(hab != null) hab.AgregarDesafio((int) des.getPuntaje(), des);
    }

    private void cargarPuerta(int[] nros, StringTokenizer st, Grafo esquema){
        //aca insertar las puertas como arcos en el GRAFO
        nros[3]++;
        int [] cajon = new int[3];
        short i =0;
        log("Puerta #" + nros[3] + " cargada");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            log("  - Puerta dato: " + token);
            cajon[i] = Integer.parseInt(token);
            i++;
        }
        esquema.insertarArco(cajon[0], cajon[1], cajon[2]);

    }

    public void log(String mensaje){
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Users\\yazmi\\OneDrive\\Desktop\\Facultad Prog\\trabajos EDAT\\TPO\\EscapeHouse\\EscapeHouse\\sistema\\testWrite.txt", true);
            String timestamp = LocalTime.now().format(formatter);
            fw.write(timestamp + " " + mensaje + "\n");
            fw.flush();
        } catch (IOException e) {
            System.out.println("Error al escribir log: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar log: " + e.getMessage());
            }
        }
    }

    public void reiniciarLog(){
        try (FileWriter fw = new FileWriter("C:\\Users\\yazmi\\OneDrive\\Desktop\\Facultad Prog\\trabajos EDAT\\TPO\\EscapeHouse\\EscapeHouse\\sistema\\testWrite.txt", false)) {
            fw.write("");
            fw.flush();
        } catch (IOException e) {
            System.out.println("Error al reiniciar log: " + e.getMessage());
        }
    }
}
