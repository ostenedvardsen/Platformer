package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.renderers.*;


public class HelloWorld implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap screen;
    // private TiledMapTileLayer boardLayer;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    int MAP_SIZE_X = 18;
    int MAP_SIZE_Y = 7;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        TmxMapLoader loader = new TmxMapLoader();
        screen = loader.load("plcStage.tmx");

        //boardLayer = (TiledMapTileLayer) screen.getLayers().get("stage");

        camera = new OrthographicCamera();

        camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
        camera.translate(0, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(screen, 1/32.0f);

        renderer.setView(camera);

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //batch.begin();
        //font.draw(batch, "Hello World", 200, 200);
        //batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
