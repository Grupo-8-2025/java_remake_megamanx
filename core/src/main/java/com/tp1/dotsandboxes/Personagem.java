package com.tp1.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Personagem extends EntidadeAnimada {

	protected ArrayList<Ataque> ataquesAtivos;
	protected Ataque ataque;
	protected float vida;	
	protected float dano;

	private boolean tomandoDano;
	protected boolean naPlataforma;
	protected boolean morreu;

	public Personagem(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, float vida, float dano, Ataque ataque) {
		super(textura, region, posX, posY, escala);
		this.ataque = ataque;
		this.vida = vida;
		this.dano = dano;
		tomandoDano = false;
		naPlataforma = true;
		morreu = false;

		ataquesAtivos = new ArrayList<>();
	}


	public Ataque getAtaque(){
		return ataque;
	}

	public ArrayList<Ataque> getAtaquesAtivos(){
		return ataquesAtivos;
	}

	public float getDano() {
		return dano;
	}

	public boolean isMorreu() {
		return morreu;
	}

	public boolean isNaPlataforma() {
		return naPlataforma;
	}

	public void setNaPlataforma(boolean naPlataforma) {
		this.naPlataforma = naPlataforma;
	}
	

	public void mover() {}

	public void atacar() {}	

	public void morrer(){}

	public void tomarDano(float dano) {
		vida = vida - dano;
		tomandoDano = true;
		deltaTime = 0f;
	}

	public void tomandoDanoPorAtaque(int qtdFrames, int incrementa, int cordX1, int cordY1, int largura1, int altura1, int cordX2, int cordY2, int largura2, int altura2){
		if (tomandoDano) {
			iterarDeltaTime();
			animar(qtdFrames, incrementa, cordX1, cordY1, largura1, altura1);
			
			if (deltaTime >= 3.0f) {
				tomandoDano = false;
				setRegion(cordX2, cordY2, largura2, altura2); 
			}
		}
	}

}
