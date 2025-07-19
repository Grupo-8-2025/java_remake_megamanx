package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Spark extends Chefao {

	private boolean congelado;

	public Spark(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida, float velX,
			float velY, boolean congelado) {
		super(textura, region, posX, posY, escala, vida, velX, velY);
		this.congelado = congelado;
	}

	public void mover() {

	}

	protected void pular() {

	}

	protected void parado() {

	}

	private void congelado() {

	}

	public void tomarDano() {

	}

}
