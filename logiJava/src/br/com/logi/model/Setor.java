package br.com.logi.model;

public class Setor {
    //settando atributos
    private String nome;
    private int capacidade;
    private int coluna;
    private int linha;
    private PilhaCaixa pilha;
    
    //criando construtor
    public Setor(String nome, int linha, int coluna, int capacidade){
        this.nome = nome;
        this.linha = linha;
        this.coluna = coluna;
        this.capacidade = capacidade;
        this.pilha = new PilhaCaixa(capacidade);
    }

    //getters e setters 
    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    //adiciona caixa na pilha (topo)
    public void adicionarCaixa(Caixa caixa){
        pilha.push(caixa);
    }

    //remover caixa do topo LIFO
    public Caixa removerCaixa(){
        return pilha.pop();
    }

    //quantidade atual de caixas
    public int getQuantidade(){
        return pilha.size();
    }

    //porcentagem de ocupação do setor
    public double getOcupacao(){
        return (pilha.size() * 100.0) / capacidade;
    }

    //mostrar topo e resto da pilha sem quebrar o topo
    public String resumoPilha() {
        if (pilha.isEmpty()) {
            return "Sem caixas";
        }

        PilhaCaixa temp = new PilhaCaixa(capacidade);

        //usa peek sem remover
        Caixa topo = pilha.peek();

        String outros = "";

        while (!pilha.isEmpty()) {
            Caixa c = pilha.pop();

            //ignora o topo na lista de outros
            if (!c.getCodigo().equals(topo.getCodigo())) {
                outros += c.getCodigo() + " ";
            }

            temp.push(c);
        }

        //reconstruir a pilha 
        while (!temp.isEmpty()) {
            pilha.push(temp.pop());
        }

        if (outros.isEmpty()) {
            return "Próximo: " + topo.getCodigo();
        }

        return " Caixa do topo: " + topo.getCodigo() +
               "\n Outras caixas: " + outros.trim();
    }

    //pegar o código do topo sem remover
    public String getTopoCodigo() {
        if (pilha.isEmpty()) {
            return "Vazio";
        }

        return pilha.peek().getCodigo();
    }
}