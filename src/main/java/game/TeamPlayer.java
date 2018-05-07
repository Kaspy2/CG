package game;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

class TeamPlayer extends Player implements Observer {
    public TeamPlayer(Coordinate coord) {
        super(coord);
    }

    public TeamPlayer(int x, int y) {
        super(new Coordinate(x,y));
    }

    // should update the visited squares
    public void update(Observable t, Object arg){
        this.visited = (ArrayList<Coordinate>) arg; 
    };
}
