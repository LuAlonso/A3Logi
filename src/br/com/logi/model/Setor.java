package br.com.logi.model;

public class Setor {
    private String nome;      
    private int capacidade;  
    private int coluna;      
    private int linha;       
    private PilhaCaixa pilha;

    //construtor
    public Setor(String nome, int linha, int coluna, int capacidade) {
        this.nome = nome;
        this.linha = linha;
        this.coluna = coluna;
        this.capacidade = capacidade;
        this.pilha = new PilhaCaixa(capacidade); 
    }

    //getters 
    public String getNome() { return nome; }
    public int getCapacidade() { return capacidade; }
    public int getColuna() { return coluna; }
    public int getLinha() { return linha; }

    //adiciona uma caixa na pilha do setor e da true se empilhou e false se ta cheia
    public boolean adicionarCaixa(Caixa caixa) {
        return pilha.push(caixa); 
    }

    //remove a caixa do topo e retorna a caixa ou null se ta vazio
    public Caixa removerCaixa() {
        return pilha.pop();
    }

    //quantas caixas tem no setor
    public int getQuantidade() {
        return pilha.size();
    }

    //porcentagem da ocupação do setor
    public double getOcupacao() {
        return (pilha.size() * 100.0) / capacidade;
    }

    //pega o código da caixa que ta no topo sem remover e se ta vazio mostra vazio
    public String getTopoCodigo() {
        if (pilha.isEmpty()) return "Vazio";
        return pilha.peek().getCodigo();
    }

    //resumo da pilha 
    public String resumoPilha() {
        if (pilha.isEmpty()) return "Sem caixas";

        //pilha temporaria pra ir desempilhar e reconstruir
        PilhaCaixa temp = new PilhaCaixa(capacidade);
        Caixa topo = pilha.peek();
        String outros = "";

        //basicamente tira tudo da pilha e joga na temporaria, anotando os códigos menos o topo
        while (!pilha.isEmpty()) {
            Caixa c = pilha.pop();
            if (!c.getCodigo().equals(topo.getCodigo())) {
                outros += c.getCodigo() + " ";
            }
            temp.push(c);
        }

        //devolve tudo pra pilha original na ordem certa
        while (!temp.isEmpty()) {
            pilha.push(temp.pop());
        }

        //se n tem outros só mostra o topo e se tiver mostra topo e outros
        if (outros.isEmpty()) {
            return " Próximo: " + topo.getCodigo();
        }
        return " Caixa do topo: " + topo.getCodigo() +
               "\n Outras caixas: " + outros.trim();
    }
}