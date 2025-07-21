package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PersonagemNaoMovel extends Personagem {

    public PersonagemNaoMovel(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, 
		int vida, int dano, Ataque ataque) {
		super(textura, region, posX, posY, escala, vida, dano, ataque);
	}

}