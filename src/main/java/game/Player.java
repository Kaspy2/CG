package game;

class Player{
    private Coordinate coordinate;

    public void move(char direction){
    	int x = this.coordinate.getX();
    	int y = this.coordinate.getY();

    	if(direction == 'u') {
    		this.setCoordinate(new Coordinate(x, y+1));
    	}
    	else if (direction == 'd') {
    		this.setCoordinate(new Coordinate(x, y-1));
    	}
    	else if (direction == 'l') {
    		this.setCoordinate(new Coordinate(x-1, y));
    	}
    	else if (direction == 'r') {
    		this.setCoordinate(new Coordinate(x+1, y));
    	}
    	// else should be programmatically avoided
    	// else does not change coordinates since invalid move direction
    	else {
    		// System.out.println("Error: Wrong direction type!");
    		// throw MoveException();
    	}
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
		return true;
    }

    public Coordinate getCoordinate() {
    	return this.coordinate;
    }
}
