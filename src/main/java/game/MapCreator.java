package game;

public class MapCreator {
    private Map map;

    MapCreator(char type, int size){
        if(type == 's') {
            map = new SafeMap(size);
        }else if(type == 'h') {
            map = new HazardousMap(size);
        }else {
            System.out.println("map type " + type);
            map = null;
        }
    }

    public Map getMap(){
        return this.map;
    }
}
