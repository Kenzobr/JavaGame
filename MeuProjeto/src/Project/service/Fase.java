package Project.service;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//criando background do game
public class Fase extends JPanel implements ActionListener {
    public static int wave;
    private Image fundo;
    private Player player;
    private Timer timer;
    private List<Inimigo1> inimigos;
    private String nomeJogador;

    public Fase(String nomeJogador) {
        this.nomeJogador = nomeJogador;
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("res\\background.png");
        fundo = referencia.getImage();

        player = new Player();

        player.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();

        spawnaInimigos();
    }

    // ----------------------- SPAWN INIMIGOS -----------------------
    public void spawnaInimigos() {
        int coordenadas[] = new int[4]; // quantos inimigos spawnam
        inimigos = new ArrayList<>(); // cria a lista

        for (int i = 0; i < coordenadas.length; i++) {
            //int y = (int)(Math.random() * 720+30);// SEGESTÃO de código para fazer o inimigo spawnar fora da tela
            //int x = (int)(Math.random() * 8000+1024);// SEGESTÃO de código para fazer o inimigo spawnar fora da tela

            Inimigo1 in = new Inimigo1(player.getX()); // Cria um novo objeto de inimigo detro da lista de inimigos criada anteriormente inserindo a localização do player como parâmetro para spawnar o inimigo com base na sua posição
            in.load(); // carrega a imagem do inimigo
            inimigos.add(in); // adiciona o inimigo na lista
        }
    }


    // ----------------------- PAINT -----------------------
    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

        for (Inimigo1 in : inimigos) {
            graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
        }

        g.dispose();
    }

    // ----------------------- ACTION -----------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        // Atualiza player
        player.update();

        // Atualiza inimigos
        for (int i = 0; i < inimigos.size(); i++) {// FOR que atualiza a posição de todos os inimigos
            Inimigo1 in = inimigos.get(i); // cria um Inimigo1 e coloca o objeto de indice no valor da variável "a" dentro de "in"
            int mindX = 20;//define a distância mínima entre o inimigo e o Player
            int dXInimigoXplayer = (in.getX() - player.getX());// diferença da posição entre inimigo e player, se positivo, inimigo está a direita, se negativo inimigo está a esquerda
            if (in.isVisivel()) {
                int o = 0;
                for (int b = 0; b < inimigos.size(); b++) {//Inicia um loop de validação da posição de todos os inimigos da classe Inimigo1
                    if (i != b) {//Garante que o inimigo Verificado não seja o inimigo Atualizado
                        Inimigo1 outroin = inimigos.get(b);// cria um Inimigo1 e coloca o objeto de indice no valor da variável "b" dentro de "outroin"
                        int dXoutroInXin = (outroin.getX() - in.getX());// calcula a diferença da posição entre o inimigo Atualizado e o inimigo verificado, se positivo, inimigo verificado está a direita, se negativo inimigo verificado está a esquerda
                        int dXoutroinXplayer = (outroin.getX() - player.getX());
                        if (dXInimigoXplayer > 0 && dXoutroinXplayer > 0 && dXoutroInXin < 0 // Se os dois inimigos estiverem no mesmo lado em relação ao player e o inimigo avaliado estiver entre eles
                                || dXInimigoXplayer < 0 && dXoutroinXplayer < 0 && dXoutroInXin > 0) { // Se os dois inimigos estiverem no mesmo lado em relação ao player e o inimigo avaliado estiver entre eles
                            o++;// ele conta quantos inimigos estão na porra DO CAMINHO
                        }
                    }
                }
                if (Math.abs(dXInimigoXplayer) > (mindX + (mindX * o))) {// Valida se o inimigo está a uma minima distancia do player mais o produto dessa mesma distância pela quantidade de inimigos entre ele e o player
                    in.update(player.getX());// Se não estiver próximo dos outros, ele se aproxima

                }
                in.atacar(player);

            } else {
                inimigos.remove(i);
            }

        }
        // FIM --------------------------------------AVALIAR PARA COLOCAR ESSE BLOCO EM OUTRO MÉTODO--------------------------------------
        repaint();
    }

    // ----------------------
    // Teclado
    // ----------------------
    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // Passa a lista de inimigos para o player
            player.keyPressed(e, inimigos);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Passa a lista de inimigos também se precisar (opcional, mas não necessário aqui)
            player.keyRelease(e);
        }
    }

}