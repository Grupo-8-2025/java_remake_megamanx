package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Ataque extends EntidadeAnimada {

	private TipoAtaque tipo;
	private boolean disponivel;
	private boolean isMegaMan;
	private boolean colidiu;
	private boolean podeDisparar;

	Ataque(TextureRegion region, float posX, float posY, Vector2 escala, TipoAtaque tipo){
		super(tipo.getTextura(), region, posX, posY, escala);
		this.tipo = tipo;		
		this.disponivel = tipo.isDisponivel();
		this.isMegaMan = tipo.isMegaMan();
		this.colidiu = false;
		this.podeDisparar = false;
	}

	public void disparar(int multiplicador) {
		if(!colidiu && podeDisparar){
			int velocidade = (int) tipo.getVelocidade() * multiplicador;
			setPosicao(posX + velocidade, posY);
			animar(tipo.getQtdFrames1(), tipo.getIncrementa1(), tipo.getCordX1(), 
			tipo.getCordY1(), tipo.getLargura1(), tipo.getAltura1());
		}
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

	

}