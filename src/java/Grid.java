abstract class Grid {
    private char[][] grid;
    Grid() {
        init();
    }
    protected String headerTextForGame() {
        return "HUMANS VERSUS GOBLINS";
    } // this should be in game class
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
        StringBuilder gridView = new StringBuilder();
        for (char[] y : _grid) {
            for (char x : y) {
                gridView.append(x);
            }
            gridView.append("\n");
        }
        return gridView.toString();
    }
    protected String displayTheHeader() {
        int paddingLength = (this.getGrid()[0].length - headerTextForGame().length()) / 2;
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < paddingLength-1; i++) {
            padding.append(" ");
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
        setGrid(generateTheGrid(row, col));
        setGrid(fillTheGridWithStat(getGrid()));
    }

}
