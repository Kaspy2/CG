package game;

import java.util.*;

public class Game{
    private static int num_players;
    private static int map_size;
    private static Player[] players;
    private static Map map;

    public static void main (String[] args){
        System.out.println("The Grass / Water / Treasure Game v. 1.0.0 - A Game by CG Devs");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        startGame();

        players = new Player[num_players];
        map = new Map(map_size);
        map.generateMap();

        for(int i = 0; i < num_players; i++){
            int tmp_x = 0;
            int tmp_y = 0;

            while(true){
                tmp_x = rand.nextInt(getMapSize());
                tmp_y = rand.nextInt(getMapSize());
                char tmp_tile_type = map.getTileType(tmp_x, tmp_y);
                if(tmp_tile_type != 't' && tmp_tile_type != 'w'){
                    break;
                }
            }

            Coordinate tmp_coord = new Coordinate(tmp_x, tmp_y);
            players[i] = new Player();
            players[i].setCoordinate(tmp_coord); //setStartCoordinate
        }

        generateHTMLFiles();

        boolean win = false;
        ArrayList<Integer> winners = new ArrayList<Integer>();

        while(!win){
            System.out.println("(u)p, (d)own, (l)eft, (r)ight");

            for(int i = 0; i < num_players; i++){
                char tmp_dir;
                do {
                    System.out.print("Player number " + (i+1) + " enter your move: ");
                    tmp_dir = sc.next().charAt(0);
                } while (tmp_dir != 'u' && tmp_dir != 'd' && tmp_dir != 'l' && tmp_dir != 'r');

                players[i].move(tmp_dir);

                char tmp_tile_type = getMap().getTileType(players[i].getCoordinate().getX(), players[i].getCoordinate().getY());

                if (tmp_tile_type == 't'){
                    win = true;
                    winners.add(i);
                } else if (tmp_tile_type == 'w'){
                    //go to start coordinate
                }
            }
            System.out.println();
            generateHTMLFiles();
        }
    }

    public static Map getMap(){
        return map;
    }

    public static int getMapSize(){
        return map_size;
    }

    public static void startGame(){
        Scanner sc = new Scanner(System.in);

        try {
            do{
                System.out.print("Number of players: ");
            }while(!setNumPlayers(sc.nextInt()));

            do{
                System.out.print("Map size: ");
            }while(!setMapSize(sc.nextInt()));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean setNumPlayers(int n_p){
        if(n_p >= 2 && n_p <= 8){
            num_players = n_p;
            return true;
        }
        System.out.println("ERROR: incorrect number of players. (MIN: 2 | MAX: 8)");
        return false;
    }

    public static boolean setMapSize(int size){
        if(num_players >= 2 && num_players <= 4 && size >= 5 && size <= 50){
            map_size = size;
            return true;
        } else if (num_players >= 5 && num_players <= 8 && size >= 8 && size <= 50){
            map_size = size;
            return true;
        }
        System.out.println("ERROR: incorrect map size. (MIN (2-4 players): 5x5 | MIN (5-8 players): 8x8 | MAX: 50x50)");
        return false;
    }

    public static void generateHTMLFiles(){}
}
