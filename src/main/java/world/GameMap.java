package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entity.Entity;
import entity.Goal;
import entity.Player;

import java.util.ArrayList;

public abstract class GameMap {
    protected ArrayList<Entity> entities;
    protected ArrayList<Player> players;

    public GameMap(){
        entities = new ArrayList<Entity>();
        players = new ArrayList<Player>();
    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        for (Entity entity: entities){
            entity.render(batch);
        }
        for(Player player: players){
            player.render(batch);
        }

    }

    public void update (float delta){
        for (Entity entity: entities){
            entity.update(delta, 400f);
        }
        for (Player player: players){
            player.update(delta, 680f);
        }

        removeDead();
        checkCollisions();
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
    
    public void removeDead() {
    	ArrayList<Entity> dead = new ArrayList<>();
    	for (Entity entity : entities) {
    		if (entity.isDead())
    			dead.add(entity);
    	}
    	for (Player player : players) {
    		if (player.isDead()) {
    			dead.add(player);
    		}
    	}
    	if (!dead.isEmpty()) {
    		for (Entity entity : dead)
    			removeEntity(entity);
    	}
    }

    public void checkCollisions() {
        ArrayList<Entity> removeObj = new ArrayList<>();
        for (Entity entity : entities) {
            for (Player player : players) {
                if (player.getCollisionRect().collidesWith(entity.getCollisionRect())) {
                    entity.playerInteract(player);
                    if (entity.removeOnPlayerInteraction()) {
                        removeObj.add(entity);
                    }
                }
            }
        }
        if (!removeObj.isEmpty()) {
            for (Entity entity : removeObj) {
                removeEntity(entity);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void removeEntity(Entity entity) {
    	if (entities.contains(entity)) {
    		entities.remove(entity);
    	}
    	players.remove(entity);
        if (players.isEmpty()) reset();
        for (Entity e : entities) { if (e instanceof Goal) return; }
        loadNextMap();
    }

    public abstract void loadNextMap();
    public abstract void reset();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

}
