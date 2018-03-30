package game;

class Player{
    Coordinate coordinate;

    public Player(){
        coordinate = new Coordinate(0, 0);
    }

    public void move(char direction){}
    public boolean setCoordinate(Coordinate coordinate){ return true; }
}
