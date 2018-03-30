package game;

public class Map{
    private int size;

    public Map(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public void generateMap(){}
    public char getTileType(int x, int y){ return ' '; }
}
