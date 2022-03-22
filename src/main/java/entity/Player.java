package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import world.GameMap;

public class Player extends Entity {

    private static final int SPEED = 65;
    private static final int JUMP_VELOCITY = 235;
    private static final float MAX_JUMP_TIME = .5f;
    private float current_jump_time = 0;

    Texture playerImage;


    
    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        playerImage = new Texture("player.jpg");
        
        gravityAffected = true;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(playerImage, pos.x, pos.y, getWidth(), getHeight());
    }


    @Override
    public void update(float deltaTime, float gravity) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if(map.doesEntityRectangleCollideWithTileOnAnyLayer(this.getX(), this.getY()-0.01f, this.getWidth(), this.getHeight())){
                this.velocityY += JUMP_VELOCITY;
                current_jump_time = 0;
            }
            else if (this.velocityY > 0){
                current_jump_time += deltaTime;
                if (current_jump_time < MAX_JUMP_TIME) this.velocityY += JUMP_VELOCITY * deltaTime;
            }
        }

        super.update(deltaTime, gravity);
        rect.move(this.getX(), this.getY());
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX(-SPEED * deltaTime);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX(SPEED * deltaTime);

        }
    }

    @Override
    public void playerInteract(GameMap gamemap, Player player) { }

}
