package inf112.platformer.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.MainMenuScreen;

public class PlatformerGame extends Game {
    public SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this, camera));
    }

    @Override
    public void render(){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;

        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
    }
}
