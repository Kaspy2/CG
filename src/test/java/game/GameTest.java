package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest{
	private Game game;

	@Before
	public void setup(){
		game = new Game();
	}

	@After
	public void teardown(){
		game = null;
	}

	@Test
	public void testSetNumPlayersMin(){
		assertFalse(game.setNumPlayers(1));
	}

	@Test
	public void testSetNumPlayersMax(){
		assertFalse(game.setNumPlayers(9));
	}

	@Test
	public void testSetNumPlayers(){
		assertTrue(game.setNumPlayers(4));
	}

	@Test
	public void testSetMapSizeMin1(){
		assertFalse(game.setMapSize(4));
	}

	@Test
	public void testSetMapSizeMin2(){
		game.setNumPlayers(5);
		assertFalse(game.setMapSize(7));
	}

	@Test
	public void testSetMapSizeLeastNumPlayers1(){
		game.setNumPlayers(2);
		assertTrue(game.setMapSize(5));
	}

	@Test
	public void testSetMapSizeLeastNumPlayers2(){
		game.setNumPlayers(5);
		assertTrue(game.setMapSize(8));
	}

	@Test
	public void testSetMapSizeMax(){
		game.setNumPlayers(2);
		assertTrue(game.setMapSize(50));
	}

	@Test
	public void testSetMapSizeGreaterMax(){
		game.setNumPlayers(7);
		assertFalse(game.setMapSize(51));
	}

	@Test
	public void testGetMapSize(){
		game.setNumPlayers(2);
		int map_size = 8;
		game.setMapSize(map_size);
		assertEquals(map_size, game.getMapSize());
	}

}
