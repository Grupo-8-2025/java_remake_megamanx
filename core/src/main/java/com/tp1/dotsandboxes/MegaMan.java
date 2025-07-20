package com.tp1.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class MegaMan extends PersonagemMovel { 

	private boolean apertouRight;
	private boolean apertouLeft;
	private boolean apertouUp;
	private boolean apertouDown;
	private boolean apertouX;
	private boolean apertouShift;

	private boolean naEscada;
	private boolean naParede;
	private boolean noRider;
	private boolean colidiuInimigo;
	
	//private boolean podeAtacar;
	private boolean ganhouJogo;

	private ArrayList<Ataque> ataques;
	private int ataqueAtual;

	public MegaMan(Texture textura, float posX, float posY) {
		super(textura, new TextureRegion(textura, 0, 0, 34, 46), posX, posY, 
		new Vector2(0.03f, 1.5f), 16, 0, null, 0, 0);
		apertouRight = false;
		apertouLeft = false;
		apertouUp = false;
		apertouDown = false;
		apertouX = false;
		//segurouC = false;
		apertouShift = false;
		naEscada = false;
		naParede = false;
		noRider = false;
		//podeAtacar = true;
		ganhouJogo = false;
		noAr = true;
		naPlataforma = false;
		paraDireita = true;
		paraEsquerda = false;
		colidiuInimigo = false;
		ataqueAtual = 0;

		ataques = new ArrayList<>();

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.TIRO_NORMAL.getTextura(), 
		TipoAtaque.TIRO_NORMAL.getCordX1(), TipoAtaque.TIRO_NORMAL.getCordY1(),
		TipoAtaque.TIRO_NORMAL.getLargura1(), TipoAtaque.TIRO_NORMAL.getAltura1()), 
		-100, -100, new Vector2(0.5f, 1.5f), TipoAtaque.TIRO_NORMAL));

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.TIRO_AZUL.getTextura(), 
		TipoAtaque.TIRO_AZUL.getCordX1(), TipoAtaque.TIRO_AZUL.getCordY1(),
		TipoAtaque.TIRO_AZUL.getLargura1(), TipoAtaque.TIRO_AZUL.getAltura1()), 
		-100, -100, new Vector2(0.3f, 1.2f), TipoAtaque.TIRO_AZUL));

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.TIRO_VERDE.getTextura(), 
		TipoAtaque.TIRO_VERDE.getCordX1(), TipoAtaque.TIRO_VERDE.getCordY1(),
		TipoAtaque.TIRO_VERDE.getLargura1(), TipoAtaque.TIRO_VERDE.getAltura1()), 
		-100, -100, new Vector2(0.3f, 1.2f), TipoAtaque.TIRO_VERDE));

		ataques.add(new Ataque(new TextureRegion(TipoAtaque.TIRO_ROSA.getTextura(), 
		TipoAtaque.TIRO_ROSA.getCordX1(), TipoAtaque.TIRO_ROSA.getCordY1(),
		TipoAtaque.TIRO_ROSA.getLargura1(), TipoAtaque.TIRO_ROSA.getAltura1()), 
		-100, -100, new Vector2(0.5f, 1.5f), TipoAtaque.TIRO_ROSA));

		ataque = ataques.get(0);
	}

	public boolean isNaEscada() {
		return naEscada;
	}

	public void setNaEscada(boolean naEscada) {
		this.naEscada = naEscada;
	}

	public boolean isNaParede() {
		return naParede;
	}

	public void setNaParede(boolean naParede) {
		this.naParede = naParede;
	}

	public boolean isNoRider() {
		return noRider;
	}

	public void setNoRider(boolean noRider) {
		this.noRider = noRider;
	}

	public boolean isGanhouJogo() {
		return ganhouJogo;
	}

	public void setGanhouJogo(boolean ganhouJogo) {
		this.ganhouJogo = ganhouJogo;
	}

	public boolean testarTecla(int tecla) {
		if(Gdx.input.isKeyPressed(tecla)){
            return true;
        }else{
			return false;
		}
	}

	public int getAtaqueAtual() {
		return ataqueAtual;
	}

	public void setAtaqueAtual(int ataqueAtual) {
		this.ataqueAtual = ataqueAtual;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public boolean isColidiuInimigo() {
		return colidiuInimigo;
	}

	public void setColidiuInimigo(boolean colidiuInimigo) {
		this.colidiuInimigo = colidiuInimigo;
	}

	@Override
	protected void setRegion(int cordX, int cordY, int largura, int altura) {
		region.setRegion(cordX, cordY, largura, altura);

		boolean flipou = region.isFlipX();
		if(paraEsquerda != flipou) {
			region.flip(true, false);
		}

		corpo.setRegion(region);
	}

	@Override
	public void mover() {
		paradoAtirando();
		moverParaDireita();
		moverParaEsquerda();
		pular();
		subirEscada();
		descerEscada();
		subirParede();
		descerParede();
		dashParaDireita();
		dashParaEsquerda();
	}

	@Override
	protected void moverParaDireita(){
        if(testarTecla(Input.Keys.RIGHT)){
            paraDireita = true;
            paraEsquerda = false;
            apertouRight = true;

            velX = 5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			if(testarTecla(Input.Keys.X)){
				animar(posX, 11, 38, 374, 14, 38, 36);
				apertouX = true;
			}else{
				animar(posX, 11, 34, 0, 16, 34, 34);
				apertouX = false;
			}

        }else{
            if(apertouRight){
                setRegion(0, 16, 34, 34); 
                apertouRight = false;
            }
        }
    }

	@Override
	protected void moverParaEsquerda() {
		if(testarTecla(Input.Keys.LEFT)){
            paraDireita = false;
            paraEsquerda = true;
            apertouLeft = true;

            velX = -5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			if(testarTecla(Input.Keys.X)){
				animar(posX, 11, 38, 374, 14, 38, 36);
				apertouX = true;
			}else{
				animar(posX, 11, 34, 0, 16, 34, 34);
				apertouX = false;
			}

        }else{
            if(apertouLeft){
                setRegion(0, 16, 34, 34); 
                apertouLeft = false;
            }
        }
	}

	@Override
	protected void pular() {
		if(testarTecla(Input.Keys.SPACE) && !noAr){
            noAr = true;
			naPlataforma = false;

            velY = 8;
            posY = posY + velY;            
            setPosicao(posX, posY);
        }
		
		if(testarTecla(Input.Keys.X)){
			apertouX = true;
			sofrerGravidade(posY, 7, 36, 1002, 0, 36, 50, 1218, 0, 36, 50);

		}else{
			apertouX = false;
			sofrerGravidade(posY, 7, 30, 792, 0, 30, 50, 966, 0, 30, 50);
		}

	}

	private void subirEscada() {
		if(testarTecla(Input.Keys.UP) && naEscada){
            apertouUp = true;
            velY = 5;
            posY = posY + velY;
            setPosicao(posX, posY);

            animar(posY, 4, 20, 1395, 0, 20, 50); 
        }else{
            if(apertouUp){
				if(testarTecla(Input.Keys.UP)){
					apertouX = true;
					setRegion(1538, 0, 26, 50);
				}else{
					setRegion(1538, 0, 26, 50);
				}
                apertouUp = false;
            }
        }
	}

	private void descerEscada() {
		if(testarTecla(Input.Keys.DOWN) && naEscada){
            apertouDown = true;
            velY = -5;
            posY = posY + velY;
            setPosicao(posX, posY);

            animar(posY, 4, 20, 1395, 0, 20, 50); 
        }else{
            if(apertouDown){
                if(testarTecla(Input.Keys.UP)){
					apertouX = true;
					setRegion(1475, 0, 21, 50);
				}else{
					setRegion(1475, 0, 21, 50);
				}
                apertouDown = false;
            }
        }
	}

	private void subirParede() {

	}

	private void descerParede() {

	}

	private void dashParaDireita() {
		if(testarTecla(Input.Keys.RIGHT) && testarTecla(Input.Keys.SHIFT_LEFT)){
            paraDireita = true;
            paraEsquerda = false;
            apertouRight = true;
			apertouShift = true;

            velX = 5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			animar(posX, 1, 49, 1890, 19, 49, 31);
        }else{
            if(apertouRight && apertouShift){
                setRegion(0, 16, 34, 34); 
                apertouRight = false;
				apertouShift = false;
            }
        }
	}

	private void dashParaEsquerda(){
		if(testarTecla(Input.Keys.LEFT) && testarTecla(Input.Keys.SHIFT_LEFT)){
            paraDireita = false;
            paraEsquerda = true;
            apertouLeft = true;
			apertouShift = true;

            velX = -5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			animar(posX, 1, 49, 1890, 19, 49, 31);
        }else{
            if(apertouLeft && apertouShift){
                setRegion(0, 16, 34, 34); 
                apertouLeft = false;
				apertouShift = false;
            }
        }
	}

	private void paradoAtirando(){
		if(testarTecla(Input.Keys.X)){
			apertouX = true;
			setRegion(1254, 16, 30, 34);            
        }else{
            if(apertouX){
                setRegion(0, 16, 34, 34); 
				apertouX = false;
            }
        }
	}


	@Override
	public void tomarDano(int dano) {
		vida = vida - dano;
		animar(3, 32, 2302, 0, 32, 50);
	}

	public void tomarDanoPorContato(int dano){
		if(colidiuInimigo){
			vida = vida - dano;
			animar(11, 33, 1939, 0, 33, 50);
		}
	}

	@Override
	public void atacar(){
		if(apertouX){
			float posXataque = corpo.getX() + corpo.getBoundingRectangle().width;
			float posYataque = corpo.getY();
			ataque.setPosicao(posXataque, posYataque);
			ataque.setColidiu(false);
			ataque.setPodeDisparar(true);
		}
		mudarAtaque();
	}

	public void mudarAtaque(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.C)){
			ataqueAtual++;
			if (ataqueAtual == 3) {
				ataqueAtual = 0;
			}
			this.ataque = ataques.get(ataqueAtual);
		}
	}

}