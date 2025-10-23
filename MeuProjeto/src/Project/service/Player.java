package Project.service;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private int dx, dy;
    private Image Imagem;
    private int altura, largura;

    private boolean noChao = false;
    private boolean correndo = false;

    private final int GRAVIDADE = 1;
    private final int FORCA_PULO = -12;
    private final int CHAO = 481-97;

    private final int VEL_NORMAL = 1;
    private final int VEL_CORRENDO = 6;

    // VARIAVEL COMBATE PORRA
    private int hp = 100;
    private boolean atacando = false;
    private long ultimoDano = 0;   // timestamp do último dano
    private final long cooldownDano = 500; // 500ms ou 0,5s
    private combate arma;;
    private Image[] ataqueFrames;
    private int atkIndex = 0;           // índice do frame atual do ataque
    private int atkCount = 0;           // contador de delay
    private int atkDelay = 5;           // quantos updates esperar antes de trocar de frame

    private Image[] andando;
    private Image parado;
    private Image pulando;

    private int frameIndex = 0;
    private int frameDelay = 10;
    private int frameCount = 0;

    private Image[] respirando;
    private int idleIndex = 0;
    private int idleCount = 0;
    private int idleDelay = 10;
    private boolean teclaAPressionada;
    private boolean teclaDPressionada;
    public Player() {
        this.x = 100;
        this.y = CHAO;
    }

    public void load() {
        parado = new ImageIcon("res\\PLAYER-01.png").getImage();
        pulando = new ImageIcon("res\\Player_Pulo.png").getImage();

        andando = new Image[6];
        andando[0] = new ImageIcon("res\\andando1.png").getImage();
        andando[1] = new ImageIcon("res\\andando2.png").getImage();
        andando[2] = new ImageIcon("res\\andando3.png").getImage();
        andando[3] = new ImageIcon("res\\andando4.png").getImage();
        andando[4] = new ImageIcon("res\\andando5.png").getImage();
        andando[5] = new ImageIcon("res\\andando3.png").getImage();

        respirando = new Image[5];
        respirando[0] = new ImageIcon("res\\PLAYER-01.png").getImage();
        respirando[1] = new ImageIcon("res\\Player-02.png").getImage();
        respirando[2] = new ImageIcon("res\\Player-03.png").getImage();
        respirando[3] = new ImageIcon("res\\Player-02.png").getImage();
        respirando[4] = new ImageIcon("res\\Player-01.png").getImage();

        Imagem = respirando[0];
        altura = Imagem.getHeight(null);
        largura = Imagem.getWidth(null);
        equiparArma(new Soco());
    }

    public void update() {
        if (!noChao) dy += GRAVIDADE;

        x += dx;
        y += dy;

        if (y >= CHAO) {
            y = CHAO;
            dy = 0;
            noChao = true;
        }

        //ANIMAÇÃO DE COMBATE FODAO
        if (atacando && ataqueFrames != null) {
            atkCount++;
            if (atkCount >= atkDelay) {
                atkIndex++;
                atkCount = 0;
            }
            if (atkIndex >= ataqueFrames.length) {
                atkIndex = 0;
                atacando = false;
            } else {
                Imagem = ataqueFrames[atkIndex];
            }
        }

        // =====================
        // ANIMAÇÕES
        // =====================

        else if (!noChao) {
            Imagem = pulando;
        } else if (dx != 0) {
            frameCount++;
            if (frameCount >= frameDelay) {
                frameIndex = (frameIndex + 1) % andando.length;
                frameCount = 0;
            }
            Imagem = andando[frameIndex];
        } else {
            idleCount++;
            if (idleCount >= idleDelay) {
                idleIndex = (idleIndex + 1) % respirando.length;
                idleCount = 0;
            }
            Imagem = respirando[idleIndex];
        }
    }

    // =====================
    // COMBATE
    // =====================
    public void equiparArma(combate novaArma) {
        arma = novaArma;
        ataqueFrames = arma.getFrames(); // atualiza os frames para a arma equipada
    }

    public void atacar(List<Inimigo1> inimigos) {
        if (!atacando) {
            atacando = true;
            atkIndex = 0;
            atkCount = 0;
            if (arma != null) {
                arma.atacar(this, inimigos); // aplica dano
            }
        }
    }
    public void tomarDano(int dano) {
        long agora = System.currentTimeMillis();
        if (agora - ultimoDano >= cooldownDano) {
            hp -= dano;
            ultimoDano = agora;
            System.out.println("Player tomou " + dano + " de dano. HP: " + hp);
            if (hp <= 0) {
                morrer();
            }
        }
    }

    private void morrer() {
        System.out.println("Player morreu!");
        // game over aqui
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    // =====================
    // CONTROLES
    // =====================
    public void keyPressed(KeyEvent tecla, List<Inimigo1> inimigos) {
        int codigo = tecla.getKeyCode();

        //movimento foda do pedro
        if (codigo == KeyEvent.VK_SHIFT) { correndo = true; if (dx < 0) dx = -VEL_CORRENDO; if (dx > 0) dx = VEL_CORRENDO; }
        if (codigo == KeyEvent.VK_W && noChao) { dy = FORCA_PULO; noChao = false; }
        if (codigo == KeyEvent.VK_A) {dx = correndo ? -VEL_CORRENDO : -VEL_NORMAL; teclaAPressionada = true;}// Variável para ajudar a informar que a tecla A está pressionada
        if (codigo == KeyEvent.VK_D) {dx = correndo ? VEL_CORRENDO : VEL_NORMAL; teclaDPressionada = true;}// Variável para ajudar a informar que a tecla D está pressionada


        // TROCA DE ARMAS
        if (codigo == KeyEvent.VK_1) equiparArma(new Soco());
        if (codigo == KeyEvent.VK_2) equiparArma(new Espada());
        if (codigo == KeyEvent.VK_3) equiparArma(new tiro());

        //atacar inimigus
        if (codigo == KeyEvent.VK_J) {
            atacar(inimigos);
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_A) {
            teclaAPressionada = false;// Variável para ajudar a informar que a tecla A está pressionada
            dx = teclaDPressionada ? VEL_NORMAL : 0;//valida se a teca "d" ainda está pressionada para mudar de direção
        }else if ( codigo == KeyEvent.VK_D) {
            teclaDPressionada = false; // sempre atualiza!
            dx = teclaAPressionada ? -VEL_NORMAL : 0;//valida se a teca "d" ainda está pressionada para mudar de direção
        }
        if (codigo == KeyEvent.VK_SHIFT) {
            correndo = false;
            if (dx < 0) dx = -VEL_NORMAL;
            if (dx > 0) dx = VEL_NORMAL;
        }
    }

    // =====================
    // GETTERS
    // =====================
    public int getX() { return x; }
    public int getY() { return y; }
    public Image getImagem() { return Imagem; }
    public int getHP() { return hp; }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }
}
