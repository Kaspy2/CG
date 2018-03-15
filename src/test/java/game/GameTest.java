package game;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

public class GameTest{

    Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @After
    public void tearDown() {
        game = null;
    }

    /*
    *   Set Number of Players
    */

    @Test
    public void setNumPlayersTests(){

        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        
        System.setIn(in);
        game.setNumOfPlayers();
        System.setIn(System.in);
        
        assertEquals(2, game.getNumOfPlayers());
    }

}