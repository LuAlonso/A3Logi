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
        
        //tamanho janela
        splash.setSize(320, 110);
        
        //centralizar
        splash.setLocationRelativeTo(null);

        //layout da janela
        splash.setLayout(new BorderLayout());   

        //mensagem splash 
        JLabel mensagem = new JLabel("Iniciando o sistema, aguarde...", SwingConstants.CENTER);
        mensagem.setFont(new Font("Arial", Font.BOLD, 14));
        splash.add(mensagem, BorderLayout.CENTER);

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