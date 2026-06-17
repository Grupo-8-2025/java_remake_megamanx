package com.tp1.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Pinguim extends Chefao implements Inimigo {

	private ArrayList<Ataque> ataques;
	private int ataqueAtual;
	private boolean podeAtacar;

	public Pinguim(Texture textura, float posX, float posY) {
		super(textura, new TextureRegion(textura, 602, 0, 43, 60), posX, posY, 
		new Vector2(0.1f, 3.0f), 32, 4, null,  0, 0);	
		naPlataforma = false;
		podeAtacar = false;

		ataques = new ArrayList<>();

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.BOLA_GELO.getTextura(), 
		TipoAtaque.BOLA_GELO.getCordX1(), TipoAtaque.BOLA_GELO.getCordY1(),
		TipoAtaque.BOLA_GELO.getLargura1(), TipoAtaque.BOLA_GELO.getAltura1()), 
		-100, -100, new Vector2(1f, 2.5f), TipoAtaque.BOLA_GELO));

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.PINGUIN_GELO.getTextura(), 
		TipoAtaque.PINGUIN_GELO.getCordX1(), TipoAtaque.TIRO_AZUL.getCordY1(),
		TipoAtaque.PINGUIN_GELO.getLargura1(), TipoAtaque.PINGUIN_GELO.getAltura1()), 
		-100, -100, new Vector2(1f, 2.5f), TipoAtaque.PINGUIN_GELO));

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.SOPRO_GELO.getTextura(), 
		TipoAtaque.SOPRO_GELO.getCordX1(), TipoAtaque.SOPRO_GELO.getCordY1(),
		TipoAtaque.SOPRO_GELO.getLargura1(), TipoAtaque.SOPRO_GELO.getAltura1()), 
		-100, -100, new Vector2(1f, 2.5f), TipoAtaque.SOPRO_GELO));

		ataque = ataques.get(0);
		ataqueAtual = 0;

		quantAcoes = 5;
	}

	

    public ArrayList<Ataque> getAtaques() {
		return ataques;
	}



	private void delimitarMovimento(){
        
    }

	public void atitudes(){
		sofrerGravidade(posY, 1, 0, 602, 0, 43, 
		60, 0, 0, 43, 60);

		int acaoAnterior = determinaAcao;
		iterarDeltaTime();
		atualizarAcao();

		if(!noAr){
			if(determinaAcao != acaoAnterior){
				if(determinaAcao == 0){
					duracaoAcao = 5.f;
					mover(); 
					podeAtacar = false;
				}else if(determinaAcao == 1){
					duracaoAcao = 2.f;
					parado();
					podeAtacar = false;
				}else if(determinaAcao == 2){
					duracaoAcao = 0.5f;
					ataqueAtual = 0;
					this.ataque = ataques.get(ataqueAtual);
					podeAtacar = true;
				}else if(determinaAcao == 3){
					duracaoAcao = 5.f;
					ataqueAtual = 1;
					this.ataque = ataques.get(ataqueAtual);
					podeAtacar = true;
				}else if(determinaAcao == 4){
					duracaoAcao = 0.5f;
					ataqueAtual = 2;
					this.ataque = ataques.get(ataqueAtual);
					podeAtacar = true;
				}
			} 
		}

	}

	@Override
	public void mover() {
		moverParaEsquerda(1, 43, 301, 0, 43, 60);		
	}

	@Override
	public void atacar(){
		if(podeAtacar){
			setRegion(688, 0, 43, 60);
			float posXataque = corpo.getX() - corpo.getBoundingRectangle().width;
			float posYataque = corpo.getY() - corpo.getBoundingRectangle().height/2;
			ataque.setPosicao(posXataque, posYataque);
			ataque.setColidiu(false);
			ataque.setPodeDisparar(true);
		}
	}

	@Override
	protected void pular() {

	}

	@Override
	protected void parado() {
		velX = 0;            
        animar(3, 43, 0, 0, 43, 60);
	}

	@Override
	public void tomarDano(int dano) {
		vida -= dano;
		if (vida <= 0) {
			vida = 0;
			morrer(774, 0, 43, 60, -500, -500); 
		} else {
			setRegion(731, 0, 43, 60); 
		}
	}

	@Override
	public void atacarMegaMan(MegaMan megaman){
		if(calculaDistanciaMegaMan(megaman) < 5){
			podeAtacar = true;
			atacar();
		}
	}

	@Override
    public Vector2 getPosition(){
		posicao.set(posX, posY);
		return posicao;
	}

	@Override
    public Sprite getCorpo(){
		return corpo;
	}

	@Override
    public double calculaDistanciaMegaMan(MegaMan megaman){
		double distancia;
		distancia = Math.sqrt(Math.pow((getPosition().x - megaman.getPosX()), 2) + Math.pow((getPosition().y - megaman.getPosY()), 2));
		return distancia;
	}

}