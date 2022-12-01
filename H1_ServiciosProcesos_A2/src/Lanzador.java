import java.io.File;
import java.io.IOException;
public class Lanzador {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        ProcessBuilder proceso1 = new
                ProcessBuilder("java","Principal","Pepe","Juan","Luis");
        ProcessBuilder proceso2 = new
                ProcessBuilder("java","Principal","Rosa","Miguel","Pedro");
        proceso1.redirectOutput(new File("examen1.txt"));
        proceso2.redirectOutput(new File("examen2.txt"));
        try {
            proceso1.start();
            proceso2.start();
            System.out.println("El proceso ha sido lanzado con éxito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
