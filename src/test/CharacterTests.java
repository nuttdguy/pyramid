import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTests {

    Human human;
    Goblin goblin;

    @BeforeEach
    void beforeEach() {
        human = new Human();
        goblin = new Goblin();
    }

    @DisplayName("Should instantiate Human Class")
    @Test
    void humanClassTest() {

        assertTrue(human instanceof Player);
        assertTrue(human.getHealth() >= 0);
        assertTrue(human.getDefense() >= 0);
        assertTrue(human.getStrength() >= 0);
        assertTrue(human.getMovesPerTurn() >= 0);
        assertTrue(human.getCoordinateX() >= 0);
        assertTrue(human.getCoordinateY() >= 0);

    }

    @DisplayName("Should instantiate Goblin Class")
    @Test
    void goblinClassTest() {

        assertTrue(goblin instanceof Player);
        assertTrue(goblin.getHealth() >= 0);
        assertTrue(goblin.getDefense() >= 0);
        assertTrue(goblin.getStrength() >= 0);
        assertTrue(goblin.getMovesPerTurn() >= 0);
        assertTrue(goblin.getCoordinateX() >= 0);
        assertTrue(goblin.getCoordinateY() >= 0);

    }


}
