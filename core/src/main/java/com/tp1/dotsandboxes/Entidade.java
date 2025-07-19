package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entidade {

	protected Texture textura;
	protected TextureRegion region;
	protected Sprite corpo;
	protected float posX;
	protected float posY;

	public Entidade(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala) {
		this.textura = textura;
		this.region = region;
		this.posX = posX;
		this.posY = posY;

		this.corpo = new Sprite(textura);
        this.corpo.setRegion(region);
        this.corpo.setScale(escala.x, escala.y);
        this.corpo.setPosition(posX, posY);
        this.corpo.setOrigin(this.corpo.getBoundingRectangle().width/2, this.corpo.getBoundingRectangle().height/2);
	}

	public void setTextura(Texture textura) {
		this.textura = textura;
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

	public void draw(SpriteBatch batch) {
		corpo.draw(batch);
	}

}
