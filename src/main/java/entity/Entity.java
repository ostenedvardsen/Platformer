package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import tools.CollisionRect;
import world.GameMap;

public abstract class Entity {
    protected Vector2 pos;
    protected EntityType type;
    protected float velocityY = 0;
    protected GameMap map;
    protected CollisionRect rect;
    protected Boolean gravityAffected = false;
    protected Boolean removeOnPlayerInteraction = false;


    public Entity(float x, float y, EntityType type, GameMap map){
        this.pos = new Vector2(x,y);
        this.type = type;
        this.map = map;
        this.rect = new CollisionRect(x, y, type.getWidth(), type.getHeight());
    }

    public void update(float deltaTime, float gravity) { }

    protected void moveX(float xAmount){
        float newX = pos.x + xAmount;

        if (!map.doesEntityRectangleCollideWithTileOnAnyLayer(newX, pos.y, getWidth(), getHeight())){
            this.pos.x = newX;
            rect.move(this.pos.x, this.pos.y);
        }
    }

    protected void moveY(float yAmount){
        this.pos.y = pos.y + yAmount;
        rect.move(this.pos.x, this.pos.y);
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

    public CollisionRect getCollisionRect () {
    	return rect;
    }

    public abstract void render (SpriteBatch batch);

    public Boolean removeOnPlayerInteraction() { return removeOnPlayerInteraction; }

    public abstract void playerInteract(Player player);

}
