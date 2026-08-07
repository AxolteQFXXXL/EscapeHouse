package EscapeHouse;
import java.io.*;

public class testFileWriter {
    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/EscapeHouse/testWrite.txt");
            fw.write("hola 3");
            System.out.println("Escrito");
        } catch (IOException e) {
            System.out.println("Error al escribir");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar");
            }
        }
    }
}
