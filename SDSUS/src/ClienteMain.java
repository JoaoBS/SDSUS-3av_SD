import java.io.IOException;
import java.util.List;
import java.util.Scanner;
;

public class ClienteMain {
    public static void main(String[] args) {
        try {
            ClienteSUS cliente = new ClienteSUS("localhost", 12345);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Registrar paciente");
                System.out.println("2. Chamar paciente");
                System.out.println("3. Atualizar prioridade do paciente");
                System.out.println("4. Visualizar fila de pacientes");
                System.out.println("5. Visualizar histórico de chamadas");
                System.out.println("6. Sair");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        System.out.println("Digite o nome do paciente:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite a prioridade do paciente (BAIXA, MEDIA, ALTA, URGENTE):");
                        Paciente.Prioridade prioridade = Paciente.Prioridade.valueOf(scanner.nextLine().toUpperCase());
                        Paciente paciente = new Paciente(nome, prioridade);
                        String resposta = cliente.registrarPaciente(paciente);
                        System.out.println(resposta);
                        break;
                    case "2":
                        Paciente chamado = cliente.chamarPaciente();
                        System.out.println("Paciente chamado: " + chamado.getNome());
                        break;
                    case "3":
                        System.out.println("Digite o nome do paciente:");
                        String nomeAtualizar = scanner.nextLine();
                        System.out.println("Digite a nova prioridade do paciente (BAIXA, MEDIA, ALTA, URGENTE):");
                        Paciente.Prioridade novaPrioridade = Paciente.Prioridade.valueOf(scanner.nextLine().toUpperCase());
                        Paciente pacienteAtualizar = new Paciente(nomeAtualizar, novaPrioridade);
                        String respostaAtualizar = cliente.atualizarPrioridade(pacienteAtualizar);
                        System.out.println(respostaAtualizar);
                        break;
                    case "4":
                        List<Paciente> fila = cliente.visualizarFila();
                        System.out.println("Fila de pacientes:");
                        for (Paciente p : fila) {
                            System.out.println(p.getNome() + " - " + p.getPrioridade());
                        }
                        break;
                    case "5":
                        List<Paciente> historico = cliente.visualizarHistorico();
                        System.out.println("Histórico de chamadas:");
                        for (Paciente p : historico) {
                            System.out.println(p.getNome() + " - " + p.getPrioridade());
                        }
                        break;
                    case "6":
                        cliente.sair();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}