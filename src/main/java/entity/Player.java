package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import tools.CollisionHandling;
import tools.CollisionRect;
import world.GameMap;

import java.util.ArrayList;

public class Player extends ActiveEntity {

    float deathTime = 0.8f;

    private static final int SPEED = 105;
    private static final int JUMP_VELOCITY = 900;
    private static final float JUMP_INTENSITY = .25f;
    private static final float MAX_JUMP_TIME = .35f;
    private static final float turn_speed = 10f;
    private float momentum;
    private float current_jump_time = 0;
    private int ID = 0;
    private int score = 0;
    private int leftKey;
    private int rightKey;
    private int jumpKey;

    Texture playerImage;

    public Player(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.PLAYER, map, hp);
        gracePeriod = 0;
        health = 25;
        gracePeriodIdentifier = 0.75f;
        attackDamage = 5;
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
        if (health <= 0) batch.setColor(new Color(1,0,0,deathTime));
        else if (gracePeriod > 0) batch.setColor(new Color(0.7f,0.7f,0.7f,(float) (Math.cos(gracePeriod*30)+1)/2f));
        batch.draw(playerImage, pos.x, pos.y, getWidth(), getHeight());
        batch.setColor(Color.WHITE);
    }

    @Override
    public boolean isDead(){
        return this.health <= 0 && deathTime <= 0;
    }

    @Override
    public void interact(Entity entity) {

    }

    @Override
    public void update(float deltaTime, float gravity) {
        if (deltaTime > 0.05f) deltaTime = 0.05f;
        if (health <= 0) { deathTime -= deltaTime; return; }

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

        if (momentum < 0.000001f & momentum > -0.000001f){
            momentum = 0;
        }

        moveX(SPEED*deltaTime*momentum);
        super.moveY(deltaTime, gravity);


        if(gracePeriod <= 0){
            gracePeriod = 0;
        } else{
            gracePeriod = gracePeriod - deltaTime;
        }

        rect.move(this.getX(), this.getY());
    }

    @Override
    public void moveX(float xAmount){
        float newX = pos.x + xAmount;

        CollisionRect nextRect = new CollisionRect(newX, pos.y, getWidth(), getHeight());

        if(!map.collisionHandling.entityCollisionX(this, nextRect, false)){
            this.pos.x = newX;
        }

        rect.move(this.pos.x, this.pos.y);
    }


    public int getID(){
        return ID;
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
    public void destroyedBy(Entity entity) {}

    public Vector2 getPos() {
        return pos;
    }
}
