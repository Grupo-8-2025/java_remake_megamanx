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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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

    private Texture texturaMegaMan;
    private Texture texturaPenguin;
    private Texture texturaNowthrower;
    private Texture texturaJamminger;
    private Texture texturaFundo;

    private ShapeRenderer shapeRenderer;

    private MegaMan megaMan;
    private Pinguim penguin;
    private PersonagemNaoMovel nowthrower;
    private PersonagemNaoMovel jamminger;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector2 cameraFoco;
    private Viewport viewport;

    private Mapa mapa;

    @Override
    public void create() {
        criaObjetosJogo();
    }

    private void criaObjetosJogo(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        cameraFoco = new Vector2();
        camera.setToOrtho(false, 800, 600);
        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();

        carregaTexturas();
        criaPersonagens();
        criaMapa();
    }

    private void carregaTexturas(){
        texturaMegaMan = new Texture("imagens/MegaMan/megaManNovo.png");
        texturaPenguin = new Texture("imagens/ChilPenguin/inimigos/Penguin/penguinNovo.png");
        texturaNowthrower = new Texture("imagens/ChilPenguin/inimigos/Nowthrower/now.png");
        texturaJamminger = new Texture("imagens/ChilPenguin/inimigos/Jamminger/jammingerAtacando.png");
        texturaFundo = new Texture("imagens/backgroundNeve.jpg");
    }

    private void criaPersonagens(){
        megaMan = new MegaMan(texturaMegaMan, 500, 1400);
        penguin = new Pinguim(texturaPenguin, 600, 600-44);
        nowthrower = new PersonagemNaoMovel(texturaNowthrower, new TextureRegion(texturaNowthrower, 0, 0, 35, 58),
            100, 400, new Vector2(0.5f, 2.5f), 6);
        jamminger = new PersonagemNaoMovel(texturaJamminger, new TextureRegion(texturaJamminger, 0, 0, 28, 35),
            100, 400, new Vector2(0.3f, 3.0f), 2);
    }

    private void criaMapa(){
        mapa = new Mapa("maps/mapaPenguim.tmx", 800, 600);
    }

    @Override
    public void render() {
        
        cameraFoco.set(megaMan.getPosX(), megaMan.getPosY());
        camera.position.set(cameraFoco, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined); 
        
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ataquesPersonagens();
        moverItens();
        aplicarFisicaMegaMan();
        desenhaItens();

        super.render();
    }

    private void desenhaItens(){
        batch.begin();

        batch.draw(texturaFundo,
            camera.position.x - camera.viewportWidth / 2,
            camera.position.y - camera.viewportHeight / 2,
            camera.viewportWidth,
            camera.viewportHeight
        );
        batch.end();

        mapa.render(camera);
        
        batch.begin();
        megaMan.draw(batch);
        penguin.draw(batch);
        nowthrower.draw(batch);
        jamminger.draw(batch);
        batch.end();
        
    
        // Desenha colisores em vermelho
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle r : mapa.getChoes()) {
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
        }
        shapeRenderer.end();
    }

    private void aplicarFisicaMegaMan() {
        Rectangle pe = new Rectangle(megaMan.getCorpo().getBoundingRectangle().x, megaMan.getCorpo().getBoundingRectangle().y, megaMan.getCorpo().getBoundingRectangle().width - 3, 1);
        boolean emPlataforma = false;
    
        for (Rectangle chao : mapa.getChoes()) {
            if (pe.overlaps(chao)) {
                emPlataforma = true;
                megaMan.setPosY(chao.y + megaMan.getCorpo().getBoundingRectangle().height + chao.height); // posiciona exatamente no topo
                megaMan.setVelocidadeY(0);
                megaMan.setNoAr(false);
                break;
            }
        }
        
        if (!emPlataforma) {
            megaMan.setNoAr(true);
        }

        if(megaMan.getNoAr()){
            megaMan.sofrerGravidade(
                megaMan.getPosY(),
                7,
                34, 374,
                0, 34, 46,
                578, 0, 34, 46,
                megaMan.getIsMegaMan()
            );
        }
    }

    private void moverItens(){
        megaMan.mover();
        penguin.mover();
        nowthrower.animar(7, 35, 0, 0, 35, 58);
        jamminger.animar(7, 39, 0, 0, 39, 72);
    }

    private void ataquesPersonagens(){
        megaMan.atacar();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        if(mapa != null) {
            mapa.dispose();
        }
        if(batch != null) {
            batch.dispose();
        }

        texturaMegaMan.dispose();
        texturaPenguin.dispose();
        texturaNowthrower.dispose();
        texturaJamminger.dispose();

        super.dispose();
    }

}