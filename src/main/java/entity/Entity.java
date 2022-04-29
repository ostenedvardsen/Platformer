package entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import tools.CollisionRect;
import world.GameMap;

public abstract class Entity {
    public Vector2 pos;
    public EntityType type;
    public float velocityY = 0;
    public float velocityX = 0;
    protected GameMap map;
    protected CollisionRect rect;
    protected int health;
    protected Boolean removeOnPlayerInteraction = false;
    protected Boolean collidable = true;


    public Entity(float x, float y, EntityType type, GameMap map, int hp){
        this.pos = new Vector2(x,y);
        this.type = type;
        this.map = map;
        this.rect = new CollisionRect(x, y, type.getWidth(), type.getHeight());
        this.health = hp;
    }

    public void update(float deltaTime, float gravity) { }

    protected void moveX(float xAmount){
        float newX = pos.x + xAmount;

        if (!map.doesEntityRectangleCollideWithTileOnAnyLayer(newX, pos.y, getWidth(), getHeight())){
            this.pos.x = newX;
            rect.move(this.pos.x, this.pos.y);
        }
    }

    public boolean isDead() { return this.health <= 0; }

    public int getHealth(){
        return health;
    }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public void damage(int amount) { health-=amount; }

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

    public abstract void interact(Entity entity);

    public void flipVelocityY(){
        velocityY = -velocityY;
    }

    public abstract void destroyedBy(Entity player);

    public Boolean getCollidable(){
        return collidable;
    }
}
