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

	public PersonagemMovel(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida,
			float velX, float velY) {
		super(textura, region, posX, posY, escala, vida);
		this.velX = velX;
		this.velY = velY;
		gravidade = 0.3f;
		noAr = false;
	}

	public void mover() {}

	protected void moverParaDireita() {}

	protected void moverParaEsquerda() {}

	protected void pular() {}

	protected void sofrerGravidade(float posicaoY, int qtdFrames, int incrementa, int cordX1, int cordY1, int largura1, int altura1, int cordX2, int cordY2, int largura2, int altura2, boolean isMegaMan) {
		if(noAr){
            velY = velY - (gravidade * 0.5f);
            setPosicao(posX, posY + velY);

            animar(posicaoY, qtdFrames, incrementa, cordX1, cordY1, largura1, altura1, isMegaMan);

            if (posY <= 200) {
                posY = 200;
                velY = 0;
                noAr = false;
                setRegion(cordX2, cordY2, largura2, altura2); 
            }

        }
	}

}
