package Project.service;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Soco extends combate {

    public Soco() {
        super(5, 20);

        //ANIMAÇÃO KRLLL
        frames = new Image[5];
        frames[0] = new ImageIcon("res\\soco1.png").getImage();
        frames[1] = new ImageIcon("res\\soco2.png").getImage();
        frames[2] = new ImageIcon("res\\soco3.png").getImage();
        frames[3] = new ImageIcon("res\\soco4.png").getImage();
        frames[4] = new ImageIcon("res\\soco5.png").getImage();
    }

    @Override
    public void atacar(Player player, List<Inimigo1> inimigos) {
        // Cria uma hitbox na frente do player
        Rectangle hitbox = new Rectangle(player.getX() + player.getLargura(), player.getY(), alcance, player.getAltura());

        for (Inimigo1 in : inimigos) {
            if (hitbox.intersects(in.getBounds())) {
                in.tomarDano(dano);
            }
        }
    }
}