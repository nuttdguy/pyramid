import java.util.HashMap;
import java.util.List;

public class Hangman {

    private final String headerText = "";
    private final String letterMissText = "";
    private final String letterGuessText = "";
    private final String letterDuplicateText = "";
    private final String gameWinText = "";
    private final String gamePlayAgainText = "";

    private List<String> guessCorrectList;
    private List<String> guessIncorrectList;
    private HashMap<Integer, String[]> wordList;
    private char[][] gameGrid;


    public Hangman() {}

    public static char[][] createGameGrid(int rows, int cols, int poleOffset) {
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {
                if (r == 0) {
                    if (c == 0 || c == cols-1) {
                        grid[r][c] = '+';
                    } else {
                        grid[r][c] = '-';
                    }
                } else if (r == rows - 1 &&  c > poleOffset - 3) {
                    grid[r][c] = '=';
                } else if (c == poleOffset) {
                    grid[r][c] = '|';
                } else {
                    grid[r][c] = ' ';
                }
            }
        }
        return grid;
    }

    public static void printGameHeader(String header) {
        System.out.printf("%s\n", header);
    }

    public static void printGameGrid(char[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            char[] row = grid[r];

            for (int c = 0; c < row.length; c++) {
                System.out.print(row[c]);
            }
            System.out.println();

        }
    }

}
