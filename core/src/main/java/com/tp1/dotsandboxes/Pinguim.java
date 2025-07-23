package com.tp1.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Pinguim extends Chefao implements Inimigo {

	private int ataqueAtual;
	private ArrayList<Ataque> ataques;
	private ArrayList<Ataque> ataquesAtivos;

	public Pinguim(Texture textura, float posX, float posY) {
		super(textura, new TextureRegion(textura, 602, 0, 43, 60), posX, posY, 
		new Vector2(0.1f, 3.0f), 32, 0.05f, null,  0, 0);	

		paraEsquerda = true;
		paraDireita = false;
		quantAcoes = 4;

		criarAtaques();
	}

	public void criarAtaques(){
		ataqueAtual = 0;
		ataquesAtivos = new ArrayList<>();

		ataques = new ArrayList<>();

		ataques.add(
			new Ataque(new TextureRegion(TipoAtaque.BOLA_GELO.getTextura(), 
			TipoAtaque.BOLA_GELO.getCordX1(), TipoAtaque.BOLA_GELO.getCordY1(),
			TipoAtaque.BOLA_GELO.getLargura1(), TipoAtaque.BOLA_GELO.getAltura1()), 
			-100, -100, new Vector2(1f, 2.5f), TipoAtaque.BOLA_GELO, 0)
		);

		ataques.add(
			new Ataque(new TextureRegion(TipoAtaque.SOPRO_GELO.getTextura(), 
			TipoAtaque.SOPRO_GELO.getCordX1(), TipoAtaque.SOPRO_GELO.getCordY1(),
			TipoAtaque.SOPRO_GELO.getLargura1(), TipoAtaque.SOPRO_GELO.getAltura1()), 
			-100, -100, new Vector2(1f, 2.5f), TipoAtaque.SOPRO_GELO, 0)
		);

		ataque = ataques.get(0);
	}



	public Rectangle getRect(){
		return corpo.getBoundingRectangle();
	}

    public float getDano() {
		return dano;
	}

	public ArrayList<Ataque> getAtaquesAtivos(){
		return ataquesAtivos;
	}

	public void tomarDano(float dano) {
		vida = vida - dano;
		tomandoDano = true;
		deltaTime = 0f;
	}

	public void setPosXmegaMan(float posXmegaMan) {
		this.posXmegaMan = posXmegaMan;
	}

	@Override
	protected void setRegion(int cordX, int cordY, int largura, int altura) {
		region.setRegion(cordX, cordY, largura, altura);

		boolean flipou = region.isFlipX();
		if(paraDireita != flipou) {
			region.flip(true, false);
		}

		corpo.setRegion(region);
	}


	public void atualizar(){
		if(!morreu){
			tomandoDanoPorAtaque(1, 43, 731, 0, 43, 60, 
			0, 0, 43, 60);

			sofrerGravidade(posY, 1, 0, 602, 0, 43, 
			60, 0, 0, 43, 60);

			delimitarMovimento();

			int acaoAnterior = determinaAcao;
			iterarDeltaTime();
			atualizarAcao();
			
			if(!noAr){
				if(determinaAcao != acaoAnterior){
					if(determinaAcao == 0){
						determinarAcaoMover();
					}else if(determinaAcao == 1){
						determinarAcaoParado();
					}else if(determinaAcao == 2){
						determinarAtaqueBolaGelo();
					}else if(determinaAcao == 3){
						determinarAtaqueSoproGelo();
					}
				} 
			}

		}
	}

	private void determinarAcaoMover(){
		duracaoAcao = 3.f;
		podeMover = true;
		podeAtacar = false;
	}

	private void determinarAcaoParado(){
		duracaoAcao = 2f;
		parado(3, 43, 0, 0, 43, 60);
		podeAtacar = false;
		podeMover = false;
	}

	private void determinarAtaqueBolaGelo(){
		duracaoAcao = 3.f;
		ataqueAtual = 0;
		ataque = ataques.get(ataqueAtual);
		podeAtacar = true;
		podeMover = false;
	}

	private void determinarAtaqueSoproGelo(){
		duracaoAcao = 3f;
		ataqueAtual = 1;
		ataque = ataques.get(ataqueAtual);
		podeAtacar = true;
		podeMover = false;
	}

	private void delimitarMovimento(){
		if(posX <= 140){
			paraDireita = true;
			paraEsquerda = false;
		} else if(posX >= 1020){
			paraEsquerda = true;
			paraDireita = false;
		}
	}

	@Override
	public void mover() {
		if (podeMover) {
			if (posXmegaMan < posX) {
				moverParaEsquerda(1, 43, 301, 0, 43, 60);
			} else if (posXmegaMan > posX) {
				moverParaDireita(1, 43, 301, 0, 43, 60);
			}
		} else {
			if (posXmegaMan < posX) {
				paraDireita = false;
				paraEsquerda = true;
			} else if (posXmegaMan > posX) {
				paraDireita = true;
				paraEsquerda = false;
			}
			setRegion(0, 0, 43, 60); 
		}
	}


	@Override
	public void atacar(){
		if(podeAtacar &&  deltaTime <= 0f && Math.abs(posXmegaMan - posX) < 600){
			setRegion(688, 0, 43, 60);

			float posXataque = corpo.getX() - corpo.getBoundingRectangle().width + 5f;
			float posYataque = corpo.getY() - corpo.getHeight() - ataque.getCorpo().getHeight() - 10f;

			int velocidadeAtaque = 0;
			if(posXmegaMan > posX){
				paraDireita = true;
				paraEsquerda = false;
				velocidadeAtaque = 5;
			}else if(posXmegaMan < posX){
				paraDireita = false;
				paraEsquerda = true;
				velocidadeAtaque = -5;
			}

			Ataque novoAtaque = new Ataque(
				new TextureRegion(ataque.getTipo().getTextura(),
				ataque.getTipo().getCordX1(), ataque.getTipo().getCordY1(),
				ataque.getTipo().getLargura1(), ataque.getTipo().getAltura1()),
				posXataque, posYataque, new Vector2(1f, 2.5f), 
				ataque.getTipo(), velocidadeAtaque
			);

			novoAtaque.setColidiu(false);
			novoAtaque.setPodeDisparar(true);
			ataquesAtivos.add(novoAtaque);
		}
	}

	@Override
	public void morrer(){
		if(vida <= 0){
			morreu = true;
			iterarDeltaTime();
			setRegion(774, 0, 43, 60);

			if (deltaTime >= 5.0f) {
				setPosicao(-500, -500);
			}
		}
	}

}