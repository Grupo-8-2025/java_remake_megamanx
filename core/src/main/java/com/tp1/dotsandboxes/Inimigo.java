package com.tp1.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

public interface Inimigo {
    
    public Rectangle getRect();

    public float getDano();

    public ArrayList<Ataque> getAtaquesAtivos();

    public void setPosXmegaMan(float posX);

    public void tomarDano(float dano);
    
}
