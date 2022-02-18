package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Player extends Entity {

    private static final int SPEED = 50;
    Texture playerImage;



    public Player(float x, float y, GameMap map) {
        super(x, y, EntityType.PLAYER, map);
        playerImage = new Texture("player.jpg");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(playerImage, pos.x, pos.y, getWidth(), getHeigth());
    }


    @Override
    public void update(float deltaTime, float gravity) {
        super.update(deltaTime, gravity);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX(-SPEED * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX(SPEED * deltaTime);
        }
    }


}
