import java.io.InputStream;
import java.util.*;

public class Hangman {

    private final String headerText = "H A N G M A N";
    private final String incorrectText = "Missed letters: ";
    private final String guessLetterText = "Guess a letter. ";
    private final String duplicateLetterText = "You have already guessed that letter. Choose again. ";
    private final String winGameText = "Yes! The secret word is \"%s\"! You have won!";
    private final String playGameAgainText = "Do you want to play again? (yes or no)";

    private int tryMultiplier;
    private int maxWordsInWordList;
    private int maxGuessAttempt;
    private char[] wordInPlay;
    private String[] words;
    private char[] correctList;
    private char[] incorrectList;
    private HashMap<Integer, String[]> wordList;
    private char[][] gameGrid;
    private Scanner scanner;

    public Hangman() {
        setMaxWordsInWordList(30);
        setTryMultiplier(2);
    }

    public Hangman(int _maxWordsInGameList) {
        setMaxWordsInWordList(_maxWordsInGameList);
    }
    public String getHeaderText() {return headerText; }
    public String getIncorrectText() {return incorrectText;}
    public String getGuessLetterText() {return guessLetterText;}
    public String getDuplicateLetterText() {return duplicateLetterText;}
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
                } else if (r == rows - 1 &&  c >= footerOffset) {  // footer row
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
//    private String printGameHeader(String header) {return String.format("%s\n", header);}
    public String printGameGridWithHeader(char[][] gameGrid, String headerText) {
        StringBuilder grid = new StringBuilder();
//        String header = this.printGameHeader(headerText);

        grid.append(headerText +"\n"); // append the header
        for (int r = 0; r < gameGrid.length; r++) {
            char[] row = gameGrid[r];

            for (int c = 0; c < row.length; c++) {
                grid.append(row[c]);
            }
            grid.append("\n");
        }
        return grid.toString();
    }
    public int getMaxWordsInWordList() {return maxWordsInWordList;}
    public void setMaxWordsInWordList(int maxWordsInWordList) {this.maxWordsInWordList = maxWordsInWordList;}
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

        int maxWordsInGameList = getMaxWordsInWordList();
        String[] wordArray = new String[maxWordsInGameList];

        int idx = 0;
        String currentWord = "";

        for (char s : words.toCharArray()) {
            if (idx >= maxWordsInGameList) {
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
    public char[] selectWordInPlay(HashMap<Integer, String[]> wordList, int wordLength) {

        if (!wordList.containsKey(wordLength)) {
            return wordList.get(3)[0].toCharArray();
        }
        int indexRandom = (int) (Math.random() * wordList.get(wordLength).length-1);
        return wordList.get(wordLength)[indexRandom].toCharArray();
    }
    public void setWordInPlay(char[] wordInPlay) { this.wordInPlay = wordInPlay;}
    public char[] getWordInPlay() {return wordInPlay;}
    public char[] getIncorrectList() {return incorrectList; }
    public void setIncorrectList(char[] incorrectList) {
        this.incorrectList = incorrectList;
    }
    public char[] initIncorrectList(char[] wordInPlay) {
        char[] incorrectList = new char[wordInPlay.length * this.getTryMultiplier()];
        int index = 0;
        for (char n : incorrectList) {
            incorrectList[index] = '_';
            index++;
        }
        return incorrectList;
    }
    public int initMaxGuessAttempt(char[] wordInPlay) {return wordInPlay.length * this.getTryMultiplier();}
    public void setTryMultiplier(int tryMultiplier) {this.tryMultiplier = tryMultiplier;}
    public int getTryMultiplier() {return tryMultiplier;}
    public int getMaxGuessAttempt() {return maxGuessAttempt;}
    public void setMaxGuessAttempt(int maxGuessAttempt) {this.maxGuessAttempt = maxGuessAttempt;}
    public char[] initCorrectList(char[] wordInPlay) {
        char[] correctList = new char[wordInPlay.length];
        int index = 0;
        for (char n : correctList) {
            correctList[index] = '_';
            index++;
        }
        return correctList;
    }
    public void setCorrectList(char[] correctList) {this.correctList = correctList;}
    public char[] getCorrectList() {return correctList;}
    public String inputStream(InputStream inputStream) {
        return new Scanner(inputStream).next();
    }
    public boolean isDuplicateLetter(char[] letter, char[] list) {

        if (letter.length == 1) {
            char charLetter = letter[letter.length-1];
            for (char c : list) {
                if (c == charLetter) {
                    return true;
                }
            }
        }
        return false;
    }

    public void start() {
        // todo adjust game board method to adjust height according to selected word length

        // initialize game property fields, game board, wordList and set game word
        String[] words = this.initWords();                                      // set word list
        HashMap<Integer, String[]> wordList = this.setWordList(words);          // sort wordList by length
        int randomWordLength = (int) (Math.random() * 5);
        char[] word = this.selectWordInPlay(wordList, randomWordLength);        // select word in play
        setWordInPlay(word);                                                    // set wordInPlay property

        // init game board with word length as height + 2 of game board
        char[][] gameGrid =
                initGameGrid(
                        word.length + 3,
                        7,
                        5,
                        3);

        int maxGuessAttempt = initMaxGuessAttempt(this.getWordInPlay());        // init max attempts allowed
        setMaxGuessAttempt(maxGuessAttempt);                                    // set max attempts allowed

        char[] incorrectList = initIncorrectList(this.getWordInPlay());         // init the incorrect list
        this.setIncorrectList(incorrectList);                                   // set incorrectList

        char[] correctList = initCorrectList(this.getWordInPlay());             // init the correct list
        setCorrectList(correctList);                                            // set the correct list

        boolean play = true;  // todo remove after implementing correct logic
        while (play) {
            String headerText = this.getHeaderText();       // get the header text
            String gameGridWithHeader = this.printGameGridWithHeader(gameGrid, headerText);     // print game grid with header
            System.out.print(gameGridWithHeader);

            String missedLetterText = this.getIncorrectText();                  // print missed letter text
            System.out.print(missedLetterText);
            System.out.println(String.valueOf(getIncorrectList()));             // print missed letters

            String guessLetterText = this.getGuessLetterText();                 // get the guessed letter text
            System.out.println(String.valueOf(correctList));                    // print guessed letters
            System.out.println(guessLetterText);                                // print guessed letter text
            System.out.println();

            String letter;
            boolean letterIsValid = false;
            do {
                letter = inputStream(System.in);                                // get letter from user prompt
                if (letter.length() != 1) {                                     // check input length
                    System.out.println("Whoa!, one Letter at a time please");
                } else {

                    if (!isDuplicateLetter(letter.toCharArray(), this.getIncorrectList())
                            && !isDuplicateLetter(letter.toCharArray(), this.getCorrectList())) {             // check if the letter is in missed or correct list

                        // check if letter is contained with word in play
                        // update game board grid
                        // update incorrect list
                        // update correct list
                    } else {
                        String duplicateLetterText = this.getDuplicateLetterText();     // get duplicate letter text
                        System.out.println(duplicateLetterText);                // display the duplicate letter text
                    }
                }
            } while (letter.length() > 1);                                      // if greater than 1 & , get new input
            System.out.println(letter);

            play = false; // todo update or remove after implementing logic
        }




    }

}
