package br.com.logi.model;
import javax.swing.*; 
import java.awt.*;

public class TelaArmazem extends JFrame {

    //construtor janela
    public TelaArmazem(Armazem armazem) {
        setTitle("Sistema de Armazém LIFO");      
        setSize(800, 800);                        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);   
        mostrarGrid(armazem);                       
        setVisible(true);
        //substituir icone da janela pelo da ostrich 
        ImageIcon icon = new ImageIcon(getClass().getResource("/imgs/ostrich.JPG"));
        Image imagem = icon.getImage();
        setIconImage(imagem);                            
    }

    //criar o grid dos botoes
    private void mostrarGrid(Armazem armazem) {
        int linhas = armazem.getLinhas();
        int colunas = armazem.getColunas();
        
        //layout do grid
        setLayout(new GridLayout(linhas, colunas, 8, 8));

        //for loop normal
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                Setor setor = armazem.getSetor(i, j); 
                JButton botao = new JButton();       

                //font e alinhamento do botao
                botao.setFont(new Font("Arial", Font.BOLD, 14));
                botao.setHorizontalAlignment(SwingConstants.CENTER);
                botao.setVerticalAlignment(SwingConstants.CENTER);
                botao.setHorizontalTextPosition(SwingConstants.CENTER);
                botao.setVerticalTextPosition(SwingConstants.CENTER);

                //tooltip (tem que segurar com mouse em cima do botao pra aparecer)
                botao.setToolTipText("Esquerdo: Adicionar | Direito: Remover topo");

                //atualizar texto e cor do botao de acordo com o setor 
                atualizarBotao(botao, setor);

                //evento de clique no botao
                botao.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        //botao esquerdo adiciona caixa
                        if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                            
                            // cria caixa com código automático
                            Caixa nova = new Caixa("Produto"); 
                            boolean ok = setor.adicionarCaixa(nova);  
                            if (!ok) {
                                //se n deu mostrar aviso
                                JOptionPane.showMessageDialog(null,
                                    "Setor lotado! Não é possível adicionar mais caixas.",
                                    "Aviso", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        //botao direito remove caixa do topo LIFO
                        else if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                            if (setor.getQuantidade() == 0) {
                                //pilha ta vazia
                                JOptionPane.showMessageDialog(null,
                                    "Pilha vazia! Nenhuma caixa para remover.");
                            } else {
                                //mostrar confirmação antes de remover
                                String topo = setor.getTopoCodigo();
                                int resposta = JOptionPane.showConfirmDialog(null,
                                    "Remover a caixa do topo?\nCódigo: " + topo,
                                    "Confirmar a remoção",
                                    JOptionPane.YES_NO_OPTION);
                                if (resposta == JOptionPane.YES_OPTION) {
                                    
                                    //faz o pop
                                    Caixa removida = setor.removerCaixa();
                                    JOptionPane.showMessageDialog(null,
                                        "Caixa removida com sucesso: " + removida);
                                }
                            }
                        }

                        //depois de clicar mostra as infos centralizadas de verdade
                        String texto = "<html><div style='text-align: center;'>"
                                     + "Nome: " + setor.getNome()
                                     + "<br>Linha: " + setor.getLinha()
                                     + "<br>Coluna: " + setor.getColuna()
                                     + "<br>Capacidade: " + setor.getCapacidade()
                                     + "<br>Caixas atuais: " + setor.getQuantidade()
                                     + "<br>" + setor.resumoPilha().replace("\n", "<br>")
                                     + "</div></html>";
                        JLabel label = new JLabel(texto, SwingConstants.CENTER);
                        //remove o ícone, o texto fica no meio
                        JOptionPane.showMessageDialog(null, label, "Detalhes do Setor", JOptionPane.PLAIN_MESSAGE);

                        //atualiza botao 
                        atualizarBotao(botao, setor);
                    }
                });

                //add botao na janela
                add(botao);
            }
        }
    }

    //atualizar texto e cor do botao
    private void atualizarBotao(JButton botao, Setor setor) {
        String nome = setor.getNome();
        String topo = setor.getTopoCodigo();
        int qtd = setor.getQuantidade();
        int cap = setor.getCapacidade();

        //texto do botao com as seguintes infos em html
        botao.setText("<html><center>" +
                      nome +
                      "<br>Topo: " + topo +
                      "<br>Caixas: " + qtd + "/" + cap +
                      "</center></html>");

        //pegar cor de acordo com ocupação do setor
        botao.setBackground(getCorGradual(setor.getOcupacao()));
        
        //garante que a cor apareça
        botao.setOpaque(true);
        
        //tira a borda pra ficar mais limpo
        botao.setBorderPainted(false);
    }

    //calculo da cor que muda gradualmente 
    private Color getCorGradual(double ocupacao) {
        
        //transforma a porcentagem em um numero de 0 a 1
        double fator = ocupacao / 100.0;

        //cores de partida verde e chegada vermelho
        Color verde = new Color(120, 220, 120);
        Color vermelho = new Color(240, 100, 100);

        //mistura as duas cores conforme o fator
        int r = (int) (verde.getRed()   + fator * (vermelho.getRed()   - verde.getRed()));
        int g = (int) (verde.getGreen() + fator * (vermelho.getGreen() - verde.getGreen()));
        int b = (int) (verde.getBlue()  + fator * (vermelho.getBlue()  - verde.getBlue()));

        //garante que os valores RGB fiquem dentro dos limites 0-255
        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));

        return new Color(r, g, b);
    }
}