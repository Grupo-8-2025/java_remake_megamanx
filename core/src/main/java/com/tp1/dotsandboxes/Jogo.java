package com.tp1.dotsandboxes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/*
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */

public class Jogo extends Game {

    private ArrayList<Texture> texturasMegaMan;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MegaMan megaMan;
    private Mapa mapa;

    @Override
    public void create() {
        criaObjetosJogo();
    }

    private void criaObjetosJogo(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        carregaTexturas();
        criaPersonagens();
        criaMapa();
    }

    private void carregaTexturas(){
        texturasMegaMan = new ArrayList<Texture>() {{
            add(new Texture("imagens/MegaMan/megaManDireita.png"));
            add(new Texture("imagens/MegaMan/megaManEsquerda.png"));
            add(new Texture("imagens/MegaMan/megaManPulandoDireita.png"));
            add(new Texture("imagens/MegaMan/MegaManPulandoEsquerda.png"));
            add(new Texture("imagens/MegaMan/MegaManSubindo.png"));
            add(new Texture("imagens/MegaMan/MegaManNaEscada.png"));
            add(new Texture("imagens/MegaMan/MegaManSubiuEscada.png"));
        }};

        // Textura de fundo agora é gerenciada pela classe Mapa
        // texturaFundo = new Texture("assets/imagens/ChilPenguin/mapa.png");
    }

    private void criaPersonagens(){
        megaMan = new MegaMan(texturasMegaMan.get(0), 100, 100);
    }

    private void criaMapa(){
        mapa = new Mapa("imagens/ChilPenguin/mapa.png", 800, 600);
    }

    @Override
    public void render() {
        // Usar a câmera do mapa
        OrthographicCamera mapaCamera = mapa.getCamera();
        mapaCamera.setToOrtho(false, 800, 600);
        mapaCamera.update();
        batch.setProjectionMatrix(mapaCamera.combined); 
        
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        desenhaItens();
        moverItens();

        super.render();
    }

    private void desenhaItens(){
        batch.begin();
        // Desenhar o fundo do mapa
        batch.draw(mapa.getBackgroundImage(), 0, -250, 10327, 1649);
        // Desenhar o personagem
        megaMan.getCorpo().draw(batch);
        batch.end();
    }

    private void moverItens(){
        megaMan.moverParaDireita(texturasMegaMan.get(0));
        megaMan.moverParaEsquerda(texturasMegaMan.get(1));
        megaMan.descerEscada(texturasMegaMan.get(4), texturasMegaMan.get(5));
        megaMan.subirEscada(texturasMegaMan.get(4), texturasMegaMan.get(5));
        megaMan.pular(texturasMegaMan.get(2), texturasMegaMan.get(3));
        //megaMan.sofrerGravidade(texturasMegaMan.get(2));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        if (mapa != null) {
            mapa.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
        // Liberar texturas do MegaMan
        for (Texture texture : texturasMegaMan) {
            texture.dispose();
        }
        // texturaFundo agora é gerenciada pela classe Mapa
        super.dispose();
    }

}