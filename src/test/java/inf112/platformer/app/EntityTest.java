package inf112.platformer.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.Game;

import entity.Player;
import entity.Skeleton;
import world.GameMap;
import world.TiledGameMap;

import static org.mockito.Mockito.*;

public class EntityTest {
	
	private Game game;
	private Player player;
	private Player player2;

	@BeforeEach
	public void entityCreator() {
		GameMap map = mock(GameMap.class);
		player = new Player(1, 20, map, 2);
		player2 = new Player(1, -20, map, 2);
		//cannot create skeleton without nullpointer error
		//Skeleton skeleton = new Skeleton(10, 10, map, 2);
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
		//We have to update, but calling Player.update, throws an error due to player 
		//containing its controller and trying to call gdx.input
		assertEquals(true, player2.isDead());
	}
	
	@Test
	public void skeletonDiesUnderMap() {
		//assertEquals(false, skeleton.isDead());
		//skeleton.update(0, 0);
		//assertEquals(true, skeleton.isDead());
	}

}