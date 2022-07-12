import java.util.ArrayList;

abstract class Grid {
    private char[][] grid;

    Grid() {
        init();
    }
    protected String headerTextForGame() {
        return "HUMANS VERSUS GOBLINS";
    }
    protected int dividerForStat() {
        return 3; }
    protected int colWidth() {
        return this.getGrid()[0].length-1;
    }
    protected int rowHeight() {
        return this.getGrid().length-1;
    }
    protected int colOffset() {
        return (getGrid()[0].length / dividerForStat()) - 1;
    }
    protected char defaultMarker() {
        return ' '; }
    public static int getRandomWithin(int _maxBoundary) {
        return (int) (Math.random() * _maxBoundary);
    }
    protected char[][] generateTheGrid(int row, int col) {
        return new char[row][col];
    }
    protected char[][] fillTheGrid(char[][] grid) {
        char fill = ' ';
        char border = '+';
        if (grid.length == 0) { return grid; }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if ((col == 0 && row == 0)  ||
                        (col == grid[row].length-1 && row == grid.length-1)) {
                    grid[row][col] = border;
                } else if (row == 0 || row == grid.length-1 ||
                        col == 0 || col == grid[row].length-1) {
                    grid[row][col] = border;
                } else {
                    grid[row][col] = fill;
                }
            }
        }

        return grid;
    }
    protected char[][] fillTheGridWithStat(char[][] grid) {
        char fill = ' ';
        char border = '+';
        if (grid.length == 0) { return grid; }

        int statOffSet = colWidth() - colOffset();
        int statOffSetWithPadding = statOffSet + 2;

        for (int row = 0; row < grid.length; row++) {

            int letterPosition = 0; // use to position the letters of stat header
            for (int col = 0; col < grid[row].length; col++) {

                // stat column
                if (col == statOffSet) {
                    grid[row][col] = border;
                } else if ( (col > statOffSetWithPadding && col < grid[row].length-2) &&
                        ( row==1 || row==3 || row==5 || row==7 || row==9) ) {
                    // get the current letter of the stat header
                    grid[row][col] = charLetterForStatHeader(letterPosition, row);
                    letterPosition++;
                }

                else if ((row == 0 && col == 0)  ||
                        (col == grid[row].length-1 && row == grid.length-1)
                ) {
                    grid[row][col] = border;
                } else if (row == 0 || row == grid.length-1 ||
                        col == 0 || col == grid[row].length-1) {
                    grid[row][col] = border;
                } else {
                    grid[row][col] = fill;
                }
            }
        }

        return grid;
    }
    protected char charLetterForStatHeader(int letterPosition, int row) {
        char[][] statHeader =
                new char[][]{
                        {'H', 'E', 'A', 'L', 'T', 'H'},
                        {'S', 'T', 'R', 'E', 'N', 'G', 'T', 'H'},
                        {'D', 'E', 'F', 'E', 'N', 'S', 'E'},
                        {'M', 'O', 'V', 'E'},
                        {'G', 'O', 'B', 'L', 'I', 'N', 'S'}
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
    protected String displayTheGrid(char[][] _grid) {
        String gridView = "";
        for (char[] y : _grid) {
            for (char x : y) {
                gridView += String.valueOf(x);
            }
            gridView += "\n";
        }
        return gridView;
    }
    protected String displayTheHeader() {
        int paddingLength = (this.getGrid()[0].length - headerTextForGame().length()) / 2;
        String padding = "";
        for (int i = 0; i < paddingLength-1; i++) {
            padding += " ";
        }
        return String.format("%s %s %s", padding, headerTextForGame(), padding);
    }
    protected char getElementAtPosition(int row, int col) {
        return getGrid()[row][col];
    }
    protected char setElementPosition(int row, int col, char _element) {
        this.grid[row][col] = _element;
        return _element;
    }
    protected char[][] getGrid() {
        return this.grid;
    }
    protected void setGrid(char[][] grid) {
        this.grid = grid;
    }
    protected void init() {
        int row = 15; // Grid.getRandomDimension(100);
        int col = 40; // Grid.getRandomDimension(50);
        this.grid = generateTheGrid(row, col);
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
