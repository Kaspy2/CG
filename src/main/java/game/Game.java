package game;

import java.util.*;
import java.io.*;
import java.lang.Character;

public class Game{
    private static int num_players;
    private static Player[] players;
    private static Team[] teams;
    private static int[] playerTeam;    // player -> team
	private static int map_size;
    private static char map_type;
    private static MapCreator map_creator;
    private static Map map;
    private static ArrayList<Integer> winners;
    private static boolean collabMode;

    public static void main (String[] args){
        System.out.println("The Maze v. 2.0.0 - A Game by CG Devs");
        Scanner sc = new Scanner(System.in);

        initGame();

        map_creator = new MapCreator(map_type, map_size);
        map = map_creator.getMap();
        map.generateMap();

        setStartingPositions();

        try {
            generateHTMLFiles();
            //generateHTMLFileRevealed();
        } catch (IOException e){
            System.out.println(e.toString());
        }

        startGame();

        System.out.println("Winners: " + winners);
    }

    public void setMap(Map map){
        this.map = map;
		if (map != null) {
			this.map_size = map.getSize();
		}
		else this.map_size = 0;
    }

    public static Map getMap(){
        return map;
    }

    public static int getMapSize(){
        return map_size;
    }

    public Player getPlayer(int index){
		if (index < num_players) {
			return this.players[index];
		}
        return null;
    }

	public boolean setPlayer(int index, Player p){
		if (index < this.num_players) {
			this.players[index] = p;
			return true;
		}
        return false;
    }


    public static void initGame(){
        Scanner sc = new Scanner(System.in);
        // teams
        do{
            System.out.print("Collaborative mode (y/n): ");
            while(!sc.hasNext()){
                System.out.print("Collaborative mode (y/n): ");
                sc.next();
            }
        } while(!setCollabMode(sc.next()));

        do{
            System.out.print("Number of players: ");
            while (!sc.hasNextInt()){
                System.out.print("Number of players: ");
                sc.next();
            }
        } while(!setNumPlayers(sc.nextInt()));

        if(collabMode == true){
            // set number of teams
            do{
                System.out.print("Number of teams: ");
                while (!sc.hasNextInt()){
                    System.out.print("Number of teams: ");
                    sc.next();
                }
            } while(!setNumberOfTeams(sc.nextInt()));
        }
        else{
            teams = new Team[0];
        }

        do{
            System.out.print("Map size: ");
            while (!sc.hasNextInt()){
                System.out.print("Map size: ");
                sc.next();
            }
        } while(!setMapSize(sc.nextInt()));

        System.out.print("Map type ((s)afe | (h)azardous): ");
        while (!sc.hasNext("s") && !sc.hasNext("h") && !sc.hasNext("S") && !sc.hasNext("H")) {
            System.out.print("Map type ((S)afe | (H)azardous): ");
            map_type = sc.next().charAt(0);
        }
        map_type = sc.next().charAt(0);

        
    }

    public static boolean setNumPlayers(int n_p){
        if(n_p >= 2 && n_p <= 8){
            num_players = n_p;
			players = new Player[n_p];
            return true;
        }
        else if (collabMode && n_p>1) { // if in collab mode can set any number of players 
            num_players = n_p;
            players = new Player[n_p];
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
        } else if (collabMode && num_players>8 && size>=8 && size <= 50) {   // same map rules apply for collab mode
            map_size = size;
            return true;
        }

        System.out.println("ERROR: incorrect map size. (MIN (2-4 players): 5x5 | MIN (5+ players): 8x8 | MAX: 50x50)");
        return false;
    }

    public static boolean setCollabMode(String s){
        if (s.toLowerCase().charAt(0)=='y'){
            collabMode = true;
            return true;
        }
        else if (s.toLowerCase().charAt(0)=='n'){
            collabMode = false;
            return true;
        }
        System.out.println("ERROR: must be 'y' or 'n'");
        return false;
    }

    public static boolean setNumberOfTeams(int n){
        if(n<=num_players && n>1) {
            teams = new Team[n];
            playerTeam = new int[players.length];
            return true;
        }
        System.out.println("ERROR: incorrect number of teams. (MIN: 2 | MAX: "+num_players+")");
        return false;
    }

    public static void setStartingPositions(){
        Random rand = new Random();
        ArrayList<Integer> unassignedPlayers = new ArrayList<Integer>();    // for teams

        for(int i = 0; i < num_players; i++){
            unassignedPlayers.add(i);
            int tmp_x = 0;
            int tmp_y = 0;

            while(true){
                tmp_x = rand.nextInt(getMapSize());
                tmp_y = rand.nextInt(getMapSize());
                char tmp_tile_type = map.getTileType(tmp_x, tmp_y);
                if(tmp_tile_type != 't' && tmp_tile_type != 'w' && map.reachableTreasure(tmp_x,tmp_y)){
                    break;
                }
            }

            Player p;
            if(collabMode){
                p = new TeamPlayer(tmp_x, tmp_y);
            }
            else{
                p = new Player(tmp_x, tmp_y);
            }
			
			if (p != null){
				players[i] = p;
			}
        }

        // teams
        if(collabMode){
            int minimumTeamMembers = (int)Math.floor(players.length/teams.length);
            int nextIndex; // next index to assign
            for (int i = 0; i<teams.length; i++) {
                teams[i] = new Team();
                for(int t = 0; t<minimumTeamMembers; t++){
                    nextIndex = rand.nextInt(unassignedPlayers.size());
                    teams[i].addPlayer((TeamPlayer)players[unassignedPlayers.get(nextIndex)]);
                    System.out.println("Assigned player "+unassignedPlayers.get(nextIndex)+" to team "+i);
                    playerTeam[unassignedPlayers.get(nextIndex)] = i;
                    unassignedPlayers.remove(nextIndex);
                }
            }

            
            int maximumTeamMembers = minimumTeamMembers+1; // minPerTeam + ceil(leftover players/teams)
            while(unassignedPlayers.size()>0){   // assign remaining players to random teams
                nextIndex = rand.nextInt(teams.length); // now used to store a random team number
                while(teams[nextIndex].getNumPlayers()==maximumTeamMembers){
                    nextIndex = rand.nextInt(teams.length); // for balancing
                }
                teams[nextIndex].addPlayer((TeamPlayer)players[unassignedPlayers.get(0)]);
                System.out.println("Assigned player "+unassignedPlayers.get(0)+" to team "+nextIndex);
                playerTeam[unassignedPlayers.get(0)] = nextIndex;
                unassignedPlayers.remove(0);
            }

            // make other player positions visible to all
            for (int i = 0; i<teams.length; i++) {
                teams[i].move(new ArrayList<Coordinate>());
            }

        }
    }

    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        boolean win = false;
        winners = new ArrayList<Integer>();
        ArrayList<ArrayList<Coordinate>> newTeamCoords = new ArrayList<ArrayList<Coordinate>>();

        if(collabMode) {
            for(int i = 0; i<teams.length; i++) {
                newTeamCoords.add(new ArrayList<Coordinate>());
            }
        }

        while(!win){
            System.out.println("(u)p, (d)own, (l)eft, (r)ight");

            for(int i = 0; i < num_players; i++){
                char tmp_dir;
                do {
                    System.out.print("Player number " + (i+1) + " enter your move: ");
                    tmp_dir = sc.next().charAt(0);
                } while (tmp_dir != 'u' && tmp_dir != 'd' && tmp_dir != 'l' && tmp_dir != 'r' || !players[i].move(tmp_dir, map.getSize()));
                // player moved

                // teams
                if(collabMode) {
                    Coordinate toReveal = players[i].getCoordinate();   // add to team arrayList
                    newTeamCoords.get(playerTeam[i]).add(toReveal);
                }


                char tmp_tile_type = getMap().getTileType(players[i].getCoordinate().getX(), players[i].getCoordinate().getY());

                if (tmp_tile_type == 't'){
                    win = true;
                    winners.add(i);
                } else if (tmp_tile_type == 'w'){
                    players[i].setCoordinate(players[i].getStartCoord());
                }
            }

            // at this point all players have submitted their moves
            // can get newest revealed coords for each player and pass to relevant team
            if(collabMode) {
                for(int teamCounter = 0; teamCounter < teams.length; teamCounter++) {   // iterate over teamCounter and pass latest coords to team
                    teams[teamCounter].move(newTeamCoords.get(teamCounter));
                    newTeamCoords.set(teamCounter, new ArrayList<Coordinate>());   // reset inner
                }
            }


            System.out.println();
            try {
                generateHTMLFiles();
            } catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }

    public static void generateHTMLFiles() throws IOException {
        File dir = new File("htmlouts");
        if (!dir.exists()){
            dir.mkdir();
        }
        for (int i = 0; i < num_players; i++){
            String html = "<html lang=\"en\"><body><link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">";
            html += genInnerHTML(players[i]);
            html += "</body></html>";

            File f = new File("htmlouts/map_player_"+i+".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(html);
            bw.close();
        }
    }

    // code to generate the board
    public static String genInnerHTML(Player p) {
        Coordinate playerPos = p.getCoordinate();
        String html = "";
        if(map!=null) {
            html += "<div class = \"board\">";
            int size = map.getSize();
            Coordinate temp;

            for (int y = size-1; y >= 0; y--) {
                // new row
                html += "<div class=\"row\">";

                // fill row with contents of each column
                for (int x = 0; x < size; x++) {    // iterate through each coord
                    // temp is current coordinate
                    temp = new Coordinate(x,y);
                    if (temp.equals(playerPos)) {
                        html += genDiv(map.getTileType(x,y), true);
                    }
                    else if (p.visited(temp)){
                        html += genDiv(map.getTileType(x,y), false);
                    }
                    else {
                        html += genDiv('h', false);
                    }
                }

                // end row
                html += "</div>";
            }

            // end board
            html += "</div>";
        }

        return html;
    }

    // code to generate a single tile
    public static String genDiv(char tileType, boolean player) {
        String s = "";
        if (tileType == 'h') {
            s += "<div class=\"hidden\">";
        }
        else if (tileType == 'g') {
            s += "<div class=\"grass\">";
        }
        else if (tileType == 'w') {
            s += "<div class=\"water\">";
        }
        else if (tileType == 't') {
            s += "<div class=\"treasure\">";
        }
        else {
            // invalid tile type
            return "";
        }

        if (player == true) {
            s += "<div class=\"player\"></div>";
        }

        s+="</div>";
        return s;
    }
    
    /*
    public static void generateHTMLFileRevealed() throws IOException {
        String html = "<html lang=\"en\"><body><link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">";
        html += genInnerHTMLRevealed();
        html += "</body></html>";

        File f = new File("htmlouts/map_full.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(html);
        bw.close();
    }

    // code to generate the board
    public static String genInnerHTMLRevealed() {
        String html = "";
        if(map!=null) {
            html += "<div class = \"board\">";
            int size = map.getSize();
            Coordinate temp;

            for (int y = size-1; y >= 0; y--) {
                // new row
                html += "<div class=\"row\">";

                // fill row with contents of each column
                for (int x = 0; x < size; x++) {
                    // temp is current coordinate
                    temp = new Coordinate(x,y);
                    html += genDiv(map.getTileType(x,y), false);
                }

                // end row
                html += "</div>";
            }

            // end board
            html += "</div>";
        }

        return html;
    }*/
}
