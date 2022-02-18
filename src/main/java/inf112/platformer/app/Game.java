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
*/
    GameMap tiledGameMap;
    private OrthographicCamera camera;



    @Override
    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.RED);

        //Gdx.input.setInputProcessor(this);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/2, h/2);
        camera.update();

        tiledGameMap = new TiledGameMap();

        /*
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



        renderer = new OrthogonalTiledMapRenderer(screen, 1/32.0f);

        renderer.setView(camera);
        */
    }
/*
    @Override
    public boolean keyUp(int keycode) {
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
*/

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
