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
        float dY = this.velocityY * deltaTime;
        moveY(dY);
    }

    protected void moveX(float xAmount){
        this.pos.x = pos.x + xAmount;
    }

    protected void moveY(float yAmount){
        this.pos.y = pos.y + yAmount;
    }

    public boolean rectTileCollision(float x, float y, int width, int height){
        return false;
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

    public float getWidth(){
        return type.getWidth();
    }

    public int getHeight(){
        return type.getWidth();
    }

    public abstract void render (SpriteBatch batch);
}
