package ar.edu.udeci.pv;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase para procesar argumentos de línea de comandos usando commons-cli
 */
public class CommandProcessor {
    // Instancia del logger para esta clase
    private static final Logger logger = LogManager.getLogger(CommandProcessor.class);

    /**
     * Procesa los argumentos de línea de comandos
     * @param args Argumentos de línea de comandos
     */
    public void processCommands(String[] args) {
        // Crear las opciones
        Options options = createOptions();
        
        // Crear el parser de argumentos
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        
        try {
            // Parsear los argumentos de línea de comandos
            CommandLine cmd = parser.parse(options, args);

            // Procesar las opciones
            if (cmd.hasOption("help")) {
                formatter.printHelp("Actividad2", options);
                return;
            }

            // Procesar la opción de nombre
            if (cmd.hasOption("name")) {
                String name = cmd.getOptionValue("name");
                logger.info("Nombre proporcionado: {}", name);
                System.out.println("¡Hola, " + name + "!");
            } else {
                logger.info("No se proporcionó un nombre");
                System.out.println("¡Hola, usuario!");
            }

            // Procesar opción de verbosidad
            if (cmd.hasOption("verbose")) {
                logger.info("Modo verboso activado");
                System.out.println("Modo verboso: Información adicional disponible en los logs");
            }

            // Procesar la opción de conteo
            if (cmd.hasOption("count")) {
                int count = Integer.parseInt(cmd.getOptionValue("count", "1"));
                logger.info("Conteo solicitado: {}", count);
                
                for (int i = 1; i <= count; i++) {
                    System.out.println("Conteo: " + i);
                }
            }

        } catch (ParseException e) {
            logger.error("Error al procesar los argumentos de línea de comandos", e);
            System.err.println("Error al procesar los argumentos: " + e.getMessage());
            formatter.printHelp("Actividad2", options);
            System.exit(1);
        } catch (NumberFormatException e) {
            logger.error("Error al convertir el valor de conteo a número", e);
            System.err.println("El valor de conteo debe ser un número entero válido");
            formatter.printHelp("Actividad2", options);
            System.exit(1);
        }
    }

    /**
     * Crea las opciones de línea de comandos disponibles
     * @return Opciones configuradas
     */
    private Options createOptions() {
        Options options = new Options();

        // Opción de ayuda
        Option help = Option.builder("h")
                .longOpt("help")
                .desc("Muestra este mensaje de ayuda")
                .build();
        
        // Opción de nombre
        Option name = Option.builder("n")
                .longOpt("name")
                .hasArg()
                .argName("nombre")
                .desc("Especifica un nombre para el saludo")
                .build();
        
        // Opción de verbosidad
        Option verbose = Option.builder("v")
                .longOpt("verbose")
                .desc("Activa el modo verboso")
                .build();
        
        // Opción de conteo
        Option count = Option.builder("c")
                .longOpt("count")
                .hasArg()
                .argName("número")
                .desc("Realiza un conteo hasta el número especificado (por defecto: 1)")
                .build();

        // Agregar las opciones
        options.addOption(help);
        options.addOption(name);
        options.addOption(verbose);
        options.addOption(count);
        
        return options;
    }
}