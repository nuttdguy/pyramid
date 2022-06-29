import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashMap;

public class HangmanTest {

    Hangman hangman;
    char[][] gameGrid;

    @BeforeEach
    void beforeEach() {
        hangman = new Hangman();

        int rows = 5, cols = 7, poleOffset = 5, footerOffset = 3;
        gameGrid = hangman.initGameGrid(rows, cols, poleOffset, footerOffset);
    }

    @DisplayName("Should init and return top, common and footer rows of the game grid")
    @Test
    void initGameGridTest() {

        char[] headerRowPattern = new char[]{'+', '-', '-', '-', '-', '-', '+'};
        char[] commonRowPattern = new char[]{' ', ' ', ' ', ' ', '|', ' ', ' '};
        char[] footerRowPattern = new char[]{' ', ' ', ' ', '=', '=', '=', '='};
        int rowCount = (int) Arrays.stream(gameGrid).count();

        assertTrue(Arrays.equals(gameGrid[0], headerRowPattern),
                String.format("Header row should match pattern: %s == %s",
                        String.valueOf(gameGrid[0]).toString(), String.valueOf(headerRowPattern).toString()));

        assertAll(String.format("Common rows should match pattern: %s == %s",
                String.valueOf(gameGrid[1]).toString(), String.valueOf(commonRowPattern).toString()),
                () -> Arrays.equals(gameGrid[1], commonRowPattern),
                () -> Arrays.equals(gameGrid[2], commonRowPattern),
                () -> Arrays.equals(gameGrid[3], commonRowPattern));

        assertTrue(Arrays.equals(gameGrid[gameGrid.length-1], footerRowPattern),
                String.format("Footer row should match pattern: %s :: %s ",
                        String.valueOf(gameGrid[gameGrid.length-1]).toString(), String.valueOf(footerRowPattern).toString()));

        assertTrue(rowCount == gameGrid.length,
                String.format("Row count should equal number of grid rows %s :: %s ", rowCount, gameGrid.length));

    }

    @DisplayName("Should return String of the game grid with header")
    @Test
    void printGameGridTest() {
        String header = hangman.getHeaderText();
        String grid = hangman.printGameGridWithHeader(gameGrid, header);
        String gridPattern = "H A N G M A N\n"
                .concat("+-----+\n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("   ====\n");

        assertTrue(grid.equals(gridPattern), String.format("Generated String grid should match pattern \n%s = \n%s", grid, gridPattern));
    }

//    @DisplayName("Should return String of the game header text")
//    @Test
//    void printGameHeaderTest() {
//        String headerText = hangman.getHeaderText();
//        String header = "HANGMAN";
//
//        assertTrue(headerText.equals(header), String.format("Header should equal %s == %s", headerText, header));
//    }

    @DisplayName("Should return String of missed letter text")
    @Test
    void printIncorrectTextTest() {
        String incorrectText = hangman.getIncorrectText();
        String testText = "Missed letters: ";
        assertTrue(incorrectText.equals(testText),
                String.format("Text for missed letters should equal %s == %s", incorrectText, testText));
    }

    @DisplayName("Should return String of the letter guess text")
    @Test
    void printGuessLetterTest() {
        String letterGuessText = hangman.getGuessLetterText();
        String testText = "Guess a letter. ";
        assertTrue(letterGuessText.equals(testText),
                String.format("Guessed letter text should equal %s == %s", letterGuessText, testText));
    }

    @DisplayName("Should return String of duplicate guess text")
    @Test
    void printDuplicateLetterTextGuess() {
        String duplicateLetterText = hangman.getDuplicateLetterText();
        String testText = "You have already guessed that letter. Choose again. ";
        assertTrue(duplicateLetterText.equals(testText),
                String.format("Guessing duplicate letter should print %s", duplicateLetterText));
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
        String testWordOne = "lab";
        int testWordIndex = 0;
        String testWordTwo = "desk";

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
        assertArrayEquals(hangman.getWordInPlay(), wordInPlay,
                String.format("Word in play property should be set to %s", String.valueOf(hangman.getWordInPlay())));
    }

    @DisplayName("Should initialize incorrect list using word length")
    @Test
    void intiIncorrectListTest() {
        char[] testWord = new char[]{'t', 'e', 's', 't'};
        char[] testIncorrectListPattern = new char[]{'_', '_', '_', '_', '_', '_', '_', '_'};
        char[] incorrectList = hangman.initIncorrectList(testWord);

        assertTrue(Arrays.equals(testIncorrectListPattern, incorrectList),
                String.format("Incorrect list should return pattern %s", String.valueOf(testIncorrectListPattern)));

    }

    @DisplayName("Should initialize correct list using word length")
    @Test
    void initCorrectListTest() {
        char[] testWord = new char[]{'t', 'e', 's', 't'};
        char[] testCorrectListPattern = new char[]{'_', '_', '_', '_'};
        char[] correctList = hangman.initCorrectList(testWord);

        assertTrue(Arrays.equals(testCorrectListPattern, correctList),
                String.format("Correct list should return pattern %s", String.valueOf(testCorrectListPattern)));
    }

    @DisplayName("Should return a String from input stream")
    @Test
    void inputStreamTest() {
        String testWord = "test";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(testWord.getBytes());
        String testInput = hangman.inputStream(byteArrayInputStream);

        assertTrue(testInput.equals(testWord),
                String.format("Output from input stream should equal %s ", testWord));
    }

    @DisplayName("Should return true or false if letter has been already chosen")
    @Test
    void isDuplicateLetterTest() {
        char[] correctList = new char[]{'t', 'e', 's', 't'};
        char[] incorrectList = new char[]{'a', 'l', 'i', 'c', 'e'};
        char[] testLetter = new char[]{'t'};

        boolean isCorrect = hangman.isDuplicateLetter(testLetter, correctList);
        boolean isIncorrect = hangman.isDuplicateLetter(testLetter, incorrectList);

        assertTrue(isCorrect,
                String.format("Should contain the test letter %s", String.valueOf(testLetter)));
        assertFalse(isIncorrect,
                String.format("Should return false when test letter %s is not in list", String.valueOf(testLetter)));

    }

    @DisplayName("Should return true or false if letter is in game word")
    @Test
    void isLetterCorrectTest() {
        char[] testInvalidLetter = new char[]{'c'};
        char[] testValidLetter = new char[]{'t'};
        char[] testWordInPlay = new char[]{'t', 'e', 's', 't'};
        boolean isValidLetter = hangman.isLetterCorrect(testValidLetter, testWordInPlay);
        boolean isInvalidLetter = hangman.isLetterCorrect(testInvalidLetter, testWordInPlay);

        assertTrue(isValidLetter,
                String.format("%s should be valid letter.", String.valueOf(testValidLetter)));

        assertFalse(isInvalidLetter,
                String.format("%s should be invalid letter.", String.valueOf(testInvalidLetter)));
    }

    @DisplayName("Should update correct list with all correct letter(s) at correct index")
    @Test
    void updateCorrectListTest() {
        char[] testLetter = new char[]{'t'};
        char[] testInPlayWord = new char[]{'t', 'e', 's', 't'};
        char[] correctList = new char[]{'_', '_', '_', '_'};

        correctList = hangman.updateCorrectList(correctList, testInPlayWord, testLetter);

        assertEquals(correctList[0], testLetter[0],
                String.format("%s should be at position 0 of correct list", testLetter[0]));

        assertEquals(correctList[3], testLetter[0],
                String.format("%s should be at position 3 of correct list", testLetter[0]));

    }

    @DisplayName("Should update incorrect list with all incorrect letter(s) at correct index")
    @Test
    void updateIncorrectListTest() {
        char[] testLetterOne = new char[]{'a'};
        char[] testLetterTwo = new char[]{'d'};
        char[] testLetterThree = new char[]{'e'};
        char[] testLetterFour = new char[]{'f'};
        char[] incorrectList = new char[]{'_', '_', '_', '_'};

        incorrectList = hangman.updateIncorrectList(incorrectList, testLetterOne);
        incorrectList = hangman.updateIncorrectList(incorrectList, testLetterTwo);
        incorrectList = hangman.updateIncorrectList(incorrectList, testLetterThree);
        incorrectList = hangman.updateIncorrectList(incorrectList, testLetterFour);

        assertEquals(incorrectList[0], testLetterOne[0],
                String.format("%s should be at position 0 of incorrect list", testLetterOne[0]));
        assertEquals(incorrectList[1], testLetterTwo[0],
                String.format("%s should be at position 1 of incorrect list", testLetterOne[0]));
        assertEquals(incorrectList[2], testLetterThree[0],
                String.format("%s should be at position 2 of incorrect list", testLetterOne[0]));
        assertEquals(incorrectList[3], testLetterFour[0],
                String.format("%s should be at position 3 of incorrect list", testLetterOne[0]));

    }

    @DisplayName("Should update game grid when letter is incorrect")
    @Test
    void updateGameGridTest() {
        char[][] gameGrid = this.gameGrid;

        // every method call should add a new game piece
        gameGrid = hangman.updateGameGrid(gameGrid);
        gameGrid = hangman.updateGameGrid(gameGrid);

        assertTrue(gameGrid[1][3] == 'O', "Row 1, col 3 should contain \"O\" ");
        assertTrue(gameGrid[2][3] == '|', "Row 1, col 3 should contain \"|\" ");

    }

    @DisplayName("Should count the number of incorrect guesses")
    @Test
    void tallyIncorrectListTest() {
        char[] incorrectList = new char[]{'t', 'e', 's', 't', '_'};

        int count = hangman.tallyIncorrectList(incorrectList);
        assertEquals(count, 4, "Count should be 4");

        incorrectList[4] = 's';
        count = hangman.tallyIncorrectList(incorrectList);
        assertEquals(count, 5, "Count should be 5");

    }

    @AfterEach
    void afterEach() {

    }
}




















