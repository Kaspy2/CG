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
		player.move('u');

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveDown(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(1,0);
		player.move('d');

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveLeft(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(0,1);
		player.move('l');

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveRight(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(2,1);
		player.move('r');

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerMoveInvalidDirection(){
		Coordinate testCoord = new Coordinate(1,2);
		player.setCoordinate(testCoord);
		player.move('a');

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testPlayerVisited(){
		assertTrue(player.visited(new Coordinate(0,0)));
	}

}
