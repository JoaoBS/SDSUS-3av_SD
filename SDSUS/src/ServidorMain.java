import java.io.IOException;

public class ServidorMain {
    public static void main(String[] args) {
        try {
            ServidorSUS servidor = new ServidorSUS(12345);
            servidor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}