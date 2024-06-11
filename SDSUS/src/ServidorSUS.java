import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorSUS {
    private final ServerSocket serverSocket;
    private final PriorityQueue<Paciente> filaPacientes;
    private final List<Paciente> historicoChamadas;

    public ServidorSUS(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        filaPacientes = new PriorityQueue<>();
        historicoChamadas = new ArrayList<>();
    }

    public void run() throws IOException {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClienteHandler(clientSocket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClienteHandler implements Runnable {
        private Socket clientSocket;

        public ClienteHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                while (true) {
                    String operacao = in.readUTF();

                    switch (operacao) {
                        case "REGISTRAR":
                            Paciente paciente = (Paciente) in.readObject();
                            filaPacientes.add(paciente);
                            out.writeObject("Paciente adicionado com sucesso!");
                            break;
                        case "CHAMAR":
                            Paciente chamado = filaPacientes.poll();
                            historicoChamadas.add(chamado);
                            out.writeObject(chamado);
                            break;
                        case "ATUALIZAR":
                            Paciente atualizado = (Paciente) in.readObject();
                            filaPacientes.removeIf(p -> p.getNome().equals(atualizado.getNome()));
                            filaPacientes.add(atualizado);
                            out.writeObject("Prioridade do paciente atualizada com sucesso!");
                            break;
                        case "VISUALIZAR":
                            out.writeObject(new ArrayList<>(filaPacientes));
                            break;
                        case "HISTORICO":
                            out.writeObject(historicoChamadas);
                            break;
                        case "SAIR":
                            in.close();
                            out.close();
                            clientSocket.close();
                            return;
                    }
                    out.reset();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
