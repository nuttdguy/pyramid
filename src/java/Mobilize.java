public interface Mobilize {

    enum Direction {
        NORTH,
        WEST,
        SOUTH,
        EAST
    }
    void setCoordinate(int x);
    void setCoordinate(int x, int y);
    int getCoordinateX();
    int getCoordinateY();
    int[] getCoordinatesXY();
    String walk();
    String run();

}
