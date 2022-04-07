package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import inf112.platformer.app.PlatformerGame;
import scenes.Hud;
import world.GameMap;
import world.TiledGameMap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;

public class ActiveGameScreen implements Screen {

    private final PlatformerGame game;
    private BitmapFont font;
    public Hud playerHud;
    GameMap tiledGameMap;
    OrthographicCamera camera;


    public ActiveGameScreen(PlatformerGame game, OrthographicCamera camera){
        this.game = game;
        this.camera = camera;

        tiledGameMap = new TiledGameMap();
        playerHud = new Hud(game.batch, tiledGameMap.getPlayers());

        font = new BitmapFont();
        font.setColor(Color.RED);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(playerHud.stage.getCamera().combined);

        //Moves the camera via mouse input.
        if(Gdx.input.isTouched()){
            camera.translate(Gdx.input.getDeltaX(), -Gdx.input.getDeltaY());
            camera.update();
        }

        if (playerHud.initializedHud)
            playerHud.updateHud();
        playerHud.stage.draw();

        camera.update();

        tiledGameMap.update(Gdx.graphics.getDeltaTime());
        tiledGameMap.render(camera, game.batch);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
