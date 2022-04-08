package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import world.GameMap;

public class Player extends ActiveEntity {

    private static final int SPEED = 115;
    private static final int JUMP_VELOCITY = 735;
    private static final float JUMP_INTENSITY = 0.3f;
    private static final float MAX_JUMP_TIME = .25f;
    private static final float turn_speed = 10f;
    private float momentum;
    private float current_jump_time = 0;
    private int ID = 0;
    private int score = 0;
    Texture playerImage;
    private int leftKey;
    private int rightKey;
    private int jumpKey;

    
    public Player(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.PLAYER, map, hp);
        
        health = 2;
    }

    public void setId(int id) {
    	ID = id;
    	playerImage = new Texture("player" + Integer.toString(ID) + ".png");
    	if (ID == 1) { leftKey = Input.Keys.LEFT; rightKey = Input.Keys.RIGHT; jumpKey = Input.Keys.UP; }
    	if (ID == 2) { leftKey = Input.Keys.A; rightKey = Input.Keys.D; jumpKey = Input.Keys.W; }
    	if (ID == 3) { leftKey = Input.Keys.J; rightKey = Input.Keys.L; jumpKey = Input.Keys.I; }
    	if (ID == 4) { leftKey = Input.Keys.F; rightKey = Input.Keys.H; jumpKey = Input.Keys.T; }
    }
    
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(playerImage, pos.x, pos.y, getWidth(), getHeight());
    }


    @Override
    public void update(float deltaTime, float gravity) {
        if(Gdx.input.isKeyPressed(jumpKey)){
            if(map.doesEntityRectangleCollideWithTileOnAnyLayer(this.getX(), this.getY()-0.01f, this.getWidth(), this.getHeight())){
                this.velocityY += JUMP_VELOCITY*JUMP_INTENSITY;
                current_jump_time = 0;
            }
            else if (this.velocityY > 0){
                current_jump_time += deltaTime;
                if (current_jump_time < MAX_JUMP_TIME) this.velocityY += JUMP_VELOCITY * deltaTime;
            }
        }

        if (Gdx.input.isKeyPressed(leftKey)) {
            if (momentum > -1) momentum-= turn_speed *deltaTime;
        } else if (Gdx.input.isKeyPressed(rightKey)) {
            if (momentum < 1) momentum+= turn_speed *deltaTime;
        } else momentum -= momentum*turn_speed*deltaTime;


        moveX(SPEED*deltaTime*momentum);

        super.update(deltaTime, gravity);
        rect.move(this.getX(), this.getY());
        }
        

    public void addScore(int value) {
        score+=value;
    }

    public void removeScore(int value) {
        score-=value;
    }

    public int getScore(){
        return score;
    }

    @Override
    public void playerInteract(Player player) { }

    public Vector2 getPos() {
        return pos;
    }
}
