package entity;

import com.badlogic.gdx.graphics.Texture;
import world.GameMap;

public class Healthpack extends Entity {

	public Healthpack(float x, float y, GameMap map) {
		super(x, y, EntityType.HEALTHPACK, map, 1);
		entityTexture = new Texture("healthpack.png");
		collidable = false;
		removeOnPlayerInteraction = true;
	}


	@Override
	public void interact(Entity entity) {
		if (entity instanceof Player) {
			entity.health = entity.getHealth() + 10;
		}
	}

	@Override
	public void destroyedBy(Entity player) {

	}
}
