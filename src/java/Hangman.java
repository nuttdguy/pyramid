import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private final String headerText = "HANGMAN";
    private final String letterMissText = "Missed letters: ";
    private final String letterGuessText = "Guess a letter. ";
    private final String letterDuplicateText = "You have already guessed that letter. Choose again. ";
    private final String winGameText = "Yes! The secret word is \"%s\"! You have won!";
    private final String playGameAgainText = "Do you want to play again? (yes or no)";

    private List<String> guessCorrectList;
    private List<String> guessIncorrectList;
    private HashMap<Integer, String[]> wordList;
    private String gameWord;
    private char[][] gameGrid;
    private Scanner scanner;


    public Hangman() {
    }

    public String getHeaderText() {return headerText; }
    public String getLetterMissText() {return letterMissText;}
    public String getLetterGuessText() {return letterGuessText;}
    public String getLetterDuplicateText() {return letterDuplicateText;}
    public String getWinGameText(String gameWord) {return String.format(winGameText, gameWord);}
    public String getPlayGameAgainText() {return playGameAgainText; }
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
    private String printGameHeader(String header) {return String.format("%s\n", header);}

    public String printGameGridWithHeader(char[][] gameGrid, String headerText) {
        StringBuilder grid = new StringBuilder();
        String header = printGameHeader(headerText);

        grid.append(header); // append the header
        for (int r = 0; r < gameGrid.length; r++) {
            char[] row = gameGrid[r];

            for (int c = 0; c < row.length; c++) {
                grid.append(row[c]);
            }
            grid.append("\n");
        }
        return grid.toString();
    }

}
