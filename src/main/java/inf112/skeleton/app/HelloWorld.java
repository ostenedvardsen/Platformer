package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;


public class HelloWorld extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap screen;

    private TiledMapTileLayer.Cell playerCell;
    private Vector2 playerPos;

    private TiledMapTileLayer playerLayer;

    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    int MAP_SIZE_X = 18;
    int MAP_SIZE_Y = 7;

    int playerX, playerY;
    int oldPlayerX, oldPlayerY;


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

        TmxMapLoader loader = new TmxMapLoader();
        screen = loader.load("plcStage.tmx");

        Texture pTexture = new Texture("player.jpg");

        TextureRegion playerTexture = new TextureRegion(pTexture);

        playerCell = new TiledMapTileLayer.Cell();
        StaticTiledMapTile playerMapTile = new StaticTiledMapTile(playerTexture);
        playerCell.setTile(playerMapTile);

        playerPos = new Vector2(0, 64);

        playerLayer = (TiledMapTileLayer) screen.getLayers().get("player");

        camera = new OrthographicCamera();

        camera.setToOrtho(false, MAP_SIZE_X, MAP_SIZE_Y);
        camera.translate(0, 0);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(screen, 1/32.0f);

        renderer.setView(camera);

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

        playerLayer.setCell(oldPlayerX, oldPlayerY, null);
        playerLayer.setCell(playerX, playerY, playerCell);

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
