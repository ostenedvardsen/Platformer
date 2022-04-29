package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entity.ActiveEntity;
import entity.Entity;
import entity.Goal;
import entity.Player;
import tools.CollisionHandling;
import tools.CollisionRect;

import java.util.ArrayList;

public abstract class GameMap {
    protected ArrayList<Entity> entities;
    protected ArrayList<Player> players;
    protected ArrayList<Entity> entityQueue;

    protected ArrayList<Integer> playerScores;
    public CollisionHandling collisionHandling;

    public GameMap(){
        entities = new ArrayList<>();
        players = new ArrayList<>();
        entityQueue = new ArrayList<>();

        playerScores = new ArrayList<>();
        collisionHandling = new CollisionHandling(this);
    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        for(Player player: players){
            player.render(batch);}
        for (Entity entity: entities) {
            entity.render(batch);}
    }

    public void update (float delta){
        for (Player player: players){
            player.update(delta, 680f);
        }
        removeDead();

        for (Entity entity: entities){
            entity.update(delta, 400f);
        }

        removeDead();
        checkCollisions();
        addEntities();
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

    public ArrayList<Entity> rectangleCollidesWithPlayers(CollisionRect collisionRect){
        ArrayList<Entity> collidedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (collisionRect.collidesWith(player.getCollisionRect())) {
                collidedPlayers.add(player);
            }
        }
        return collidedPlayers;
    }

    public ArrayList<Entity> rectangleCollidesWithNonPlayerActiveEntities(CollisionRect collisionRect){
        ArrayList<Entity> collidedActiveEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof ActiveEntity){
                if (collisionRect.collidesWith(entity.getCollisionRect())) {
                    if(entity.getCollidable()){
                        collidedActiveEntities.add(entity);
                    }
                }
            }
        }
        return collidedActiveEntities;

    }

    public ArrayList<Entity> rectangleCollidesWithEntities(CollisionRect collisionRect){
        ArrayList<Entity> collidedEntities = new ArrayList<>();
        for (Entity entity : entities) {
            if (collisionRect.collidesWith(entity.getCollisionRect())) {
                if(entity.getCollidable()){
                    collidedEntities.add(entity);
                }
            }
        }
        return collidedEntities;
    }

    public ArrayList<Entity> collidedUncollidableEntities(Entity entity){
        ArrayList<Entity> collidedEntities = new ArrayList<>();
        for (Entity ent : entities) {
            if (entity.getCollisionRect().collidesWith(ent.getCollisionRect()) && !ent.getCollidable()) {
                collidedEntities.add(ent);
            }
        }
        return collidedEntities;
    }

    public boolean doesEntityRectangleCollideWithTileOnAnyLayer(float x, float y, int width, int height){
        int firstRow =  (int) (y / TileType.TILE_SIZE);
        double lastRow = (Math.ceil((y + width) / TileType.TILE_SIZE));

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
        for (Player player : players) {
            for (Entity entity : this.collidedUncollidableEntities(player)) {
                if (player.getCollisionRect().collidesWith(entity.getCollisionRect())) {
                    entity.interact(player);
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

    public void addEntity(Entity entity){
        entityQueue.add(entity);
    }

    public void addEntities(){
        entities.addAll(entityQueue);
        entityQueue.clear();
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        if (entity instanceof Player){
            players.remove(entity);
        }
        for (Entity e : entities) { if (e instanceof Goal) return; }
        loadNextMap();
    }

    public abstract void loadNextMap();
    public abstract void reset();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public void interactEntities(Entity interacter, Entity interactedWith){
        interacter.interact(interactedWith);
    }

    public void damageAndKillEntity(Entity defender, int damage, Entity attacker) {
        if(defender.getHealth() > 0){
            defender.damage(damage);
            if(defender.getHealth() <= 0){
                defender.destroyedBy(attacker);
            }
        }
    }

}
