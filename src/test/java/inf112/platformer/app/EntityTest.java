package inf112.platformer.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import entity.*;
import world.GameMap;
import world.TiledGameMap;

import static org.mockito.Mockito.*;

public class EntityTest {

	private Player player;
	private Player player2;
	private Skeleton skeleton;
	private Coin coin;
	private Goal goal;

	/**
	 * Will essentially run a game instance, while we do our tests,
	 * this is to prevent libgdx from throwing nullpointers whenever it
	 * tries to load textures in the tested code.
	 *
	 * Right now the game has to load, and be closed for the tests to be done.
	 */
	@BeforeAll
	public static void setUpBeforeAll() {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("platformer");
		cfg.setWindowedMode(1280, 720);

		new Lwjgl3Application(new PlatformerGame(), cfg);
	}

	@AfterAll
	public static void tearDownAfterAll() {
		Gdx.app.exit();
	}

	@BeforeEach
	public void entityCreator() {
		GameMap map = mock(GameMap.class);
		player = new Player(1, 20, map, 2);
		player2 = new Player(1, -20, map, 2);
		skeleton = new Skeleton(10, -10, map, 2);
		coin = new Coin(1, 123, map);
		goal = new Goal(1, 123, map);
	}

	@Test
	public void playerGetsAndLooseScore() {
		player.addScore(20);
		assertEquals(20, player.getScore());
		player.removeScore(20);
		assertEquals(0,player.getScore());
	}

	@Test
	public void playerPosCorrect() {
		assertEquals(1, player.getX());
		assertEquals(20, player.getY());
	}

	@Test
	public void playerDiesUnderMap() {
		assertEquals(false, player.isDead());
		player2.update(0, 0);
		assertEquals(true, player2.isDead());

	}

	@Test
	public void skeletonDiesUnderMap() {
		//Before we update, the skeleton is not dead, as the game has not checked what
		//the position of the skeleton is and if it should be dead or not.
		assertEquals(false, skeleton.isDead());
		skeleton.update(0, 0);
		assertEquals(true, skeleton.isDead());
	}

	@Test
	public void skeletonKillsPlayer() {
		skeleton.interact(player);
		assertEquals(-300, player.getScore());
		assertEquals(false, player.isDead());
		skeleton.interact(player);
		assertEquals(-600, player.getScore());
		assertEquals(true, player.isDead());
	}

	@Test
	public void coinGivesPoints() {
		coin.interact(player);
		assertEquals(100, player.getScore());
	}

	@Test
	public void goalAdvancesToNextMap() {
		goal.interact(player);
		assertEquals(1000, player.getScore());
	}
}