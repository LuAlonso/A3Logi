package br.com.logi.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaArmazem extends JFrame {
    
    //construtor da tela
    public TelaArmazem(Armazem armazem) {
        setTitle("Sistema de Armazém");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mostrarGrid(armazem);

        setVisible(true);
    }

    //criar grid de botoes pra mostrar setores e interagir 
    private void mostrarGrid(Armazem armazem) {

        int linhas = armazem.getLinhas();
        int colunas = armazem.getColunas();

        //espaçamento
        setLayout(new GridLayout(linhas, colunas, 10, 10));

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {

                Setor setor = armazem.getSetor(i, j);

                JButton botao = new JButton();
                
                //settando fonte e tamanho 
                botao.setFont(new Font("Arial", Font.BOLD, 14));

                atualizarBotao(botao, setor);

                //eventos de clicar
                botao.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        //clicar com botao esquerdo pra adicionar caixa aleatoria *precisa remover duplicadas depois, testei e n vieram duplicadas mas pode acontecer
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            setor.adicionarCaixa(new Caixa(
                                    String.valueOf((int)(Math.random() * 1000)),
                                    "Produto"
                            ));
                        }

                        //clicar com botao remove a caixa do topo seguindo a logica LIFO
                        else if (e.getButton() == MouseEvent.BUTTON3) {
                            Caixa removida = setor.removerCaixa();  // pop da pilha
                            if (removida != null) {
                                JOptionPane.showMessageDialog(null,
                                    "Caixa removida do topo: " + removida);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                    "Pilha vazia! Nenhuma caixa para remover.");
                            }
                        }

                        //mostrar infos no popup
                        JOptionPane.showMessageDialog(null,
                                "Nome: " + setor.getNome() +
                                "\nLinha: " + setor.getLinha() +
                                "\nColuna: " + setor.getColuna() +
                                "\nCapacidade: " + setor.getCapacidade() +
                                "\nCaixas atuais: " + setor.getQuantidade() +
                                "\n" + setor.resumoPilha()
                        );

                        //atualiza o botão depois da ação
                        atualizarBotao(botao, setor);
                    }
                });

                add(botao);
            }
        }
    }

    //atualizar o texto do botao e a cor pra indicar capacidade(cinza = vazio, verde = tem algo, laranja = 6, vermelho = 9 e 10)
    private void atualizarBotao(JButton botao, Setor setor) {

        botao.setText(
                "<html>" + setor.getNome() +
                "<br>Topo: " + setor.getTopoCodigo() +
                "<br>Caixas: " + setor.getQuantidade() +
                "/" + setor.getCapacidade() + "</html>"
        );

        botao.setBackground(getCor(setor));
        botao.setOpaque(true);
        botao.setBorderPainted(false);
    }

    //cores
    private Color getCor(Setor setor) {

        double ocupacao = setor.getOcupacao();

        //cinza 
        if (ocupacao == 0) return new Color(220, 220, 220); 
        
        //verde 
        else if (ocupacao <= 50) return new Color(120, 200, 120); 
        
        //laranja
        else if (ocupacao <= 80) return new Color(255, 200, 120);
        
        //vermelho 
        else return new Color(255, 120, 120);
    }
}