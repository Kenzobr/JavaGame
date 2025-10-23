package Project.service;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class tiro extends combate {

    public tiro() {
        super(1, 3000); // dano baixo, alcance alto

        //ANIMAÇÃO KRLLL
        frames = new Image[3];
        frames[0] = new ImageIcon("res\\espada1.png").getImage();
        frames[1] = new ImageIcon("res\\espada2.png").getImage();
        frames[2] = new ImageIcon("res\\espada3.png").getImage();
    }

    @Override
    public void atacar(Player player, List<Inimigo1> inimigos) {
        // Hitbox longa simulando um tiro
        Rectangle hitbox = new Rectangle(player.getX(), player.getY(), alcance, player.getAltura());

        for (Inimigo1 in : inimigos) {
            if (hitbox.intersects(in.getBounds())) {
                in.tomarDano(dano);
            }
        }
    }
}