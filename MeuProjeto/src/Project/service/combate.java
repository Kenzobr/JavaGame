package Project.service;

import java.awt.*;
import java.util.List;

public abstract class combate {
    protected int dano;
    protected int alcance; // distância do ataque
    protected Image[] frames;




    public combate(int dano, int alcance) {
        this.dano = dano;
        this.alcance = alcance;
    }

    // método que será chamado quando o player atacar
    public abstract void atacar(Player player, List<Inimigo1> inimigos);


    //getters fodoes
    public Image[] getFrames() {
        return frames;
    }


    public int getDano() {
        return dano;
    }

    public int getAlcance() {
        return alcance;
    }
}