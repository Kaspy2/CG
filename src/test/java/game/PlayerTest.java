package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest{
	private Player player;

	@Before
	public void setup(){
		player = new Player(0,0);
	}

	@After
	public void teardown(){
		player = null;
	}

	@Test
	public void testPlayerSetCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(4,4);

		// Verify
		assertEquals(true,player.setCoordinate(c));
	}

	@Test
	public void testPlayerSetNegativeCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(-1,-1);

		// Verify
		assertEquals(false,player.setCoordinate(c));
	}

	@Test
	public void testPlayerSetSameCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(10,20);
		player.setCoordinate(c);

		// Verify
		assertEquals(false,player.setCoordinate(c));
	}

	@Test
	public void testPlayerMoveUp(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(1,2);
		boolean a = player.move('u',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveDown(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(1,0);
		boolean a = player.move('d',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveLeft(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(0,1);
		boolean a = player.move('l',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveRight(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(2,1);
		boolean a = player.move('r',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveInvalidDirection(){
		Coordinate testCoord = new Coordinate(1,2);
		player.setCoordinate(testCoord);
		boolean a = player.move('a',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveInvalidUp(){
		Coordinate testCoord = new Coordinate(2,2);
		player.setCoordinate(testCoord);

		assertFalse(player.move('u',3));
	}

	@Test
	public void testPlayerMoveInvalidDown(){
		Coordinate testCoord = new Coordinate(2,0);
		player.setCoordinate(testCoord);

		assertFalse(player.move('d',3));
	}

	@Test
	public void testPlayerMoveInvalidLeft(){
		Coordinate testCoord = new Coordinate(0,2);
		player.setCoordinate(testCoord);

		assertFalse(player.move('l',3));
	}

	@Test
	public void testPlayerMoveInvalidRight(){
		Coordinate testCoord = new Coordinate(2,1);
		player.setCoordinate(testCoord);

		assertFalse(player.move('r',3));
	}



	@Test
	public void testPlayerVisited(){
		assertTrue(player.visited(new Coordinate(0,0)));
	}

	@Test
	public void testPlayerGetStartCoord(){
		Coordinate c = new Coordinate(0,0);
		assertEquals(c,player.getStartCoord());
	}

	@Test
	public void testPlayerGetStartCoordIneq(){
		Coordinate c = new Coordinate(1,1);
		assertNotEquals(c,player.getStartCoord());
	}

}
