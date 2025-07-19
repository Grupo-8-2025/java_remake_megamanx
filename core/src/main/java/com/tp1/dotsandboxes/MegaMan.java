package com.tp1.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MegaMan extends PersonagemMovel {

	private boolean apertouRight;
	private boolean apertouLeft;
	private boolean apertouUp;
	private boolean apertouDown;
	//private boolean apertouX;
	//private boolean segurouC;
	//private boolean segurouShift;
	private boolean naEscada;
	private boolean naParede;
	private boolean noRider;
	private boolean podeAtacar;
	private boolean ganhouJogo;
	private boolean isMegaMan;

	public MegaMan(Texture textura, float posX, float posY) {
		super(textura, new TextureRegion(textura, 0, 0, 34, 46), posX, posY, 
		new Vector2(0.1f, 3.0f), 16, 0, 0);
		apertouRight = false;
		apertouLeft = false;
		apertouUp = false;
		apertouDown = false;
		//apertouX = false;
		//segurouC = false;
		//segurouShift = false;
		naEscada = false;
		naParede = false;
		noRider = false;
		podeAtacar = true;
		ganhouJogo = false;

		paraDireita = true;
		paraEsquerda = false;
	}

	public boolean isNaEscada() {
		return naEscada;
	}

	public void setNoAr(boolean noAr) {
		this.noAr = noAr;
	}

	public boolean getNoAr(){
		return noAr;
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

	private boolean testarTecla(int tecla) {
		if(Gdx.input.isKeyPressed(tecla)){
            return true;
        }else return false;
	}

	public void setVelocidadeX(float velX){
		this.velX = velX;
	}
	public void setVelocidadeY(float velY){
		this.velY = velY;
	}


	@Override
	public void mover() {
		moverParaDireita();
		moverParaEsquerda();
		pular();
		subirEscada();
		descerEscada();
		subirParede();
		descerParede();
		dash();
	}

	@Override
	protected void moverParaDireita(){
        if(testarTecla(Input.Keys.RIGHT)){
            paraDireita = true;
            paraEsquerda = false;
            apertouRight = true;
			isMegaMan = false;

            velX = 5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			animar(posX, 11, 34, 0, 0, 34, 46, isMegaMan);
        }else{
            if(apertouRight){
                setRegion(0, 0, 34, 46, isMegaMan); 
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
			isMegaMan = true;

            velX = -5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
			animar(posX, 11, 34, 0, 0, 34, 46, isMegaMan);
        }else{
            if(apertouLeft){
                setRegion(0, 0, 34, 46, isMegaMan); 
                apertouLeft = false;
            }
        }
	}

	@Override
	protected void pular() {
		if(testarTecla(Input.Keys.SPACE) && (!noAr)){
            noAr = true;
            velY = 5;
            posY = posY + velY;            
            setPosicao(posX, posY);

        }
		sofrerGravidade(posY, 7, 34, 374, 0, 34, 46, 578, 0, 34, 46, isMegaMan);
	}

	private void subirEscada() {
		if(testarTecla(Input.Keys.UP)){
            apertouUp = true;
            velY = 5;
            posY = posY + velY;
            setPosicao(posX, posY);

            animar(posY, 4, 34, 612, 0, 34, 46, true); 
        }else{
            if(apertouUp){
                setRegion(816, 0, 34, 46); 
                apertouUp = false;
            }
        }
	}

	private void descerEscada() {
		if(testarTecla(Input.Keys.DOWN)){
            apertouDown = true;
            velY = -5;
            posY = posY + velY;
            setPosicao(posX, posY);

            animar(posY, 4, 34, 612, 0, 34, 46, true); 
        }else{
            if(apertouDown){
                setRegion(816, 0, 34, 46); 
                apertouDown = false;
            }
        }
	}

	public boolean getIsMegaMan(){
		return this.isMegaMan;
	}

	private void subirParede() {

	}

	private void descerParede() {

	}

	private void dash() {

	}

	@Override
	public void tomarDano(int dano) {

	}

}
