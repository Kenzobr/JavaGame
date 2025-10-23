package Project.service;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Espada extends combate {

    public Espada() {
        super(20, 80);


        //ANIMAÇÃO KRLLL
        frames = new Image[5];
        frames[0] = new ImageIcon("res\\espada1.png").getImage();
        frames[1] = new ImageIcon("res\\espada2.png").getImage();
        frames[2] = new ImageIcon("res\\espada3.png").getImage();
        frames[3] = new ImageIcon("res\\espada4.png").getImage();
        frames[4] = new ImageIcon("res\\espada5.png").getImage();

    }

    @Override
    public void atacar(Player player, List<Inimigo1> inimigos) {
        Rectangle hitbox = new Rectangle(player.getX() + player.getLargura(), player.getY(), alcance, player.getAltura());

        for (Inimigo1 in : inimigos) {
            if (hitbox.intersects(in.getBounds())) {
                in.tomarDano(dano);
            }
        }
    }
}