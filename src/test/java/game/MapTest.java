package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapTest{
	private Map map;

	// Map dimension constraints handled in Game
	@Before
	public void setup(){
		map = new Map(5);
	}

	@After
	public void teardown(){
		map = null;
	}

	@Test
	public void testMapInit(){
		// Exercise
		map.generateMap();
		char tileType = map.getTileType(2,2);

		// Verify
		assertEquals(' ',tileType);
	}

}