package com.tp1.dotsandboxes;

import com.badlogic.gdx.math.Rectangle;

public class GerenciadorColisoes {
    
    public boolean colisaoPersonagemPlataformas(Rectangle corpoPersonagem, Rectangle plataforma){
        if (corpoPersonagem.overlaps(plataforma)) {
            return true;
        }else return false;
    }
    
    public boolean colisaoAtaquePlataformas(Rectangle corpoAtaque, Rectangle plataforma){
        if (corpoAtaque.overlaps(plataforma)) {
            return true;
        }else return false;
    }

    public boolean colisaoMegaManInimigos(MegaMan megaman, InimigoIterator inimigos){
        inimigos.reset();
        while (inimigos.hasNext()) {
            Inimigo inimigo = inimigos.next();
            if ((inimigo.calculaDistanciaMegaMan(megaman) == 0) || inimigo.getCorpo().getBoundingRectangle().contains(megaman.getCorpo().getBoundingRectangle())) {
                inimigos.reset();
                return true;
            }
        }
        inimigos.reset();
        return false;
    }
    

}

