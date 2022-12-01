# HitoSyPT1
Desarrolla una aplicación Java que simula un gestor de exámenes tipo test multihilo. 
Una clase con capacidad de multitarea hará de generador de exámenes asignando a cada examen un código. Cada código de examen generado se añadirá a una colección tipo cola.
Otra clase con capacidad de multitarea hará de consumidor de exámenes extrayendo un examen de la cola para que un alumno pueda examinarse.
El consumidor y el generador de exámenes compartirán un recurso (la cola de exámenes).
Desde el programa principal se crearán varias instancias de las clases consumidor y generadora de exámenes para simular el gestor de exámenes donde muchos alumnos podrán examinarse simultáneamente.
Sigue estos pasos para desarrollar la actividad:
Comienza por crear un proyecto Eclipse con el nombre ACTIVIDAD-1.
Para que te resulte más sencillo, te daremos parte del código ya desarrollado. Solo tendrás que completar las partes que faltan para sustituir los comentarios que aparecen en color rojo.
Para comenzar, aquí tienes todo el código de la clase Principal con su método main(), que arrancará el programa:
 
public class Principal {
    	public static void main(String[] args) throws InterruptedException {
            	BufferExamenes generador = new BufferExamenes();
            	new ProductorExamenes(generador);
            	new Examinador("Rosa", generador);
            	new ProductorExamenes(generador);
            	new Examinador("Miguel", generador);
            	new ProductorExamenes(generador);
            	new Examinador("Carlos", generador);
    	}
}
Para cada alumno que va a examinarse se debe imprimir un examen, que tendrá un código diferenciado. La clase BufferExamenes mantiene una cola (objeto LinkedList) de códigos de examen. Para cada alumno se extrae un examen de la cola.

import java.util.LinkedList;
import java.util.Queue;
 
public class BufferExamenes {
    	private Queue<String> colaExamenes;
    	
    	public BufferExamenes() {
            	colaExamenes = new LinkedList<String>();
    	}
    	
    	public synchronized void fabricarNuevoExamen(String codigo) {
            	// Aquí se fabrica un nuevo examen.
            	// Un hilo de la clase ProductorExamenes
// se encargará de fabricarlo
            	// y pasarlo como argumento a este método.
 
            	// Añade el código pasado como argumento a la cola
            	// y libera el hilo que está intentando consumir
            	// un nuevo examen.
    	}
 
    	public synchronized String consumirExamen() {
            	// Este método se encargará de suministrar un examen
            	// a cada hilo de tipo Examinador que lo solicite.
 
            	// Para suministrar el examen habrá antes que esperar
            	// hasta que haya algún examen para consumir en la cola.
 
            	// Haz aquí una pausa hasta que se haya fabricado algún examen.
            	
    	// Si la cola sigue sin estar vacía, saca un examen y
            	// entrégalo como retorno de esta función.
    	}
}
La clase ProductorExamenes se encargará de generar exámenes, asignándole a cada uno un código que estará formado por la letra E seguida del número de examen, un guión y el año, por ejemplo: E2-2018 (segundo examen del año 2018). El número de examen se establece a partir de la variable estática numeroExamen.

import java.time.LocalDateTime;
 
public class ProductorExamenes implements Runnable {
    	private BufferExamenes buffer;
    	private static int numeroExamen = 0;
    	private Thread hilo;
    	
    	public ProductorExamenes(BufferExamenes buffer) {
            	// Incrementa el contador de exámenes (variable numeroExamen).
 
            	// Construye el hilo. El nombre será la letra E seguida
            	// del valor de la variable numeroExamen.
 
            	// Establece el valor de la propiedad buffer
 
            	// Inicia el hilo.
    	    	}
 
    	@Override
    	public void run() {
            	int aa = LocalDateTime.now().getYear();
            	// Generación del código de examen.
String codigo = this.hilo.getName() + "-" +aa;
 
// Añade el nuevo código al buffer de exámenes.
 
// Muestra un mensaje en consola informando sobre el
// código del examen que se acaba de producir.
    	}   	
}
 
La clase Examinador se lanza cada vez que un alumno va a examinarse, simulando la realización del examen por parte del alumno. Un ejemplo de salida de examen podría ser:
   
    
E2-2018;Miguel; Pregunta 1;C
E2-2018;Miguel; Pregunta 2;-
E2-2018;Miguel; Pregunta 3;A
E2-2018;Miguel; Pregunta 4;C
E2-2018;Miguel; Pregunta 5;D
E2-2018;Miguel; Pregunta 6;-
E2-2018;Miguel; Pregunta 7;-
E2-2018;Miguel; Pregunta 8;B
E2-2018;Miguel; Pregunta 9;D
E2-2018;Miguel; Pregunta 10;-
 
Esta salida en pantalla representa la realización del examen con código E2-2018, que corresponde al alumno Miguel. El examen consta de 10 preguntas, cuyas respuestas se han seleccionado al azar en los valores A, B, C, D o guión (sin responder).
Puesto que varios alumnos se examinan simultáneamente, se podrán entremezclar líneas de la respuesta del examen de un alumno con las del examen de otro. Pero las respuestas del examen de un mismo alumno siempre tendrán el mismo código.
 
public class Examinador implements Runnable {
     	private Thread hilo;
     	BufferExamenes buffer;
     	
     	public Thread getHilo() {
               	return hilo;
     	}
 
     	public Examinador(String alumno, BufferExamenes generador) {
               	// Construye el hilo. El nombre será el nombre del alumno.
 
               	// Establece el valor de la propiedad buffer
 
               	// Inicia el hilo.
 
     	}
     	
     	@Override
     	public void run() {
               	String codigoExamen = this.buffer.consumirExamen();
               	if (codigoExamen != null) {
     	               	// Simula aquí un examen de 10 preguntas
                        	// cuyas respuestas se seleccionarán al azar
                        	// entre A, B, C, D o – (sin contestar).
               	}
               	else {
                        	System.out.println("Agotado tiempo de espera y " +
"no hay más exámenes");
               	}
     	}
}
 
 
La ejecución completa del programa debe ofrecer una salida similar a esta:

Producido examen E2-2018
E1-2018;Carlos; Pregunta 1;B
E1-2018;Carlos; Pregunta 2;B
E2-2018;Rosa; Pregunta 1;A
E2-2018;Rosa; Pregunta 2;A
Producido examen E3-2018
E2-2018;Rosa; Pregunta 3;C
E2-2018;Rosa; Pregunta 4;-
E3-2018;Miguel; Pregunta 1;C
Producido examen E1-2018
E1-2018;Carlos; Pregunta 3;-
E1-2018;Carlos; Pregunta 4;D
E3-2018;Miguel; Pregunta 2;-
E2-2018;Rosa; Pregunta 5;D
E3-2018;Miguel; Pregunta 3;D
E1-2018;Carlos; Pregunta 5;C
E3-2018;Miguel; Pregunta 4;B
E2-2018;Rosa; Pregunta 6;-
E3-2018;Miguel; Pregunta 5;B
E1-2018;Carlos; Pregunta 6;D
E3-2018;Miguel; Pregunta 6;C
E2-2018;Rosa; Pregunta 7;-
E3-2018;Miguel; Pregunta 7;-
E1-2018;Carlos; Pregunta 7;C
E3-2018;Miguel; Pregunta 8;B
E2-2018;Rosa; Pregunta 8;A
E3-2018;Miguel; Pregunta 9;C
E1-2018;Carlos; Pregunta 8;D
E3-2018;Miguel; Pregunta 10;A
E2-2018;Rosa; Pregunta 9;C
E2-2018;Rosa; Pregunta 10;A
E1-2018;Carlos; Pregunta 9;B
E1-2018;Carlos; Pregunta 10;-


Debes crear esta actividad como una ampliación de la actividad anterior. 
Se lanzarán dos procesos simultáneos del programa desarrollado en la actividad anterior, con el objeto de poder realizar dos exámenes diferentes, cada uno a un grupo de alumnos distinto.

Sigue estos pasos para realizar la actividad:

Realiza una copia del proyecto ACTIVIDAD1-1 como ACTIVIDAD1-2.
Modifica la clase Principal para examinar a tantos alumnos como argumentos se pasan en la línea de comandos. Los argumentos llevarán los nombres de las personas que se examinarán. 
Crea un programa multiproceso, llamado Lanzador, que lanzará dos exámenes distintos:
El primer examen será para los alumnos Pepe, Juan y Luis.
El segundo examen será para los alumnos Rosa, Miguel y Pedro.

La salida deberá escribirse en los archivos examen1.txt y examen2.txt.
