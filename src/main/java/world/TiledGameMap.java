package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import entity.Entity;
import entity.EntityFactory;
import entity.Player;

public class TiledGameMap extends GameMap {
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    int PlayerNumber = 4;

    public TiledGameMap(){
        tiledMap = new TmxMapLoader().load("stage1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        AddEntities();
    }

    private void AddEntities(){
        EntityFactory entityFactory = new EntityFactory();
        for (MapLayer layer : tiledMap.getLayers()){
            if (layer instanceof TiledMapTileLayer) continue;

            String name = layer.getName();
            for (MapObject object : layer.getObjects()){

                if (object instanceof RectangleMapObject rectangleObject) {

                    Entity newEntity = entityFactory.getEntity(rectangleObject, name, this);
                    if(newEntity != null){
                        if (newEntity instanceof Player) { 
                        	if (PlayerNumber > 0) { 
                        		Player newPlayer = (Player) newEntity; 
                        		newPlayer.setId(PlayerNumber);
                        		players.add(newPlayer); 
                        		PlayerNumber--; 
                        	}
                        			
                        } else { entities.add(newEntity); }
                    }
                }
            }
        }
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.render(camera, batch);
        batch.end();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void dispose() {

    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {
        if (!(tiledMap.getLayers().get(layer) instanceof TiledMapTileLayer)) return null;

        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col,row);

        if(cell != null) {
            TiledMapTile tile = cell.getTile();

            if(tile != null){
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }
        return null;
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }
}
