package com.tp1.dotsandboxes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Tiro extends Entidade {

    private static final float VELOCIDADE_TIRO = 500f; 
    private Vector2 posicao;

    public Tiro(float posX, float posY) {
        //super(new Texture("imagens/tiro.png"), posX, posY, new TextureRegion(new Texture("imagens/tiro.png"), 0, 0, 10, 5), new Vector2(1.0f, 1.0f));
        this.posicao = new Vector2(posX, posY);
    }

    /* No momento, a funcao é desnecessária, pois o tiro é inicializado 
     * em seu construtor com a posição correta.
     * 
     * public void atirar(MegaMan megaMan, float deltaTime) {  
     *     posicao.y = megaMan.getPosY() + megaMan.getCorpo().getHeight() / 2;
     *     posicao.x = megaMan.getPosX() + megaMan.getCorpo().getWidth();
     * }
     * 
     */

    public void atualizarTiroDireta(float deltaTime) {
        posicao.x += VELOCIDADE_TIRO * deltaTime;
    }

    public void atualizarTiroEsquerda(float deltaTime) {
        posicao.x -= VELOCIDADE_TIRO * deltaTime;
    }

    public void morrer() {
        if (Gdx.graphics.getWidth() > posicao.x || 0 > posicao.y + corpo.getWidth()) {
            System.out.println("Deletando tiro");
        }
    }

    public Vector2 getPosicao() {
        return posicao;
    }


}
