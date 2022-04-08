package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Skeleton extends ActiveEntity {
	
    private static final int SPEED = 25;
	
	Texture skeletonImage;
	
	public Skeleton(float x, float y, GameMap map, int hp) {
        super(x, y, EntityType.SKELETON, map, hp);
        skeletonImage = new Texture("skeleton.png");
        
        health = 1;
	}

	@Override
	public void render(SpriteBatch batch) {
        batch.draw(skeletonImage, pos.x, pos.y, getWidth(), getHeight());
	}
	
	@Override
	public void update(float deltaTime, float gravity) {
		super.update(deltaTime, gravity);
		moveX(-SPEED * deltaTime);
	}

	@Override
	public void playerInteract(Player player) {
		player.removeScore(300);
		player.damage(1);
	}
}
