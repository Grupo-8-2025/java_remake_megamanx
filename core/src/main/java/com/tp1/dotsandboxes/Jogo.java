package com.tp1.dotsandboxes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

/*
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */

public class Jogo extends Game {

    private Texture texturaMegaMan;
    private Texture texturaPenguin;
    private Texture texturaThrower;
    private Texture texturaJamminger;
    private Texture texturaFundo;

    private ShapeRenderer shapeRenderer;

    private MegaMan megaMan;
    private Pinguim penguin;
    private PersonagemNaoMovel thrower;
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
        TipoAtaque.carregarTodasTexturas();
        texturaMegaMan = new Texture("imagens/MegaMan/megaMan.png");
        texturaPenguin = new Texture("imagens/ChilPenguin/inimigos/Penguin/penguin.png");
        texturaThrower = new Texture("imagens/ChilPenguin/inimigos/now.png");
        texturaJamminger = new Texture("imagens/ChilPenguin/inimigos/jaminger.png");
        texturaFundo = new Texture("imagens/backgroundNeve.jpg");
    }

    private void criaPersonagens(){
        megaMan = new MegaMan(texturaMegaMan, 500, 1400);
        penguin = new Pinguim(texturaPenguin, 800, 1400);
        
        Ataque ataqueThrower = new Ataque(new TextureRegion(TipoAtaque.BOLA_NEVE.getTextura(), 
		TipoAtaque.BOLA_NEVE.getCordX1(), TipoAtaque.BOLA_NEVE.getCordY1(),
		TipoAtaque.BOLA_NEVE.getLargura1(), TipoAtaque.BOLA_NEVE.getAltura1()), 
		0, 0, new Vector2(0.05f, 0.5f), TipoAtaque.BOLA_NEVE);

        thrower = new PersonagemNaoMovel(texturaThrower, new TextureRegion(texturaThrower, 0, 0, 35, 58),
            100, 400, new Vector2(0.5f, 2.5f), 6, 3, ataqueThrower);

        Ataque ataqueJamminger = new Ataque(new TextureRegion(TipoAtaque.DISCO.getTextura(), 
		TipoAtaque.DISCO.getCordX1(), TipoAtaque.DISCO.getCordY1(),
		TipoAtaque.DISCO.getLargura1(), TipoAtaque.DISCO.getAltura1()), 
		0, 0, new Vector2(0.05f, 0.5f), TipoAtaque.DISCO);
        
        jamminger = new PersonagemNaoMovel(texturaJamminger, new TextureRegion(texturaJamminger, 0, 0, 28, 35),
            100, 400, new Vector2(0.3f, 3.0f), 3, 2, ataqueJamminger);
    }

    private void criaMapa(){
        mapa = new Mapa("maps/mapaPenguim.tmx", 800, 600);
    }

    @Override
    public void render() {

        cameraFoco.set(megaMan.getPosX() + megaMan.getCorpo().getBoundingRectangle().width, 
        megaMan.getPosY() + (megaMan.getCorpo().getBoundingRectangle().height/2));
        camera.position.set(cameraFoco, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined); 
        
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mortePersonagens();
        if(!megaMan.isMorreu()){
            colisoes();
            ataquesPersonagens();
            moverObjetos();
        }

        desenhaItens();
        super.render();
    }

    private boolean colisaoCorpos(Rectangle corpo1, Rectangle corpo2){
        if (corpo1.overlaps(corpo2)) {
            return true;
        }else return false;
    }

    private void colisaoMegaManMapa(){
        Rectangle corpoMegaman = megaMan.getCorpo().getBoundingRectangle();
        for (Rectangle plataforma : mapa.getChaos()) {
            if(colisaoCorpos(corpoMegaman, plataforma)){
                float posBaseMegaMan = corpoMegaman.y + corpoMegaman.height;
                if(posBaseMegaMan >= plataforma.y && corpoMegaman.y <= plataforma.y){
                    megaMan.setNoAr(false);
                    megaMan.setNaPlataforma(true);
                    megaMan.setPosicao(megaMan.getPosX(), plataforma.y + corpoMegaman.height/2);
                }
                break;
            }
        }
    }

    private void colisaoPenguinMapa(){
        Rectangle corpoPenguin = penguin.getCorpo().getBoundingRectangle();
        for (Rectangle plataforma : mapa.getChaos()) {
            if(colisaoCorpos(corpoPenguin, plataforma)){
                float posBasePenguin = corpoPenguin.y + corpoPenguin.height;
                if(posBasePenguin >= plataforma.y && corpoPenguin.y <= plataforma.y){
                    penguin.setNoAr(false);
                    penguin.setNaPlataforma(true);
                    penguin.setPosicao(penguin.getPosX(), plataforma.y + corpoPenguin.height);
                }
                break;
            }
        }
    }

    private void colisaoAtaquesMapa(){
        for(int i=0; i<megaMan.getAtaques().size(); i++){
            Rectangle corpoAtaque = megaMan.getAtaques().get(i).getCorpo().getBoundingRectangle();
            for (Rectangle plataforma : mapa.getChaos()) {
                if(colisaoCorpos(corpoAtaque, plataforma)){
                    megaMan.getAtaques().get(i).setColidiu(true);
                    megaMan.getAtaques().get(i).setPodeDisparar(false);;
                    megaMan.getAtaques().get(i).setPosicao(-100, -100);
                    break;
                }
            }
        }
    }
    
    private void colisaoAtaquesPersonagem(){
        Rectangle corpoPenguin = penguin.getCorpo().getBoundingRectangle();
        for(int i=0; i<megaMan.getAtaques().size(); i++){
            Rectangle corpoAtaque = megaMan.getAtaques().get(i).getCorpo().getBoundingRectangle();
            if(colisaoCorpos(corpoAtaque, corpoPenguin)){
                megaMan.getAtaques().get(i).setColidiu(true);
                megaMan.getAtaques().get(i).setPodeDisparar(false);;
                megaMan.getAtaques().get(i).setPosicao(-100, -100);
                penguin.tomarDano(megaMan.getAtaques().get(i).getTipo().getDano());
                break;
            }
        }

        Rectangle corpoMegaman = megaMan.getCorpo().getBoundingRectangle();
        for(int i=0; i<penguin.getAtaques().size(); i++){
            Rectangle corpoAtaque = penguin.getAtaques().get(i).getCorpo().getBoundingRectangle();
            if(colisaoCorpos(corpoAtaque, corpoMegaman)){
                penguin.getAtaques().get(i).setColidiu(true);
                penguin.getAtaques().get(i).setPodeDisparar(false);;
                penguin.getAtaques().get(i).setPosicao(-100, -100);
                megaMan.tomarDano(penguin.getAtaques().get(i).getTipo().getDano());
                break;
            }
        }
    }

    private void colisaoMegaManPersonagens(){
        Rectangle corpoPenguin = penguin.getCorpo().getBoundingRectangle();
        Rectangle corpoMegaman = megaMan.getCorpo().getBoundingRectangle();
        if(colisaoCorpos(corpoMegaman, corpoPenguin)){
            megaMan.setColidiuInimigo(true);
            megaMan.tomarDanoPorContato(penguin.getDano());
        }

    }

    private void colisoes() {    
        colisaoMegaManMapa();
        colisaoPenguinMapa();
        colisaoAtaquesMapa();
        colisaoAtaquesPersonagem();
        colisaoMegaManPersonagens();
    }

    private void moverObjetos(){
        megaMan.mover();
        penguin.atitudes();
        thrower.animar(7, 35, 0, 0, 35, 58);
        jamminger.animar(7, 39, 0, 0, 39, 72);
    }

    private void ataquesPersonagens(){
        megaMan.atacar();
        penguin.atacar();
        
        for(int i=0; i<megaMan.getAtaques().size(); i++){
            int multiplicador = 0;
            if(megaMan.isParaDireita()) multiplicador = 1;
            else multiplicador = -1;
            megaMan.getAtaques().get(i).disparar(multiplicador);
        }

        for(int i=0; i<penguin.getAtaques().size(); i++){
            int multiplicador = 0;
            if(penguin.isParaDireita()) multiplicador = 1;
            else multiplicador = -1;
            penguin.getAtaques().get(i).disparar(multiplicador);
        }
    }

    private void mortePersonagens(){
        //megaMan.morrer(2398, 0, 35, 50, megaMan.getPosX(), megaMan.getPosY());
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
        thrower.draw(batch);
        jamminger.draw(batch);

        for(int i=0; i<megaMan.getAtaques().size(); i++){
            megaMan.getAtaques().get(i).draw(batch);
        }

        for(int i=0; i<penguin.getAtaques().size(); i++){
            penguin.getAtaques().get(i).draw(batch);
        }

        batch.end();
        
    
        // Desenha colisores em vermelho
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle r : mapa.getChaos()) {
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
        }
        shapeRenderer.end();
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

        TipoAtaque.disposeTodasTexturas();
        texturaMegaMan.dispose();
        texturaPenguin.dispose();
        texturaThrower.dispose();
        texturaJamminger.dispose();

        super.dispose();
    }

}