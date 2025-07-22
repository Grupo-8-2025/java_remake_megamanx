package com.tp1.dotsandboxes;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Chefao extends PersonagemMovel {

	protected Random random;
	protected int quantAcoes;
	protected int determinaAcao;
	protected float duracaoAcao;

	protected boolean dependurado;
	protected boolean podeMover;
	protected boolean podeAtacar;

	public Chefao(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, float vida, 
		float dano, Ataque ataque, float velX, float velY) {
		super(textura, region, posX, posY, escala, vida, dano, ataque, velX, velY);
		random = new Random();
		quantAcoes = 0;
		determinaAcao = 0;
		duracaoAcao = 0;
		dependurado = false;
		podeMover = false;
		podeAtacar = false;

		noAr = true;
	}

	protected void atualizarAcao() {
		if (deltaTime >= duracaoAcao) {
			determinaAcao = random.nextInt(quantAcoes);
			deltaTime = 0f; 
		}
	}

	protected void moverParaDireita(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		paraDireita = true;
		paraEsquerda = false;
		velX = 5;
		setPosicao(posX + velX, posY);
		animar(posX, qtdFrames, incrementa, cordX, cordY, largura, altura);
	}

	protected void moverParaEsquerda(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {
		paraDireita = false;
		paraEsquerda = true;
		velX = -5;
		setPosicao(posX + velX, posY);
		animar(posX, qtdFrames, incrementa, cordX, cordY, largura, altura);
	}

	protected void parado(int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura){
		velX = 0;            
        animar(qtdFrames, incrementa, cordX, cordY, largura, altura);
	}

	protected void dependurado(float posicao, int qtdFrames, int incrementa, int cordX, int cordY, int largura, int altura) {

	}

}