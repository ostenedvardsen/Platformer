package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entity.Coin;
import entity.Entity;
import entity.EntityType;
import entity.Goal;
import entity.Player;
import inf112.platformer.app.Game;
import scenes.Hud;

import java.util.ArrayList;

public abstract class GameMap {
    protected ArrayList<Entity> entities;
    protected ArrayList<Coin> coins;
    protected Player player;
    
    public GameMap(){
        entities = new ArrayList<Entity>();
        coins = new ArrayList<Coin>();
        player = new Player(50, 300, this);
        entities.add(player);
    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        for (Entity entity: entities){
            entity.render(batch);
        }
        
        ArrayList<Coin> removeCoin = new ArrayList<Coin>();
        for (Coin c : coins) {
        	if (player.getCollisionRect().collidesWith(c.getCollisionRect())) {
        		removeCoin.add(c);
        	}
        }
        coins.removeAll(removeCoin);
        Hud.addScore(removeCoin.size()*100);
        for (Coin c : coins) {
        	c.render(batch);
    	}
    }

    public void update (float delta){
        for (Entity entity: entities){
            entity.update(delta, 100f);
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

    public boolean doesEntityRectangleCollideWithTileOnAnyLayer(float x, float y, int width, int height){
        // Add some checks to see if the position is valid?

        int firstRow =  (int) (y / TileType.TILE_SIZE);
        double lastRow = (Math.ceil((y + height) / TileType.TILE_SIZE));

        int firstCol =  (int) (x / TileType.TILE_SIZE);
        double lastCol = (Math.ceil((x + height) / TileType.TILE_SIZE));

        for(int row = firstRow; row < lastRow; row++){
            for (int col = firstCol; col < lastCol; col++){
                for(int layer = 0; layer < getLayers(); layer++){
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if(type != null && type.isCollidable()){
                        return true;
                    }
                }
            }
        }

        return false;
    }



    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

}
