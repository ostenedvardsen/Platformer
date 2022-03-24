package entity;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import world.GameMap;

public class EntityFactory {
    public Entity getEntity(RectangleMapObject rectangleObject, String name, GameMap gameMap){
        if(name == null){
            return null;
        }
        if(name.equalsIgnoreCase("Skeleton")) {
            return new Skeleton(rectangleObject.getRectangle().getX(), rectangleObject.getRectangle().getY(), gameMap);
        }
        if(name.equalsIgnoreCase("Goal")) {
            return new Goal(rectangleObject.getRectangle().getX(), rectangleObject.getRectangle().getY(), gameMap);
        }
        if(name.equalsIgnoreCase("Coin")) {
            return new Coin(rectangleObject.getRectangle().getX(), rectangleObject.getRectangle().getY(), gameMap);
        }
        return null;

    }
}
