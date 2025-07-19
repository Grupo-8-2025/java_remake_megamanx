package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Rider extends PersonagemMovel {

    public Rider(Texture textura, TextureRegion region, float posX, float posY, Vector2 escala, int vida,
			float velX, float velY) {
		super(textura, region, posX, posY, escala, vida, velX, velY);
		
	}

}
