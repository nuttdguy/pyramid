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
    public String[] initWords() {
        String words = "data\n" +
                "law\n" +
                "ad\n" +
                "desk\n" +
                "fact\n" +
                "mood\n" +
                "gene\n" +
                "town\n" +
                "poet\n" +
                "king\n" +
                "cell\n" +
                "unit\n" +
                "loss\n" +
                "army\n" +
                "bath\n" +
                "hat\n" +
                "oven\n" +
                "gate\n" +
                "lake\n" +
                "song\n" +
                "dirt\n" +
                "mode\n" +
                "meal\n" +
                "food\n" +
                "lady\n" +
                "tale\n" +
                "menu\n" +
                "year\n" +
                "disk\n" +
                "hall\n" +
                "poem\n" +
                "soup\n" +
                "idea\n" +
                "goal\n" +
                "user\n" +
                "city\n" +
                "art\n" +
                "son\n" +
                "tea\n" +
                "sir\n" +
                "girl\n" +
                "wood\n" +
                "mom\n" +
                "role\n" +
                "pie\n" +
                "ear\n" +
                "road\n" +
                "debt\n" +
                "mall\n" +
                "hair";

        int maxWordsInGameList = getWordsMaxInWordList();
        String[] wordArray = new String[maxWordsInGameList];

        int idx = 0;
        String currentWord = "";
        for (char s : words.toCharArray()) {
            if (idx == maxWordsInGameList) {
                break;
            }
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
    public HashMap<Integer, String[]> setWordList(String[] words) {
        // sort words by length, k = length of word, v = words of k length
        HashMap<Integer, String[]> wordList = new HashMap<>();

        for (int i = 0; i < words.length; i++) { // iterate through words
            if (!wordList.containsKey(words[i].length())) { // if key does not exist, add key and init element
                String[] tempArr = new String[]{words[i]};
                wordList.put(words[i].length(), tempArr);

            } else {  // when key exists, get old values from old array and add new item
                String[] tempArr = wordList.get(words[i].length());
                tempArr = Arrays.copyOf(tempArr, tempArr.length+1);
                tempArr[tempArr.length-1] = words[i];
                wordList.put(words[i].length(), tempArr);
            }
        }

        return wordList;
    }

    public void start() {

        // initialize game property fields, game board and game word

        // print the game board



    }

}
