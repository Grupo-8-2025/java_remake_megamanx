package com.tp1.dotsandboxes;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Mapa {

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderizadorMapa;
    private TiledMap mapa;
    private Array<Rectangle> retangulosColisao;
    private float escala = 3.8f;

    public Mapa(String pathMapaTmx, int largura, int altura) {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, largura, altura);

        this.mapa = new TmxMapLoader().load(pathMapaTmx);
        this.renderizadorMapa = new OrthogonalTiledMapRenderer(mapa, escala);

        carregarColisores("ColisoesHorizontais");
    }

    private void carregarColisores(String nomeCamada) {
        retangulosColisao = new Array<>();
        MapLayer camada = mapa.getLayers().get(nomeCamada);
        if (camada == null) {
            System.out.println("Camada de colisão não encontrada: " + nomeCamada);
            return;
        }

        for (MapObject objeto : camada.getObjects()) {
            if (objeto instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) objeto).getRectangle();
                
                rect.set(
                    rect.x * escala,
                    rect.y * escala,
                    rect.width * escala,
                    rect.height * escala
                );
                retangulosColisao.add(rect);
            }
        }

        System.out.println("Colisores carregados: " + retangulosColisao.size);
    }

    public Array<Rectangle> getRetangulosColisao() {
        return retangulosColisao;
    }

    public Array<Rectangle> getChaos() {
        return retangulosColisao;
    }

    public float getEscala() {
        return escala;
    }

    public void render(OrthographicCamera camera) {
        this.camera = camera;
        this.camera.update();
        renderizadorMapa.setView(this.camera);
        renderizadorMapa.render();
    }

    public void dispose() {
        mapa.dispose();
        renderizadorMapa.dispose();
    }
}
