package entity;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import world.TiledGameMap;

public class EntityFactory {
    public Entity getEntity(RectangleMapObject rectangleObject, String name, TiledGameMap tiledGameMap){
        if(name == null){
            return null;
        }
        float xPos = rectangleObject.getRectangle().getX();
        float yPos = rectangleObject.getRectangle().getY();

        if(name.equalsIgnoreCase("Skeleton")) {
            return new Skeleton(xPos, yPos, tiledGameMap, 100);
        }
        if(name.equalsIgnoreCase("Goal")) {
            return new Goal(xPos, yPos, tiledGameMap);
        }
        if(name.equalsIgnoreCase("Coin")) {
            return new Coin(xPos, yPos, tiledGameMap);
        }
        if(name.equalsIgnoreCase("Player")) {
            return new Player(xPos, yPos, tiledGameMap, 500);
        }
        if(name.equalsIgnoreCase("Frog")) {
            return new Frog(xPos, yPos, tiledGameMap, 500);
        }
        return null;

    }
}
