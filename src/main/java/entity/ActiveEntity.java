package entity;

import world.GameMap;

public abstract class ActiveEntity extends Entity {

    public ActiveEntity(float x, float y, EntityType type, GameMap map, int hp) {
        super(x, y, type, map, hp);
    }

    public void update (float deltaTime, float gravity){
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        this.velocityY = this.velocityY - gravity * deltaTime;

        float newY = getY() + this.velocityY * deltaTime;

        if(map.doesEntityRectangleCollideWithTileOnAnyLayer(this.getX(), newY, this.getWidth(), this.getHeight())) {
            if(this.velocityY < 0){
                this.pos.y = (float) Math.floor(pos.y);
            }
            this.velocityY = 0;
        }
        else{
            this.pos.y = newY;
        }
    }


}
