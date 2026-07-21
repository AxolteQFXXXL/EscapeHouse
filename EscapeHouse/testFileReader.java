
import java.io.*;
import java.util.StringTokenizer;


public class testFileReader {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("src/EscapeHouse/test.txt");
            BufferedReader br = new BufferedReader(fr)) {
            int nroHabitaciones = 0;
            int nroEquipos = 0;
            int nroDesafios = 0;
            int nroPuertas = 0;
            String line;
            // Read line-by-line until the end of the file (null)
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line,";");

                    switch (st.nextToken()) {
                        case "H":
                            //aca hacer la carga de las habitaciones en el AVL y en el GRAFO como vertices
                            nroHabitaciones++;
                            System.out.print("habitacion nro: " + nroHabitaciones);
                            System.out.println();
                            while (st.hasMoreTokens()) {
                                System.out.println(st.nextToken());
                            }
                            break;
                        case "E":
                            //aca hacer la carga de los equipos en el Hash
                            nroEquipos++;
                            System.out.print("equipo nro: " + nroEquipos);
                            System.out.println();
                            while (st.hasMoreTokens()) {
                                System.out.println(st.nextToken());
                            }
                            break;
                        case "D":
                            //aca hacer la carga de los desafios en el AVL dentro de las habitaciones, tener en cuenta que cada desafio tiene como 3er token del String a la habitacion que corresponde
                            nroDesafios++;
                            System.out.print("desafio nro: " + nroDesafios);
                            System.out.println();
                            while (st.hasMoreTokens()) {
                                System.out.println(st.nextToken());
                            }
                            break;
                        case "P":
                            //aca insertar las puertas como arcos en el GRAFO
                            nroPuertas++;
                            System.out.print("puerta nro: " + nroPuertas);
                            System.out.println();
                            while (st.hasMoreTokens()) {
                                System.out.println(st.nextToken());
                            }
                            break;
                        default:
                            System.out.println();
                }
                //System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
