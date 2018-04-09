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
		map = new Map(25);
	}

	@After
	public void teardown(){
		map = null;
	}

	@Test
	public void testMapGetTileType(){
		// Exercise
		map.generateMap();
		char tileType;
		int num_treasure = 0;

		// Verify
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				tileType = map.getTileType(i,j);
				assertTrue(tileType == 'g' || tileType == 'w' || tileType == 't');
				if(tileType == 't'){
					num_treasure = 1;
				}
			}
		}
		assertEquals(1, num_treasure);
	}

	@Test
	public void testGetTileTypeFail(){
		map.generateMap();
		assertEquals('f', map.getTileType(26, 15));
	}

	@Test
	public void testGetTileTypePass(){
		map.generateMap();
		assertTrue(map.getTileType(22, 22) == 'g' || map.getTileType(22, 22) == 'w' || map.getTileType(22, 22) == 't');
	}
}
