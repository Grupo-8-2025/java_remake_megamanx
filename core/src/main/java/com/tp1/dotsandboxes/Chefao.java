package com.tp1.dotsandboxes;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Chefao extends PersonagemMovel {

	protected Random random;
	protected int determinaAcao;
	protected float duracaoAcao;
	protected boolean dependurado;

	public Chefao(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida, float velX,
			float velY) {
		super(textura, region, posX, posY, escala, vida, velX, velY);
		random = new Random();
		determinaAcao = 0;
		duracaoAcao = 0;
		dependurado = false;

		noAr = true;
	}

	protected void atualizarMovimentoAleatorio() {
		if (deltaTime >= duracaoAcao) {
            determinaAcao = random.nextInt(3);
            deltaTime = 0f; 
        }
	}

	protected void parado() {}

	protected void dependurado(float posicao, int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {}

	protected void moverParaDireita(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		velX = 5;
		posX = posX + velX;
		setPosicao(posX, posY);

		animar(posX, qtdFrames, incrementa, cordX, cordY, largura, altura, noAr);
	}

	protected void moverParaEsquerda(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		velX = -5;
		posX = posX + velX;
		setPosicao(posX, posY);

		animar(posX, qtdFrames, incrementa, cordX, cordY, largura, altura, noAr);
	}

}
