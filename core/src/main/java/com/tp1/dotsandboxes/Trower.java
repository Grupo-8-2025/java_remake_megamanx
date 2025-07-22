package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Trower extends Personagem implements Inimigo{

    public Trower(Texture textura, float posX, float posY, Vector2 escala, Ataque ataque) {
		super(textura, new TextureRegion(textura, 0, 0, 35, 58), posX, posY, new Vector2(0.5f, 2.5f), 
        6, 0.02f, ataque);
	}


    public Rectangle getRect(){
		return corpo.getBoundingRectangle();
	}

    public float getDano() {
		return dano;
	}


	@Override
	public void mover(){
		animar(7, 35, 0, 0, 35, 58);
	}

	@Override
	public void atacar(){
		float posXataque = corpo.getX() + corpo.getBoundingRectangle().width;
		float posYataque = corpo.getY();

		Ataque novoAtaque = new Ataque(
			new TextureRegion(ataque.getTipo().getTextura(),
			ataque.getTipo().getCordX1(), ataque.getTipo().getCordY1(),
			ataque.getTipo().getLargura1(), ataque.getTipo().getAltura1()),
			posXataque, posYataque, new Vector2(0.3f, 1.2f), 
			ataque.getTipo(), ataque.getTipo().getVelocidade()
		);

		novoAtaque.setColidiu(false);
		novoAtaque.setPodeDisparar(true);
		ataquesAtivos.add(novoAtaque);
	}
	
	@Override
	public void morrer(){
		if(vida <= 0){
			morreu = true;
			iterarDeltaTime();
			setRegion(27, 0, 27, 75);

			if (deltaTime >= 5.0f) {
				setPosicao(-500, -500);
			}
		}
	}

}