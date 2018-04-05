package game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest{
	private Coordinate coordinate;

	@Before
	public void setup(){
		coordinate = new Coordinate(0,0);
	}

	@After
	public void teardown(){
		coordinate = null;
	}

	@Test
	public void testCoordinateX(){
		// Exercise
		int x = coordinate.getX();

		// Verify
		assertEquals(0,x);
	}

	@Test
	public void testCoordinateY(){
		// Exercise
		int y = coordinate.getY();

		// Verify
		assertEquals(0,y);
	}

	// this is to test coordinate inequality
	// requires Coordinate's equals() method
	@Test
	public void testCoordinateInequality(){
		// Exercise
		Coordinate not = new Coordinate(1,1);

		// Verify
		assertNotEquals(not,coordinate);
	}

	@Test
	public void testCoordinateEquality(){
		// Exercise
		Coordinate same = new Coordinate(0,0);

		// Verify
		assertEquals(same,coordinate);
	}

	@Test
	public void testCoordinateInequalityNull(){
		assertNotEquals(null,coordinate);
	}

	@Test
	public void testCoordinateInequalityUnassignable(){
		assertNotEquals(1,coordinate);
	}
	
}
