package com.tp1.dotsandboxes;

import com.tp1.dotsandboxes.Iterators.InimigoIterator;
import com.tp1.dotsandboxes.Iterators.PersonagemIterator;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GerenciadorColisoes {

    private boolean colisaoCorpos(Rectangle corpo1, Rectangle corpo2){
        if (corpo1.overlaps(corpo2)) {
            return true;
        }else return false;
    }
    
    public void colisaoPersonagensPlataformas(Array<Rectangle> plataformas, PersonagemIterator personagens){
        for(Rectangle plataforma : plataformas){
            personagens.reset();
            while (personagens.hasNext()) {
                Personagem personagem = personagens.next();
                Rectangle corpoPersonagem = personagem.getCorpo().getBoundingRectangle();
                if (colisaoCorpos(plataforma, corpoPersonagem)) {
                    personagem.setNaPlataforma(true);

                    float posBaseMegaMan = corpoPersonagem.y;
                    float posTopoMegaMan = corpoPersonagem.y + corpoPersonagem.height;
                    float posTopoPlataforma = plataforma.y + plataforma.height;
                    if(posBaseMegaMan <= posTopoPlataforma && posTopoMegaMan >= posTopoPlataforma){
                        personagem.setPosicao(personagem.getPosX(),  posTopoPlataforma);
                    }
                }
            }
            personagens.reset();
        } 
    }
    
    public void colisaoAtaquesPlataformas(Array<Rectangle> plataformas, PersonagemIterator personagens){
        for(Rectangle plataforma : plataformas){
            personagens.reset();
            while (personagens.hasNext()) {
                Personagem personagem = personagens.next();
                for(int i=0; i<personagem.getAtaquesAtivos().size(); i++){
                    Ataque ataque = personagem.getAtaquesAtivos().get(i);
                    Rectangle corpoAtaque = ataque.getCorpo().getBoundingRectangle();
                    if (colisaoCorpos(plataforma, corpoAtaque)) {
                        ataque.setColidiu(true);
                        ataque.setPodeDisparar(false);
                        ataque.setPosicao(-100, -100);
                        personagem.getAtaquesAtivos().remove(personagem.getAtaquesAtivos().get(i)); 
                    }
                }
            }
            personagens.reset();
        } 
    }

    public void colisaoAtaquesPersonagens(PersonagemIterator personagens) {
        personagens.reset();
        while (personagens.hasNext()) {
            Personagem atacante = personagens.next();
            ArrayList<Ataque> ataques = atacante.getAtaquesAtivos();

            for (int i = ataques.size() - 1; i >= 0; i--) {
                Ataque ataque = ataques.get(i);
                Rectangle corpoAtaque = ataque.getCorpo().getBoundingRectangle();

                personagens.reset();
                while (personagens.hasNext()) {
                    Personagem alvo = personagens.next();
                    if (alvo == atacante) continue;

                    Rectangle corpoAlvo = alvo.getCorpo().getBoundingRectangle();

                    if (colisaoCorpos(corpoAlvo, corpoAtaque)) {
                        ataque.setColidiu(true);
                        ataque.setPodeDisparar(false);
                        ataque.setPosicao(-100, -100);
                        alvo.tomarDano(ataque.getTipo().getDano());
                        ataques.remove(i);
                        break; 
                    }
                }
            }
        }
        personagens.reset();
    }


    public void colisaoMegaManInimigos(MegaMan megaman, InimigoIterator inimigos){
        Rectangle corpoMegaman = megaman.getCorpo().getBoundingRectangle();
        inimigos.reset();
        while (inimigos.hasNext()) {
            Inimigo inimigo = inimigos.next();
            Rectangle corpoInimigo = inimigo.getRect();
            if (colisaoCorpos(corpoMegaman, corpoInimigo)) {
                megaman.setColidiuInimigo(true);
                megaman.tomarDanoPorContato(inimigo.getDano());
            }
        }
        inimigos.reset();
    }
    

}

