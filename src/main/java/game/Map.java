package game;

import java.util.*;

public class Map {
    private int size;
    private char[][] map;
    private static double water_rate;

    public Map(){
        this.size = 0;
        this.map = new char[this.size][this.size];
        water_rate = 40;
    }

    public boolean setMapSize(int size){
        if(size >= 5 && size <= 50){
            this.size = size;
            return true;
        }
        return false;
    }

    public int getSize(){
        return this.size;
    }

    public void generateMap(){
        Random rand = new Random();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if((rand.nextInt(100) + 1) < water_rate){
                    map[i][j] = 'w';
                } else {
                    map[i][j] = 'g';
                }
            }
        }

        map[rand.nextInt(size)][rand.nextInt(size)] = 't';
    }

    public char getTileType(int x, int y){
        return map[x][y];
    }
}
