import java.util.ArrayList;
import java.util.Arrays;

abstract class Grid {
    private char[][] grid;
    Grid() {
        init();
    }
    public static int getRandomDimension(int _maxDimension) {
        return (int) (Math.random() * _maxDimension);
    }
    public char[][] generateTheGrid(int _yHeight, int _xWidth) {
        return new char[_yHeight][_xWidth];
    }
    public char[][] fillTheGrid(char[][] land) {
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
    public char[][] fillTheGridWithStat(char[][] land) {
        char fill = ' ';
        char border = '+';
        if (land.length == 0) { return land; }

        int statOffSet = land[0].length - (land[0].length / 3);
        int statOffSetWithPadding = statOffSet + 2;

        for (int y = 0; y < land.length; y++) {

            int letterPosition = 0; // use to position the letters of stat header
            for (int x = 0; x < land[y].length; x++) {

                // stat column
                if (x == statOffSet) {
                    land[y][x] = border;
                } else if ( (x > statOffSetWithPadding && x < land[y].length-2) &&
                        ( y==1 || y==3 || y==5 || y==7 || y==9) ) {
                    // get the current letter of the stat header
                    land[y][x] = getCharOfStatHeader(land, letterPosition, y);
                    letterPosition++;
                }

                else if ((x == 0 && y == 0)  ||
                        (x == land[y].length-1 && y == land.length-1)
                ) {
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
    protected char getCharOfStatHeader(char[][] grid, int letterPosition, int row) {
        char[][] statHeader =
                new char[][]{
                        {'H', 'E', 'A', 'L', 'T', 'H'},
                        {'S', 'T', 'R', 'E', 'N', 'G', 'T', 'H'},
                        {'D', 'E', 'F', 'E', 'N', 'S', 'E'},
                        {'M', 'O', 'V', 'E'},
                        {'E', 'N', 'E', 'M', 'I', 'E', 'S'}
                };
        char letter = ' ';
        try {
            if (row == 1 && letterPosition < statHeader[0].length) {
                letter = statHeader[0][letterPosition];
            } else if (row == 3 && letterPosition < statHeader[1].length) {
                letter = statHeader[1][letterPosition];
            } else if (row == 5 && letterPosition < statHeader[2].length) {
                letter = statHeader[2][letterPosition];
            } else if (row == 7 && letterPosition < statHeader[3].length) {
                letter = statHeader[3][letterPosition];
            } else if (row == 9 && letterPosition < statHeader[4].length) {
                letter = statHeader[4][letterPosition];
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        return letter;
    }

    public String displayTheGrid(char[][] _grid) {
        String gridView = "";
        for (char[] y : _grid) {
            for (char x : y) {
                gridView += String.valueOf(x);
            }
            gridView += "\n";
        }
        return gridView;
    }
    public String displayTheHeader(String _headerText) {
        int paddingLength = (this.getGrid()[0].length - _headerText.length()) / 2;
        String padding = "";
        for (int i = 0; i < paddingLength-1; i++) {
            padding += " ";
        }
        return String.format("%s %s %s", padding, _headerText, padding);
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
        int y = 15; // Grid.getRandomDimension(50);
        int x = 60; // Grid.getRandomDimension(100);
        this.grid = generateTheGrid(y, x);
        this.fillTheGridWithStat(this.grid);
    }
    protected void subArray() {
        ArrayList<Integer> nums = new ArrayList<>();
//        nums.add(-7);
//        nums.add(10);
//        nums.add(-13);
//        nums.add(-3);
//        nums.add(0);
//        nums.add(13);
//        nums.add(13);
//        nums.add(14);
//        nums.add(1);
        nums.add(-6);
        nums.add(2);
        nums.add(6);
        nums.add(-1);
        nums.add(1);
        nums.add(-6);

        int max = Integer.MIN_VALUE; // sets the lowest possible value of int data type
        int startIdx = 0;
        int endIdx = 0;
        for (int i = 0; i < nums.size(); i++) {
            int sum = nums.get(i);

            for (int k = i+1; k < nums.size(); k++) {

                max = Math.max(max, sum);
                sum += nums.get(k);
                endIdx = k;
            }
            max = Math.max(max, sum);
            startIdx = i;
        }

        ArrayList<Integer> numList = new ArrayList<>();

        nums.forEach(e->System.out.print(e+","));
        System.out.println("\n");
        for (int idx = 0; idx < nums.size(); idx++) {
            if (idx >= startIdx && idx <= endIdx) {
                System.out.println(nums.get(idx) + " [" +startIdx + " :: " + endIdx + "]");
                numList.add(nums.get(idx));
            }
        }
        System.out.println("=================");

    }
    protected void nthPascal(int nth) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        if (nth == 0 ) { return; }

        for (int i = 1; i <= nth; i++) {
            int baseFactor = list.get(i - 1);
            int elementValue = baseFactor * (nth - i + 1) / i;
            list.add(i, elementValue);
        }

        list.forEach(System.out::println);
    }
}
