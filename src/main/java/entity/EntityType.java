package entity;

public enum EntityType {
    PLAYER("player", 16, 32),
    GOAL("goal", 32, 32),
    SKELETON("skeleton", 16, 32),
    COIN("coin", 16, 32),
    FROG("frog", 16, 32),
    CANNON("cannon", 16, 32),
    CANNONBALL("cannonball", 16, 32),
    HEALTHPACK("healthpack", 32, 30);

    private final String id;
    private final int width;
    private final int height;


    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private EntityType(String id, int width, int heigth) {
        this.id = id;
        this.width = width;
        this.height = heigth;
    }
}
