package br.com.logi.model;

public class Armazem {
    //matriz de setores do armazem
    private Setor[][] setores;

    //criando construtor do armazem que recebe linhas e colunas 
    public Armazem(int linhas, int colunas) {
        setores = new Setor[linhas][colunas];
        //preenche cada posição com um setor
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                //linha 0 coluna 0 --> A-1
                String nome = (char) ('A' + i) + "-" + (j + 1);
                //capacidade máximo do setor, da pra alterar se quiser mas ta fixo por enquanto
                setores[i][j] = new Setor(nome, i, j, 10);
            }
        }
    }

    //retorna setor especifico
    public Setor getSetor(int linha, int coluna) {
        return setores[linha][coluna];
    }

    //numero de linhas
    public int getLinhas() {
        return setores.length;
    }

    //numero de colunas
    public int getColunas() {
        return setores[0].length;
    }
}