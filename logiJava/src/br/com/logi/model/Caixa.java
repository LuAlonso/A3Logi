package br.com.logi.model;

public class Caixa {
    //criando os atributos
    private String codigo;
    private String descricao;

    //criando construtor
    public Caixa(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    //criando os getters e setters
    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    //criando toString pra mostrar caixa e entender oq é
    @Override
    public String toString(){
        return "Caixa codigo: " + codigo + " | " + descricao;
    }
}
