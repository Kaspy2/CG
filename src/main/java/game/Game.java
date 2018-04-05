package game;

import java.util.*;

public class Game{
    private int turns;
    private ArrayList<Player> players;
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

    public String genInnerHTML(Coordinate playerPos, Map map) {
        String html = "";
        if(map!=null) {
            html += "<div class = \"board\">";
            int size = map.getSize();
            

            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {




                }
            }
            html += "</div>";
        }
        
        return html;
    }

    public void generateHTMLFiles(){}
}
