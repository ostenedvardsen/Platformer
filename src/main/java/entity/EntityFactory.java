package entity;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import world.TiledGameMap;

public class EntityFactory {
    public Entity getEntity(RectangleMapObject rectangleObject, String name, TiledGameMap tiledGameMap){
        if(name == null){
            return null;
        }
        if(name.equalsIgnoreCase("Skeleton")) {
            return new Skeleton(rectangleObject.getRectangle().getX(), rectangleObject.getRectangle().getY(), tiledGameMap);
        }
        if(name.equalsIgnoreCase("Goal")) {
            return new Goal(rectangleObject.getRectangle().getX(), rectangleObject.getRectangle().getY(), tiledGameMap);
        }

        return null;

    }
}
