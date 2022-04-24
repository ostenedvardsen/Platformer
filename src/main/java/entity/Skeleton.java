package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Skeleton extends ActiveEntity {

    private int SPEED = 25;
	private int moveDir = 1;
	
	Texture skeletonImage;
	
	public Skeleton(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.SKELETON, map, hp);
        skeletonImage = new Texture("skeleton.png");
        
        health = 1;
	}

	@Override
	protected void moveX(float xAmount){
		float newX = pos.x + xAmount;

		boolean collidesAhead = map.doesEntityRectangleCollideWithTileOnAnyLayer(newX, pos.y, getWidth(), getHeight());
		boolean walkingOffCliff = !map.doesEntityRectangleCollideWithTileOnAnyLayer(pos.x-(getWidth()*moveDir), pos.y-getHeight(), getWidth(), getHeight());

		if (!collidesAhead){
			this.pos.x = newX;
			rect.move(this.pos.x, this.pos.y);
			if (!walkingOffCliff) return;
		}
		moveDir=-moveDir;
	}


	@Override
	public void render(SpriteBatch batch) {
        batch.draw(skeletonImage, pos.x, pos.y, getWidth(), getHeight());
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		super.update(deltaTime, gravity);
		moveX(-SPEED * deltaTime * moveDir);
	}

	@Override
	public void playerInteract(Player player) {
		map.damageAndKillEntity(player, attackDamage, this);
	}

	@Override
	public void destroyedBy(Entity entity){
		if (entity instanceof Player){
			((Player) entity).addScore(1000);
		}
	}
}
