package EscapeHouse;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class testLogger {
    private static final Logger log = Logger.getLogger(testLogger.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("src/EscapeHouse/log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());

            log.addHandler(fileHandler);
            log.setLevel(Level.INFO);

            log.info("Se cambio X en el grafo");
            log.info("Se añadió una nueva habitación");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
