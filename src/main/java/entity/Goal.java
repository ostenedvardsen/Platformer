package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Goal extends Entity {

    Texture goalImage;

    public Goal(float x, float y, GameMap map) {
        super(x, y, EntityType.GOAL, map, 1);
        goalImage = new Texture("goal.png");
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(goalImage, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public void interact(Entity entity) {
        if (entity instanceof Player){
            ((Player) entity).addScore(1000);
            map.loadNextMap();
        }
    }

    @Override
    public void destroyedBy(Entity player) {

    }
}
