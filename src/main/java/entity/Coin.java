package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tools.CollisionRect;
import world.GameMap;

public class Coin extends Entity {

	Texture coinImage;
	
	public Coin(float x, float y, GameMap map) {
		super(x, y, EntityType.COIN, map);
		coinImage = new Texture("coin.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(coinImage, pos.x,pos.y,getWidth(), getHeight());
	}

	
}
