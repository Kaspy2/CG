package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

public class Team extends Observable {
	private int numPlayers;
	private HashSet<Coordinate> visited;	// by the team

	public Team() {
		numPlayers = 0;
		visited = new HashSet<Coordinate>();
	}

	public void addPlayer(TeamPlayer p) {
		addObserver(p);
		numPlayers++;
		visited.add(p.getStartCoord());
	};

	// move whole team at once
	public void move(ArrayList<Coordinate> newPositions){
		visited.addAll(newPositions);
		setChanged();
		notifyObservers(new ArrayList<Coordinate>(visited));	// pass the visited arraylist to the players
	}

	public int getNumPlayers(){
		return numPlayers;
	}

}