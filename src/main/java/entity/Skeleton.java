package entity;

import com.badlogic.gdx.graphics.Texture;
import tools.CollisionRect;
import world.GameMap;

public class Skeleton extends ActiveEntity {

	public Skeleton(float x, float y, GameMap map, int hp) {
		super(x, y, EntityType.SKELETON, map, hp);
		entityTexture = new Texture("skeleton.png");
		attackDamage = 5;
		health = 15;
		collidable = true;
		SPEED = 25;
		moveDir = 1;

		velocityX = 1;
	}

	@Override
	protected void moveX(float xAmount){
		float newX = pos.x + xAmount;

		CollisionRect nextRect = new CollisionRect(newX, pos.y, getWidth(), getHeight());

		boolean walkingOffCliff = !map.doesEntityRectangleCollideWithTileOnAnyLayer(pos.x-(getWidth()*moveDir), pos.y-getHeight(), getWidth(), getHeight());

		if(!map.collisionHandling.entityCollisionX(this, nextRect, true)){
			if(!walkingOffCliff){
				this.pos.x = newX;
			} else{
				moveDir = -moveDir;
			}
		} else{
			moveDir = -moveDir;
		}

		rect.move(this.pos.x, this.pos.y);

		if (moveDir == 1){
			flip = true;
		} else{
			flip = false;
		}
	}

	@Override
	public void update(float deltaTime, float gravity) {
		if (deltaTime > 0.05f) deltaTime = 0.05f;

		moveX(-SPEED*deltaTime*moveDir);
		super.moveY(deltaTime, gravity);

		if (pos.y < 0) {
			this.health = 0;
		}

		if(gracePeriod <= 0){
			gracePeriod = 0;
		} else{
			gracePeriod = gracePeriod - deltaTime;
		}

	}


	@Override
	public void interact(Entity entity) {
		if (entity instanceof Player) {
			map.damageAndKillEntity(entity, attackDamage, this);
			((Player) entity).removeScore(300);
		}
	}

	@Override
	public void destroyedBy(Entity entity){
		if (entity instanceof Player){
			((Player) entity).addScore(1000);
		}
	}

}
