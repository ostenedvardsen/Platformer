package inf112.platformer.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import world.GameMap;

import static org.mockito.Mockito.*;

public class MapTest {
	
	private static GameMap map;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		map = mock(GameMap.class);
	}

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUpBeforeEach() {
	}
	
	@AfterAll
	void tearDown() throws Exception {
	}

}