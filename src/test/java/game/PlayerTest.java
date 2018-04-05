package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest{
	private Player player;

	@Before
	public void setup(){
		player = new Player();
	}

	@After
	public void teardown(){
		player = null;
	}

	@Test
	public void testPlayerSetCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(0,0);

		// Verify
		assertEquals(true,player.setCoordinate(c));
	}

}
