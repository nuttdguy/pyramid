
abstract class Grid {

    private int xWidth;
    private int yHeight;

    Grid() {}

    public static int getRandomDimension(int _maxDimension) {
        return (int) (Math.random() * _maxDimension);
    }

    public static char[][] generateTheGrid(int _xWidth, int _yHeight) {
        return new char[_yHeight][_xWidth];
    }

    protected void setxWidth(int xWidth) {
        this.xWidth = xWidth;
    }

    protected int getxWidth() {
        return xWidth;
    }
    protected void setyHeight(int yHeight) {
        this.yHeight = yHeight;
    }

    protected int getyHeight() {
        return yHeight;
    }
}
