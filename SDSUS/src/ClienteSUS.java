import java.io.*;
import java.net.*;
import java.util.List;

public class ClienteSUS{
    private Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public ClienteSUS(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public String registrarPaciente(Paciente paciente) throws IOException, ClassNotFoundException {

        out.writeUTF("REGISTRAR");
        out.writeObject(paciente);
        out.flush();
        return (String) in.readObject();
    }

    public Paciente chamarPaciente() throws IOException, ClassNotFoundException {

        out.writeUTF("CHAMAR");
        out.flush();

        return (Paciente) in.readObject();
    }

    public String atualizarPrioridade(Paciente paciente) throws IOException, ClassNotFoundException {

        out.writeUTF("ATUALIZAR");
        out.writeObject(paciente);
        out.flush();

        return (String) in.readObject();
    }

    public List<Paciente> visualizarFila() throws IOException, ClassNotFoundException {

        out.writeUTF("VISUALIZAR");
        out.flush();

        return (List<Paciente>) in.readObject();
    }

    public List<Paciente> visualizarHistorico() throws IOException, ClassNotFoundException {

        out.writeUTF("HISTORICO");
        out.flush();

        return (List<Paciente>) in.readObject();
    }

    public void sair() throws IOException {

        out.writeUTF("SAIR");
        out.flush();
        socket.close();
    }
}

