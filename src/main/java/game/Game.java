package game;

import java.util.*;
import java.io.*;

public class Game{
    private int turns;
    public ArrayList<Player> players;
    private Map map;

    public Game(){
        turns = 0;
        players = new ArrayList<Player>();
        map = new Map();
    }

    public void setTurns(int turns){
        this.turns = turns;
    }

    public void setMap(Map map){
        this.map = map;
    }

    public int getTurns(){
        return this.turns;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public Map getMap(){
        return this.map;
    }

    public static void main (String[] args){}
    public void startGame(){}
    public boolean setNumPlayers(int n){ return false; }

    public void generateHTMLFiles() throws IOException {
        for (int i = 0; i < players.size(); i++){
            String html = "<html lang=\"en\"><body><link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">";
            html += genInnerHTML(players.get(i).getCoordinate());
            html += "</body></html>";

            File f = new File("htmlouts/map_player_"+i+".html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(html);
            bw.close();
        }
    }

    // code to generate the board
    public String genInnerHTML(Coordinate playerPos) {
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
                    temp = new Coordinate(x,y);
                    if (temp.equals(playerPos)) {
                        html += genDiv(map.getTileType(x,y), true);
                    }
                    else {
                        html += genDiv(map.getTileType(x,y), false);
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
    public String genDiv(char tileType, boolean player) {
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
}
