package com.tp1.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Personagem extends Entidade{
    

    //protected ArrayList<Texture> texturas; 
    protected float velX;
    protected float velY;
    protected float deltaTime;
    protected float gravidade;
    protected boolean podeAndar;

    public Personagem(){
        super();
        this.velX = 0;
        this.velY = 0;
        this.deltaTime = 0.05f;
        this.gravidade = 0.03f;
        this.podeAndar = true;
    }

    public Personagem(Texture textura, float posX, float posY, TextureRegion region, Vector2 escala, float velX, float velY){
        super(textura, posX, posY, region, escala);
        this.velX = velX;
        this.velY = velY;
        this.deltaTime = 0.5f;
        this.gravidade = 0.3f;
        podeAndar = true;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isPodeAndar() {
        return podeAndar;
    }

    public void setPodeAndar(boolean podeAndar) {
        this.podeAndar = podeAndar;
    }

    public void mover(){
        setPosX(posX + velX);
        setPosY(posY + velY);
    }

}