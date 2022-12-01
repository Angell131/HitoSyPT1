import java.time.LocalDateTime;
import java.util.Random;
public class Examinador implements Runnable {
    private Thread hilo;
    BufferExamenes buffer;
    private static int numeroExamen = 0;
    public Thread getHilo() {
        return hilo;
    }
    public Examinador(String alumno, BufferExamenes generador) { numeroExamen++;
        hilo = new Thread(this,alumno);
        this.buffer = generador;
        hilo.start();
    }
    @Override
    public void run() {
        String codigoExamen = this.buffer.consumirExamen();  if (codigoExamen != null) {
            int aa = LocalDateTime.now().getYear();
            String codigo= "E"+numeroExamen+"-" +aa+";"+this.hilo.getName();  Random r_method = new Random();
            String respuestas[]= {"A","B","C","D","-"};

            for (int j = 1; j <= 10; j++) {
                int e = r_method.nextInt(respuestas.length);
                System.out.println(codigo+";Pregunta "+j+";"+respuestas[e]);
            }

        }
        else {
            System.out.println("Agotado tiempo de espera y " +  "no hay más exámenes");
        }
    }
}

