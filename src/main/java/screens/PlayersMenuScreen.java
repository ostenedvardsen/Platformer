package screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import inf112.platformer.app.PlatformerGame;
import org.lwjgl.system.CallbackI;

public class PlayersMenuScreen implements Screen {

    PlatformerGame game;
    OrthographicCamera camera;
    private Vector3 mouseInputPosition;

    Texture imageBackground;
    float width;
    float height;

    Texture onePlayerButton;
    private final float buttonScale = 0.1f;

    Texture quitButton;

    public PlayersMenuScreen(PlatformerGame game, OrthographicCamera camera) {

        imageBackground = new Texture("main_menu_background.png");
        onePlayerButton = new Texture("1playersTest.png");
        this.game = game;

        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.camera = camera;

        mouseInputPosition = new Vector3(0,0,0);


        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int x, int y, int pointer, int button){
                if(button == Input.Buttons.LEFT){
                    mouseClick();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void show() {

    }

    boolean onePlayerButtonIsHovered = false;
    boolean quitButtonIsHovered = false;


    public void mouseClick(){
        if(onePlayerButtonIsHovered){
        	onePlayerButtonIsHovered = false;
            this.dispose();
            game.setScreen(new ActiveGameScreen(game, camera));

        }
        else if (quitButtonIsHovered) {
            this.dispose();
            Gdx.app.exit();
        }

    }

    public void draw(SpriteBatch batch){
        //batch.draw(imageBackground, 0, 0, width/2, height);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //camera.translate(0,0);

        //camera.viewportWidth = width;
        //camera.viewportHeight = height;
        camera.position.set(width/4, height/2f, 0);
        camera.update();

        game.batch.draw(imageBackground, 0, 0, imageBackground.getWidth()/2, imageBackground.getHeight());

        mouseInputPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseInputPosition);

        Vector2 OnePlayerButtonLowerLeft = new Vector2(width/4-buttonScale*width/2, height/4);
        Vector2 OnePlayerButtonUpperRight = new Vector2(width/4-buttonScale*width/2 + buttonScale*width,height/4 + buttonScale*height);

        Vector2 quitButtonLowerLeft = new Vector2(width/4-buttonScale*width/2, height/4-height/8);
        Vector2 quitButtonUpperRight = new Vector2(width/4-buttonScale*width/2 + buttonScale*width, height/4-height/8 + buttonScale*height);

        if(mouseHover(OnePlayerButtonLowerLeft, OnePlayerButtonUpperRight)){
        	onePlayerButtonIsHovered = true;
        	onePlayerButton = new Texture("1playersTestHovered.png");
        }
        else if(mouseHover(quitButtonLowerLeft, quitButtonUpperRight)){
            quitButtonIsHovered = true;
            quitButton = new Texture("quitbuttontouched.png");
        }
        else{
        	onePlayerButtonIsHovered = false;
            quitButtonIsHovered = false;
            onePlayerButton = new Texture("startbutton.png");
            quitButton = new Texture("quitbutton.png");

        }

        game.batch.draw(onePlayerButton, OnePlayerButtonLowerLeft.x, OnePlayerButtonLowerLeft.y,  buttonScale*width, buttonScale*height);
        game.batch.draw(quitButton, quitButtonLowerLeft.x, quitButtonLowerLeft.y, buttonScale*width, buttonScale*height);

        game.batch.end();
    }


    public boolean mouseHover(Vector2 lowerLeft, Vector2 upperRight) {
        if(mouseInputPosition.x > lowerLeft.x && mouseInputPosition.x < upperRight.x) {
            if (mouseInputPosition.y > lowerLeft.y && mouseInputPosition.y < upperRight.y) {
                return true;
            }
        }
        return false;
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
