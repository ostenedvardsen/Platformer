package inf112.platformer.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import world.GameMap;
import world.TiledGameMap;


public class Game extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;

    GameMap tiledGameMap;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.RED);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/2, h/2);
        camera.update();

        tiledGameMap = new TiledGameMap();
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

        //Moves the camera via mouse input.

        if(Gdx.input.isTouched()){
            camera.translate(Gdx.input.getDeltaX(), -Gdx.input.getDeltaY());
            camera.update();
        }

        camera.update();
        tiledGameMap.update(Gdx.graphics.getDeltaTime());
        tiledGameMap.render(camera, batch);
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
