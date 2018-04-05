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
    public void generateHTMLFiles(){}
}
