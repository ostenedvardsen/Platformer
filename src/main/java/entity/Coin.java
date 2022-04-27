package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import world.GameMap;

public class Coin extends Entity {

	Texture coinImage;

	public Coin(float x, float y, GameMap map) {
		super(x, y, EntityType.COIN, map, 1);
		coinImage = new Texture("coin.png");
		collidable = false;
		removeOnPlayerInteraction = true;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(coinImage, pos.x,pos.y,getWidth(), getHeight());
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
