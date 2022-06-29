import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;

public class HangmanTest {

    Hangman hangman;
    char[][] gameGrid;

    @BeforeEach
    void beforeEach() {
        hangman = new Hangman();

        int rows = 5, cols = 7, poleOffset = 5, footerOffset = 3;
        gameGrid = hangman.initGameGrid(rows, cols,poleOffset, footerOffset);
    }

    @DisplayName("Should init and return the game grid")
    @Test
    void initGameGridTest() {

        char[] headerRowPattern = new char[]{'+', '-', '-', '-', '-', '-', '+'};
        char[] commonRowPattern = new char[]{' ', ' ', ' ', ' ', '|', ' ', ' '};
        char[] footerRowPattern = new char[]{' ', ' ', ' ', '=', '=', '=', '='};

        assertTrue(gameGrid[0][0] == headerRowPattern[0], String.format("Row 0, column 0 should equal %s == %s)", gameGrid[0][0], headerRowPattern[0]));
        assertTrue(gameGrid[0][1] == headerRowPattern[1], String.format("Row 0, column 0 should equal %s == %s)", gameGrid[0][1], headerRowPattern[1]));
        assertTrue(gameGrid[0][2] == headerRowPattern[2], String.format("Row 0, column 0 should equal %s == %s)", gameGrid[0][2], headerRowPattern[2]));
        assertTrue(gameGrid[0][3] == headerRowPattern[3], String.format("Row 0, column 0 should equal %s == %s)", gameGrid[0][3], headerRowPattern[3]));
        assertTrue(gameGrid[0][4] == headerRowPattern[4], String.format("Row 0, column 0 should equal %s == %s)", gameGrid[0][4], headerRowPattern[4]));


        assertTrue(Arrays.equals(gameGrid[0], headerRowPattern),
                String.format("Header row should match pattern: %s == %s",
                        String.valueOf(gameGrid[0]).toString(), String.valueOf(headerRowPattern).toString()));

        assertAll(String.format("Common rows should match pattern: %s == %s",
                String.valueOf(gameGrid[1]).toString(), String.valueOf(commonRowPattern).toString()),
                () -> Arrays.equals(gameGrid[1], commonRowPattern),
                () -> Arrays.equals(gameGrid[2], commonRowPattern),
                () -> Arrays.equals(gameGrid[3], commonRowPattern));

        assertTrue(Arrays.equals(gameGrid[4], footerRowPattern),
                String.format("Footer row should match pattern: %s == %s",
                        String.valueOf(gameGrid[4]).toString(), String.valueOf(footerRowPattern).toString()));

    }

    @DisplayName("Should return String of the game grid with header")
    @Test
    void printGameGridTest() {
        String header = hangman.getHeaderText();
        String grid = hangman.printGameGridWithHeader(gameGrid, header);
        String gridPattern = "HANGMAN\n"
                .concat("+-----+\n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("   ====\n");

        assertTrue(grid.equals(gridPattern), String.format("Generated String grid should match pattern \n%s = \n%s", grid, gridPattern));
    }

    @DisplayName("Should return String of the game header text")
    @Test
    void printGameHeaderTest() {
        String headerText = hangman.getHeaderText();
        String header = "HANGMAN";

        assertTrue(headerText.equals(header), String.format("Header should equal %s == %s", headerText, header));
    }

    @DisplayName("Should return String of missed letter text")
    @Test
    void printMissedLetterTest() {
        String letterMissText = hangman.getLetterMissText();
        String letterMiss = "Missed letters: ";
        assertTrue(letterMissText.equals(letterMiss),
                String.format("Text for missed letters should equal %s == %s", letterMissText, letterMiss));
    }

    @DisplayName("Should return String of the letter guess text")
    @Test
    void printLetterGuessTest() {
        String letterGuessText = hangman.getLetterGuessText();
        String letterGuess = "Guess a letter. ";
        assertTrue(letterGuessText.equals(letterGuess),
                String.format("Guessed letter text should equal %s == %s", letterGuessText, letterGuess));
    }

    @DisplayName("Should return String of duplicate guess text")
    @Test
    void printLetterDuplicateTextGuess() {
        String letterDuplicateText = hangman.getLetterDuplicateText();
        String letterDuplicate = "You have already guessed that letter. Choose again. ";
        assertTrue(letterDuplicateText.equals(letterDuplicate),
                String.format("Guessing duplicate letter should print %s", letterDuplicateText));
    }

    @DisplayName("Should return String of win game text")
    @Test
    void printWinGameTextTest() {
        String gameWord = "Blue";
        String winGameText = hangman.getWinGameText(gameWord);
        String testText = "Yes! The secret word is \""+gameWord+"\"! You have won!";

        assertTrue(winGameText.equals(testText),
                String.format("Win game text should show winning word \n%s = \n%s ", winGameText, testText));
    }

    @DisplayName("Should return String of play again text")
    @Test
    void printPlayAgainText() {
        String playAgainText = hangman.getPlayGameAgainText();
        String testText = "Do you want to play again? (yes or no)";

        assertTrue(playAgainText.equals(testText), String.format("Play again text should equal %s", testText));
    }

    @DisplayName("Should return the String of words as an array with default size = 30")
    @Test
    void initWordsTest() {
        String[] wordList = hangman.initWords();
        String testWordOne = "data";
        int testWordIndex = 0;
        String testWordTwo = "year";

        assertTrue(wordList.length == 30, String.format("String of words should result in new array of length %s", wordList.length));
        assertTrue( wordList[testWordIndex].equals(testWordOne), String.format("Word list should contain word %s at index %s", testWordOne, testWordIndex));
        assertTrue( Arrays.asList(wordList).contains(testWordTwo), String.format("Word list should contain %s", testWordTwo));
    }

    @DisplayName("Should return a hashmap of words k = length, v = words of length k")
    @Test
    void setWordListTest() {
        String[] words = hangman.initWords();
        HashMap<Integer, String[]> wordList = hangman.setWordList(words);
        boolean containsAll = true;

        for (Integer k : wordList.keySet()) {
            String[] values = wordList.get(k);
            if (!Arrays.stream(values).allMatch((v) -> Arrays.asList(words).contains(v))) {
                containsAll = false;
            }
        }

        assertTrue(containsAll, String.format("All words in words should be included in wordList"));

        // assert test will fail when extra word is added to wordList
        wordList.put(5, new String[]{"FailTest"});
        for (Integer k : wordList.keySet()) {
            String[] values = wordList.get(k);
            if (!Arrays.stream(values).allMatch((v) -> Arrays.asList(words).contains(v))) {
                containsAll = false;
            }
        }
        assertFalse(containsAll, "Extra word(s) should not be allowed");

    }

    @DisplayName("Should return the selected word")
    @Test
    void selectWordInPlayTest() {
        String[] words = hangman.initWords();
        HashMap<Integer, String[]> wordList = hangman.setWordList(words);

        int wordLength = 3;
        char[] wordInPlay = hangman.selectWordInPlay(wordList, wordLength);
        String[] wordsOfRow = wordList.get(wordLength);

        assertTrue(Arrays.asList(wordsOfRow).contains(String.valueOf(wordInPlay)),
                String.format("%s should be in words of selected row ", String.valueOf(wordInPlay)));
    }

    @DisplayName("Should set the wordInPlay property")
    @Test
    void setWordInPlayTest() {
        String[] words = hangman.initWords();
        HashMap<Integer, String[]> wordList = hangman.setWordList(words);

        int wordLength = 3;
        char[] wordInPlay = hangman.selectWordInPlay(wordList, wordLength);
        String[] wordsOfRow = wordList.get(wordLength);

        hangman.setWordInPlay(wordInPlay);

        assertTrue(Arrays.asList(wordsOfRow).contains(String.valueOf(wordInPlay)),
                String.format("%s should be in words of selected row ", String.valueOf(wordInPlay)));
        assertTrue(Arrays.equals(hangman.getWordInPlay(), wordInPlay),
                String.format("Word in play property should be set to %s", String.valueOf(hangman.getWordInPlay())));
    }

    @AfterEach
    void afterEach() {

    }
}




















