package ar.edu.udeci.pv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase principal de la aplicación Actividad2
 */
public class App {
    // Instancia del logger para esta clase
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Iniciando aplicación Actividad2");
        
        try {
            // Procesar los argumentos de línea de comandos
            CommandProcessor processor = new CommandProcessor();
            processor.processCommands(args);
            
            logger.info("Aplicación finalizada correctamente");
        } catch (Exception e) {
            logger.error("Error en la ejecución de la aplicación", e);
            System.exit(1);
        }
    }
}