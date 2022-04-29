package entity;

import com.badlogic.gdx.graphics.Texture;
import tools.CollisionRect;
import world.GameMap;

import java.util.ArrayList;

public class CannonBall extends ActiveEntity {

    public CannonBall(float x, float y, GameMap map, int hp){
        super(x, y, EntityType.CANNONBALL, map, hp);
    }

    public CannonBall(float x, float y, GameMap map, int hp, int dirX, int dirY){
        super(x, y, EntityType.CANNONBALL, map, hp);
        entityTexture = new Texture("canonball.png");

        attackDamage = 5;
        health = 1;
        SPEED = 100;
        this.velocityX = 250*dirX;

        if (dirX == 0){
            this.velocityY = 250;
        } else if (dirY == 1){
            this.velocityY = 50;
        } else if(dirY == 2){
            this.velocityY = 100;
        } else if(dirY == 3){
            this.velocityY = 150;
        }
    }


    @Override
    public void update(float deltaTime, float gravity){
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        moveY(deltaTime,gravity);
        moveX(velocityX*deltaTime);

        this.rect.move(this.pos.x, this.pos.y);

        if (moveDir == 1){
            flip = true;
        } else{
            flip = false;
        }
    }

    @Override
    protected void moveX(float xAmount){
        float newX = pos.x + xAmount;

        if(map.doesEntityRectangleCollideWithTileOnAnyLayer(this.pos.x, this.pos.y, getWidth(), getHeight())){
            this.health = 0;
        } else{
            this.pos.x = newX;
        }

        rect.move(this.pos.x, this.pos.y);
    }

    @Override
    protected void moveY(float deltaTime, float gravity) {
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        this.velocityY = this.velocityY - gravity * deltaTime;
        float newY = getY() + this.velocityY * deltaTime;

        CollisionRect underRect = new CollisionRect(this.pos.x,this.pos.y + this.getHeight(), this.getWidth(), getHeight());
        ArrayList<Entity> collidedPlayers = map.rectangleCollidesWithPlayers(underRect);

        if (map.doesEntityRectangleCollideWithTileOnAnyLayer(pos.x, pos.y-0.01f, getWidth(), getHeight())){
            this.health = 0;
        }
        else if (!collidedPlayers.isEmpty()) {
            for (Entity player : collidedPlayers) {
                map.damageAndKillEntity(player, attackDamage, this);
            }
            this.health = 0;
        }

        if (pos.y < 0) {
            this.health = 0;
        }

        this.pos.y = newY;
        this.rect.move(this.pos.x, newY);
    }


    @Override
    public void interact(Entity entity) {
        if (entity instanceof Player) {
            map.damageAndKillEntity(entity, attackDamage, this);
            this.health = 0;
        }
    }

    @Override
    public void destroyedBy(Entity entity){
        if (entity instanceof Player){
            ((Player) entity).addScore(1000);
        }
    }
}
