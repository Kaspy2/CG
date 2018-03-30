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
	public void testGameX(){
		// Verify
		assertEquals(0,0);
	}

}
