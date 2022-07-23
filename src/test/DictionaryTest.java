import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {

    Dictionary dictionary;
    File file;

    String[] alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "K",
    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "w", "x", "y", "z"};
    String[] gameWord;

    private String[] getGameWord() {
        for (String l : alphabet) {
            if (dictionary.isLetterIn(Dictionary.ListType.GAME_WORD, l)) {
                dictionary.isCorrect(l);
            };
        }
        return dictionary.getGuessList();
    }

    private String[] fillMissList() {
        for (String l : alphabet) {
            try {
                if (!(dictionary.isLetterIn(Dictionary.ListType.GAME_WORD, l))) {
                    dictionary.isCorrect(l);
                }
            } catch(IndexOutOfBoundsException iob) {
                break;
            }
        }
        return dictionary.getMissList();
    }

    @BeforeEach
    void beforeEach() {
        file = new File("words.txt");
        dictionary = new Dictionary(10, file.getPath());
        gameWord = getGameWord();
    }

    @DisplayName("Should return contents of file into an array")
    @Test
    void readAndStoreFileContent() {
        String[] wordList = dictionary.getWordList();
        assertTrue(wordList.length > 0,
                "The selected word should be greater than zero");
    }

    @DisplayName("Guess list should contain all letters of the game word")
    @Test
    void guessListShouldContainAllLetterOfGameWord() {
        for (int i = 0; i < gameWord.length; i++) {
            assertTrue(dictionary.isCorrect(dictionary.getGuessList()[i]),
                    "Guess list should contain all letters of game word");
        }
    }

    @DisplayName("Length of missed list should not be greater than game word")
    @Test
    void missListLengthShouldEqualGameWordLength() {
        String[] actual = fillMissList();
        assertNotEquals(actual.length, gameWord.length + 1,
                "List should nto be greater than the game word");
    }

    @DisplayName("Missed list should not contain any letter of the game word")
    @Test
    void shouldNotContainAnyLetterOfGameWord() {
        String[] actual = fillMissList();
        for (int i = 0; i < actual.length; i++) {
            assertTrue( !(dictionary.isLetterIn(Dictionary.ListType.GAME_WORD, actual[i]) ),
                    "no letters should be in the game word");
        }
    }

    @DisplayName("All lists should be initialized to the game word length")
    @Test
    void guessListShouldBeSameLengthAsGameWord() {
        assertEquals(dictionary.getGuessList().length, gameWord.length);
        assertEquals(dictionary.getMissList().length, gameWord.length);
    }

    @DisplayName("Drawings length should equal the missed list length of")
    @Test
    void drawingsShouldMatchMissedListPlusOne() {
        int maxDrawings = 11;
        int lengthOfMissList = dictionary.getMissList().length;
        int lengthOfDrawings = dictionary.getDrawings().length;

        if (lengthOfMissList+1 <= maxDrawings) {
            assertEquals(lengthOfMissList + 1, lengthOfDrawings, "Should be equal");
        }
    }


}
