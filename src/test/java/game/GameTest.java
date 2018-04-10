package game;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.IOException;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

public class GameTest{
	private Game game;
	private int size;
	private Map map;

	@Before
	public void setup(){
		game = new Game();
		size = 10;
		map = new Map(size);
		map.generateMap();
	}

	@After
	public void teardown(){
		game = null;
		map = null;
	}

	@Test
	public void testGetPlayer(){
		game.setNumPlayers(2);
		assertEquals(null,game.getPlayer(2));
	}

	@Test
	public void testSetPlayer(){
		game.setNumPlayers(2);
		assertEquals(false,game.setPlayer(2,null));
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

	@Test
	public void testGetMap(){
		game.setMap(map);
		assertEquals(map, game.getMap());
	}

	@Test
	public void testInitGame() {
		String goodInput = "2\n20\n";
		ByteArrayInputStream bais = new ByteArrayInputStream(goodInput.getBytes());
		System.setIn(bais);
		game.initGame();
		System.setIn(System.in);
		assertEquals(0,bais.available());
	}

	@Test
	public void testInitGamePlayers() {
		String goodInput = "a\n9\n8\n20\n";
		ByteArrayInputStream bais = new ByteArrayInputStream(goodInput.getBytes());
		System.setIn(bais);
		game.initGame();
		System.setIn(System.in);
		assertEquals(0,bais.available());
	}

	@Test
	public void testInitGameMapSize() {
		String goodInput = "8\nxyz\n50\n";
		ByteArrayInputStream bais = new ByteArrayInputStream(goodInput.getBytes());
		System.setIn(bais);
		game.initGame();
		System.setIn(System.in);
		assertEquals(0,bais.available());
	}

	// @Test
	// public void testInitGame(){
	// 	ByteArrayOutputStream out = new ByteArrayOutputStream();
	// 	ByteArrayInputStream in1 = new ByteArrayInputStream("a".getBytes());
	//
	// 	System.setIn(new ByteArrayInputStream("a".getBytes()));
	// 	System.setOut(new PrintStream(out));
	// 	assertEquals("Number of players: ", out);
	//
	// 	System.setIn(new ByteArrayInputStream("1".getBytes()));
	// 	System.setOut(new PrintStream(out));
	// 	assertEquals("ERROR: incorrect number of players. (MIN: 2 | MAX: 8)", out);
	//
	// 	System.setIn(new ByteArrayInputStream("2".getBytes()));
	// 	System.setOut(new PrintStream(out));
	// 	assertEquals("Map size: ", out);
	//
	// 	System.setIn(new ByteArrayInputStream("10".getBytes()));
	//
	// 	game.initGame();
	//
	// 	System.setOut(System.out);
	// 	System.setIn(System.in);
	// }

	@Test
	public void testGenHTMLNull(){
		Player p = new Player (0,0);
		//Coordinate pos = new Coordinate(0,0);
		game.setMap(null);
		String html = game.genInnerHTML(p);

		assertEquals(html.length(), 0);
	}

	@Test
	public void testGenHTMLPlayer(){
		// check if player exists on map
		Player p = new Player (0,0);
		// Coordinate pos = new Coordinate(0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(p);

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
		Player p = new Player (0,0);
		// Coordinate pos = new Coordinate(0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(p);

		// initially player should be on a grass tile
		assertTrue(innerHTML.contains("<div class=\"hidden\">"));
	}

	@Test
	public void testHTMLMapSize(){
		// Coordinate pos = new Coordinate(0,0);
		Player p = new Player (0,0);
		game.setMap(map);
		String innerHTML = game.genInnerHTML(p);
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
		// Player p = new Player(0,0);
		// game.players.add(p);
		game.setMap(map);
		game.setNumPlayers(5);
		game.setStartingPositions();


		game.generateHTMLFiles();
		// if no exception is thrown, all good
	}

	@Test
	public void testGenHTMLFilesPlayerMoved() throws IOException{
		//Player p = new Player(1,1);
		game.setMap(map);
		game.setNumPlayers(2);
		game.setStartingPositions();

		Player p = game.getPlayer(0);
		p.move('u',map.getSize());
		p.move('r',map.getSize());
		p.move('d',map.getSize());
		p.move('l',map.getSize());
		game.setPlayer(0,p);

		//game.players.add(p);

		game.generateHTMLFiles();

		// assertEquals(new Coordinate(1,1), game.players.get(0).getCoordinate());
		// if no exception is thrown, all good
	}

}
