package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Coin extends Entity {

	public Coin(float x, float y, GameMap map) {
		super(x, y, EntityType.COIN, map, 1);
		entityTexture = new Texture("coin.png");
		collidable = false;
		removeOnPlayerInteraction = true;
	}


	@Override
	public void interact(Entity entity) {
		if (entity instanceof Player) {
			((Player) entity).addScore(1000);
		}
	}

	@Override
	public void destroyedBy(Entity player) {

	}
}
