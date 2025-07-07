package com.tp1.dotsandboxes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Mapa {
    
    private String imagePath;
    private static int window_width;
    private static int window_height;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Rectangle viewport;
    private Texture background_image;
	private Stage stage;
	

    public Mapa(String imagePath, int window_width, int window_height) {
        this.imagePath = imagePath;
        Mapa.window_width = window_width;
        Mapa.window_height = window_height;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, window_width, window_height);
        this.viewport = new Rectangle(0, 0, window_width, window_height);
        this.background_image = new Texture(imagePath);
        this.renderer = new OrthogonalTiledMapRenderer(null); 
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Rectangle getViewport() {
        return viewport;
    }

    public Texture getBackgroundImage() {
        return background_image;
    }

    public Stage getStage() {
        return stage;
    }

    public void updateCamera() {
        camera.update();
    }

    public void render() {
        updateCamera();
        // Pode ser usado para renderizar tiles se necessário
    }

    public void dispose() {
        if (background_image != null) {
            background_image.dispose();
        }
    }
}
