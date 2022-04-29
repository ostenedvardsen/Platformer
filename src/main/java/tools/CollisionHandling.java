package tools;
import entity.ActiveEntity;
import entity.Entity;
import entity.Player;
import world.GameMap;

import java.util.ArrayList;

public class CollisionHandling {

    GameMap map;

    public CollisionHandling(GameMap gameMap){
        map = gameMap;
    }

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

    public Entity lowestYEntity(ArrayList<Entity> entities){
        int lowestYIndex = 0;
        int lowestYValue = 0;

        for(Entity entity: entities){
            if(entity.getY() + entity.getHeight() > lowestYValue){
                lowestYIndex = entities.indexOf(entity);
            }
        }

        return entities.get(lowestYIndex);
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

    public boolean entityCollisionX(Entity entity, CollisionRect newPosition, boolean attacksSideways){
        ArrayList<Entity> collidedPlayers = map.rectangleCollidesWithPlayers(newPosition);
        ArrayList<Entity> collidedActiveEntities = map.rectangleCollidesWithEntities(newPosition);

        if(entity instanceof Player){
            collidedPlayers.remove(entity);
        } else{
            collidedActiveEntities.remove(entity);
        }

        ArrayList<Entity> allCollidedActiveEntities = new ArrayList<>();
        allCollidedActiveEntities.addAll(collidedPlayers);
        allCollidedActiveEntities.addAll(collidedActiveEntities);

        if(!map.doesEntityRectangleCollideWithTileOnAnyLayer(newPosition.x, newPosition.y, newPosition.width, newPosition.height)){
            if(allCollidedActiveEntities.isEmpty()) {
                return false;
            }

            else {
                if(this.entityFromTheLeft(entity, allCollidedActiveEntities)){
                    entity.pos.x = this.lowestXEntity(allCollidedActiveEntities).getX() - entity.getWidth() - 0.01f;
                    if (attacksSideways){
                        for(Entity entityItem: collidedActiveEntities){
                            map.interactEntities(entity, entityItem);
                        }
                    } else {
                        for (Entity entityItem: collidedActiveEntities){
                            map.interactEntities(entityItem, entity);
                        }
                    }
                }

                else if (this.entityFromTheRight(entity, allCollidedActiveEntities)) {
                    entity.pos.x = this.highestXEntity(allCollidedActiveEntities).getX() + this.highestXEntity(allCollidedActiveEntities).getWidth() + 0.01f;
                    if (attacksSideways){
                        for(Entity entityItem: allCollidedActiveEntities){
                            map.interactEntities(entity, entityItem);
                        }
                    } else {
                        for (Entity entityItem: allCollidedActiveEntities){
                            map.interactEntities(entityItem, entity);
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean entityCollisionY(Entity entity, CollisionRect newPosition) {
        if (map.doesEntityRectangleCollideWithTileOnAnyLayer(newPosition.x, newPosition.y, newPosition.width, newPosition.height)) {
            return false;
        } else {
            CollisionRect nextY;
            CollisionRect approximateY;
            ArrayList<Entity> collidedPlayersNextY;
            ArrayList<Entity> collidedActiveEntitiesNextY;
            ArrayList<Entity> collidedPlayersApproximateY;
            ArrayList<Entity> collidedActiveEntitiesApproximateY;


            if (entity.velocityY < 0) {
                nextY = new CollisionRect(entity.pos.x, newPosition.y - 0.01f, entity.getWidth(), entity.getHeight());
                approximateY = new CollisionRect(entity.pos.x, entity.pos.y- 0.01f, entity.getWidth(), entity.getHeight());
            } else if (entity.velocityY > 0) {
                nextY = new CollisionRect(entity.pos.x, newPosition.y + entity.getHeight()*2 + 0.01f, entity.getWidth(), entity.getHeight());
                approximateY = new CollisionRect(entity.pos.x, entity.pos.y + entity.getHeight()*2 + 0.01f, entity.getWidth(), entity.getHeight());
            } else {
                return true;
            }

            collidedPlayersNextY = map.rectangleCollidesWithPlayers(nextY);
            collidedActiveEntitiesNextY = map.rectangleCollidesWithNonPlayerActiveEntities(nextY);

            collidedPlayersApproximateY = map.rectangleCollidesWithPlayers(approximateY);
            collidedActiveEntitiesApproximateY = map.rectangleCollidesWithNonPlayerActiveEntities(approximateY);

            if (entity instanceof Player) {
                collidedPlayersNextY.remove(entity);
                collidedPlayersApproximateY.remove(entity);
            } else {
                collidedActiveEntitiesNextY.remove(entity);
                collidedActiveEntitiesApproximateY.remove(entity);
            }

            ArrayList<Entity> allCollidedActiveEntities = new ArrayList<>();

            allCollidedActiveEntities.addAll(collidedPlayersNextY);
            allCollidedActiveEntities.addAll(collidedActiveEntitiesNextY);

            for (Entity entityItem : collidedActiveEntitiesApproximateY) {
                if (!allCollidedActiveEntities.contains(entityItem)) {
                    allCollidedActiveEntities.add(entityItem);
                }
            }

            for (Entity entityItem : collidedPlayersApproximateY) {
                if (!allCollidedActiveEntities.contains(entityItem)) {
                    allCollidedActiveEntities.add(entityItem);
                }
            }

            if (entity.velocityY < 0) {
                if (allCollidedActiveEntities.isEmpty()) {
                    entity.pos.y = newPosition.y;
                }
                else{
                    Entity tallestCollidingEntity = highestYEntity(allCollidedActiveEntities);
                    entity.pos.y = tallestCollidingEntity.getY() + tallestCollidingEntity.getHeight() + 0.1f;

                    for (Entity attackEntity : allCollidedActiveEntities) {
                        map.damageAndKillEntity(attackEntity, ((ActiveEntity) entity).attackDamage, entity);
                    }

                    entity.flipVelocityY();

                    if (entity.velocityY < 150){
                        entity.velocityY += 150;
                    } else if(entity.velocityY < 250){
                        entity.velocityY += 100;
                    } else if (entity.velocityY < 350){
                        entity.velocityY += 50;
                    }
                }
            }

            else if(entity.velocityY > 0) {
                if (allCollidedActiveEntities.isEmpty()) {
                    entity.pos.y = newPosition.y;
                }

                else if(!collidedPlayersApproximateY.isEmpty() || !collidedActiveEntitiesApproximateY.isEmpty()){
                    entity.pos.y = entity.pos.y;
                    entity.velocityY = 0;

                }
                else if (!collidedPlayersNextY.isEmpty() || !collidedActiveEntitiesNextY.isEmpty()){
                    float lowestCollidingEntityY = lowestYEntity(allCollidedActiveEntities).getY() - entity.getHeight() - 0.1f;

                    if (!map.doesEntityRectangleCollideWithTileOnAnyLayer(entity.getX(), lowestCollidingEntityY, entity.getWidth(), entity.getHeight())) {
                        entity.pos.y = lowestCollidingEntityY;
                    }
                    else{
                        entity.pos.y = (float) Math.floor(entity.pos.y);
                    }

                    for (Entity attackEntity : allCollidedActiveEntities) {
                        //map.damageAndKillEntity(entity, ((ActiveEntity) attackEntity).attackDamage, attackEntity);
                    }
                    entity.velocityY = 0;
                }
            }
        }
        return true;
    }

}
