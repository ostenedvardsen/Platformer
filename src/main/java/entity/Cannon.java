package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Cannon extends ActiveEntity {

    Texture cannonImage;
    Texture cannonImage1;
    Texture cannonImage2;
    Texture cannonImage3;

    int bombtimer;
    int pointDirX;
    int pointDirY;

    boolean flip = false;

    public Cannon(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.CANNON, map, hp);
        cannonImage = new Texture("canon.png");
        cannonImage1 = new Texture("canon.png");
        cannonImage2 = new Texture("canonx.png");
        cannonImage3 = new Texture("canonu.png");

        attackDamage = 0;
        health = 10000;
        SPEED = 0;
        moveDir = 0;

        pointDirX = 1;
        pointDirY = 1;
    }

    @Override
    public void update(float deltaTime, float gravity){
        super.update(deltaTime, gravity);

        if(!map.getPlayers().isEmpty()) {
            Player closestPlayer = closestPlayer();

            if (closestPlayer.pos.x < this.pos.x - 25) {
                pointDirX = -1;
            } else if (closestPlayer.pos.x > this.pos.x + 25) {
                pointDirX = 1;
            } else {
                pointDirX = 0;
            }

            if (closestPlayer.pos.y < this.pos.y) {
                pointDirY = 1;
            } else if (closestPlayer.pos.y < this.pos.y + 25) {
                pointDirY = 2;
            } else {
                pointDirY = 3;
            }

            if (pointDirX == -1) {
                if (pointDirY == 1) {
                    cannonImage = cannonImage1;
                } else if (pointDirY == 2) {
                    cannonImage = cannonImage2;
                } else {
                    cannonImage = cannonImage2;
                }
            } else if (pointDirX == 1) {
                if (pointDirY == 1) {
                    cannonImage = cannonImage1;
                } else if (pointDirY == 2) {
                    cannonImage = cannonImage2;
                } else {
                    cannonImage = cannonImage2;
                }
            } else {
                cannonImage = cannonImage3;
            }

            if (bombtimer == 0) {
                float multiplier;
                if (pointDirY == 1) {
                    if (pointDirX == 0) {
                        multiplier = this.getHeight();
                    } else {
                        multiplier = 1;
                    }
                } else {
                    multiplier = this.getHeight();
                }

                Entity ent = new CannonBall(pos.x + this.getWidth() * pointDirX, pos.y + multiplier, map, 1, pointDirX, pointDirY);

                map.addEntity(ent);
                bombtimer += 150;
            } else {
                bombtimer -= 1;
            }

            flip = pointDirX == 1;
        }
    }

    protected Player closestPlayer(){
        float lowestPossibleY = this.pos.y - 32;
        float airDistance = 1000;
        int closestPlayerIndex = 0;

        for(Player player: map.getPlayers()){
            if (player.pos.y > lowestPossibleY){
                float airDistanceToPlayer = (float) (Math.sqrt(Math.pow((this.pos.x - player.pos.x), 2) + Math.pow((this.pos.y-player.pos.y),2)));
                if (airDistanceToPlayer < airDistance){
                    airDistance = airDistanceToPlayer;
                    closestPlayerIndex = map.getPlayers().indexOf(player);
                }
            }
        }

        return map.getPlayers().get(closestPlayerIndex);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(flip){
            Sprite sprite = new Sprite(cannonImage);
            sprite.flip(true, false);
            batch.draw(sprite, pos.x, pos.y, getWidth(), getHeight());
        } else{
            batch.draw(cannonImage, pos.x, pos.y, getWidth(), getHeight());
        }
    }

    @Override
    public void interact(Entity entity) {
    }

    @Override
    public void destroyedBy(Entity entity) {

    }
}
