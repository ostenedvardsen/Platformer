package tools;
import entity.Entity;

import java.util.ArrayList;

public class CollisionHandling {

    public Entity lowestXEntity(ArrayList<Entity> entities) {
        int furthestLeftIndex = 0;
        int furthestLeftValue = 0;

        for(Entity entity: entities){
            if(entity.getX() > furthestLeftValue){
                furthestLeftIndex = entities.indexOf(entity);
            }
        }

        return entities.get(furthestLeftIndex);
    }

    public Entity highestXEntity(ArrayList<Entity> entities) {
        int furthestRightIndex = 0;
        int furthestRightValue = 0;

        for(Entity entity: entities){
            if(entity.getX() + entity.getWidth() > furthestRightValue){
                furthestRightIndex = entities.indexOf(entity);
            }
        }

        return entities.get(furthestRightIndex);
    }

    public Entity highestYEntity(ArrayList<Entity> entities){
        int highestYIndex = 0;
        int highestYValue = 0;

        for(Entity entity: entities){
            if(entity.getY() + entity.getHeight() > highestYValue){
                highestYIndex = entities.indexOf(entity);
            }
        }

        return entities.get(highestYIndex);

    }

    public boolean entityFromTheLeft(Entity oldPositionEntity1, ArrayList<Entity> entities){
        float lowestX = lowestXEntity(entities).getX();

        return oldPositionEntity1.getX() + oldPositionEntity1.getWidth() < lowestX;
    }

    public boolean entityFromTheRight(Entity oldPositionEntity1, ArrayList<Entity> entities){
        float highestX = highestXEntity(entities).getX() + highestXEntity(entities).getWidth();

        return oldPositionEntity1.getX() > highestX;
    }

    public boolean entityFromWithin(Entity oldPositionEntity1, ArrayList<Entity> entities){
        return entityFromTheLeft(oldPositionEntity1, entities) && entityFromTheRight(oldPositionEntity1, entities);
    }

}
