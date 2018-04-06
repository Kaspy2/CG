package game;

import java.util.*;

public class Map {
    private int size;
    private char[][] map;
    private static double water_rate;

    public Map(int size){
        this.size = size;
        water_rate = 40;
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
    }

    public char getTileType(int x, int y){
        return map[x][y];
    }
}
