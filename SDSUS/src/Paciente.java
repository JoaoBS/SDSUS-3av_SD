import java.io.Serializable;

public class Paciente implements Serializable, Comparable<Paciente> {
    private String nome;
    private Prioridade prioridade;

    public enum Prioridade {
        BAIXA(1),
        MEDIA(2),
        ALTA(3),
        URGENTE(4);

        private int valor;

        Prioridade(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    public Paciente(String nome, Prioridade prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    @Override
    public int compareTo(Paciente o) {
        return o.prioridade.getValor() - this.prioridade.getValor();
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}

