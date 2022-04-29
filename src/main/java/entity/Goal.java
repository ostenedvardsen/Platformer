package entity;

import com.badlogic.gdx.graphics.Texture;
import world.GameMap;

public class Goal extends Entity {


    public Goal(float x, float y, GameMap map) {
        super(x, y, EntityType.GOAL, map, 1);
        entityTexture = new Texture("goal.png");
        collidable = false;
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
