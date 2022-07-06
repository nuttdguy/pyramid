
abstract class Grid {
    private char[][] grid;
    Grid() {
        init();
    }
    public static int getRandomDimension(int _maxDimension) {
        return (int) (Math.random() * _maxDimension);
    }
    public static char[][] generateTheGrid(int _yHeight, int _xWidth) {
        return new char[_yHeight][_xWidth];
    }
    public static char[][] fillTheGrid(char[][] land) {
        char fill = ' ';
        char border = '+';
        if (land.length == 0) { return land; }

        for (int y = 0; y < land.length; y++) {
            for (int x = 0; x < land[y].length; x++) {
                if ((x == 0 && y == 0)  ||
                        (x == land[y].length-1 && y == land.length-1)) {
                    land[y][x] = border;
                } else if (y == 0 || y == land.length-1 ||
                        x == 0 || x == land[y].length-1) {
                    land[y][x] = border;
                } else {
                    land[y][x] = fill;
                }
            }
        }

        return land;
    }
    public static String displayTheGrid(char[][] _grid) {
        String gridView = "";
        for (char[] y : _grid) {
            for (char x : y) {
                gridView += String.valueOf(x);
            }
            gridView += "\n";
        }
        return gridView;
    }
    protected char getElementAtPosition(int _x, int _y) {return getGrid()[_x][_y];}
    protected char setElementPosition(int _x, int _y, char _element) {return getGrid()[_x][_y] = _element;}
    protected char[][] getGrid() {
        return grid;
    }
    protected void setGrid(char[][] grid) {
        this.grid = grid;
    }
    protected void init() {
        int y = 20; // Grid.getRandomDimension(50);
        int x = 60; // Grid.getRandomDimension(100);
        this.grid = generateTheGrid(y, x);
        Grid.fillTheGrid(this.grid);
    }
}
