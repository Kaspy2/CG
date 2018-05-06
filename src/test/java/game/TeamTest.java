package game;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest{
	private Team team;

	@Before
	public void setup(){
		team = new Team();
	}

	@After
	public void teardown(){
		team = null;
	}

	@Test
	public void testTeamGetNumPlayers(){
		team.addPlayer(new TeamPlayer(0,0));
		team.addPlayer(new TeamPlayer(0,1));
		assertEquals(2,team.getNumPlayers());
	}

	@Test
	public void testTeamAddPlayer(){
		TeamPlayer p = new TeamPlayer(0,0);
		team.addPlayer(p);
		assertEquals(1,team.getNumPlayers());
	}

	@Test
	public void testTeamMove(){
		TeamPlayer p = new TeamPlayer(0,0);
		team.addPlayer(p);
		TeamPlayer p2 = new TeamPlayer(1,1);
		team.addPlayer(p2);
		ArrayList<Coordinate> newposs = new ArrayList<Coordinate>();
		newposs.add(new Coordinate(1,0));
		newposs.add(new Coordinate(2,1));
		team.move(newposs);		// players should get updated with positions explored
		assertTrue(p2.visited(new Coordinate(0,0)));
	}

}