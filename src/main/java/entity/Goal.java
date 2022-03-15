package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Goal extends Entity {

    Texture goalImage;

    public Goal(float x, float y, GameMap map) {
        super(x, y, EntityType.GOAL, map);
        goalImage = new Texture("goal.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(goalImage, pos.x, pos.y, getWidth(), getHeight());
    }
}
