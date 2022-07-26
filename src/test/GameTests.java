

import character.*;
import main.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    Game game;

    @BeforeEach
    void beforeEach() {
        game = new Game();
    }

    @DisplayName("Should create list of goblin players")
    @Test
    void initPlayersTest() {
        ArrayList<Goblin> goblinPlayers = game.getGoblins();
        ArrayList<Human> humanPlayers = game.getHumans();
        Goblin goblin = goblinPlayers.get(0);
        Human human = humanPlayers.get(0);

        assertTrue(goblinPlayers.size() > 0,  "Qty should match expected");
        assertTrue(goblin instanceof Goblin, "Should be an instance of expected");
        assertEquals("character.Goblin", goblin.getClass().getName(), "Should be character.Goblin Instance");

        assertTrue(humanPlayers.size() > 0,  "Qty should match expected");
        assertTrue(human instanceof Human, "Should be an instance of expected");
        assertEquals("character.Human", human.getClass().getName(), "Should be character.Human Instance");

    }

    @DisplayName("Should grid with stats of player")
    @Test
    void updateStatOfTest() {
        Human human = game.getHumans().get(0);
        char statTestPosition1 = String.valueOf(human.getHealth()).charAt(0);
        char statTestPosition2 = String.valueOf(human.getHealth()).charAt(1);
        char statTestPosition3 = String.valueOf(human.getHealth()).charAt(2);
        char statTestPosition4 = String.valueOf(human.getHealth()).charAt(3);
        char expected1 = game.getMap()
                .getElementAtPosition(2, game.getMap().colWidth() -game.getMap().colOffset() + 3);
        char expected2 = game.getMap()
                .getElementAtPosition(2, game.getMap().colWidth() -game.getMap().colOffset() + 4);
        char expected3 = game.getMap()
                .getElementAtPosition(2, game.getMap().colWidth() -game.getMap().colOffset() + 5);
        char expected4 = game.getMap()
                .getElementAtPosition(2, game.getMap().colWidth() -game.getMap().colOffset() + 6);

        assertEquals(statTestPosition1, expected1, "Char value should be found an expected location");
        assertEquals(statTestPosition2, expected2, "Char value should be found an expected location");
        assertEquals(statTestPosition3, expected3, "Char value should be found an expected location");
        assertEquals(statTestPosition4, expected4, "Char value should be found an expected location");

    }

    @DisplayName("Should place players onto the game board")
    @Test
    void setPlayersTest() {
        int goblinCount = game.getGoblins().size();
        int humanCount = game.getHumans().size();
        char expectedGoblinMarker = game.getGoblins().get(0).getMarker();
        char expectedHumanMarker = game.getHumans().get(0).getMarker();
        char[][] land = game.getMap().getGrid();

        int actualGoblinMarkers = 0;
        int actualHumanMarkers = 0;
        int gameBoundary = game.getMap().colWidth() - game.getMap().colOffset();
        int colCount = 0;
        for (char[] c : land) {
            for (char e : c) {
                if (colCount < gameBoundary) {
                    if (e == expectedGoblinMarker) {
                        actualGoblinMarkers++;
                    } else if (e == expectedHumanMarker) {
                        actualHumanMarkers++;
                    }
                    colCount++;
                }
            }
            colCount = 0;
        }

        assertEquals(goblinCount, actualGoblinMarkers, "Markers on map should equal goblin count");
        assertEquals(humanCount, actualHumanMarkers, "Markers on map should equal human count");



    }

    private <T extends Player> int[][] getMarkerPositionOfHelper(ArrayList<T> t) {
        char[][] land = game.getMap().getGrid();
        String tName = t.get(0).getClass().getName();
        char markerToCheck = tName.equals("character.Goblin") ? 'G' : 'H';
        int[][] playerPositions = tName.equals("character.Goblin") ?
                new int[1][ game.getGoblins().size() * 2] : new int[1][ game.getHumans().size() * 2];
        int positionSlot = 0;

        int landBoundaryToSearch = game.getMap().colWidth() - game.getMap().colOffset();
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < landBoundaryToSearch; col++) {
                if (land[row][col] == markerToCheck) {
                    playerPositions[0][positionSlot++] = row;
                    playerPositions[0][positionSlot++] = col;
                }
            }
        }
        return playerPositions;
    }





















}
