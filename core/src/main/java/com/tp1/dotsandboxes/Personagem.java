package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Personagem extends EntidadeAnimada {

	protected int vida;	

	public Personagem(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida) {
		super(textura, region, posX, posY, escala);
		this.vida = vida;
	}

	public void atacar() {}	

	public void tomarDano(int dano) {}

}
