import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Scanner;


public class GuessNumberTest {

    Game game;

    @BeforeAll
    public static void beforeClass() {

    }

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @DisplayName("Set the variables of the default constructor.")
    @Test
    void initDefaultGameConstructor() {
        assertEquals(game.getGameMin(), 1, "Game min field has been set to default value of 1");
        assertEquals(game.getGameMax(), 20, "Game max field has been set to default value of 20");
        assertEquals(game.getUserGuess(), 0, "Users guess counter has been set to 0");
        assertTrue(game.getPlayGame(), "Play game field has been initialized with true");
        assertEquals(game.getUsername(), "", "Username has been initialized to empty String");
        assertEquals(game.getPlayAgain(), "n", "Play again field has been set to \"n\" ");
    }

    @DisplayName("Set the min and max game range of parameterized constructor.")
    @Test
    void initGameConstructorWithMinMaxValues() {
        int gameMin = 1;
        int gameMax = 20;
        game = new Game(gameMin, gameMax);
        assertEquals(game.getGameMin(), gameMin, String.format("Game min set to %n", gameMin));
        assertEquals(game.getGameMax(), gameMax, String.format("Game max set to %n", gameMax));
    }

    @DisplayName("Validate the games descriptive text messages")
    @Test
    void validateGameText() {
        assertEquals(game.getGameIntro(), "Hello! What is your name?\n", String.format("Game Intro text should be %s", game.getGameIntro()));
        assertEquals(game.getWelcomeMessage(), "Well, %s, I am thinking of a number between %s and %s.\n", String.format("Game welcome message should be %s", game.getWelcomeMessage()));
        assertEquals(game.getGameQuestion(), "Take a guess.\n", String.format("Game Question should be %s", game.getGameQuestion()));
        assertEquals(game.getHighGuessMessage(), "Your guess is too high.\n", String.format("High guess message should be %s", game.getHighGuessMessage()));
        assertEquals(game.getLowGuessMessage(), "your guess is too low.\n", String.format("Low guess message should be %s", game.getLowGuessMessage()));
        assertEquals(game.getWinMessage(), "Good job, %s! You guessed my number in %s guesses!\n", String.format("Win message should be %s", game.getWinMessage()));
        assertEquals(game.getPlayAgainMessage(), "Would you like to play again? (y or n)\n", String.format("Play again message should be %s", game.getPlayAgainMessage()));
        assertEquals(game.getErrorInvalidType(), "%s is not a valid number.\n", String.format("Error, invalid type message should be %s", game.getErrorInvalidType()));
        assertEquals(game.getErrorInvalidOption(), "%s is not a valid option.\n", String.format("Error, invalid option message should be %s", game.getErrorInvalidOption()));
    }

    @DisplayName("Set the username utilizing input")
    @Test
    void setUsernameByInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Alice".getBytes());

        game.initInputStream(inputStream);
        game.setUsernameByInput();

        assertEquals(game.getUsername(), "Alice", String.format("Username should be set to %s", game.getUsername()));

    }

    @DisplayName("Should display game intro")
    @Test
    void displayGameIntro() {
        assertEquals(game.printGameIntro(), "Hello! What is your name?\n", String.format("Should display game intro"));
    }

    @DisplayName("Should display welcome message with username")
    @Test
    void displayWelcomeMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Alice".getBytes());

        game.initInputStream(inputStream);
        game.setUsernameByInput();

        String username = game.getUsername();
        int min = game.getGameMin();
        int max = game.getGameMax();

        assertEquals(game.printWelcomeMessage(), String.format("Well, %s, I am thinking of a number between %s and %s.\n", username, min, max), String.format("Should display game welcome message with username, min and max values"));
    }

    @DisplayName("Should accept users guess from input")
    @Test
    void setGuessByInput() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("4".getBytes());

        game.initInputStream(inputStream);
        game.setUserGuessByInput();

        int userGuess = game.getUserGuess();
        assertEquals(userGuess, 4, String.format("Users guess should equal %s", userGuess));

    }

    @DisplayName("Should increment the guess counter by 1")
    @Test
    void incrementGuessCounter() {
        assertEquals(game.getGuessCounter(), 0, String.format("Guess counter should equal 0"));

        game.incrementGuessCounter();
        assertEquals(game.getGuessCounter(), 1, String.format("Guess counter should equal 1"));

    }

    @DisplayName("Should return whether guess is correct")
    @Test
    void isUserGuessCorrect() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream( String.valueOf(game.getWinNumber()).getBytes());

        game.initInputStream(inputStream);
        game.setUserGuessByInput();

        int winNumber = game.getWinNumber();
        int guess = game.getUserGuess();

        assertEquals(game.isGuessCorrect(guess, winNumber),
                String.format(game.getWinMessage(), game.getUsername(), game.getGuessCounter()),
                String.format(game.getWinMessage(), game.getUsername(), game.getGuessCounter()));

    }

    @DisplayName("Should display guess too high message when guess is higher than win number.")
    @Test
    void displayHighMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream( String.valueOf(game.getWinNumber()).getBytes());

        game.initInputStream(inputStream);
        game.setUserGuessByInput();

        int winNumber = game.getWinNumber();
        int guess = game.getUserGuess() + 1;

        assertEquals(game.isGuessCorrect(guess, winNumber),
                String.format(game.getHighGuessMessage()),
                String.format(game.getHighGuessMessage()));
    }

    @DisplayName("Should display guess too low message when guess is lower than win number.")
    @Test
    void displayLowMessage() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream( String.valueOf(game.getWinNumber()).getBytes());

        game.initInputStream(inputStream);
        game.setUserGuessByInput();

        int winNumber = game.getWinNumber();
        int guess = game.getUserGuess() + 1;

        assertEquals(game.isGuessCorrect(guess, winNumber),
                String.format(game.getHighGuessMessage()),
                String.format(game.getHighGuessMessage()));
    }


}
