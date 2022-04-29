package world;

import java.util.HashMap;

public enum TileType {
    GRASS(1, true, "Grass"),
    STONE(2, true, "Stone"),
	DIRT(3, true, "Dirt"),
	SPIKE(4, true, "Spike", 5),
	HOLE(5, false, "Hole", 999),
	LAVA(6, true, "Lava", 10),
	PLATFORM(7, true, "Platform");

    public static final int TILE_SIZE = 32;


    private final int id;
    private final boolean collidable;
    private final String name;
    private final int damage;

    private TileType (int id, boolean collidable, String name){
        this(id, collidable, name, 0);
    }

    private TileType (int id, boolean collidable, String name, int damage){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    private static final HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<>();
        for(TileType tileType: TileType.values()){
            assert tileMap != null;
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById (int id){
        return tileMap.get(id);
    }
}
