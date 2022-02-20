package inf112.platformer.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
<<<<<<< HEAD:src/main/java/inf112/skeleton/app/HelloWorld.java
import com.badlogic.gdx.Input;
=======
>>>>>>> Osten.Edvardsen/platformer-oblig1Forbedring:src/main/java/inf112/platformer/app/Game.java
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import world.GameMap;
import world.TiledGameMap;


<<<<<<< HEAD:src/main/java/inf112/skeleton/app/HelloWorld.java
public class HelloWorld extends InputAdapter implements ApplicationListener {
=======
public class Game extends InputAdapter implements ApplicationListener {
>>>>>>> Osten.Edvardsen/platformer-oblig1Forbedring:src/main/java/inf112/platformer/app/Game.java
    private SpriteBatch batch;
    private BitmapFont font;

<<<<<<< HEAD
    /*
    private TiledMap screen;

    private TiledMapTileLayer.Cell playerCell;
    private Vector2 playerPos;

    private TiledMapTileLayer playerLayer;

    private OrthogonalTiledMapRenderer renderer;

    int MAP_SIZE_X = 18;
    int MAP_SIZE_Y = 7;

    int playerX, playerY;
    int oldPlayerX, oldPlayerY;
<<<<<<< HEAD:src/main/java/inf112/skeleton/app/HelloWorld.java
=======
*/
    GameMap tiledGameMap;
    private OrthographicCamera camera;

>>>>>>> Osten.Edvardsen/platformer-oblig1Forbedring:src/main/java/inf112/platformer/app/Game.java


=======
    GameMap tiledGameMap;
    private OrthographicCamera camera;

>>>>>>> Osten.Edvardsen/platformer-oblig1Forbedring
    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);

        oldPlayerX = 0;
        oldPlayerY = 0;
        playerX = 2;
        playerY = 2;

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
    public boolean keyDown(int keycode) {
        oldPlayerY = playerY;
        oldPlayerX = playerX;

        if (keycode == Input.Keys.UP) {
            playerY++;
        }
        else if (keycode == Input.Keys.DOWN) {
            playerY--;
        }
        else if (keycode == Input.Keys.RIGHT) {
            playerX++;
        } else if (keycode == Input.Keys.LEFT) {
            playerX--;
        } else {
            return false;
        }
        render();
        return true;
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

<<<<<<< HEAD:src/main/java/inf112/skeleton/app/HelloWorld.java
        playerLayer.setCell(oldPlayerX, oldPlayerY, null);
        playerLayer.setCell(playerX, playerY, playerCell);

        renderer.render();
        //batch.begin();
        //font.draw(batch, "Hello World", 200, 200);
        //batch.end();
=======
        //Moves the camera via mouse input.

        if(Gdx.input.isTouched()){
            camera.translate(Gdx.input.getDeltaX(), -Gdx.input.getDeltaY());
            camera.update();
        }

        camera.update();
        tiledGameMap.update(Gdx.graphics.getDeltaTime());
        tiledGameMap.render(camera, batch);
>>>>>>> Osten.Edvardsen/platformer-oblig1Forbedring:src/main/java/inf112/platformer/app/Game.java
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
