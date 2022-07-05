import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    Game game;

    @BeforeEach
    @Test
    void beforeEach() {
        game = new Game();
    }

    void gameIntroTest() {
        String expected = "\nYou are in a land full of dragons. In front of you,\n" +
                "you see two caves. In one cave, the dragon is friendly\n" +
                "and will share his treasure with you. The other dragon\n" +
                "is greedy and hungry and will eat you on sight.\n";
        String actual = game.getGameIntro();

        assertEquals(expected.equals(actual), "Should match expected String");
    }

    @Test
    void getPromptQuestionsTest() {
        String expected = "Which cave will you go into?\n";
        String actual = game.getPromptQuestion();

        assertEquals(expected.equals(actual), "Should match expected String");
    }

    @Test
    void getPromptOptionsTest() {
        String[] expected = new String[]{"1. You approach the cave...", "2. It is dark and spooky..."};
        int expectedLength = expected.length;

        String[] actual = game.getPromptOptions();

        assertTrue(Arrays.equals(expected, actual), "Should match expected");
        assertTrue(expectedLength == actual.length, "Should contain same amount of elements");
    }

    @Test
    void getPromptRepliesTest() {
        String[] expected =  new String[]{"A large dragon jumps out in front of you! He opens his jaws and... Gobbles you down in one bite!"};
        int expectedLength = expected.length;

        String[] actual = game.getPromptOptions();

        assertTrue(Arrays.equals(expected, actual), "Should match expected");
        assertTrue(expectedLength == actual.length, "Should contain same amount of elements");
    }

    @Test
    void getInputStreamTest() {
        ByteArrayInputStream inputText = new ByteArrayInputStream("Test".getBytes());
        String actual = game.getInputStream(inputText);

        assertTrue(actual.equals("Test"), "Should return input text");

    }


}
