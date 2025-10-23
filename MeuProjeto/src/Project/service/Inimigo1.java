package Project.service;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class Inimigo1 {
    private int x;
    private int y;
    private Image Imagem;
    private boolean isVisivel;
    private int altura;
    private int largura;
    private final int CHAO = 481-85;

    // Agora são variáveis de instância
    private int vida = 20;
    private int velocidade = 1;
    private int dano = 1; // coloquei 10 em vez de 100 pra não ser hitkill

    public Inimigo1(int playerX) {
        int dX = (-1) * ((int)(Math.random() * (900 - 50 + 1) + 50)); // spawn rand
        Random rand = new Random();
        boolean alea_pos = rand.nextBoolean();
        if (Math.abs(playerX - dX) > playerX || (Math.abs(playerX - dX) < playerX && alea_pos)) {
            dX = dX * (-1);
        }
        this.x = playerX + dX;
        this.y = CHAO;
        this.isVisivel = true;
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("res\\Inimigo1.png");
        this.Imagem = referencia.getImage();
        this.altura = this.Imagem.getHeight(null);
        this.largura = this.Imagem.getWidth(null);
    }

    public void update(int playerX) {
        // Inimigo segue o player
        if (playerX > this.x) {
            this.x += velocidade;
        } else if (playerX < this.x) {
            this.x -= velocidade;
        }
    }

    // ==============================
    // COMBATE
    // ==============================

    public void atacar(Player player) {
        if (this.getBounds().intersects(player.getBounds())) {
            player.tomarDano(dano);
        }
    }

    public void tomarDano(int dano) {
        vida -= dano;
        System.out.println("Inimigo tomou " + dano + " de dano. Vida: " + vida);
        if (vida <= 0) {
            morrer();
        }
    }

    private void morrer() {
        isVisivel = false;
        System.out.println("Inimigo morreu!");
    }

    // ==============================
    // GETTERS E BOUNDS
    // ==============================

    public Rectangle getBounds() {
        return new Rectangle(x, y, largura, altura);
    }

    public boolean isVisivel() {
        return isVisivel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return Imagem;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }
}
