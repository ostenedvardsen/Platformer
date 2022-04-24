package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;
import tools.CollisionHandling;
import tools.CollisionRect;
import world.GameMap;

import java.util.ArrayList;

public class Player extends ActiveEntity {

    private static final int SPEED = 105;
    private static final int JUMP_VELOCITY = 900;
    private static final float JUMP_INTENSITY = .25f;
    private static final float MAX_JUMP_TIME = .35f;
    private static final float turn_speed = 10f;
    private float momentum;
    private float current_jump_time = 0;
    private int ID = 0;
    private int score = 0;
    Texture playerImage;
    private int leftKey;
    private int rightKey;
    private int jumpKey;
    private int gracePeriod;

    CollisionHandling collisionHandling;



    public Player(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.PLAYER, map, hp);
        gracePeriod = 0;
        health = 100;

        collisionHandling = new CollisionHandling();
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

    @Override
    public void moveX(float xAmount){
        float newX = pos.x + xAmount;

        CollisionRect testRect = new CollisionRect(newX, pos.y, getWidth(), getHeight());
        ArrayList<Entity> collidedEntities = map.doesRectangleCollideWithAnyEntity(testRect);

        if(!map.doesEntityRectangleCollideWithTileOnAnyLayer(newX, pos.y, getWidth(), getHeight())){
            if(collidedEntities.isEmpty()) {
                this.pos.x = newX;
            }

            else {
                if(collisionHandling.entityFromTheLeft(this, collidedEntities)){
                    this.pos.x = collisionHandling.lowestXEntity(collidedEntities).getX()- this.getWidth() - 0.01f;
                    this.playerAttack(collidedEntities);

                } else if (collisionHandling.entityFromTheRight(this, collidedEntities)) {
                    this.pos.x = collisionHandling.highestXEntity(collidedEntities).getX() + collisionHandling.highestXEntity(collidedEntities).getWidth() - 0.01f;
                    this.playerAttack(collidedEntities);
                }
            }
        }
    }

    @Override
    public void damage(int amount){
        if (gracePeriod == 0){
            super.damage(amount);
            gracePeriod = 1;
        }
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

    private void playerAttack(ArrayList<Entity> attacked){
        for (Entity entity: attacked){
            this.playerAttack(entity);
        }
    }

    private void playerAttack(Entity attacked){
        map.interactEntities(this, attacked);
    }

    @Override
    public void playerInteract(Player player) {}

    @Override
    public void destroyedBy(Entity entity) {}

    public Vector2 getPos() {
        return pos;
    }
}
