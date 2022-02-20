package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import world.GameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    protected float velocityY = 0;
    protected GameMap map;

    public Entity(float x, float y, EntityType type, GameMap map){
        this.pos = new Vector2(x,y);
        this.type = type;
        this.map = map;
    }

    public void update (float deltaTime, float gravity){

        this.velocityY = this.velocityY - gravity * deltaTime;

        float newY = getY() + this.velocityY * deltaTime;

        if(map.doesEntityRectangleCollideWithTileOnAnyLayer(this.getX(), newY, this.getWidth(), this.getHeight())) {
            if(this.velocityY < 0){
                this.pos.y = (float) Math.floor(pos.y);
                //System.out.println("newY collides, with negative velocity.");
            }
            this.velocityY = 0;
        }
        else{
            this.pos.y = newY;
        }
    }

    protected void moveX(float xAmount){
        float newX = pos.x + xAmount;

        if (!map.doesEntityRectangleCollideWithTileOnAnyLayer(newX, pos.y, getWidth(), getHeight())){
            this.pos.x = newX;
        }
    }

    protected void moveY(float yAmount){
        this.pos.y = pos.y + yAmount;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public EntityType getType() {
        return type;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public GameMap getMap() {
        return map;
    }

    public int getWidth(){
        return type.getWidth();
    }

    public int getHeight(){
        return type.getWidth();
    }

    public abstract void render (SpriteBatch batch);
}
