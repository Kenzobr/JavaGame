package Project.service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Inimigo {

    private int x, y;
    private int dx, dy;
    private Image Imagem;
    private int altura, largura;

    public Inimigo(int x, int y) {
        this.x = x;//Criar uma lógica que o inimigo vai ser espalnar aleatóriamente de acordo com a posição do player
        this.y = y;//Criar uma lógica que o inimigo vai ser espalnar aleatóriamente de acordo com a posição do player
    }

}
