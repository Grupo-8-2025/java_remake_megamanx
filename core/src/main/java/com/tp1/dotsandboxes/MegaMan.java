package com.tp1.dotsandboxes;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MegaMan extends Personagem{
    
    private boolean ganhouJogo;
    private boolean paraEsquerda;
    private boolean paraDireita;
    private boolean pegouMartelo;
    private boolean apertouX;
    private boolean naEscada;
    private boolean noAr;

    private boolean apertouRight;
    private boolean apertouLeft;
    private boolean apertouUp;
    private boolean apertouDown;

    private int vidas;
    private int moedas;
    //private Sound somAndar;
    //private Sound somPular;
    //private Sound somNoTubo;

    MegaMan(Texture textura, float posX, float posY){
        super(textura, posX, posY, new TextureRegion(textura, 0, 0, 28, 35), new Vector2(0.3f, 3.0f), 0, 0);
        ganhouJogo = false;
        paraEsquerda = false;
        paraDireita = true;
        pegouMartelo = false;
        apertouX = false;
        naEscada = false;
        noAr = false;
        apertouRight = false;
        apertouLeft = false;
        vidas = 3;
        moedas = 0;

        //somAndar = Gdx.audio.newSound(Gdx.files.internal("somAndarMario.wav"));
        //setVolume
        //somPular = Gdx.audio.newSound(Gdx.files.internal("somPularMario.wav"));
        //setVolume
        //somNoTubo = Gdx.audio.newSound(Gdx.files.internal("somTubo.wav"));
        //setVolume
    }

    public boolean isGanhouJogo() {
        return ganhouJogo;
    }

    public void setGanhouJogo(boolean ganhouJogo) {
        this.ganhouJogo = ganhouJogo;
    }

    public boolean isParaEsquerda() {
        return paraEsquerda;
    }

    public void setParaEsquerda(boolean paraEsquerda) {
        this.paraEsquerda = paraEsquerda;
    }

    public boolean isParaDireita() {
        return paraDireita;
    }

    public void setParaDireita(boolean paraDireita) {
        this.paraDireita = paraDireita;
    }

    public boolean isPegouMartelo() {
        return pegouMartelo;
    }

    public void setPegouMartelo(boolean pegouMartelo) {
        this.pegouMartelo = pegouMartelo;
    }

    public boolean isApertouX() {
        return apertouX;
    }

    public void setApertouX(boolean apertouX) {
        this.apertouX = apertouX;
    }

    public boolean isNaEscada() {
        return naEscada;
    }

    public void setNaEscada(boolean naEscada) {
        this.naEscada = naEscada;
    }

    public boolean isNoAr() {
        return noAr;
    }

    public void setNoAr(boolean noAr) {
        this.noAr = noAr;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getMoedas() {
        return moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public boolean testarTecla(int tecla){
        if(Gdx.input.isKeyPressed(tecla)){
            return true;
        }else return false;
    }

    public void moverParaDireita(Texture textura){
        if(testarTecla(Input.Keys.RIGHT) && (podeAndar)){
            paraDireita = true;
            paraEsquerda = false;
            apertouRight = true;
            velX = 5;
            posX = posX + velX;
            setPosicao(posX, posY);
            
            int x = (int) posX / 50 % 11;
            x = x * 34;
            this.textura = textura;
            setTexturaCorpo(this.textura, new TextureRegion(this.textura, x, 0, 34, 34), new Vector2(0.3f, 3.0f));
            
        }else{
            if(apertouRight){
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, 0, 0, 34, 34), new Vector2(0.3f, 3.0f));
                apertouRight = false;
            }
        }
    }

    public void moverParaEsquerda(Texture textura){
        if(testarTecla(Input.Keys.LEFT) && (podeAndar)){
            paraDireita = false;
            paraEsquerda = true;
            apertouLeft = true;
            velX = -5;
            posX = posX + velX;
            setPosicao(posX, posY);
  
            int x = (int) posX / 50 % 11;
            x = x * 34;
            this.textura = textura;
            setTexturaCorpo(this.textura, new TextureRegion(this.textura, 374-x, 0, 34, 34), new Vector2(0.3f, 3.0f));
            
        }else{
            if(apertouLeft){
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, 374-34, 0, 34, 34), new Vector2(0.3f, 3.0f));
                apertouLeft = false;
            }
        }
    }

    public void subirEscada(Texture texturaSubindo, Texture texturaNaEscada){
        if(testarTecla(Input.Keys.UP) && (podeAndar)){
            apertouUp = true;
            velY = 5;
            posY = posY + velY;
            setPosicao(posX, posY);

            int x = (int) posY / 50 % 4;
            x = x * 20;
            this.textura = texturaSubindo;
            setTexturaCorpo(this.textura, new TextureRegion(this.textura, x, 0, 20, 46), new Vector2(0.2f, 4.0f));

        }else{
            if(apertouUp){
                this.textura = texturaNaEscada;
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, 0, 0, 21, 36), new Vector2(0.2f, 3.0f));
                apertouUp = false;
            }
        }
    }

    public void descerEscada(Texture texturaSubindo, Texture texturaNaEscada){
        if(testarTecla(Input.Keys.DOWN) && (podeAndar)){
            apertouDown = true;
            velY = -5;
            posY = posY + velY;
            setPosicao(posX, posY);

            int x = (int) posY / 50 % 4;
            x = x * 20;
            this.textura = texturaSubindo;
            setTexturaCorpo(this.textura, new TextureRegion(this.textura, 80-x, 0, 20, 46), new Vector2(0.2f, 4.0f));
            
        }else{
            if(apertouDown){
                this.textura = texturaNaEscada;
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, 0, 0, 21, 36), new Vector2(0.2f, 3.0f));
                apertouDown = false;
            }
        }
    }

    public void pular(Texture texturaPulandoDireita, Texture texturaPulandoEsquerda){
        if(testarTecla(Input.Keys.SPACE) && (!noAr)){
            noAr = true;
            velY = 5;
            posY = posY + velY;            
            setPosicao(posX, posY);
        }
        if(paraDireita){
            sofrerGravidade(texturaPulandoDireita);
        }else if(paraEsquerda){
            sofrerGravidade(texturaPulandoEsquerda);
        }
        
    }

    public void sofrerGravidade(Texture textura){
        if(noAr){
            velY = velY - (gravidade * deltaTime);
            setPosicao(posX, posY+velY);

            this.textura = textura;
            int x = (int) posY / 100 % 7;
            x = x * 30;

            if(paraDireita){
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, x, 0, 30, 46), new Vector2(0.3f, 4.0f));
            }else if(paraEsquerda){
                setTexturaCorpo(this.textura, new TextureRegion(this.textura, 210-x, 0, 30, 46), new Vector2(0.3f, 4.0f));
            }

            if (posY <= 200) {
                posY = 200;
                velY = 0;
                noAr = false;
                if(paraDireita){
                    setTexturaCorpo(this.textura, new TextureRegion(this.textura, 210-30, 0, 30, 46), new Vector2(0.3f, 4.0f));
                }else if(paraEsquerda){
                    setTexturaCorpo(this.textura, new TextureRegion(this.textura, 0, 0, 30, 46), new Vector2(0.3f, 4.0f));
                }
            }
        }
    }
    
}