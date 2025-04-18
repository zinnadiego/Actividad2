# Actividad 2 - Aplicación Java con Maven

Este proyecto es una aplicación Java que demuestra el uso de Maven para la gestión de dependencias y la construcción de proyectos Java.

## Descripción

La aplicación es una herramienta de línea de comandos simple que puede procesar diferentes opciones como:
- Mostrar un saludo personalizado
- Realizar un conteo hasta un número especificado
- Activar un modo verboso
- Mostrar ayuda

El proyecto se centra en demostrar:
1. Configuración y uso de Maven
2. Integración de bibliotecas externas (commons-cli y log4j)
3. Creación de un JAR ejecutable

## Requisitos Previos

- Java 11 o superior
- Maven 3.6 o superior
- Git (para clonar el repositorio)

## Estructura del Proyecto

```
Actividad2/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ar/
│   │   │       └── edu/
│   │   │           └── udeci/
│   │   │               └── pv/
│   │   │                   ├── App.java          # Clase principal
│   │   │                   └── CommandProcessor.java  # Procesa argumentos
│   │   └── resources/
│   │       └── log4j2.xml   # Configuración de log4j
│   │
│   └── test/
│       └── java/
│
├── pom.xml                  # Configuración de Maven
└── README.md                # Este archivo
```

## Especificaciones del Proyecto

- **Artefacto**: Actividad2
- **GroupId**: ar.edu.udeci.pv
- **Versión**: 1.0-SNAPSHOT
- **Bibliotecas incluidas**:
  - commons-cli (procesamiento de argumentos de línea de comandos)
  - log4j (registro de eventos)

## Detalles de Implementación

### Clase App

La clase principal (`App.java`) inicia la aplicación, configura el logger, y delega el procesamiento de los argumentos de línea de comandos a la clase `CommandProcessor`.

```java
public static void main(String[] args) {
    logger.info("Iniciando aplicación Actividad2");
    
    try {
        CommandProcessor processor = new CommandProcessor();
        processor.processCommands(args);
        
        logger.info("Aplicación finalizada correctamente");
    } catch (Exception e) {
        logger.error("Error en la ejecución de la aplicación", e);
        System.exit(1);
    }
}
```

### Procesamiento de Comandos (commons-cli)

La clase `CommandProcessor` utiliza la biblioteca commons-cli para definir y procesar las opciones de línea de comandos:

- `-h, --help`: Muestra el mensaje de ayuda
- `-n, --name <nombre>`: Especifica un nombre para el saludo
- `-v, --verbose`: Activa el modo verboso
- `-c, --count <número>`: Realiza un conteo hasta el número especificado (por defecto: 1)

Commons-CLI permite definir opciones de forma clara y estructurada:

```java
private Options createOptions() {
    Options options = new Options();

    // Opción de ayuda
    Option help = Option.builder("h")
            .longOpt("help")
            .desc("Muestra este mensaje de ayuda")
            .build();
    
    // Más opciones...
    
    return options;
}
```

### Sistema de Logging (log4j)

El proyecto utiliza log4j2 para el registro de eventos. La configuración se encuentra en `src/main/resources/log4j2.xml`:

- Los logs se envían tanto a la consola como a un archivo
- Se registran eventos como inicio/fin de la aplicación, opciones seleccionadas, y errores

Ejemplo de uso de log4j en el código:

```java
// Declaración del logger
private static final Logger logger = LogManager.getLogger(CommandProcessor.class);

// Uso en el código
logger.info("Nombre proporcionado: {}", name);
logger.error("Error al procesar los argumentos de línea de comandos", e);
```

## Compilación y Ejecución

### Compilar el proyecto:
```bash
mvn clean compile
```

### Generar el archivo JAR:
```bash
mvn package
```

### Ejecutar la aplicación:
```bash
java -jar target/Actividad2-1.0-SNAPSHOT.jar [opciones]
```

## Ejemplos de Uso e Interacción

1. Mostrar la ayuda:
   ```bash
   java -jar target/Actividad2-1.0-SNAPSHOT.jar -h
   ```
   Resultado:
   ```
   usage: Actividad2
   -c,--count <número>   Realiza un conteo hasta el número especificado (por
                         defecto: 1)
   -h,--help             Muestra este mensaje de ayuda
   -n,--name <nombre>    Especifica un nombre para el saludo
   -v,--verbose          Activa el modo verboso
   ```

2. Ejecutar con un nombre:
   ```bash
   java -jar target/Actividad2-1.0-SNAPSHOT.jar -n Hola Mundo
   ```
   Resultado:
   ```
   ¡Hola, Hola Mundo!
   ```
   (También genera entradas de log como: `INFO ar.edu.udeci.pv.CommandProcessor - Nombre proporcionado: Hola Mundo`)

3. Ejecutar con contador:
   ```bash
   java -jar target/Actividad2-1.0-SNAPSHOT.jar -c 5
   ```
   Resultado:
   ```
   ¡Hola, usuario!
   Conteo: 1
   Conteo: 2
   Conteo: 3
   Conteo: 4
   Conteo: 5
   ```

4. Combinar opciones:
   ```bash
   java -jar target/Actividad2-1.0-SNAPSHOT.jar -n Hola -c 3 -v
   ```
   Resultado:
   ```
   ¡Hola, Hola!
   Modo verboso: Información adicional disponible en los logs
   Conteo: 1
   Conteo: 2
   Conteo: 3
   ```

## Proceso de Desarrollo

El proyecto fue desarrollado siguiendo estos pasos:

1. **Configuración inicial**:
   - Creación del proyecto Maven con las coordenadas especificadas
   - Configuración del archivo `pom.xml` para incluir las dependencias requeridas

2. **Integración de bibliotecas**:
   - Incorporación de commons-cli para procesar argumentos de línea de comandos
   - Configuración del sistema de logging con log4j2
   - Estudio de la documentación de ambas bibliotecas para implementarlas correctamente

3. **Implementación**:
   - Desarrollo de la clase principal `App.java` como punto de entrada de la aplicación
   - Implementación del procesador de comandos con commons-cli
   - Configuración del logging en diferentes puntos de la aplicación
   - Separación de responsabilidades entre las clases

4. **Empaquetado**:
   - Configuración del plugin maven-jar-plugin para especificar la clase principal
   - Configuración del plugin maven-shade-plugin para crear un JAR ejecutable con todas las dependencias
   - Pruebas de empaquetado y ejecución para verificar su funcionamiento

5. **Documentación**:
   - Creación de este README con instrucciones y ejemplos
   - Comentarios en el código para facilitar su comprensión y mantenimiento
