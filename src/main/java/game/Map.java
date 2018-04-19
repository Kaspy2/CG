package game;

import java.util.*;

public class Map {
    private int size;
    private char[][] map;
    private static double water_rate;
    private Coordinate treasure;

    public Map(int size){
        this.size = size;
        water_rate = 30;
    }

    public int getSize(){
        return this.size;
    }

    public void generateMap(){
        Random rand = new Random();

        this.map = new char[this.size][this.size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if((rand.nextInt(100) + 1) < water_rate){
                    map[i][j] = 'w';
                } else {
                    map[i][j] = 'g';
                }
            }
        }

        int treasure_x = rand.nextInt(size);
        int treasure_y = rand.nextInt(size);
        map[treasure_x][treasure_y] = 't';
        treasure = new Coordinate(treasure_x,treasure_y);

        // make sure at least one grass tile is reachable from treasure
        // so players can actually win
        Coordinate[] coords = getAdjacent(treasure_x,treasure_y);
        map[coords[0].getX()][coords[0].getY()] = 'g';

    }

    public Coordinate[] getAdjacent(int x, int y){
        if (x<this.size && y<this.size){
            Coordinate[] max = new Coordinate[]{null,null,null,null};
            int len = 0;
            if(x!=0){
                // add left coord
                max[0] = new Coordinate(x-1,y);
                len++;
            }
            if(x!=(this.size-1)){
                // add right coord
                max[1] = new Coordinate(x+1,y);
                len++;
            }
            if(y!=0){
                // add bottom coord
                max[2] = new Coordinate(x,y-1);
                len++;
            }
            if(y!=(this.size-1)){
                // add top coord
                max[3] = new Coordinate(x,y+1);
                len++;
            }

            Coordinate[] ret = new Coordinate[len];
            int index = 0;

            for (Coordinate c: max){
                if(c!=null){
                    ret[index] = c;
                    index++;
                }
            }

            return ret;
        }
        return new Coordinate[0];
    }

    public char getTileType(int x, int y){
        if(x < this.size && y < this.size){
            return map[x][y];
        }
        return 'f';
    }

    // check if treasure is reachable from (x,y)
    public boolean reachableTreasure(int x, int y){
        if (x<this.size && y<this.size){
            Coordinate goal = new Coordinate(x,y);

            HashSet<Coordinate> flooded = new HashSet<Coordinate>();

            // start from treasure
            ArrayList<Coordinate> queue = new ArrayList<Coordinate>();
            queue.add(treasure);
            flooded.add(treasure);

            while(queue.size()>0){
                Coordinate current = queue.get(0);
                queue.remove(0);

                if(current.equals(goal)){
                    return true;
                }

                Coordinate [] adj = getAdjacent(current.getX(),current.getY());
                for (Coordinate c: adj){
                    if (getTileType(c.getX(),c.getY())=='g' && !flooded.contains(c)){
                        flooded.add(c);
                        queue.add(c);
                    }
                }
            }
        }

        return false;
    }
}
