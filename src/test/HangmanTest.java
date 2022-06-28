import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HangmanTest {

    Hangman hangman;

    @BeforeEach
    void beforeEach() {
        hangman = new Hangman();
    }

    @DisplayName("Should init and return the game grid")
    @Test
    void initGameGridTest() {
        int rows = 5, cols = 7, poleOffset = 5, footerOffset = 3;
        char[][] gameGrid = hangman.initGameGrid(rows, cols,poleOffset, footerOffset);

        char[] headerRowPattern = new char[]{'+', '-', '-', '-', '-', '-', '+'};
        char[] commonRowPattern = new char[]{' ', ' ', ' ', ' ', '|', ' ', ' '};
        char[] footerRowPattern = new char[]{' ', ' ', ' ', '=', '=', '=', '='};

        assertTrue(gameGrid[0].length == cols, String.format("Rows should have %s columns", cols));
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

    @AfterEach
    void afterEach() {

    }
}
