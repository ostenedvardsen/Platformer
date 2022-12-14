package entity;

import com.badlogic.gdx.graphics.Texture;
import world.GameMap;


public class Frog extends ActiveEntity {

    public Frog(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.FROG, map, hp);
        entityTexture = new Texture("frog.png");
        attackDamage = 5;
        health = 10;
        SPEED = 25;
        moveDir = 1;
    }

    @Override
    public void interact(Entity entity) {
        if (entity instanceof Player) {
            map.damageAndKillEntity(entity, attackDamage, this);
        }
    }


    @Override
    public void update(float deltaTime, float gravity) {
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        boolean jumpingOffMap = !map.doesEntityRectangleCollideWithTileOnAnyLayer(pos.x + 40*moveDir, pos.y-getHeight(), getWidth(), getHeight());
        if (map.doesEntityRectangleCollideWithTileOnAnyLayer(pos.x, pos.y-0.01f, getWidth(), getHeight())){
            if (!jumpingOffMap){
                velocityY += 250;
            }
            else{
                moveDir = -moveDir;
            }
        }

        super.update(deltaTime, gravity);
    }

    @Override
    public void destroyedBy(Entity entity){
        if (entity instanceof Player){
            ((Player) entity).addScore(2000);
        }
    }

}
