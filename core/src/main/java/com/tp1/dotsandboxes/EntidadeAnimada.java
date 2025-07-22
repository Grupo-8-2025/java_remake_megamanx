package com.tp1.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EntidadeAnimada extends Entidade {

	protected float deltaTime;
	
	protected boolean paraEsquerda;
	protected boolean paraDireita;

	EntidadeAnimada(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala){
		super(textura, region, posX, posY, escala);
		deltaTime = 0f;
		paraEsquerda = true;
		paraDireita = false;
	}
	

	protected void setRegion(int cordX, int cordY, int largura, int altura) {
		region.setRegion(cordX, cordY, largura, altura);

		boolean flipou = region.isFlipX();
		if(paraDireita != flipou) {
			region.flip(true, false);
		}

		corpo.setRegion(region);
	}

	public void setPosicao(float posX, float posY) {
		setPosX(posX);
        setPosY(posY);
        corpo.setPosition(posX, posY);
	}


	protected void iterarDeltaTime() {
		deltaTime += Gdx.graphics.getDeltaTime();
	}

	protected void animar(float posicao, int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		int x = (int) posicao / 50 % qtdFrames;
        x = x * incrementa;
        setRegion(cordX + x, cordY, largura, altura); 
	}

	public void animar(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		iterarDeltaTime();
		int frame = (int)(deltaTime / 0.15f) % qtdFrames; 
        int x = frame * incrementa; 
        setRegion(cordX + x, cordY, largura, altura); 
	}

}
