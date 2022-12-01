import java.util.LinkedList;
import java.util.Queue;
public class BufferExamenes {
    private Queue<String> colaExamenes;

    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {
        colaExamenes.add(codigo);
        notify();

    }
    public synchronized String consumirExamen() {
        int esperar=0;
        while (colaExamenes.isEmpty() && esperar<30) {
            esperar++;
            try {
                wait(10);
            } catch (InterruptedException e) {
// TODO: handle exception
                System.out.println(e.getMessage());
            }
        }
        if (esperar<30) {
            String examen=colaExamenes.remove();
            return examen;
        }
        else {
            return null;
        }
    }
}

