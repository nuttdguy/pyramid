import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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


    }


}
