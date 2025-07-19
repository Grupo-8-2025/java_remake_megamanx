package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Pinguim extends Chefao {

	private EntidadeAnimada protecao;

	public Pinguim(Texture textura, float posX, float posY) {
		super(textura, new TextureRegion(textura, 602, 0, 43, 60), posX, posY, 
		new Vector2(0.1f, 3.0f), 32, 0, 0);
				
		protecao = new EntidadeAnimada(new Texture("imagens/ChilPenguin/inimigos/Penguin/protecao.png"), 
		new TextureRegion(textura, 0, 0, 14, 32), posX-30, posY, new Vector2(0.1f, 3.0f));	
	}

    private void delimitarMovimento(){
        if(posX <= 0 + 43){
            paraEsquerda = false;
            paraDireita = true;
        }else if(posX >= 800){
            paraDireita = false;
            paraEsquerda= true;
        }
    }

	@Override
	public void mover() {
		delimitarMovimento();
		sofrerGravidade(posY, 1, 0, 602, 0, 43, 
		60, 0, 0, 43, 60, false);
        
		iterarDeltaTime();
		atualizarMovimentoAleatorio();

        if(!noAr){
            if(determinaAcao == 0){
				duracaoAcao = 5.0f;
                moverParaDireita();
            }else if(determinaAcao == 1){
				duracaoAcao = 5.0f;
                moverParaEsquerda();
            }else if(determinaAcao == 2){
				duracaoAcao = 5.0f;
                parado();
            }
        }
	}

	@Override
	protected void pular() {

	}

	@Override
	protected void parado() {
		velX = 0;            
        animar(6, 43, 0, 0, 43, 60);
	}

	@Override
	public void tomarDano(int dano) {

	}

}
