package game;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.File;
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
		game.setMap(null);
		String html = game.genInnerHTML(pos);

		assertEquals(html.length(), 0);
	}

	@Test
	public void testGenHTMLPlayer(){
		// check if player exists on map
		Coordinate pos = new Coordinate(0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(pos);

		assertTrue(innerHTML.contains("<div class=\"player\"></div>"));
	}

	// @Test
	// public void testGenHTMLPlayerStart(){
	// 	// first get a valid grass tile
	// 	Coordinate pos = new Coordinate(0,0);
	// 	game.setMap(map);
	// 	String innerHTML = game.genInnerHTML(pos);

	// 	// initially player should be on a grass tile
	// 	assertTrue(innerHTML.contains("<div class=\"grass\"><div class=\"player\"></div></div>"));
	// }

	@Test
	public void testGenHTMLTreasure(){
		Coordinate pos = new Coordinate(0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(pos);

		// initially player should be on a grass tile
		assertTrue(innerHTML.contains("<div class=\"treasure\">"));
	}

	@Test
	public void testHTMLMapSize(){
		Coordinate pos = new Coordinate(0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(pos);
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
	public void testGenDivHidden(){
		String ret = game.genDiv('h', false);
		assertEquals(ret,"<div class=\"hidden\"></div>");
	}

	@Test
	public void testGenDivWater(){
		String ret = game.genDiv('w', false);
		assertEquals(ret,"<div class=\"water\"></div>");
	}

	@Test
	public void testGenDivGrass(){
		String ret = game.genDiv('g', false);
		assertEquals(ret,"<div class=\"grass\"></div>");
	}

	@Test
	public void testGenDivTreasure(){
		String ret = game.genDiv('t', false);
		assertEquals(ret,"<div class=\"treasure\"></div>");
	}

	@Test
	public void testGenDivInvalid(){
		String ret = game.genDiv('a', false);
		assertEquals(ret,"");
	}

	@Test
	public void testGenDivInvalidPlayer(){
		String ret = game.genDiv('b', true);
		assertEquals(ret,"");
	}

	@Test
	public void testGenDivPlayer(){
		String ret = game.genDiv('g', true);
		assertEquals(ret,"<div class=\"grass\"><div class=\"player\"></div></div>");
	}

	@Test
	public void testGenHTMLFiles() throws IOException{
		Player p = new Player();
		p.setCoordinate(new Coordinate(0,0));
		game.players.add(p);
		game.setMap(map);
		game.generateHTMLFiles();
		// if no exception is thrown, all good
	}

}
