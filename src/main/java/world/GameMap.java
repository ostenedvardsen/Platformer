package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.Entity;
import entity.EntityType;
import entity.Player;

import java.util.ArrayList;

public abstract class GameMap {
    protected ArrayList<Entity> entities;

    public GameMap(){
        entities = new ArrayList<Entity>();
        entities.add(new Player(50, 300, this));
    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        for (Entity entity: entities){
            entity.render(batch);
        }
    }

    public void update (float delta){
        for (Entity entity: entities){
            entity.update(delta, -9.8f);
        }
    }

    public abstract void dispose ();

    /**
     * Gets a tile by pixel position on the game world at a specified layer.
     * @param layer
     * @param x
     * @param y
     * @return
     */
    public TileType getTileTypeByLocation(int layer, float x, float y){
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

}
