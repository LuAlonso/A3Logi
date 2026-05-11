package br.com.logi.model;

public class PilhaCaixa {
    
    //guardar caixas
    private Caixa[] dados;
    
    //topo da pilha 
    private int topo;
    
    //Construtor
    public PilhaCaixa(int capacidade){
        this.dados = new Caixa[capacidade];
        this.topo = -1; // começa vazio
    }
    
    //add caixa na pilha seguindo lógica LIFO
    public void push(Caixa caixa){
        if (isFull()){
            System.out.println("A pilha está cheia! ");
            return;
        }

        topo++; //sobe o topo
        dados[topo] = caixa; //adiciona a caixa no novo topo
    }

    //remover caixa da pilha (remove sempre o topo)
    public Caixa pop(){
        if (isEmpty()){
            System.out.println("A pilha está vazia! ");
            return null;
        }

        Caixa caixaRemovida = dados[topo];
        
        //limpar referência da posição
        dados[topo] = null;
        
        //desce o topo
        topo--;
        return caixaRemovida;
    }
    
    //verificar se está vazia
    public boolean isEmpty(){
        return topo == -1;
    }

    //verificar se está cheia
    public boolean isFull(){
        return topo == dados.length - 1;
    }

    //quantidade de caixas na pilha 
    public int size(){
        return topo + 1;
    }

    //olhar topo sem remover pro LIFO
    public Caixa peek(){
        if (isEmpty()){
            return null;
        }
        // retorna o último inserido
        return dados[topo];
    }
}