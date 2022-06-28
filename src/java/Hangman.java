import java.util.*;

public class Hangman {

    private final String headerText = "HANGMAN";
    private final String letterMissText = "Missed letters: ";
    private final String letterGuessText = "Guess a letter. ";
    private final String letterDuplicateText = "You have already guessed that letter. Choose again. ";
    private final String winGameText = "Yes! The secret word is \"%s\"! You have won!";
    private final String playGameAgainText = "Do you want to play again? (yes or no)";

    private int wordsMaxInWordList;
    private String[] words;
    private List<String> guessCorrectList;
    private List<String> guessIncorrectList;
    private HashMap<Integer, String[]> wordList;
    private String gameWord;
    private char[][] gameGrid;
    private Scanner scanner;


    public Hangman() {
        setWordsMaxInWordList(30);
    }

    public Hangman(int _maxWordsInGameList) {
        setWordsMaxInWordList(_maxWordsInGameList);
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
    public int getWordsMaxInWordList() {return wordsMaxInWordList;}
    public void setWordsMaxInWordList(int wordsMaxInWordList) {this.wordsMaxInWordList = wordsMaxInWordList;}
    public String[] initWordList() {
        String words = "bird\n" +
                "meat\n" +
                "unit\n" +
                "army\n" +
                "mode\n" +
                "dad\n" +
                "tea\n" +
                "math\n" +
                "oven\n" +
                "road\n" +
                "hair\n" +
                "year\n" +
                "law\n" +
                "tale\n" +
                "ear\n" +
                "dirt\n" +
                "two\n" +
                "way\n" +
                "gene\n" +
                "idea\n" +
                "meal\n" +
                "king\n" +
                "ad\n" +
                "food\n" +
                "beer\n" +
                "news\n" +
                "city\n" +
                "sir\n" +
                "menu\n" +
                "son";

        int maxWordsInGameList = getWordsMaxInWordList();
        String[] wordArray = new String[maxWordsInGameList];

        int idx = 0;
        String currentWord = "";
        for (char s : words.toCharArray()) {
            if (s == '\n') {
                wordArray[idx] = currentWord;
                currentWord = "";
                idx++;
            } else {
                currentWord += s;
            }
        }

        return wordArray;
    }
    public HashMap<Integer, String[]> loadWordList(String[] words) {
        // sort words by length, k = length of word, v = words of k length
        HashMap<Integer, String[]> wordList = new HashMap<>();


        return wordList;
    }

    public void start() {

        // initialize game property fields, game board and game word

        // print the game board



    }

}
