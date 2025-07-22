package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Ataque extends EntidadeAnimada {

	private TipoAtaque tipo;
	private boolean disponivel;
	private boolean isMegaMan;
	private boolean colidiu;
	private boolean podeDisparar;
	private boolean podeDefinirPosicao;
	private boolean disparou;
	private float velocidade;

	Ataque(TextureRegion region, float posX, float posY, Vector2 escala, TipoAtaque tipo, float velocidade){
		super(tipo.getTextura(), region, posX, posY, escala);
		this.tipo = tipo;		
		this.disponivel = tipo.isDisponivel();
		this.isMegaMan = tipo.isMegaMan();
		this.colidiu = false;
		this.podeDisparar = false;
		this.podeDefinirPosicao = true;
		this.disparou = false;
		this.velocidade = velocidade;
	}

	public void disparar() {
		if(!colidiu && podeDisparar){
			setPosicao(posX + velocidade, posY);
			animar(tipo.getQtdFrames1(), tipo.getIncrementa1(), tipo.getCordX1(), 
			tipo.getCordY1(), tipo.getLargura1(), tipo.getAltura1());
		}
	}

	public boolean isDisparou() {
		return disparou;
	}

	public void setDisparou(boolean disparou) {
		this.disparou = disparou;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public boolean isMegaMan() {
		return isMegaMan;
	}

	public void setMegaMan(boolean isMegaMan) {
		this.isMegaMan = isMegaMan;
	}

	public boolean isColidiu() {
		return colidiu;
	}

	public void setColidiu(boolean colidiu) {
		this.colidiu = colidiu;
	}

	public TipoAtaque getTipo() {
		return tipo;
	}

	public boolean isPodeDisparar() {
		return podeDisparar;
	}

	public void setPodeDisparar(boolean podeDisparar) {
		this.podeDisparar = podeDisparar;
	}

	public boolean isPodeDefinirPosicao() {
		return podeDefinirPosicao;
	}

	public void setPodeDefinirPosicao(boolean podeDefinirPosicao) {
		this.podeDefinirPosicao = podeDefinirPosicao;
	}


	

}