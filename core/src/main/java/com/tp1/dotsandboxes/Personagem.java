package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Personagem extends EntidadeAnimada {

	protected int vida;	
	protected int dano;
	protected Ataque ataque;
	protected boolean morreu;

	public Personagem(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida, int dano, Ataque ataque) {
		super(textura, region, posX, posY, escala);
		this.vida = vida;
		this.dano = dano;
		this.ataque = ataque;
		this.morreu = false;
	}

	public void atacar() {}	

	public void tomarDano(int dano) {}

	public void morrer(int cordX, int cordY, int largura, int altura, float posX, float posY){
		if(vida == 0){
			morreu = true;
			setRegion(cordX, cordY, largura, altura);
			setPosicao(posX, posY);
		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDano() {
		return dano;
	}

	public boolean isMorreu() {
		return morreu;
	}

	

}
