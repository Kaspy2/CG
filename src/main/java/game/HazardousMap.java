package game;

import java.util.Random;

public class HazardousMap extends Map {
    HazardousMap(int size){
        super(size);
        Random rand = new Random();
        water_rate = rand.nextInt(10) + 25;
    }
}
