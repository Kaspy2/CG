package game;

class Coordinate{
    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Coordinate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.getX() || this.y != other.getY()) {
            return false;
        }
        return true;
    }
}
