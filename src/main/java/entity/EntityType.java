package entity;

public enum EntityType {
    // Spillerhøyden er nå like høy som en tile. Burde kanskje være litt lavere, slik at spilleren kan snike seg gjennom litt sprekker.
    PLAYER("player", 16, 32),
    GOAL("goal", 32, 32),
	SKELETON("skeleton", 16, 32);

    private String id;
    private int width, height;


    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    //Add health in entity type?
    private EntityType(String id, int width, int heigth) {
        this.id = id;
        this.width = width;
        this.height = heigth;
    }
}
