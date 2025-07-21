package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public interface Inimigo {

    final Vector2 posicao = new Vector2();

    public void atacarMegaMan(MegaMan megaman);
    public Vector2 getPosition();
    public Sprite getCorpo();
    public double calculaDistanciaMegaMan(MegaMan megaman);
}
