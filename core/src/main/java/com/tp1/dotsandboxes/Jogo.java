package com.tp1.dotsandboxes;

import com.tp1.dotsandboxes.Iterators.InimigoIterator;
import com.tp1.dotsandboxes.Iterators.PersonagemIterator;

import java.util.Random;
import java.util.ArrayList;

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

public class Jogo extends Game {

    private Texture texturaMegaMan;
    private Texture texturaPenguin;
    private Texture texturaTrower;
    private Texture texturaJaminger;
    private Texture texturaFundo;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector2 cameraFoco;
    private Viewport viewport;

    ArrayList<Vector2> posicoesValidas;
    private Random random;

    private Mapa mapa;

    private GerenciadorColisoes gerenciadorColisoes;
    private InimigoIterator inimigos;
    private PersonagemIterator personagens;

    private MegaMan megaMan;
    private Pinguim penguin;

    private ShapeRenderer shapeRenderer;

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

        random = new Random();
        posicoesValidas = new ArrayList<>();

        gerenciadorColisoes = new GerenciadorColisoes();
        inimigos = new InimigoIterator();
        personagens = new PersonagemIterator();

        carregaTexturas();
        criaMapa();
        criaPersonagens();
    }

    private void criaMapa(){
        mapa = new Mapa("maps/mapaPenguim.tmx", 800, 600);
    }

    private void carregaTexturas(){
        TipoAtaque.carregarTodasTexturas();
        texturaMegaMan = new Texture("imagens/MegaMan/megaMan.png");
        texturaPenguin = new Texture("imagens/ChilPenguin/inimigos/Penguin/penguin.png");
        texturaTrower = new Texture("imagens/ChilPenguin/inimigos/now.png");
        texturaJaminger = new Texture("imagens/ChilPenguin/inimigos/jaminger.png");
        texturaFundo = new Texture("imagens/backgroundNeve.jpg");
    }

    private void criaPersonagens(){
        criarInimigos();
        megaMan = new MegaMan(texturaMegaMan, 8295, 2005);

        personagens.add(megaMan);
        personagens.add(penguin);
    }

    private void criarInimigos(){
        penguin = new Pinguim(texturaPenguin, 8635, 2300);
        inimigos.add(penguin);
        
        Ataque ataqueTrower = new Ataque(new TextureRegion(TipoAtaque.BOLA_NEVE.getTextura(), 
		TipoAtaque.BOLA_NEVE.getCordX1(), TipoAtaque.BOLA_NEVE.getCordY1(),
		TipoAtaque.BOLA_NEVE.getLargura1(), TipoAtaque.BOLA_NEVE.getAltura1()), 
		0, 0, new Vector2(0.05f, 0.5f), TipoAtaque.BOLA_NEVE, -5);

        Ataque ataqueJaminger = new Ataque(new TextureRegion(TipoAtaque.DISCO.getTextura(), 
		TipoAtaque.DISCO.getCordX1(), TipoAtaque.DISCO.getCordY1(),
		TipoAtaque.DISCO.getLargura1(), TipoAtaque.DISCO.getAltura1()), 
		0, 0, new Vector2(0.05f, 0.5f), TipoAtaque.DISCO, -5);
            
        determinarPosicoesValidas();

        int indexPosicaoAnterior = -1;
        for(int i=0; i<15; i++){
            int indexPosicao = random.nextInt(posicoesValidas.size());

            if(indexPosicaoAnterior == -1 || Math.abs(posicoesValidas.get(indexPosicao).x - posicoesValidas.get(indexPosicaoAnterior).x) > 800){
                int sortearPersonagem = random.nextInt(2);
                if(sortearPersonagem == 0){
                    Jaminger jaminger = new Jaminger(texturaJaminger, 0, 
                    0, ataqueJaminger, 0, 5);

                    float posX = posicoesValidas.get(indexPosicao).x + jaminger.getCorpo().getBoundingRectangle().width;
                    float posY = posicoesValidas.get(indexPosicao).y;
                    jaminger.setPosicao(posX, posY);

                    inimigos.add(jaminger);
                    personagens.add(jaminger);
                }else{
                    Trower trower = new Trower(texturaTrower, 0, 
                    0, ataqueTrower, 0, 5);

                    float posX = posicoesValidas.get(indexPosicao).x - trower.getCorpo().getBoundingRectangle().width;
                    float posY = posicoesValidas.get(indexPosicao).y;
                    
                    trower.setPosicao(posX, posY);
                    inimigos.add(trower);
                    personagens.add(trower);
                }
            }
            indexPosicaoAnterior = indexPosicao;
        }
        
    }

    private void determinarPosicoesValidas(){
        for(Rectangle plataforma : mapa.getChaos()){
            if(plataforma.height > 60 && plataforma.width > 200 && plataforma.x < 7000){
                float posYplataforma = plataforma.y + plataforma.height + 300;
                float posXplataforma = plataforma.x + plataforma.width/2;
                posicoesValidas.add(new Vector2(posXplataforma, posYplataforma));
            }
        }
    }

    @Override
    public void render() {
        super.render();

        cameraFoco.set(megaMan.getPosX() + megaMan.getCorpo().getBoundingRectangle().width, 
        megaMan.getPosY() + (megaMan.getCorpo().getBoundingRectangle().height/2));
        camera.position.set(cameraFoco, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined); 
        
        Gdx.gl.glClearColor(255f, 255f, 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!megaMan.isMorreu()){
            atualizarPersonagens();
            ataquesPersonagens();
            colisoes();
        }

        desenhaItens();
    }

     private void atualizarPersonagens(){
        personagens.reset();
        while (personagens.hasNext()) {
            Personagem personagem = personagens.next();
            personagem.mover();
            personagem.atacar();
            personagem.morrer();
        }
        personagens.reset();

        inimigos.reset();
        while (inimigos.hasNext()) {
            Inimigo inimigo = inimigos.next();
            inimigo.setPosXmegaMan(megaMan.getPosX());
        }
        inimigos.reset();

        penguin.atualizar();
    }

    private void ataquesPersonagens(){
        personagens.reset();
        while (personagens.hasNext()) {
            Personagem personagem = personagens.next();
            for(int i=0; i < personagem.getAtaquesAtivos().size(); i++){
                personagem.getAtaquesAtivos().get(i).disparar();
            }
        }
        personagens.reset();
    }

    private void colisoes() { 
        gerenciadorColisoes.colisaoPersonagensPlataformas(mapa.getChaos(), personagens);   
        gerenciadorColisoes.colisaoMegaManPlataforma(mapa.getChaos(), megaMan);
        gerenciadorColisoes.colisaoMegaManInimigos(megaMan, inimigos);

        inimigos.reset();
        while (inimigos.hasNext()) {
            Inimigo inimigo = inimigos.next();
            gerenciadorColisoes.colisaoAtaquesMegaman(megaMan, inimigo.getAtaquesAtivos());
            gerenciadorColisoes.colisaoAtaquesMegamanInimigos(inimigo, megaMan.getAtaquesAtivos());
        }
        inimigos.reset();

        personagens.reset();
        while (personagens.hasNext()) {
            Personagem personagem = personagens.next();
            gerenciadorColisoes.colisaoAtaquesPlataformas(mapa.getChaos(), personagem.getAtaquesAtivos());
        }
        personagens.reset();
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

        desenharEntidades();

        batch.end();
        
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (Rectangle r : mapa.getChaos()) {
            shapeRenderer.rect(r.x, r.y, r.width, r.height);
        }
        shapeRenderer.end();
    }

    private void desenharEntidades(){
        desenharAtaques();
        personagens.reset();
        while (personagens.hasNext()) {
            Personagem personagem = personagens.next();
            personagem.draw(batch);
        }
        personagens.reset();
    }

    private void desenharAtaques(){
        personagens.reset();
        while (personagens.hasNext()) {
            Personagem personagem = personagens.next();
            for(int i=0; i < personagem.getAtaquesAtivos().size(); i++){
                personagem.getAtaquesAtivos().get(i).draw(batch);;
            }
        }
        personagens.reset();
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
        texturaTrower.dispose();
        texturaJaminger.dispose();

        super.dispose();
    }

}