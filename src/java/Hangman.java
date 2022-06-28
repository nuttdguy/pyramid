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


    public Hangman() {
    }

    public char[][] initGameGrid(int rows, int cols, int poleOffset, int footerOffset) {
        gameGrid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {
                if (r == 0) {  // header row
                    if (c == 0 || c == cols-1) {
                        gameGrid[r][c] = '+';
                    } else {
                        gameGrid[r][c] = '-';
                    }
                } else if (r == rows - 1 &&  c > poleOffset - footerOffset) {  // footer row
                    gameGrid[r][c] = '=';
                } else if (c == poleOffset) {
                    gameGrid[r][c] = '|';  // right pole column
                } else {
                    gameGrid[r][c] = ' ';  // common columns
                }
            }
        }
        return gameGrid;
    }

    public void printGameHeader(String header) {
        System.out.printf("%s\n", header);
    }

    public void printGameGrid(char[][] gameGrid) {
        for (int r = 0; r < gameGrid.length; r++) {
            char[] row = gameGrid[r];

            for (int c = 0; c < row.length; c++) {
                System.out.print(row[c]);
            }
            System.out.println();
        }
    }

}
