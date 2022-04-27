package entity;

import tools.CollisionRect;
import world.GameMap;

public abstract class ActiveEntity extends Entity {
    public int attackDamage;
    public int moveDir;
    public int SPEED;
    public float gracePeriod;
    public float gracePeriodIdentifier;

    public ActiveEntity(float x, float y, EntityType type, GameMap map, int hp) {
        super(x, y, type, map, hp);
    }

    public void update (float deltaTime, float gravity){
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        this.moveX(SPEED*deltaTime*moveDir);
        this.moveY(deltaTime, gravity);

        this.rect.move(this.pos.x, this.pos.y);

        if(gracePeriod <= 0){
            gracePeriod = 0;
        } else{
            gracePeriod = gracePeriod - deltaTime;
        }
    }

    protected void moveY(float deltaTime, float gravity){
        if (deltaTime > 0.05f) deltaTime = 0.05f;

        this.velocityY = this.velocityY - gravity * deltaTime;
        float newY = getY() + this.velocityY * deltaTime;

        CollisionRect nextRect = new CollisionRect(pos.x, newY, getWidth(), getHeight());

        if(!map.collisionHandling.entityCollisionY(this, nextRect)){
            if(this.velocityY < 0){
                this.pos.y = (int) Math.floor(pos.y);
            }
            this.velocityY = 0;

            if(map.getTileTypeByLocation(0, this.getX(), newY) != null){
                this.damage(map.getTileTypeByLocation(0, this.getX(), newY).getDamage());
            }
        }

        if (pos.y < 0) {
            this.health = 0;
        }

        this.rect.move(this.pos.x, this.pos.y);
    }

    protected void moveX(float xAmount) {
        float newX = pos.x + xAmount;

        CollisionRect nextRect = new CollisionRect(newX, pos.y, getWidth(), getHeight());

        if(!map.collisionHandling.entityCollisionX(this, nextRect, false)){
            this.pos.x = newX;
        }
        else{
            moveDir = -moveDir;
        }

        rect.move(this.pos.x, this.pos.y);
    }

    @Override
    public void damage(int amount){
        if (gracePeriod <= 0 && amount > 0){
            health -= amount;
            gracePeriod += gracePeriodIdentifier;
        }
    }
    public int getAttackDamage(){
        return attackDamage;
    }

    public abstract void destroyedBy(Entity entity);
}
