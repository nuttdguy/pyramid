import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class WordPanelTest {

    WordPanel wordPanel;
    File file;
    String[] correctGameWord;
    String[] incorrectGameWord = new String[]{"b", "o", "b","f", "r"};


    @BeforeEach
    void beforeEach() {
        file = new File("words.txt");
        correctGameWord = new String[]{"a", "l", "i", "c", "e"};
        wordPanel = new WordPanel(file.getAbsolutePath(), correctGameWord);
    }

    @DisplayName("Should return contents of file into an array")
    @Test
    void readAndStoreFileContent() {
        String[] wordList = wordPanel.getWordList();
        assertTrue(wordList.length > 0,
                "The selected word should be greater than zero");
    }

    @DisplayName("Should return whether the letter was in a list")
    @Test
    void isCorrectTest() {
        String correctLetterToTest = "a";
        boolean isCorrectLetter = wordPanel.isCorrect(correctLetterToTest);

        String incorrectLetterToTest = "b";
        boolean isIncorrectLetter = wordPanel.isCorrect(incorrectLetterToTest);

        // test correct letter
        if (isCorrectLetter) {
            assertTrue(Arrays.asList(wordPanel.getGuessList()).contains(correctLetterToTest),
                    "Guess list should contain the test letter when letter is correct");
        }
        if (isIncorrectLetter) {
            assertTrue(Arrays.asList(wordPanel.getMissList()).contains(incorrectLetterToTest),
                    "Miss list should contain the test letter when letter is a miss ");
        }

    }

    @DisplayName("Guess list should equal game word")
    @Test
    void isGuessEqualToGameWordTest() {
        String[] gameWord = correctGameWord;
        boolean doesGameWordMatchGuessList = false;
        for (String letter : gameWord) {
            doesGameWordMatchGuessList = wordPanel.isCorrect(letter);
        }

        assertTrue(doesGameWordMatchGuessList,
                "When all letters of game word have been guessed, " +
                        "guess list should match game word");

    }

    @DisplayName("Missed list should include all missed letters")
    @Test
    void isMissedListFilledWithIncorrectGuesses() {
        String[] incorrectWord = incorrectGameWord;
        boolean isTheLetterAMatchGameWord = true;
        for (String letter : incorrectWord) {
            isTheLetterAMatchGameWord = wordPanel.isCorrect(letter);
        }

        assertFalse(isTheLetterAMatchGameWord,
                "No letters should match the game word");
        assertTrue(Arrays.equals(wordPanel.getMissList(), incorrectWord),
                "Missed List should contain the incorrect letters");

    }

}
