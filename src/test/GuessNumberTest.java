import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


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
        game = new Game(1, 20);
        assertEquals(game.getGameMin(), 1, String.format("Game min set to %n", 1));
        assertEquals(game.getGameMax(), 20, String.format("Game max ser to %n", 20));
    }

}
