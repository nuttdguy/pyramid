import entity.*;
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

    @DisplayName("Should instantiate character.Human Class")
    @Test
    void humanClassTest() {

        assertTrue(human instanceof Entity);
        assertTrue(human.getHealth() >= 0);
        assertTrue(human.getDefense() >= 0);
        assertTrue(human.getStrength() >= 0);
        assertTrue(human.getMapX() >= 0);
        assertTrue(human.getMapY() >= 0);

    }

    @DisplayName("Should instantiate character.Goblin Class")
    @Test
    void goblinClassTest() {

        assertTrue(goblin instanceof Entity);
        assertTrue(goblin.getHealth() >= 0);
        assertTrue(goblin.getDefense() >= 0);
        assertTrue(goblin.getStrength() >= 0);
        assertTrue(goblin.getMapX() >= 0);
        assertTrue(goblin.getMapY() >= 0);

    }


}
