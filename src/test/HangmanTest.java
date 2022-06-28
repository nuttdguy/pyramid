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

    @DisplayName("Should print the game grid")
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

    @DisplayName("Should print the game header")
    @Test
    void printGameHeaderTest() {
        String headerText = hangman.getHeaderText();
        String header = "HANGMAN";

        assertTrue(headerText == header, String.format("Header should equal %s == %s", headerText, header));
    }

    @AfterEach
    void afterEach() {

    }
}
