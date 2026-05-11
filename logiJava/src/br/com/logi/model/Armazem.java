package br.com.logi.model;


public class Armazem {
    private Setor [][] setores;

    //criando o construtor do armazem
    public Armazem(int linhas, int colunas) {
        setores = new Setor[linhas][colunas];

        //preenchendo a matriz dos setores
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                setores[i][j] = new Setor("Corredor " + (char) ('A' + i) + " - Prateleira " + (j + 1), i, j, 10);
            }
        }
    }

    //criando getter do setor pra ver setor x
    public Setor getSetor(int linha, int coluna) {
        return setores[linha][coluna];
    }

    //getters pra pegar informações do settor e mostrar na tela escalando com elas
    public int getLinhas() {
    return setores.length;
    }

    public int getColunas() {
        return setores[0].length;
    }

}
