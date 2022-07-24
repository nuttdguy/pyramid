package expandable;

public interface Mobilize {

    void setCoordinate(int x);
    void setCoordinate(int x, int y);
    int getCoordinateX();
    int getCoordinateY();
    int[] getCoordinates();
    void setMovesRemaining(int movesRemaining);
    int getMovesRemaining();

}
