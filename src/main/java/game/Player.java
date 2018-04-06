package game;

import java.util.ArrayList;

class Player{
    private Coordinate startCoord;
    private ArrayList<Coordinate> visited;
    private Coordinate coordinate;

    public Player(Coordinate coord) {
        this.coordinate = coord;
        this.startCoord = coord;
        visited = new ArrayList<Coordinate>();
        visited.add(coord);
    }

    public Player(int x, int y) {
        this(new Coordinate(x,y));
    }

    public void move(char direction){
    	int x = this.coordinate.getX();
    	int y = this.coordinate.getY();

    	if(direction == 'u') {
            y += 1;
    	}
    	else if (direction == 'd') {
            y -= 1;
    	}
    	else if (direction == 'l') {
            x -= 1;
    	}
    	else if (direction == 'r') {
            x += 1;
    	}
    	// else should be programmatically avoided
    	// else does not change coordinates since invalid move direction
    	else {
    		// System.out.println("Error: Wrong direction type!");
    		// throw MoveException();
    	}

        Coordinate newpos = new Coordinate(x,y);
        this.setCoordinate(newpos);
        visited.add(newpos);
    }

    public boolean setCoordinate(Coordinate coordinate){ 
    	// must always set to a different coordinate
    	if (this.coordinate == coordinate) {
    		return false;
    	}
    	// coordinates must always be in positive sides of both dimensions
    	if (coordinate.getX() < 0 || coordinate.getY() < 0) {
    		return false;
    	}

		this.coordinate = coordinate;
        if (!this.visited.contains(coordinate)) {
            this.visited.add(coordinate);
        }
		return true;
    }

    public Coordinate getCoordinate() {
    	return this.coordinate;
    }

    public boolean visited(Coordinate c) {
        return this.visited.contains(c);
    }
}
