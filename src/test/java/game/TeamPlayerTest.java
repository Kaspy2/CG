package game;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamPlayerTest{
	private TeamPlayer player;

	@Before
	public void setup(){
		player = new TeamPlayer(0,0);
	}

	@After
	public void teardown(){
		player = null;
	}

	@Test
	public void testTeamPlayerConstructor(){
		// Exercise
		Coordinate c = new Coordinate(4,4);
		player = new TeamPlayer(c);

		// Verify
		assertEquals(c,player.getCoordinate());
	}

	@Test
	public void testTeamPlayerSetCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(4,4);

		// Verify
		assertEquals(true,player.setCoordinate(c));
	}

	@Test
	public void testTeamPlayerSetNegativeCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(-1,-1);

		// Verify
		assertEquals(false,player.setCoordinate(c));
	}

	@Test
	public void testTeamPlayerSetSameCoordinates(){
		// Exercise
		Coordinate c = new Coordinate(10,20);
		player.setCoordinate(c);

		// Verify
		assertEquals(false,player.setCoordinate(c));
	}

	@Test
	public void testTeamPlayerMoveRight(){
		player.setCoordinate(new Coordinate(1,1));
		Coordinate testCoord = new Coordinate(2,1);
		boolean a = player.move('r',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testTeamPlayerMoveInvalidDirection(){
		Coordinate testCoord = new Coordinate(1,2);
		player.setCoordinate(testCoord);
		boolean a = player.move('a',20);

		assertEquals(testCoord, player.getCoordinate());
	}

	@Test
	public void testTeamPlayerVisited(){
		assertTrue(player.visited(new Coordinate(0,0)));
	}

	@Test
	public void testTeamPlayerGetStartCoord(){
		Coordinate c = new Coordinate(0,0);
		assertEquals(c,player.getStartCoord());
	}

	@Test
	public void testTeamPlayerUpdate(){
		ArrayList<Coordinate> cs = new ArrayList<Coordinate>();
		cs.add(new Coordinate(10,10));
		player.update(null, cs);
		assertTrue(player.visited(new Coordinate(10,10)));
	}
}