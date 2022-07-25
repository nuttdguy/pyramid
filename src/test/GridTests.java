import grid.Grid;
import grid.Map;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;


public class GridTests {

    Map map;

    @BeforeEach
    void beforeEach() {
        map = new Map();
    }

    @DisplayName("Should return number less than max and greater than 1")
    @Test
    void getRandomDimensionTest() {
        int maxDimension = 100;
        int actual = Grid.getRandomWithin(maxDimension);

        assertTrue(actual < maxDimension, "Should return value less than max dimension");
        assertTrue(actual >= 0, "Should return value greater than 0");
    }

    @DisplayName("Should return a grid of x and y size")
    @Test
    void generateTheGridTest() {
        int height = 10, width = 10;
        char[][] expected = map.generateTheGrid(height, width);
        int actualCount = 0;
        for (char[] c : expected) {
            for (char k : c) {
                actualCount++;
            }
        }

        assertEquals(expected[0].length, height, "Width should return expected width");
        assertEquals(actualCount, height * width, "Height should return expected cell count :: " + actualCount);
    }

    @DisplayName("Should fill land with open marker")
    @Test
    void fillGridTest() {
        int height = 2, width = 4;
        char[][] expected = new char[][]{{'+', '+', '+', '+'}, {'+', '+', '+', '+'}};
        char[][] actual = map.generateTheGrid(height, width);
        actual = map.fillTheGrid(actual);

        assertTrue(Arrays.deepEquals(expected, actual), "Should match expected");
    }

    @DisplayName("Should get the element of the xy position")
    @Test
    void getElementPositionTest() {
        int height = 4, width = 4;
        char[][] expectedGrid = map.generateTheGrid(height, width);
        expectedGrid = map.fillTheGrid(expectedGrid);
        map.setGrid(expectedGrid);
        char actual = map.setElementPosition(2, 2, 'x');
        char expected = map.getElementAtPosition(2, 2);

        assertEquals(actual, expected, "Expected should match expected : " + expected);

    }

    @DisplayName("Should print out grid")
    @Test
    void displayTheGrid() {
        int height = 4, width = 8;
        char[][] expectedGrid = map.generateTheGrid(height, width);
        expectedGrid = map.fillTheGrid(expectedGrid);
        map.setGrid(expectedGrid);

        String expected = "" +
                "++++++++\n" +
                "+      +\n" +
                "+      +\n" +
                "++++++++\n";
        String actual = map.displayTheGrid(map.getGrid());

        assertEquals(actual, expected, String.format("Should match \n%s", expected));
    }


}
