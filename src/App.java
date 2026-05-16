import br.com.logi.model.Armazem;
import br.com.logi.model.TelaArmazem;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        //criar janela splash
        JDialog splash = new JDialog((Frame) null, "Iniciando", true);
        
        //sem borda
        splash.setUndecorated(true);       
        
        //tamanho janela (aumentei a altura pra caber a logo)
        splash.setSize(400, 300);
        
        //centralizar
        splash.setLocationRelativeTo(null);

        //layout da janela
        splash.setLayout(new BorderLayout());   

        //painel do topo: logo + mensagem
        JPanel painelTopo = new JPanel();
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.setBackground(Color.WHITE); // fundo branco pra combinar

        //carregar a imagem da logo
        java.net.URL urlImagem = App.class.getResource("/imgs/ostrich.JPG");
        if (urlImagem != null) {
            ImageIcon iconeLogo = new ImageIcon(urlImagem);
            //redimensionar a imagem pra não ficar gigante
            Image imagemRedimensionada = iconeLogo.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            JLabel labelLogo = new JLabel(new ImageIcon(imagemRedimensionada));
            labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelTopo.add(Box.createVerticalStrut(20));
            painelTopo.add(labelLogo);
        } else {
            //se não encontrar a imagem, mostra um aviso no lugar
            JLabel labelErro = new JLabel("Logo não encontrada", SwingConstants.CENTER);
            labelErro.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelTopo.add(Box.createVerticalStrut(20));
            painelTopo.add(labelErro);
        }

        //mensagem splash 
        JLabel mensagem = new JLabel("Iniciando o sistema, aguarde...", SwingConstants.CENTER);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));
        mensagem.setAlignmentX(Component.CENTER_ALIGNMENT);

        //montar o painel superior com espaçamentos
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(mensagem);
        painelTopo.add(Box.createVerticalStrut(10));

        splash.add(painelTopo, BorderLayout.CENTER);

        //barra de loading encher
        JProgressBar progressBar = new JProgressBar(0, 100);

        //mostrar porcentagem na barra
        progressBar.setStringPainted(true);   
        splash.add(progressBar, BorderLayout.SOUTH);

        //animar a barra de loading e abrir tela principal
        new Thread(() -> {
            try {
                //encher de 0 a 100 indo de 10 em 10 com mini pausa pro usuario ter tempo de ler 
                for (int i = 0; i <= 100; i += 10) {
                    progressBar.setValue(i);
                    Thread.sleep(150); 
                }
                
                //fechar splash
                splash.dispose();

                //criar o armazem (troque valor para alterar linhas e colunas)
                Armazem armazem = new Armazem(4, 4);
                //abrir a tela do armazem
                SwingUtilities.invokeLater(() -> new TelaArmazem(armazem));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //mostrar splash
        splash.setVisible(true);
    }
}