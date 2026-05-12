package br.com.logi.model;

public class PilhaCaixa {
    //guardar caixas
    private Caixa[] dados;
    private int topo;

    //construtor
    public PilhaCaixa(int capacidade) {
        this.dados = new Caixa[capacidade];
        
        //-1 e pilha vazia
        this.topo = -1;
    }

    //adiciona uma caixa no topo e retorna true se deu certo e false se pilha ta cheia
    public boolean push(Caixa caixa) {
        if (isFull()) {
            System.out.println("A pilha está cheia!");
            return false; 
        }
        topo++;             
        dados[topo] = caixa; 
        return true;       
    }

    //tira a caixa do topo e retorna a caixa removida ou null se pilha ta vazia
    public Caixa pop() {
        if (isEmpty()) {
            System.out.println("A pilha está vazia!");
            return null;
        }
        Caixa removida = dados[topo];
        dados[topo] = null;        
        topo--;                   
        return removida;
    }

    //ver se pilha ta vazia
    public boolean isEmpty() {
        return topo == -1;
    }

    //ver se pilha ta cheia
    public boolean isFull() {
        return topo == dados.length - 1;
    }

    //dizer quantas pilhas tão na caixa
    public int size() {
        return topo + 1;
    }

    //ver caixa do topo sem remover 
    public Caixa peek() {
        if (isEmpty()) return null;
        return dados[topo];
    }
}