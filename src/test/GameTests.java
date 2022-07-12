import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTests {

    Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game();
    }

    @DisplayName("Should create list of goblin players")
    @Test
    void playerFactoryTest() {
        ArrayList<Goblin> goblinPlayers = game.playerFactory(Game.PlayerType.GOBLIN, 10);
        ArrayList<Human> humanPlayers = game.playerFactory(Game.PlayerType.HUMAN, 1);
        Goblin goblin = goblinPlayers.get(1);
        Human human = humanPlayers.get(0);

        assertEquals(10, goblinPlayers.size(),  "Qty should match expected");
        assertTrue(goblin instanceof Goblin, "Should be an instance of expected");
        assertTrue(goblin.getClass().getName().equals("Goblin"), "Should be Goblin Instance");
        assertEquals(1, humanPlayers.size(),  "Qty should match expected");
        assertTrue(human instanceof Human, "Should be an instance of expected");
        assertTrue(human.getClass().getName().equals("Human"), "Should be Human Instance");

    }

    @Test
    void updateStatTest() {}

    @Test
    void setPlayersTest() {}

}
