package game;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest{
	private Game game;
	private int size;
	private Map map;

	@Before
	public void setup(){
		game = new Game();
		map = new Map();
		size = 10;
		map.setMapSize(size);
		map.generateMap();
	}

	@After
	public void teardown(){
		game = null;
		map = null;
	}

	@Test
	public void testGenHTMLNull(){
		Coordinate pos = new Coordinate(0,0);
		String html = game.genInnerHTML(pos,null);

		assertEquals(html.length(), 0);
	}

	@Test
	public void testGenHTMLPlayer(){
		// check if player exists on map
		Coordinate pos = new Coordinate(0,0);
		String innerHTML = game.genInnerHTML(pos,map);

		assertTrue(innerHTML.contains("<div class=\"player\"></div>"));
	}

	@Test
	public void testGenHTMLPlayerStart(){
		Coordinate pos = new Coordinate(0,0);
		String innerHTML = game.genInnerHTML(pos,map);

		// initially player should be on a grass tile
		assertTrue(innerHTML.contains("<div class=\"grass\"><div class=\"player\"></div></div>"));
	}

	@Test
	public void testHTMLMapSize(){
		Coordinate pos = new Coordinate(0,0);
		String innerHTML = game.genInnerHTML(pos,map);
		Pattern pattern = Pattern.compile("grass|treasure|water|hidden");
		Matcher matcher = pattern.matcher(innerHTML);

		int counter = 0;
		while(matcher.find()){
			counter++;
		}
		int expected = size*size;

		assertEquals(expected,counter);
	}


	@Test
	public void testGameX(){
		// Verify
		assertEquals(0,0);
	}

}
