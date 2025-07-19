package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entidade {
    
    protected Texture textura;
    protected Sprite corpo;
    protected float posX;
    protected float posY;

    public Entidade(){
        posX = 0;
        posY = 0;
    }

    public Entidade(Texture textura, float posX, float posY, TextureRegion region, Vector2 escala){
        this.posX = posX;
        this.posY = posY;
        this.textura = textura;
        this.corpo = new Sprite(textura);
        this.corpo.setRegion(region);
        this.corpo.setScale(escala.x, escala.y);
        this.corpo.setPosition(posX, posY);
        this.corpo.setOrigin(this.corpo.getBoundingRectangle().width/2, this.corpo.getBoundingRectangle().height/2);
    }

    public Sprite getCorpo() {
        return corpo;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void setPosicao(float x, float y){
        setPosX(x);
        setPosY(y);
        corpo.setPosition(x, y);
    }

    public void setTexturaCorpo(Texture textura, TextureRegion region, Vector2 escala){
        this.corpo.setTexture(textura);
        this.corpo.setRegion(region);
        this.corpo.setScale(escala.x, escala.y);
        //this.corpo.setScale(0.4f, 2f);
    }

}
