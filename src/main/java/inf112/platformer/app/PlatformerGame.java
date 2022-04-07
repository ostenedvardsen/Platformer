package inf112.platformer.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import screens.MainMenuScreen;

public class PlatformerGame extends Game {
    public SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/2, h/2);
        camera.update();

        this.setScreen(new MainMenuScreen(this, camera, 1.0f));
    }

    @Override
    public void render(){
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }


    @Override
    public void resize(int width, int height){


    }
}
