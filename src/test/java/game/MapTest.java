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
		map = new Map();
	}

	@After
	public void teardown(){
		map = null;
	}

	@Test
    public void testMaxSizeMap(){
        assertFalse(map.setMapSize(51));
    }

    @Test
    public void testMinSizeMap(){
        assertFalse(map.setMapSize(4));
    }

    @Test
    public void testCorrectSizeMap(){
	    assertTrue(map.setMapSize(25));
    }

	@Test
	public void testMapGetTileType(){
		// Exercise
		map.setMapSize(50);
		map.generateMap();
		char tileType = map.getTileType(0,0);

		// Verify
		assertTrue(tileType == 'g' || tileType == 'w' || tileType == 't');
	}

}
