package inf112.platformer.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("platformer");
        cfg.setWindowedMode(1280, 720);

        new Lwjgl3Application(new PlatformerGame(), cfg);
    }
}
