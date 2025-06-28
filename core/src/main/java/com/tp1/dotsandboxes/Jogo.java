package com.tp1.dotsandboxes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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

    private Texture texturaFundo;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MegaMan megaMan;

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
    }

    private void carregaTexturas(){
        texturasMegaMan = new ArrayList<Texture>() {{
            add(new Texture("assets/imagens/MegaMan/megaManDireita.png"));
            add(new Texture("assets/imagens/MegaMan/megaManEsquerda.png"));
            add(new Texture("assets/imagens/MegaMan/megaManPulandoDireita.png"));
            add(new Texture("assets/imagens/MegaMan/megaManPulandoEsquerda.png"));
            add(new Texture("assets/imagens/MegaMan/megaManSubindo.png"));
            add(new Texture("assets/imagens/MegaMan/megaManNaEscada.png"));
            add(new Texture("assets/imagens/MegaMan/megaManSubiuEscada.png"));
        }};

        texturaFundo = new Texture("assets/imagens/ChilPenguin/mapa.png");
        System.err.println(texturaFundo.getWidth() + " x " + texturaFundo.getHeight());

    }

    private void criaPersonagens(){
        megaMan = new MegaMan(texturasMegaMan.get(0), 100, 100);
    }

    @Override
    public void render() {
        camera.update(); // Atualiza a câmera
        batch.setProjectionMatrix(camera.combined); 
        
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        desenhaItens();
        moverItens();

        super.render();
    }

    private void desenhaItens(){
        batch.begin();
        megaMan.getCorpo().draw(batch);
        batch.draw(texturaFundo, 800, 500);
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
        super.dispose();
    }

}