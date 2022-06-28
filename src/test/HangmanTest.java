import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

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

    @DisplayName("Should return String of the game grid")
    @Test
    void printGameGridTest() {
        String grid = hangman.printGameGrid(gameGrid);
        String gridPattern = "".concat("+-----+\n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("     | \n")
                .concat("   ====\n");

        assertTrue(grid.equals(gridPattern), String.format("Generated String grid should match pattern \n%s = \n%s", grid, gridPattern));
    }

    @DisplayName("Should return String of the game header")
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

    @AfterEach
    void afterEach() {

    }
}
