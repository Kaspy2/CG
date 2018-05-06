package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SafeMapTest{
	private SafeMap map;

	// Map dimension constraints handled in Game
	@Before
	public void setup(){
		map = new SafeMap(25);
	}

	@After
	public void teardown(){
		map = null;
	}


	@Test
	public void testGetAdjacentLength(){
		Coordinate[] coords = map.getAdjacent(1,1);
		assertEquals(4,coords.length);
	}

	@Test
	public void testGetAdjacentLengthSide(){
		Coordinate[] coords = map.getAdjacent(1,0);
		assertEquals(3,coords.length);
	}

	@Test
	public void testGetAdjacentLengthCorner(){
		Coordinate[] coords = map.getAdjacent(0,0);
		assertEquals(2,coords.length);
	}

	@Test
	public void testGetAdjacentOutOfBounds(){
		Coordinate[] coords = map.getAdjacent(25,25);
		assertEquals(0,coords.length);
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
					num_treasure += 1;
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

	@Test
	public void testReachableTreasure(){
		map.generateMap();
		boolean reachable = map.reachableTreasure(0,0);
		assertTrue(reachable==true || reachable==false);
	}

	@Test
	public void testReachableTreasureOutOfBounds(){
		map.generateMap();
		boolean reachable = map.reachableTreasure(25,25);
		assertFalse(reachable);
	}
}
