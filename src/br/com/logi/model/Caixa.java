package br.com.logi.model;

public class Caixa {
    private String codigo;
    private String descricao;

    //esse contador e compartilhado entre todas as caixas e gera codigos unicos automaticamente (acho q talvez tenha q alterar algo nessa classe depois)
    private static int proximoCodigo = 1;

    //construtor
    public Caixa(String descricao) {
        this.codigo = String.valueOf(proximoCodigo++); // pega o número e vira texto
        this.descricao = descricao;
    }

    //getters 
    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    //imprimir caixa 
    @Override
    public String toString() {
        return "Caixa codigo: " + codigo + " | " + descricao;
    }
}