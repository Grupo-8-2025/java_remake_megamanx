package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PersonagemMovel extends Personagem {

	protected float velX;
	protected float velY;
	protected float gravidade;
	protected boolean paraEsquerda;
	protected boolean paraDireita;
	protected boolean noAr;	
	protected boolean naPlataforma;

	public PersonagemMovel(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida, 
			int dano, Ataque ataque, float velX, float velY) {
		super(textura, region, posX, posY, escala, vida, dano, ataque);
		this.velX = velX;
		this.velY = velY;
		gravidade = 0.3f;
		noAr = false;
		naPlataforma = true;
	}

	public void mover() {}

	protected void moverParaDireita() {}

	protected void moverParaEsquerda() {}

	protected void pular() {}

	protected void sofrerGravidade(float posicaoY, int qtdFrames, int incrementa, int cordX1, int cordY1, int largura1, int altura1, int cordX2, int cordY2, int largura2, int altura2) {
		if(noAr && !naPlataforma){
            velY = velY - (gravidade * 0.5f);
            setPosicao(posX, posY + velY);

            animar(posicaoY, qtdFrames, incrementa, cordX1, cordY1, largura1, altura1);

            if (naPlataforma) {
                velY = 0;
                noAr = false;
                setRegion(cordX2, cordY2, largura2, altura2); 
            }

        }
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

	public float getGravidade() {
		return gravidade;
	}

	public void setGravidade(float gravidade) {
		this.gravidade = gravidade;
	}

	public boolean isParaEsquerda() {
		return paraEsquerda;
	}

	public void setParaEsquerda(boolean paraEsquerda) {
		this.paraEsquerda = paraEsquerda;
	}

	public boolean isParaDireita() {
		return paraDireita;
	}

	public void setParaDireita(boolean paraDireita) {
		this.paraDireita = paraDireita;
	}

	public boolean isNoAr() {
		return noAr;
	}

	public void setNoAr(boolean noAr) {
		this.noAr = noAr;
	}

	public boolean isNaPlataforma() {
		return naPlataforma;
	}

	public void setNaPlataforma(boolean naPlataforma) {
		this.naPlataforma = naPlataforma;
	}

}