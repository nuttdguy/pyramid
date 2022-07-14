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

        assertEquals(2, goblinPlayers.size(),  "Qty should match expected");
        assertTrue(goblin instanceof Goblin, "Should be an instance of expected");
        assertTrue(goblin.getClass().getName().equals("Goblin"), "Should be Goblin Instance");
        assertEquals(1, humanPlayers.size(),  "Qty should match expected");
        assertTrue(human instanceof Human, "Should be an instance of expected");
        assertTrue(human.getClass().getName().equals("Human"), "Should be Human Instance");

    }

    @DisplayName("Should grid with stats of player")
    @Test
    void updateStatOfTest() {
        Human human = game.getHumans().get(0);
        char statTestPosition1 = String.valueOf(human.getHealth()).charAt(0);
        char statTestPosition2 = String.valueOf(human.getHealth()).charAt(1);
        char statTestPosition3 = String.valueOf(human.getHealth()).charAt(2);
        char statTestPosition4 = String.valueOf(human.getHealth()).charAt(3);
        char expected1 = game.getLand()
                .getElementAtPosition(2, game.getLand().colWidth() -game.getLand().colOffset() + 3);
        char expected2 = game.getLand()
                .getElementAtPosition(2, game.getLand().colWidth() -game.getLand().colOffset() + 4);
        char expected3 = game.getLand()
                .getElementAtPosition(2, game.getLand().colWidth() -game.getLand().colOffset() + 5);
        char expected4 = game.getLand()
                .getElementAtPosition(2, game.getLand().colWidth() -game.getLand().colOffset() + 6);

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
        char[][] land = game.getLand().getGrid();

        int actualGoblinMarkers = 0;
        int actualHumanMarkers = 0;
        int gameBoundary = game.getLand().colWidth() - game.getLand().colOffset();
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

    @DisplayName("Should get a player from the list using its coordinates")
    @Test
    void retrievePlayerTest() {
        int[][] expectedGoblin = getMarkerPositionOfHelper(game.getGoblins());
        Goblin actualGoblin = game.retrievePlayer(game.getGoblins(), expectedGoblin[0][0], expectedGoblin[0][1]);

        int[][] expectedHuman = getMarkerPositionOfHelper(game.getHumans());
        Human actualHuman = game.retrievePlayer(game.getHumans(), expectedHuman[0][0], expectedHuman[0][1]);

        assertNotNull(actualGoblin, "Should not be null");
        assertNotNull(actualHuman, "Should not be null");

    }
    @DisplayName("Should remove player having zero health from list")
    @Test
    void removePlayerTest() {
        ArrayList<Goblin> goblins = game.getGoblins();
        goblins.get(0).setHealth(0);
        int id = goblins.get(0).getObjectId();

        goblins = game.removePlayer(goblins, goblins.get(0));
        boolean isRemoved = true;
        for (Goblin g : goblins) {
            if (g.getObjectId() == id) {
                isRemoved = false;
            }
        }

        assertTrue(isRemoved, "Should return true if the id was not found");

    }
    // update game board
    @DisplayName("Update the map markers of a player")
    @Test
    void updateMapMarkersOfTest() {
    }
    @DisplayName("Should move one players position according to the chosen direction ")
    @Test
    void moveOnePlayerTest() {
        Human human = game.getHumans().get(0);
        int startRow = human.getCoordinateX(), startCol = human.getCoordinateY();

        assertEquals(game.moveOnePlayer('n', startRow, startCol, human).getCoordinateX(), startRow - 1, "Should set position x -1");
        assertEquals(game.moveOnePlayer( 's', startRow, startCol, human).getCoordinateX(), startRow + 1, "Should set position x +1");
        assertEquals(game.moveOnePlayer( 'w', startRow, startCol, human).getCoordinateY(), startCol - 1, "Should set position y -1");
        assertEquals(game.moveOnePlayer( 'e', startRow, startCol, human).getCoordinateY(), startCol + 1, "Should set position y +1");
    }

    @DisplayName("Should engage in combat until health is zero or less")
    @Test
    void engageCombatBetweenTest() {
        Human human = game.getHumans().get(0);
        Goblin goblin = game.getGoblins().get(0);
        double goblinStartingHealth = goblin.getHealth();
        double humanStartingHealth = human.getMaxHealth();
        game.engageCombatBetween(human, goblin);

        assertTrue(goblin.getHealth() < goblinStartingHealth, "Health should be less than starting health" );
        assertTrue(human.getHealth() <= humanStartingHealth, "Health should be less than starting health" );
    }

    @DisplayName("Should replenish health of player")
    @Test
    void replenishHealthTest() {
        Human human = game.getHumans().get(0);
        double startingHealth = human.getHealth();
        human.setHealth(startingHealth - human.getHealth()); // should be zero
        assertEquals(0, human.getHealth(), "Health should be set to zero");

        game.replenishHealthOf(human);
        assertTrue(human.getHealth() > 0, "Health should be greater than zero");
    }

    private <T extends Player> int[][] getMarkerPositionOfHelper(ArrayList<T> t) {
        char[][] land = game.getLand().getGrid();
        String tName = t.get(0).getClass().getName();
        char markerToCheck = tName.equals("Goblin") ? 'G' : 'H';
        int[][] playerPositions = tName.equals("Goblin") ?
                new int[1][ game.getGoblins().size() * 2] : new int[1][ game.getHumans().size() * 2];
        int positionSlot = 0;

        int landBoundaryToSearch = game.getLand().colWidth() - game.getLand().colOffset();
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

    // row col to move onto





















}
