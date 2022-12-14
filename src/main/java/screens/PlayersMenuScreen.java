package screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import inf112.platformer.app.PlatformerGame;

public class PlayersMenuScreen implements Screen {

    PlatformerGame game;
    OrthographicCamera camera;
    private Vector3 mouseInputPosition;

    Texture imageBackground;
    float width;
    float height;

    
    private final float buttonScale = 0.1f;

    Texture quitButton;
    Texture onePlayerButton;
    Texture twoPlayerButton;
    Texture threePlayerButton;
    Texture fourPlayerButton;
    
    public PlayersMenuScreen(PlatformerGame game, OrthographicCamera camera) {

        imageBackground = new Texture("main_menu_background.png");
        onePlayerButton = new Texture("1playerbutton.png");
        twoPlayerButton = new Texture("2playerbutton.png");
        threePlayerButton = new Texture("3playerbutton.png");
        fourPlayerButton = new Texture("4playerbutton.png");
        
        quitButton = new Texture("quitbutton.png");
        this.game = game;

        this.width = camera.viewportWidth;
        this.height = camera.viewportHeight;


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
    boolean twoPlayerButtonIsHovered = false;
    boolean threePlayerButtonIsHovered = false;
    boolean fourPlayerButtonIsHovered = false;
    boolean quitButtonIsHovered = false;


    public void mouseClick(){
        if(onePlayerButtonIsHovered){
        	onePlayerButtonIsHovered = false;
            this.dispose();
            game.setScreen(new ActiveGameScreen(game, camera, 1));
        }
        
        
        else if(twoPlayerButtonIsHovered){
        	twoPlayerButtonIsHovered = false;
            this.dispose();
            game.setScreen(new ActiveGameScreen(game, camera, 2));
        }
        
        
        else if(threePlayerButtonIsHovered){
        	threePlayerButtonIsHovered = false;
            this.dispose();
            game.setScreen(new ActiveGameScreen(game, camera, 3));
        }
        
        
        else if(fourPlayerButtonIsHovered){
        	fourPlayerButtonIsHovered = false;
            this.dispose();
            game.setScreen(new ActiveGameScreen(game, camera, 4));
        }        
        else if (quitButtonIsHovered) {
            this.dispose();
            Gdx.app.exit();
        }

    }

    public void draw(SpriteBatch batch){
        batch.draw(imageBackground, 0, 0, width/2, height);
    }

    @Override
    public void render(float v) {
        this.width = camera.viewportWidth;
        this.height = camera.viewportHeight;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        camera.update();

        game.batch.draw(imageBackground, 0, 0, camera.viewportWidth, camera.viewportHeight);

        mouseInputPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouseInputPosition);

        Vector2 OnePlayerButtonLowerLeft = new Vector2(width/2-buttonScale*width/2 - width/4, height/2-buttonScale*height);
        Vector2 OnePlayerButtonUpperRight = new Vector2(width/2-buttonScale*width/2 - width/4 + buttonScale*width,height/2-buttonScale*height + buttonScale*height);

        Vector2 TwoPlayerButtonLowerLeft = new Vector2(width/2-buttonScale*width/2 + width/4, height/2-buttonScale*height);
        Vector2 TwoPlayerButtonUpperRight = new Vector2(width/2-buttonScale*width/2 + width/4+buttonScale*width,height/2-buttonScale*height + buttonScale*height);
        
        Vector2 ThreePlayerButtonLowerLeft = new Vector2(width/2-buttonScale*width/2 - width/4, height/2-buttonScale*height - height/4);
        Vector2 ThreePlayerButtonUpperRight = new Vector2(width/2-buttonScale*width/2 - width/4 + buttonScale*width,height/2-buttonScale*height - height/4 + height*buttonScale);

        Vector2 FourPlayerButtonLowerLeft = new Vector2(width/2-buttonScale*width/2 + width/4, height/2-buttonScale*height - height/4);
        Vector2 FourPlayerButtonUpperRight = new Vector2(width/2-buttonScale*width/2 + width/4+buttonScale*width,height/2-buttonScale*height - height/4 + height*buttonScale);
                
        
        Vector2 quitButtonLowerLeft = new Vector2(width/2-buttonScale*width/2, height/4-height/8);
        Vector2 quitButtonUpperRight = new Vector2(width/2-buttonScale*width/2 + buttonScale*width, height/4-height/8 + buttonScale*height);

        if(mouseHover(OnePlayerButtonLowerLeft, OnePlayerButtonUpperRight)){
        	onePlayerButtonIsHovered = true;
        	onePlayerButton = new Texture("1playerbuttonhovered.png");
        }
        else if(mouseHover(TwoPlayerButtonLowerLeft, TwoPlayerButtonUpperRight)) {
        	twoPlayerButtonIsHovered = true;
        	twoPlayerButton = new Texture("2playerbuttonhovered.png");
        }
        else if(mouseHover(ThreePlayerButtonLowerLeft, ThreePlayerButtonUpperRight)) {
        	threePlayerButtonIsHovered = true;
        	threePlayerButton = new Texture("3playerbuttonhovered.png");
        }
        else if(mouseHover(FourPlayerButtonLowerLeft, FourPlayerButtonUpperRight)) {
        	fourPlayerButtonIsHovered = true;
        	fourPlayerButton = new Texture("4playerbuttonhovered.png");
        }
        else if(mouseHover(quitButtonLowerLeft, quitButtonUpperRight)){
            quitButtonIsHovered = true;
            quitButton = new Texture("quitbuttontouched.png");
        }
        else{
        	onePlayerButtonIsHovered = false;
        	twoPlayerButtonIsHovered = false;
            threePlayerButtonIsHovered = false;
            fourPlayerButtonIsHovered = false;
            quitButtonIsHovered = false;

            onePlayerButton = new Texture("1playerbutton.png");
            twoPlayerButton = new Texture("2playerbutton.png");
            threePlayerButton = new Texture("3playerbutton.png");
            fourPlayerButton = new Texture("4playerbutton.png");
            quitButton = new Texture("quitbutton.png");

        }

        game.batch.draw(onePlayerButton, OnePlayerButtonLowerLeft.x, OnePlayerButtonLowerLeft.y,  buttonScale*width, buttonScale*height);
        game.batch.draw(twoPlayerButton, TwoPlayerButtonLowerLeft.x, TwoPlayerButtonLowerLeft.y, buttonScale*width, buttonScale*height);
        game.batch.draw(threePlayerButton, ThreePlayerButtonLowerLeft.x, ThreePlayerButtonLowerLeft.y, buttonScale*width, buttonScale*height);
        game.batch.draw(fourPlayerButton, FourPlayerButtonLowerLeft.x, FourPlayerButtonLowerLeft.y, buttonScale*width, buttonScale*height);
        game.batch.draw(quitButton, quitButtonLowerLeft.x, quitButtonLowerLeft.y, buttonScale*width, buttonScale*height);
        
        game.batch.end();
    }


    public boolean mouseHover(Vector2 lowerLeft, Vector2 upperRight) {
        if(mouseInputPosition.x > lowerLeft.x && mouseInputPosition.x < upperRight.x) {
            return mouseInputPosition.y > lowerLeft.y && mouseInputPosition.y < upperRight.y;
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
