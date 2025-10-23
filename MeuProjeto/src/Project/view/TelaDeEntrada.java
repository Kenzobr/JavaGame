package Project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeEntrada extends JFrame {

    private JTextField campoNome;
    private JButton botaoIniciar;

    public TelaDeEntrada() {
        // Configurações da janela
        setTitle("Digite seu nome");
        setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        this.setResizable(false);

        // Layout da janela
        setLayout(new BorderLayout());

        // Configurar painel de fundo
        JPanel painelFundo = new JPanel();
        painelFundo.setBackground(new Color(40, 40, 40)); // Fundo escuro
        painelFundo.setLayout(new GridBagLayout()); // Usando GridBagLayout para facilitar o posicionamento
        add(painelFundo, BorderLayout.CENTER);  // Adiciona o painelFundo à parte central da janela

        // GridBagConstraints para controlar o
        // posicionamento dos componentes
        GridBagConstraints gbc = new GridBagConstraints();

        // Título
        JLabel labelTitulo = new JLabel("Teste");
        labelTitulo.setFont(new Font("ALGERIAN", Font.BOLD, 26));
        labelTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        painelFundo.add(labelTitulo, gbc);

        // Campo de texto
        JLabel labelNome = new JLabel("Digite seu nome: ");
        labelNome.setFont(new Font("ALGERIAN", Font.PLAIN, 18));
        labelNome.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painelFundo.add(labelNome, gbc);

        campoNome = new JTextField(20);
        campoNome.setFont(new Font("ALGERIAN", Font.PLAIN, 18));
        campoNome.setBackground(new Color(255, 255, 255));
        campoNome.setForeground(new Color(40, 40, 40));
        campoNome.setPreferredSize(new Dimension(250, 40));
        campoNome.setCaretColor(Color.BLACK); // Cor do cursor
        campoNome.setSelectionColor(new Color(58, 174, 90)); // Cor da seleção do texto

        // Bordas arredondadas na caixa do campo de texto
        campoNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelFundo.add(campoNome, gbc);

        // Botão Iniciar
        botaoIniciar = new JButton("Iniciar Jogo");
        botaoIniciar.setFont(new Font("ALGERIAN", Font.BOLD, 18));
        botaoIniciar.setBackground(new Color(58, 174, 90)); // Cor verde
        botaoIniciar.setForeground(Color.WHITE);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setPreferredSize(new Dimension(200, 40));

        // Bordas arredondadas na caixa do botão
        botaoIniciar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 40, 40), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeJogador = campoNome.getText();
                if (nomeJogador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome válido!");
                } else {
                    iniciarJogo(nomeJogador);  // Passa o nome para o jogo
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        painelFundo.add(botaoIniciar, gbc);

        // Efeito hover para o botão
        botaoIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botaoIniciar.setBackground(new Color(48, 145, 72)); // Cor mais escura quando passa o mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botaoIniciar.setBackground(new Color(58, 174, 90)); // Cor original
            }
        });

        setVisible(true);  // Torna a janela visível
    }

    private void iniciarJogo(String nomeJogador) {
        // Fechar a tela de entrada
        this.dispose();

        // Iniciar o jogo, passando o nome do jogador para o Container
        new Container2(nomeJogador);
    }

    public static void main(String[] args) {
        new TelaDeEntrada();  // Inicia a tela de entrada
    }
}
