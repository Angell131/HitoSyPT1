package Actividad2;
import com.clases.BufferExamenes;
import com.clases.Examinador;
import ProductorExamenes;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        BufferExamenes generador = new BufferExamenes();
        for (int i=0; i<args.length; i++) {
            new ProductorExamenes(generador);
            new Examinador(args[i], generador);
        }
    }
}

