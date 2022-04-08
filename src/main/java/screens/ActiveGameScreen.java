package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import entity.Player;
import inf112.platformer.app.PlatformerGame;
import scenes.Hud;
import world.GameMap;
import world.TiledGameMap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;

public class ActiveGameScreen implements Screen {

    private final PlatformerGame game;
    private BitmapFont font;
    private float xOffset = 50;
    private float yOffset = 70;
    private float cameraSpeed = 0.04f;
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

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            this.dispose();
            game.setScreen(new MainMenuScreen(game, camera));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.E)){
            tiledGameMap.loadNextMap();
        }

        game.batch.setProjectionMatrix(playerHud.stage.getCamera().combined);

        //Moves the camera via mouse input.
        if(Gdx.input.isTouched()){
            camera.translate(Gdx.input.getDeltaX(), -Gdx.input.getDeltaY());
        }

        if (playerHud.initializedHud)
            playerHud.updateHud();
        playerHud.stage.draw();

        cameraFollowPlayer();
        camera.update();

        tiledGameMap.update(Gdx.graphics.getDeltaTime());
        tiledGameMap.render(camera, game.batch);
    }

    private void cameraFollowPlayer() {
        if (tiledGameMap.getPlayers().isEmpty()) return;
        Vector2 startValue = tiledGameMap.getPlayers().get(0).getPos();
        float xMax = startValue.x;
        float xMin = startValue.x;
        float yMax = startValue.y;
        float yMin = startValue.y;

        for (Player player : tiledGameMap.getPlayers()){
            Vector2 value = player.getPos();

            if (value.x > xMax) xMax = value.x;
            if (value.x < xMin) xMin = value.x;
            if (value.y > yMax) yMax = value.y;
            if (value.y < yMin) yMin = value.y;
        }

        camera.translate((xOffset + xMin - camera.position.x + (xMax-xMin)/2)*cameraSpeed,(yOffset + yMin - camera.position.y + (yMax-yMin)/2)*cameraSpeed);
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
